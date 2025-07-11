package io.github.StardewValley.Controllers.MenuControllers.ProfileControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Regexes.SignupRegexes;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ChangePassword;

public class ChangePasswordController {
    private ChangePassword view;
    public void setView(ChangePassword view) {
        this.view = view;
    }

    public void handlePasswordMenuButtons() {
        if(view != null){
            String password = view.getNewUsernameField().getText();
            if(view.getSubmitButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                if(SignupRegexes.USERNAME.getMatcher(password) == null){
                    view.showError("Invalid password format!");
                }
                else{
                    App.getCurrentUser().setPassword(password);
                    view.showError("Your password changed successfully!");
                }
            }
            if(view.getBackButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenu(
                    new ProfileMenuController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
        }
    }
}
