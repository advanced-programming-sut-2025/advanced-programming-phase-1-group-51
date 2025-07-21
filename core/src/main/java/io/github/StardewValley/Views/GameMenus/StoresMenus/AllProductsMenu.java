package io.github.StardewValley.Views.GameMenus.StoresMenus;

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
import io.github.StardewValley.Models.Enums.Types.StoreProductsType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllProductsMenu extends BaseStoresMenu {
    private String storeName;
    private List<StoreProducts> products;

    public AllProductsMenu(GameController gameController, Skin skin, String storeName) {
        super(gameController, skin, storeName + " (All Products)", storeName);
        this.storeName = storeName;
        this.products = new ArrayList<>();
    }

    @Override
    public void show() {
        super.show();
        showAllProducts();
    }

    private void showAllProducts() {
        scrollContentTable.clear();
        scrollContentTable.align(Align.top);
        scrollContentTable.defaults().pad(5);

        // Load products...
        switch (storeName) {
            case "Black Smith":
                products.addAll(Arrays.asList(BlackSmithProducts.values()));
                break;
            case "Carpenter's Shop":
                products.addAll(Arrays.asList(CarpenterShopProducts.values()));
                break;
            case "Fish Shop":
                products.addAll(Arrays.asList(FishShopProducts.values()));
                break;
            case "JojaMart":
                products.addAll(Arrays.asList(JojaMartProducts.values()));
                break;
            case "Marnie's Ranch":
                products.addAll(Arrays.asList(MarnieRanchProducts.values()));
                break;
            case "Pierre General Store":
                products.addAll(Arrays.asList(PierreGeneralStoreProducts.values()));
                break;
            case "StarDrop Saloon":
                products.addAll(Arrays.asList(StarDropSaloonProducts.values()));
                break;
        }

        // Create a table for the products grid
        Table productsTable = new Table();
        productsTable.align(Align.top);
        productsTable.defaults().pad(5);

        // Calculate items per row based on screen width
        int itemsPerRow = Math.max(4, Math.min(12, (int)(Gdx.graphics.getWidth() / 150f)));
        int itemCount = 0;

        for (StoreProducts product : products) {
            Texture productTexture = getProductTexture(product);

            Table productCell = new Table();
            productCell.setBackground(skin.getDrawable("window"));

            Image productImage = new Image(new TextureRegionDrawable(productTexture));
            productImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Main.playSound(Main.getButtonClickSound());
                    player.getInventory().addItem(product.getItemType(),1);
                    player.addNotification("Purchased: " + product.getName());
                }
            });

            productCell.add(productImage).size(64).pad(20);
            productCell.row();

            // Add product name
            Label nameLabel = new Label(product.getName(), skin);
            nameLabel.setFontScale(0.7f);
            productCell.add(nameLabel).padBottom(2);
            productCell.row();

            // Add product price
            Label priceLabel = new Label("Price: " + product.getPrice() + "g", skin);
            priceLabel.setFontScale(0.6f);
            productCell.add(priceLabel).padBottom(2);
            productCell.row();

            // Add available quantity (for now just showing unlimited)
            Label quantityLabel = new Label("Available: Unlimited", skin);
            quantityLabel.setFontScale(0.6f);
            productCell.add(quantityLabel).padBottom(5);

            productsTable.add(productCell).width(120).pad(5);
            itemCount++;

            if (itemCount % itemsPerRow == 0) {
                productsTable.row();
            }
        }

        // Add the products table to scroll content
        scrollContentTable.add(productsTable).expandX().fillX().top();

        // Add padding at the bottom to ensure all items are reachable
        scrollContentTable.row();
        scrollContentTable.add().height(20); // Extra space at bottom

        // Force layout calculations
        scrollContentTable.pack();
        scrollPane.layout();

        // Reset scroll position to top
        scrollPane.setScrollY(0);
        scrollPane.updateVisualScroll();
    }


}
