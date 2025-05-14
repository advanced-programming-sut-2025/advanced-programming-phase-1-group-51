package Controllers.Others;

import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TurnAndSaveGameController {


    public Result newGame(String firstUsername, String secondUsername, String thirdUsername, String extraInvalid) {
        // Validation checks
        if (extraInvalid != null && !extraInvalid.isEmpty()) {
            return new Result(false, "You can start a game with maximum 3 players");
        }

        User currentUser = App.getCurrentUser();
        if (currentUser.getCurrentGame() != null) {
            return new Result(false, "You are already in a game");
        }

        // Validate and collect all participating users
        ArrayList<User> participants = new ArrayList<>();
        participants.add(currentUser);

        User user2 = validateAndAddUser(secondUsername, participants);
        if (user2 == null) return new Result(false, secondUsername + " doesn't exist or is in a game");

        User user3 = validateAndAddUser(thirdUsername, participants);
        if (user3 == null) return new Result(false, thirdUsername + " doesn't exist or is in a game");

        // Create the game
        Game newGame = new Game(new ArrayList<>(), null);

        // Create players for all participants
        for (User user : participants) {
            Player player = new Player(user);
            newGame.getPlayers().add(player);
            user.setCurrentGame(newGame);
        }

        // Set the first player as current
        newGame.setCurrentPlayer(newGame.getPlayers().get(0));

        return new Result(true, "New game created successfully. Write 'load game' to enter the game.");
    }

    private User validateAndAddUser(String username, ArrayList<User> participants) {
        if (username == null || username.isEmpty()) {
            return null;
        }

        User user = findUserByUsername(username);
        if (user == null || user.getCurrentGame() != null) {
            return null;
        }

        participants.add(user);
        return user;
    }

    public Result gameMap(int mapNumber){
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        if (mapNumber != 1 && mapNumber != 2) {
            return new Result(false, "Invalid map number");
        }

        return new Result(true, "map number chose successfully");
    }

    public Result loadGame() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();

        if (game == null) {
            return new Result(false, "No saved game found.");
        }

        // Find the player corresponding to the current user
        Player currentPlayer = game.findPlayerByUser(currentUser);
        if (currentPlayer == null) {
            return new Result(false, "Error: Could not find your player in the game");
        }

        game.setCurrentPlayer(currentPlayer);
        App.setCurrentMenu(Menu.GameMenu);

        return new Result(true, "Game loaded successfully. Welcome to the game!");
    }


    public Result deleteGame(){
        return null;
    }


    public Result exitGame() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();

        if (game != null) {
            // Remove user from game
            currentUser.setCurrentGame(null);

            // If no players left, end the game
            if (game.getPlayers().stream().noneMatch(p -> p.getUser().getCurrentGame() == game)) {
                // Clean up game resources
            }
        }

        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Exited game successfully");
    }



    public Result nextTurn() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();

        if (game == null) {
            return new Result(false, "No active game found");
        }

        // Get all players in the game
        ArrayList<Player> players = game.getPlayers();
        if (players.isEmpty()) {
            return new Result(false, "No players in the game");
        }

        // Find current player index
        int currentIndex = players.indexOf(game.getCurrentPlayer());
        if (currentIndex == -1) {
            return new Result(false, "Current player not found in game");
        }

        // Determine next player
        int nextIndex = (currentIndex + 1) % players.size();
        Player nextPlayer = players.get(nextIndex);
        game.setCurrentPlayer(nextPlayer);

        // Update game time (only advance time when full cycle completes)
        if (nextIndex == 0) {
            advanceTime(game);
        }

        return new Result(true, "It's now " + nextPlayer.getUser().getUsername() + "'s turn");
    }

    private void advanceTime(Game game) {
        // Advance time by 1 hour
        LocalDateTime newTime = game.getDate().plusHours(1);
        game.setDate(newTime);

        // Check for day change
        if (newTime.getHour() == 0) { // Midnight
            // Reset player energies for new day
            for (Player player : game.getPlayers()) {
                player.setEnergy(player.getMaxEnergy());
                player.setCurrentTurnUsedEnergy(0);
            }

            // Check for season change (28 days per season)
            int dayOfSeason = newTime.getDayOfMonth() % 28;
            if (dayOfSeason == 1) { // First day of new season
                Season currentSeason = game.getSeason();
                Season nextSeason = getNextSeason(currentSeason);
                game.setSeason(nextSeason);

                // Handle season change effects
                handleSeasonChange(game, currentSeason, nextSeason);
            }
        }
    }

    private Season getNextSeason(Season currentSeason) {
        switch (currentSeason) {
            case SPRING: return Season.SUMMER;
            case SUMMER: return Season.FALL;
            case FALL: return Season.WINTER;
            case WINTER: return Season.SPRING;
            default: return Season.SPRING;
        }
    }

    private void handleSeasonChange(Game game, Season oldSeason, Season newSeason) {
        // Handle crop changes (some crops die between seasons)
        for (Player player : game.getPlayers()) {
            player.getFarm().handleSeasonChange(oldSeason, newSeason);
        }

        // Update weather patterns
        game.setWeatherToday(Weather.SUNNY); // Default to sunny on season change
        game.setWeatherTomorrow(Weather.SUNNY);

        // You can add other season change effects here
    }

    private User findUserByUsername(String username) {

        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
