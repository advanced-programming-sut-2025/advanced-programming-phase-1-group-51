package Controllers.MenuControllers;

import Models. App;
import Models.Result;
import Models.Enums.MenuCommands.Menu;

public class MainMenuController {

    public Result showCurrentMenu(){
        return new Result(true, "Main Menu");
    }


    public Result logout(){
         App.setCurrentMenu(Menu.LoginMenu);
         App.setCurrentUser(null);
        return new Result(true, "user logged out successfully");
    }
    public Result goToAvatarMenu(){
         App.setCurrentMenu(Menu.AvatarMenu);
        return new Result(true, "You are now in Avatar Menu");
    }
    public Result goToProfileMenu(){
         App.setCurrentMenu(Menu.ProfileMenu);
        return new Result(true, "You are now in Profile Menu");
    }
    public Result goToGameMenu(){
       App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You are now in Pre App Menu");
    }

}
