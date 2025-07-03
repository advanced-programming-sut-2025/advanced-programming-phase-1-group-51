package io.github.StardewValley.Controllers.MenuControllers;

import io.github.StardewValley.Controllers.MenuControllers.LoginControllers.LoginMenuController;
import io.github.StardewValley.Controllers.MenuControllers.ProfileControllers.ProfileMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Views.MenusBeforeGame.*;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.LoginMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;

public class MainMenuController {
    private MainMenu view;
    public void setView(MainMenu view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if(view != null){
            if(view.getLoginButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToLogin();
            }
            else if(view.getPregameButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToPregame();
            }
            else if(view.getProfileButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToProfile();
            }
            else if(view.getLogoutButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                App.setCurrentUser(null);
                navigateToLogin();
            }
        }
    }

    public void navigateToLogin() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(
            new LoginMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void navigateToPregame() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PregameMenu(
            new PregameMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void navigateToProfile() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenu(
            new ProfileMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }
}
