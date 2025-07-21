package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.github.StardewValley.Controllers.GameControllers.GameController;
public class SkillsMenu extends BaseInventoryMenu {

    public SkillsMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Skills");
    }

    @Override
    public void show() {
        super.show();
        showSkills();
    }

    private void showSkills() {
        contentTable.clear();
        contentTable.add(new Label("Skills will be shown here", skin)).colspan(2).padBottom(20);
    }
}
