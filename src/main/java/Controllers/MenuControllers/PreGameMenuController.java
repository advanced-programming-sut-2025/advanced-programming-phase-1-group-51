package Controllers.MenuControllers;

import Controllers.BaseController;
import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Maps.Cells;
import Models.Maps.Farm;

import java.util.ArrayList;

public class PreGameMenuController extends BaseController {
    public Result goToMain() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        App.setCurrentMenu(Menu.MainMenu);
        return saveGameState(game)
                .combine(Result.success( "You are now in main menu"));
    }

    public Result showCurrentMenu(){
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( "PreGame Menu"));
    }

    public Result newGame(String firstUsername, String secondUsername, String thirdUsername, String extraInvalid) {
        if(firstUsername == null || firstUsername.isEmpty()){
            return  Result.failure( "You can start a game with minimum 1 other player");
        }
        if (extraInvalid != null && !extraInvalid.isEmpty()) {
            return  Result.failure( "You can start a game with maximum 3 players");
        }

        User currentUser = App.getCurrentUser();
        if (currentUser.getCurrentGame() != null) {
            return  Result.failure( "You are already in a game");
        }

        // Validate and collect all participating users
        ArrayList<User> participants = new ArrayList<>();
        participants.add(currentUser);

        User user2 = validateAndAddUser(secondUsername, participants);
        if (user2 == null) return  Result.failure( secondUsername + " doesn't exist or is in a game");

        User user3 = validateAndAddUser(thirdUsername, participants);
        if (user3 == null) return  Result.failure( thirdUsername + " doesn't exist or is in a game");

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

        return saveGameState(newGame)
                .combine(Result.success( "New game created successfully. Write 'load game' to enter the game."));
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
            return  Result.failure( "Invalid map number");
        }

        return saveGameState(game)
                .combine(Result.success( "map number chose successfully"));
    }

    public Result loadGame() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();

        if (game == null) {
            return Result.failure("No saved game found.");
        }

        Player currentPlayer = game.findPlayerByUser(currentUser);
        if (currentPlayer == null) {
            return Result.failure("Error: Could not find your player in the game");
        }

        game.setCurrentPlayer(currentPlayer);

        // Set player position to their farmhouse
        Farm playerFarm = currentPlayer.getFarm();
        Cells farmHouse = playerFarm.findCellFarm(10, 10); // Or use getFarmHousePosition()
        if (farmHouse != null) {
            currentPlayer.setPosition(farmHouse.getPosition());
        }

        App.setCurrentMenu(Menu.GameMenu);

        return saveGameState(game)
                .combine(Result.success("Game loaded successfully. Welcome to the game!"));
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
