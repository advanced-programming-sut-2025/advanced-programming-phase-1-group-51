package io.github.StardewValley.Controllers.MenuControllers.ProfileControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Regexes.SignupRegexes;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ChangeEmail;

public class ChangeEmailController {
    private ChangeEmail view;
    public void setView(ChangeEmail view) {
        this.view = view;
    }

    public void handleEmailMenuButtons() {
        if(view != null){
            String email = view.getNewEmailField().getText();
            if(view.getSubmitButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                if(SignupRegexes.EMAIL.getMatcher(email) == null){
                    view.showError("Invalid email format!");
                }
                else{
                    App.getCurrentUser().setEmail(email);
                    view.showError("Your email changed successfully!");
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
