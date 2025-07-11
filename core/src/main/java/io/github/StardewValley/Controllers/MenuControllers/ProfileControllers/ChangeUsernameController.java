package io.github.StardewValley.Controllers.MenuControllers.ProfileControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Regexes.SignupRegexes;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ChangeUsername;

public class ChangeUsernameController {
    private ChangeUsername view;
    public void setView(ChangeUsername view) {
        this.view = view;
    }

    public void handleUsernameMenuButtons() {
        if(view != null){
            String username = view.getNewUsernameField().getText();
            if(view.getSubmitButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                if(SignupRegexes.USERNAME.getMatcher(username) == null){
                    view.showError("Invalid username format!");
                }
                else{
                    App.getCurrentUser().setUsername(username);
                    view.showError("Your username changed successfully!");
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
