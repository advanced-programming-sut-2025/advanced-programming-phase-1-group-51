package io.github.StardewValley.Controllers.MenuControllers.LoginControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Regexes.SignupRegexes;
import io.github.StardewValley.Models.User;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.ForgetPasswordMenu;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.LoginMenu;

public class ForgetPasswordController {
    private ForgetPasswordMenu view;

    public void setView(ForgetPasswordMenu view) {
        this.view = view;
    }

    public void handleForgotPasswordButtons() {
        if (view != null) {
            if (view.getBackButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                navigateToLogin();
            }
            else if (view.getSubmitButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                User user = App.findUserByUsername(view.getUsername().getText());
                String answer = view.getAnswer().getText();
                String newPassword = view.getNewPassword().getText();
                if (!user.getSecurityAnswer().equals(answer)) {
                    view.showError("wrong security answer");
                    return;
                }

                if (SignupRegexes.PASSWORD_LENGTH.getMatcher(newPassword) == null) {
                    view.showError("Password must be longer than 8 characters");
                    return;
                }
                if (SignupRegexes.PASSWORD_SPECIALS.getMatcher(newPassword) == null) {
                    view.showError("Password must contain at least one Special character [@%$#&*()_]");
                    return;
                }
                if (SignupRegexes.PASSWORD_NUMBERS.getMatcher(newPassword) == null) {
                    view.showError("Password must contain at least one number");
                    return;
                }
                if (SignupRegexes.PASSWORD_LETTERS.getMatcher(newPassword) == null) {
                    view.showError("Password must contain at least one uppercase letter");
                }
                user.setPassword(newPassword);
                view.showError("password changed successfully");
            }
        }
    }

    private void navigateToLogin() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(
            new LoginMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }
}
