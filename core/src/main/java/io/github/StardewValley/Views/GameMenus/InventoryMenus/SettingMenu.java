package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Main;

public class SettingMenu extends BaseInventoryMenu {
    private TextButton exitButton;

    public SettingMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "Setting");
        this.exitButton = new TextButton("Exit",skin);
    }

    @Override
    public void show() {
        super.show();
        showSetting();
    }

    private void showSetting() {
        contentTable.clear();
        contentTable.add(new Label("Setting will be shown here", skin)).colspan(2).padBottom(20).padRight(60);
        contentTable.add(exitButton).colspan(2).padBottom(20);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                Gdx.app.exit(); // This will close the game
            }
        });
    }
}
