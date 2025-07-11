package io.github.StardewValley.Controllers.MenuControllers.ProfileControllers;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ProfileMenu;
import io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus.ChangeNickname;

public class ChangeNicknameController {
    private ChangeNickname view;
    public void setView(ChangeNickname view) {
        this.view = view;
    }

    public void handleNicknameMenuButtons() {
        if(view != null) {
            if (view.getSubmitButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                String nickname = view.getNewUsernameField().getText();
                App.getCurrentUser().setNickName(nickname);
                view.showError("Your nickname changed successfully!");
            }
            if (view.getBackButton().isPressed()) {
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
