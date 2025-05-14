package Controllers.Others;

import Controllers.BaseController;
import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TurnAndSaveGameController  extends BaseController {

    public Result goToMain() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "You are now in main menu");
    }

    public Result showCurrentMenu(){
        return new Result(true, "Game Menu");
    }

    public Result newGame(String firstUsername, String secondUsername, String thirdUsername, String extraInvalid) {

        if(firstUsername == null || firstUsername.isEmpty()){
            return new Result(false, "You can start a game with minimum 1 other player");
        }
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

        if (game == null) {
            return new Result(false, "No active game to exit");
        }

        // Check if current player is the game starter (first player)
        if (game.getCurrentPlayer() != game.getPlayers().get(0)) {
            return new Result(false, "Only the game creator can exit the game on their turn");
        }

        try {
            // Save the game state (you'll need to implement this)
           // saveGameToFile(game);

            // Clear current game reference for all players
            for (Player player : game.getPlayers()) {
                player.getUser().setCurrentGame(null);
            }

            // Return to main menu
            App.setCurrentMenu(Menu.MainMenu);
            return new Result(true, "Game saved successfully. You can load it later using 'load game'");
        }
        catch (Exception e) {
            return new Result(false, "Failed to save game: " + e.getMessage());
        }
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

        // Check for 10 PM curfew
        if (newTime.getHour() == 22) { // 10 PM
            // Return all players to their houses
            for (Player player : game.getPlayers()) {
                returnPlayerToHouse(player);
            }
            // Fast forward to next morning at 9 AM
            newTime = newTime.plusHours(11); // 10 PM + 11 hours = 9 AM next day
            game.setDate(newTime);

            // Handle day change effects
            handleDayChange(game);
            return;
        }

        // Check for midnight (normal day change)
        if (newTime.getHour() == 0) { // Midnight
            handleDayChange(game);
        }
    }

    private void handleDayChange(Game game) {
        LocalDateTime date = game.getDate();

        // Reset player energies for new day
        for (Player player : game.getPlayers()) {
            player.setEnergy(player.getMaxEnergy());
            player.setCurrentTurnUsedEnergy(0);

            // Daily animal care reset
            resetAnimalCareStatus(player);
        }

        // Check for season change (28 days per season)
        int dayOfSeason = date.getDayOfMonth() % 28;
        if (dayOfSeason == 1) { // First day of new season
            Season currentSeason = game.getSeason();
            Season nextSeason = getNextSeason(currentSeason);
            game.setSeason(nextSeason);
        }

        // Generate new weather for the day
        generateDailyWeather(game);
    }

    private void returnPlayerToHouse(Player player) {
        // Set player position to their house coordinates
        player.setPosition(new Position(5, 10)); // Example house coordinates
        player.setInHouse(true);
        player.setInFarm(false);
        player.setInVillage(false);
        player.setCloseToLake(false);

        // Apply energy penalty for staying out late
        int energyPenalty = (int)(player.getEnergy() * 0.2); // Lose 20% energy
        player.setEnergy(player.getEnergy() - energyPenalty);

        // You might want to add a notification/message about being forced home
    }

    private void resetAnimalCareStatus(Player player) {
        for (Animal animal : player.getAnimals()) {
            animal.setHasBeenPetToday(false);
            animal.setHasBeenFedGrassToday(false);
            animal.setHasBeenFedHayToday(false);
        }
    }

    private void generateDailyWeather(Game game) {
        // Simple weather generation - can be enhanced
        Random random = new Random();
        Weather[] possibleWeathers = Weather.values();
        game.setWeatherToday(possibleWeathers[random.nextInt(possibleWeathers.length)]);

        // Set tomorrow's weather (50% chance of same weather)
        if (random.nextBoolean()) {
            game.setWeatherTomorrow(game.getWeatherToday());
        } else {
            game.setWeatherTomorrow(possibleWeathers[random.nextInt(possibleWeathers.length)]);
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




    private User findUserByUsername(String username) {

        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
