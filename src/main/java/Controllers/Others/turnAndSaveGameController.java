package Controllers.Others;

import Controllers.Controller;
import Models.Enums.MenuCommands.Menu;
import Models.Game;
import Models.Player;
import Models.Result;
import Models.User;

public class turnAndSaveGameController extends Controller {

    public Result newGame(String firstUsername, String secondUsername, String thirdUsername, String extraInvalid) {

        if (extraInvalid != null && !extraInvalid.isEmpty()) {
            return new Result(false, "You can start a game with maximum 3 players");
        }

        if (firstUsername == null || firstUsername.isEmpty()) {
            return new Result(false, "You should start the game with at least one player!");
        }

        User user1 = findUserByUsername(firstUsername);
        if (user1 == null) {
            return new Result(false, firstUsername + " doesn't exist");
        }

        User user2 = null;
        User user3 = null;

        if (secondUsername != null && !secondUsername.isEmpty()) {
            user2 = findUserByUsername(secondUsername);
            if (user2 == null) {
                return new Result(false, secondUsername + " doesn't exist");
            }
        }

        if (thirdUsername != null && !thirdUsername.isEmpty()) {
            user3 = findUserByUsername(thirdUsername);
            if (user3 == null) {
                return new Result(false, thirdUsername + " doesn't exist");
            }
        }

        Player.players.clear();

        Player currentPlayer = Game.getCurrentPlayer();
        if (currentPlayer == null || !currentPlayer.getUser().equals(Game.getCurrentUser())) {
            currentPlayer = new Player(Game.getCurrentUser());
            Game.setCurrentPlayer(currentPlayer);
        }
        Player.players.add(currentPlayer);

        Player.players.add(new Player(user1));
        if (user2 != null) {
            Player.players.add(new Player(user2));
        }
        if (user3 != null) {
            Player.players.add(new Player(user3));
        }
        Game.setGameStarterPlayer(currentPlayer);
        return new Result(true, "New game created successfully. Write 'load game' to enter the game.");
    }

    public Result gameMap(int mapNumber){
        User currentUser = Game.getCurrentUser();
        Game CurrentGame = currentUser.getCurrentGame();
        Player currentPlayer = Game.getCurrentPlayer();
        if (mapNumber != 1 && mapNumber != 2) {
            return new Result(false, "Invalid map number");
        }

        return new Result(true, "map number chose successfully");
    }

    public Result loadGame(){
        Game.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now in game");
    }


    public Result deleteGame(){
        return null;
    }


    public Result exitGame(){
        Player currentPlayer = Game.getCurrentPlayer();
        Game game = Game.getCurrentUser().getCurrentGame();
        if(currentPlayer != Game.getGameStarterPlayer()){
            return new Result(false, "only Starter player can exit the game!");
        }

        Game.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "you exit the game successfully!");
    }

    public Result nextTurn(){

        return new Result(true,"its now next player's turn");
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
