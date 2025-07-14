package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Types.TrashcanType;
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

        // Get trashcan texture
        Texture trashcanTexture = GameAssetsManager.getInstance()
            .getForagingAssetsManager()
            .getTrashcanTexture(TrashcanType.DEFAULT);
        Image trashcanImage = new Image(new TextureRegionDrawable(trashcanTexture));
        trashcanImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                // Add your trashcan functionality here
                System.out.println("Trashcan clicked!");
            }
        });

        // Create a container table for the inventory and trashcan
        Table inventoryAndTrashTable = new Table();

        // Inventory items table (left side)
        Table itemsTable = new Table();
        itemsTable.align(Align.center);

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
                itemImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Main.playSound(Main.getButtonClickSound());
                        player.setItemInHand(slot.getItem());
                        System.err.println("Item " + slot.getItem().getName());
                    }
                });
                itemCell.add(itemImage).size(64).pad(5);
                itemCell.row();

                Label countLabel = new Label(String.valueOf(slot.getCount()), skin);
                itemCell.add(countLabel).padBottom(5);

                itemsTable.add(itemCell).pad(5);
                itemCount++;
                if (itemCount % itemsPerRow == 0) {
                    itemsTable.row();
                }
            }
        }

        // Add items table and trashcan to the container table
        inventoryAndTrashTable.add(itemsTable).expand().fill().padRight(20); // Items on the left
        inventoryAndTrashTable.add(trashcanImage).size(100).pad(20).right(); // Trashcan on the right

        // Add the container to the content table
        contentTable.add(inventoryAndTrashTable).expand().fill();
    }
}
