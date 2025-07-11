package io.github.StardewValley.Controllers.MenuControllers.ProfileControllers;

import io.github.StardewValley.Controllers.MenuControllers.MainMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Views.MenusBeforeGame.MainMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.*;

public class ProfileMenuController {
    private ProfileMenu view;
    public void setView(ProfileMenu view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {
        if(view != null){
            if(view.getChangeUsernameButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeUsername(
                    new ChangeUsernameController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
            if(view.getChangePasswordButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangePassword(
                    new ChangePasswordController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
            if(view.getChangeNicknameButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeNickname(
                    new ChangeNicknameController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
            if(view.getChangeEmailButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeEmail(
                    new ChangeEmailController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
            if(view.getUserInfoButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new UserInfo(
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
            if(view.getBackButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(
                    new MainMenuController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
        }
    }
}
