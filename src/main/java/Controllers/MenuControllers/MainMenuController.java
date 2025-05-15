package Controllers.MenuControllers;

import Models. App;
import Models.Result;
import Models.Enums.MenuCommands.Menu;

import java.io.File;

public class MainMenuController {

    public Result showCurrentMenu(){
        return new Result(true, "Main Menu");
    }


    public Result logout() {
        if (App.getCurrentUser() != null) {
            // Clear persistent login flag
            App.getCurrentUser().setStayLoggedIn(false);

            // Delete the active user file
            File file = new File("saved_data/active_user.json");
            if (file.exists()) {
                file.delete();
            }
        }

        App.setCurrentUser(null);
        App.setCurrentMenu(Menu.LoginMenu);
        return Result.success("Logged out successfully");
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
        return new Result(true, "You are now in Game menu Menu");
    }

}
