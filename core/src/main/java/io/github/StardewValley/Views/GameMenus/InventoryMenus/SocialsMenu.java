package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.StardewValley.Controllers.GameControllers.GameController;

public class SocialsMenu extends BaseMenu {
    public SocialsMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Socials");
    }

    @Override
    public void show() {
        super.show();
        showSocials();
    }

    private void showSocials() {
        contentTable.clear();
        contentTable.add(new Label("Socials will be shown here", skin)).colspan(2).padBottom(20);
    }
}
