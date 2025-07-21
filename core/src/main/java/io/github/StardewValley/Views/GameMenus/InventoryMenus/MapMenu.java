package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.StardewValley.Controllers.GameControllers.GameController;

public class MapMenu extends BaseInventoryMenu {

    public MapMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Map");
    }

    @Override
    public void show() {
        super.show();
        showMap();
    }

    private void showMap() {
        contentTable.clear();
        contentTable.add(new Label("Map will be shown here", skin)).colspan(2).padBottom(20);
    }
}
