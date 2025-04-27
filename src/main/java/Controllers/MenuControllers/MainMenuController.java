package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Game;
import Models.Result;
import Models.Enums.MenuCommands.Menu;

public class MainMenuController extends Controller {

    public Result showCurrentMenu(){
        return new Result(true, "Main Menu");
    }


    public Result logout(){
        Game.setCurrentMenu(Menu.LoginMenu);
        Game.setCurrentUser(null);
        return new Result(true, "user logged out successfully");
    }
    public Result goToAvatarMenu(){
        Game.setCurrentMenu(Menu.AvatarMenu);
        return new Result(true, "You are now in Avatar Menu");
    }
    public Result goToProfileMenu(){
        Game.setCurrentMenu(Menu.ProfileMenu);
        return new Result(true, "You are now in Profile Menu");
    }
    public Result goToGameMenu(){
      Game.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now in PreGame Menu");
    }

}
