package io.github.StardewValley.Controllers.MenuControllers.LoginControllers;

import io.github.StardewValley.Controllers.MenuControllers.MainMenuController;
import io.github.StardewValley.Controllers.MenuControllers.SignupMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.User;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.ForgetPasswordMenu;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.LoginMenu;
import io.github.StardewValley.Views.MenusBeforeGame.MainMenu;
import io.github.StardewValley.Views.MenusBeforeGame.SignupMenu;

public class LoginMenuController {
    private LoginMenu view;
    public void setView(LoginMenu view) {
        this.view = view;
    }

    public void handleLoginMenuButtons() {
        if(view != null){
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();
            User user = App.findUserByUsername(username);

            if(view.getForgotPasswordButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToForget();
            }

            else if(view.getStayLoggedInButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                if(user != null){
                    user.setStayLoggedIn(true);
                }
            }
            else if(view.getBackButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToSignup();
            }

            else if(view.getGoToMainButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());

                if (user == null) {
                    view.showError("username not found!");
                }

                else if (!user.getPassword().equals(password)) {
                  view.showError("Password is incorrect!");
                }
                else
                {
                    App.setCurrentUser(user);
                    navigateToMain();
                }
            }
        }
    }

    public void navigateToForget() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ForgetPasswordMenu(
            new ForgetPasswordController(),
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
    public void navigateToSignup() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SignupMenu(
            new SignupMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }
}
