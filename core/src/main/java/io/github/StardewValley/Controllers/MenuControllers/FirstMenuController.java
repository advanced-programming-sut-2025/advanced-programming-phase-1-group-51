package io.github.StardewValley.Controllers.MenuControllers;

import io.github.StardewValley.Controllers.MenuControllers.LoginControllers.LoginMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.User;
import io.github.StardewValley.Views.MenusBeforeGame.FirstMenu;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.LoginMenu;
import io.github.StardewValley.Views.MenusBeforeGame.MainMenu;
import io.github.StardewValley.Views.MenusBeforeGame.SignupMenu;

public class FirstMenuController {
    private FirstMenu view;

    public void setView(FirstMenu view) {
        this.view = view;
    }

    public void handleFirstMenuButtons(){
        if(view != null){
            if(view.getGoToSignupButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToSignup();
            }
            else if(view.getGoToLoginButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToLogin();
            }
            else if(view.getGoToMainButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToMain();
                User user = new User("default username", "default password","default email",
                    "default nickname","default gender", "default question", "default answer");
                App.setCurrentUser(user);
                App.getUsers().add(user);
            }
        }
    }

    public void navigateToSignup() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SignupMenu(
            new SignupMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void navigateToLogin() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(
            new LoginMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void navigateToMain() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(
            new MainMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }
}
