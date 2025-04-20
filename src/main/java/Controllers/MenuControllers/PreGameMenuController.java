package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.MenuComands.Menu;
import Models.Game;
import Models.Result;

public class PreGameMenuController extends Controller {


    public Result showCurrentMenu(){
        return new Result(true, Game.getCurrentMenu().name());
    }
    public Result newGame(String firstUsername,String secondUsername,String thirdUsername) {

        Game.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "New game created successfully, Write load game to enter the game.");
    }

    public Result gameMap(int mapNumber){

        return new Result(true, "map number chose successfully");
    }

    public Result loadGame(){

        Game.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now in game");
    }
}
