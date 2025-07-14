package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.StardewValley.Controllers.GameControllers.GameController;

public class SettingMenu extends BaseMenu {
    public SettingMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Setting");
    }

    @Override
    public void show() {
        super.show();
        showSetting();
    }

    private void showSetting() {
        contentTable.clear();
        contentTable.add(new Label("Setting will be shown here", skin)).colspan(2).padBottom(20);
    }
}
