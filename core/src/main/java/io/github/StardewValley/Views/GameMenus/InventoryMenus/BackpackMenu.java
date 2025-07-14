package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Types.BackpackType;
import io.github.StardewValley.Models.Slot;

public class BackpackMenu extends BaseMenu {

    public BackpackMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "BackPack");
    }

    @Override
    public void show() {
        super.show();
        showInventory();
    }

    private void showInventory() {
        contentTable.clear();
        contentTable.align(Align.center);
        BackPack backpack = player.getInventory();
        if (backpack == null) {
            backpack = new BackPack(BackpackType.DEFAULT);
        }

        int itemsPerRow = 4;
        int itemCount = 0;

        for (Slot slot : backpack.getSLots()) {
            if (slot != null && slot.getItem() != null) {
                Texture itemTexture = GameAssetsManager.getInstance()
                    .getForagingAssetsManager()
                    .getItemTexture(slot.getItem());

                if (itemTexture == null) {
                    itemTexture = createPlaceholderTexture();
                }

                Table itemCell = new Table();
                itemCell.setBackground(skin.getDrawable("window"));

                Image itemImage = new Image(new TextureRegionDrawable(itemTexture));
                itemCell.add(itemImage).size(64).pad(5);
                itemCell.row();

                Label countLabel = new Label(String.valueOf(slot.getCount()), skin);
                itemCell.add(countLabel).padBottom(5);

                contentTable.add(itemCell).pad(5);
                itemCount++;
                if (itemCount % itemsPerRow == 0) {
                    contentTable.row();
                }
            }
        }
    }
}
