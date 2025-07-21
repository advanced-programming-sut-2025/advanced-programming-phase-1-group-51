package io.github.StardewValley.Views.GameMenus.StoresMenus;

import com.badlogic.gdx.graphics.Color;
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
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Types.StoreProductsType.*;

import java.util.ArrayList;
import java.util.List;

public class AvailableProductsMenu extends BaseStoresMenu {
    private String storeName;
    private List<StoreProducts> availableProducts;

    public AvailableProductsMenu(GameController gameController, Skin skin, String storeName) {
        super(gameController, skin, storeName + " (Available Products)", storeName);
        this.storeName = storeName;
        this.availableProducts = new ArrayList<>();
    }

    @Override
    public void show() {
        super.show();
        showAvailableProducts();
    }

    private void showAvailableProducts() {
        scrollContentTable.clear();
        scrollContentTable.align(Align.center);
        scrollContentTable.setFillParent(true);

        // Get current season
        Season currentSeason = App.getCurrentGame().getSeason();

        // Load available products based on store name and season
        switch (storeName) {
            case "Black Smith":
                for (BlackSmithProducts product : BlackSmithProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "JojaMart":
                for (JojaMartProducts product : JojaMartProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "Carpenter's Shop":
                for (CarpenterShopProducts product : CarpenterShopProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "Fish Shop":
                for (FishShopProducts product : FishShopProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "Marnie's Ranch":
                for (MarnieRanchProducts product : MarnieRanchProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "Pierre General Store":
                for (PierreGeneralStoreProducts product : PierreGeneralStoreProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
            case "StarDrop Saloon":
                for (StarDropSaloonProducts product : StarDropSaloonProducts.values()) {
                    if (isProductAvailable(product, currentSeason)) {
                        availableProducts.add(product);
                    }
                }
                break;
        }

        // Create a table for the products grid
        Table productsTable = new Table();
        productsTable.align(Align.center); // Center the products grid
        productsTable.defaults().pad(5);

        int itemsPerRow = 8;
        int itemCount = 0;

        for (StoreProducts product : availableProducts) {
            Texture productTexture = getProductTexture(product);

            Table productCell = new Table();
            productCell.setBackground(skin.getDrawable("window"));

            Image productImage = new Image(new TextureRegionDrawable(productTexture));
            productImage.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Main.playSound(Main.getButtonClickSound());
                    // Handle product purchase here
                    player.addNotification("Purchased: " + product.getName());
                }
            });

            productCell.add(productImage).size(64).pad(5);
            productCell.row();

            // Add product name
            Label nameLabel = new Label(product.getName(), skin);
            nameLabel.setFontScale(0.7f);
            productCell.add(nameLabel).padBottom(2);
            productCell.row();

            // Add product price (may show seasonal price)
            double price = product.getProductPrice(currentSeason);
            Label priceLabel = new Label("Price: " + (int)price + "g", skin);
            priceLabel.setFontScale(0.6f);
            productCell.add(priceLabel).padBottom(2);
            productCell.row();

            // Add availability note if out of season
            if (!product.isInSeason(currentSeason)) {
                Label seasonLabel = new Label("(Out of season)", skin);
                seasonLabel.setFontScale(0.5f);
                seasonLabel.setColor(Color.RED);
                productCell.add(seasonLabel).padBottom(5);
            } else {
                Label stockLabel = new Label("In stock", skin);
                stockLabel.setFontScale(0.5f);
                stockLabel.setColor(Color.GREEN);
                productCell.add(stockLabel).padBottom(5);
            }

            productsTable.add(productCell).width(120).pad(5);
            itemCount++;

            if (itemCount % itemsPerRow == 0) {
                productsTable.row();
            }
        }

        scrollContentTable.add(productsTable).expand().fill().center();

        // Set scroll position to top when first showing
        scrollPane.setScrollY(0);
        scrollPane.updateVisualScroll();
    }

    private boolean isProductAvailable(StoreProducts product, Season currentSeason) {
        // For now, just check if it's in season
        // You might add more complex availability logic later
        return product.isInSeason(currentSeason);
    }


}
