package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.Gdx;
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
import io.github.StardewValley.Models.Enums.Types.ItemTypes.TrashcanType;
import io.github.StardewValley.Models.Slot;

public class BackpackMenu extends BaseInventoryMenu {

    public BackpackMenu(GameController gameController, Skin skin) {
        super(gameController, skin, "BackPack");
    }

    @Override
    public void show() {
        super.show();
        showInventory();
    }

    private void showInventory() {
        scrollContentTable.clear();
        scrollContentTable.align(Align.top);
        BackPack backpack = player.getInventory();

        // Get trashcan texture
        Texture trashcanTexture = GameAssetsManager.getInstance()
            .getInitialAssets()
            .getTrashcanTexture(TrashcanType.DEFAULT);
        Image trashcanImage = new Image(new TextureRegionDrawable(trashcanTexture));
        trashcanImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                System.out.println("Trashcan clicked!");
            }
        });

        // Create a container table for the inventory and trashcan
        Table inventoryAndTrashTable = new Table();

        // Inventory items table (left side)
        Table itemsTable = new Table();
        itemsTable.align(Align.top);

        int itemsPerRow = Math.max(4, Math.min(12, (int)(Gdx.graphics.getWidth() / 150f)));
        int itemCount = 0;

        for (Slot slot : backpack.getSLots()) {
            if (slot != null && slot.getItem() != null) {
                Texture itemTexture = GameAssetsManager.getInstance()
                    .getInitialAssets()
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

                itemsTable.add(itemCell).width(120).pad(5);
                itemCount++;
                if (itemCount % itemsPerRow == 0) {
                    itemsTable.row();
                }
            }
        }

        // Add items table and trashcan to the container table
        inventoryAndTrashTable.add(itemsTable).expand().fill().padRight(20); // Items on the left
        inventoryAndTrashTable.add(trashcanImage).size(100).pad(20).right(); // Trashcan on the right

        // Add the container to the scroll content table
        scrollContentTable.add(inventoryAndTrashTable).expand().fill().top();

        // Add padding at the bottom to ensure all items are reachable
        scrollContentTable.row();
        scrollContentTable.add().height(20);

        // Force layout calculations
        scrollContentTable.pack();
        scrollPane.layout();

        // Reset scroll position to top
        scrollPane.setScrollY(0);
        scrollPane.updateVisualScroll();
    }
}
