package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.StardewValley.Controllers.GameControllers.GameController;

public class MissionsMenu extends BaseMenu {

    public MissionsMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Missions");
    }

    @Override
    public void show() {
        super.show();
        showMissions();
    }

    private void showMissions() {
        contentTable.clear();
        contentTable.add(new Label("Missions will be shown here", skin)).colspan(2).padBottom(20);
    }
}
