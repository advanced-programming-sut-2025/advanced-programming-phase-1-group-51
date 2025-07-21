package io.github.StardewValley.Views.GameMenus.StoresMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Assets.MapInitialAssets;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.*;
import io.github.StardewValley.Models.Enums.Types.StoreProductsType.*;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.TrashcanType;
import io.github.StardewValley.Models.Player;

public abstract class BaseStoresMenu implements Screen {
    protected final Skin skin;
    protected Stage stage;
    protected final TextButton allProductsButton;
    protected final TextButton availableProductsButton;
    protected final TextButton backButton;
    protected Label menuTitle;
    protected Table mainTable;
    protected Table contentTable;
    protected GameController gameController;
    protected Player player;
    protected Label errorLabel;
    protected String storeName;
    protected ScrollPane scrollPane;
    protected Table scrollContentTable;;

    public BaseStoresMenu(GameController gameController, Skin skin, String title, String storeName) {
        this.skin = skin;
        this.storeName = storeName;
        this.player = App.getCurrentGame().getCurrentPlayer();
        this.gameController = gameController;
        this.allProductsButton = new TextButton("All Products", skin);
        this.availableProductsButton = new TextButton("Available Products", skin);
        this.backButton = new TextButton("Back", skin);
        this.menuTitle = new Label(title, skin);
        this.mainTable = new Table();
        this.contentTable = new Table();
        setupListeners();
    }

    protected void setupListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                resumeGame();
            }
        });
        allProductsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToAllProducts( );
            }
        });
        availableProductsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToAvailableProducts();
            }
        });
    }

    protected void navigateToAllProducts() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new AllProductsMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin(),
            storeName
        ));
    }

    protected void navigateToAvailableProducts() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new AvailableProductsMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin(),
            storeName
        ));
    }


    protected void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }

    protected void setupMainTable() {
        mainTable.clear();
        mainTable.setFillParent(true);
        mainTable.top(); // Changed from center() to top()

        // Title
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        menuTitle.setFontScale(0.7f);
        mainTable.add(menuTitle).colspan(2).padBottom(20).center();
        mainTable.row();

        // Navigation buttons
        Table navTable = new Table();
        navTable.add(availableProductsButton).padRight(10);
        navTable.add(allProductsButton).padRight(10);
        mainTable.add(navTable).colspan(2).padBottom(20).center();
        mainTable.row();

        // Create scroll pane and content table
        scrollContentTable = new Table();
        scrollContentTable.top(); // Align content to top

        scrollPane = new ScrollPane(scrollContentTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.setScrollBarPositions(false, true);

        // Add scroll pane to main table
        mainTable.add(scrollPane).colspan(2).expand().fill().padBottom(10);
        mainTable.row();

        // Back button
        mainTable.add(backButton).colspan(2).padTop(10).width(200).center();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        // Let the scroll pane calculate its own height based on parent container
    }

    protected Texture createPlaceholderTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAGENTA);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        setupMainTable();
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    protected Texture getProductTexture(StoreProducts product) {
        // First try to get texture based on item type
        ItemType itemType = product.getItemType();
        if (itemType != null) {
            if (itemType instanceof ForagingMineralType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMineralTexture((ForagingMineralType) itemType);
            } else if (itemType instanceof FoodType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getFoodTexture((FoodType) itemType);
            } else if (itemType instanceof CropSeedType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getCropSeedTexture((CropSeedType) itemType);
            } else if (itemType instanceof MiscType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMiscTexture((MiscType) itemType);
            } else if (itemType instanceof ToolType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getToolTexture((ToolType) itemType);
            } else if (itemType instanceof TreeSeedType) {
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getTreeSeedTexture((TreeSeedType) itemType);
            }
        }

        // If no item type or specific texture not found, handle by store and product name
        switch (storeName) {
            case "Black Smith":
                return getBlackSmithProductTexture((BlackSmithProducts) product);
            case "JojaMart":
                return getJojaMartProductTexture((JojaMartProducts) product);
            case "Carpenter's Shop":
                return getCarpenterShopProductTexture((CarpenterShopProducts) product);
            case "Fish Shop":
                return getFishShopProductTexture((FishShopProducts) product);
            case "Marnie's Ranch":
                return getMarnieRanchProductTexture((MarnieRanchProducts) product);
            case "Pierre General Store":
                return getPierreGeneralStoreProductTexture((PierreGeneralStoreProducts) product);
            case "StarDrop Saloon":
                return getStarDropSaloonProductTexture((StarDropSaloonProducts) product);
            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture getBlackSmithProductTexture(BlackSmithProducts product) {
        MapInitialAssets assets = GameAssetsManager.getInstance().getInitialAssets();

        switch (product) {
            // Ores and minerals
            case COPPER_ORE:
                return assets.getMineralTexture(ForagingMineralType.COPPER_ORE);
            case IRON_ORE:
                return assets.getMineralTexture(ForagingMineralType.IRON_ORE);
            case GOLD_ORE:
                return assets.getMineralTexture(ForagingMineralType.GOLD_ORE);
            case IRIDIUM_ORE:
                return assets.getMineralTexture(ForagingMineralType.IRIDIUM_ORE);
            case COAL:
                return assets.getMineralTexture(ForagingMineralType.COAL);

            // Tool upgrades
            case COPPER_HOE:
                return assets.getToolTexture(ToolType.COPPER_HOE);
            case STEEL_HOE:
                return assets.getToolTexture(ToolType.STEEL_HOE);
            case GOLD_HOE:
                return assets.getToolTexture(ToolType.GOLD_HOE);
            case IRIDIUM_HOE:
                return assets.getToolTexture(ToolType.IRIDIUM_HOE);

            case COPPER_PICKAXE:
                return assets.getToolTexture(ToolType.COPPER_PICKAXE);
            case STEEL_PICKAXE:
                return assets.getToolTexture(ToolType.STEEL_PICKAXE);
            case GOLD_PICKAXE:
                return assets.getToolTexture(ToolType.GOLD_PICKAXE);
            case IRIDIUM_PICKAXE:
                return assets.getToolTexture(ToolType.IRIDIUM_PICKAXE);

            case COPPER_AXE:
                return assets.getToolTexture(ToolType.COPPER_AXE);
            case STEEL_AXE:
                return assets.getToolTexture(ToolType.STEEL_AXE);
            case GOLD_AXE:
                return assets.getToolTexture(ToolType.GOLD_AXE);
            case IRIDIUM_AXE:
                return assets.getToolTexture(ToolType.IRIDIUM_AXE);

            case WATERING_CAN_COPPER:
                return assets.getToolTexture(ToolType.WATERING_CAN_COPPER);
            case WATERING_CAN_STEEL:
                return assets.getToolTexture(ToolType.WATERING_CAN_STEEL);
            case WATERING_CAN_GOLD:
                return assets.getToolTexture(ToolType.WATERING_CAN_GOLD);
            case WATERING_CAN_IRIDIUM:
                return assets.getToolTexture(ToolType.WATERING_CAN_IRIDIUM);

            case COPPER_SCYTHE:
                return assets.getToolTexture(ToolType.COPPER_SCYTHE);
            case STEEL_SCYTHE:
                return assets.getToolTexture(ToolType.STEEL_SCYTHE);
            case GOLD_SCYTHE:
                return assets.getToolTexture(ToolType.GOLD_SCYTHE);
            case IRIDIUM_SCYTHE:
                return assets.getToolTexture(ToolType.IRIDIUM_SCYTHE);

            case COPPER_TRASH_CAN:
                return assets.getTrashcanTexture(TrashcanType.COPPER);
            case STEEL_TRASH_CAN:
                return assets.getTrashcanTexture(TrashcanType.STEEL);
            case GOLD_TRASH_CAN:
                return assets.getTrashcanTexture(TrashcanType.GOLD);
            case IRIDIUM_TRASH_CAN:
                return assets.getTrashcanTexture(TrashcanType.IRIDIUM);

            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture getJojaMartProductTexture(JojaMartProducts product) {
        switch (product) {
            case JOJA_COLA:
                return findTexture("items/food/joja_cola.png");
            case ANCIENT_SEED:
                return findTexture("cropSeeds/Ancient_Seed.png");
            case GRASS_STARTER:
                return findTexture("items/misc/grass_starter.png");
            case SUGAR:
                return findTexture("items/food/sugar.png");
            case WHEAT_FLOUR:
                return findTexture("items/food/wheat_flour.png");
            case RICE:
                return findTexture("items/food/rice.png");

            // Seed cases - updated to follow cropSeeds/[Crop]_Seed.png pattern
            case PARSNIP_SEEDS:
                GameAssetsManager.getInstance().getInitialAssets()
                    .getCropSeedTexture(CropSeedType.AMARANTH_SEEDS);
            case BEAN_STARTER:
                return findTexture("cropSeeds/Bean_Seeds.png");
            case CAULIFLOWER_SEEDS:
                return findTexture("cropSeeds/Cauliflower_Seeds.png");
            case POTATO_SEEDS:
                return findTexture("cropSeeds/Potato_Seeds.png");
            case STRAWBERRY_SEEDS:
                return findTexture("cropSeeds/Strawberry_Seeds.png");
            case TULIP_BULB:
                return findTexture("cropSeeds/Tulip_Seeds.png");
            case KALE_SEEDS:
                return findTexture("cropSeeds/Kale_Seeds.png");
            case COFFEE_BEANS:
                return findTexture("cropSeeds/Coffee_Seeds.png");
            case CARROT_SEEDS:
                return findTexture("cropSeeds/Carrot_Seeds.png");
            case RHUBARB_SEEDS:
                return findTexture("cropSeeds/Rhubarb_Seeds.png");
            case JAZZ_SEEDS:
                return findTexture("cropSeeds/Jazz_Seeds.png");
            case TOMATO_SEEDS:
                return findTexture("cropSeeds/Tomato_Seeds.png");
            case PEPPER_SEEDS:
                return findTexture("cropSeeds/Pepper_Seeds.png");
            case WHEAT_SEEDS:
                return findTexture("cropSeeds/Wheat_Seeds.png");
            case SUMMER_SQUASH_SEEDS:
                return findTexture("cropSeeds/Summer_Squash_Seeds.png");
            case RADISH_SEEDS:
                return findTexture("cropSeeds/Radish_Seeds.png");
            case MELON_SEEDS:
                return findTexture("cropSeeds/Melon_Seeds.png");
            case HOPS_STARTER:
                return findTexture("cropSeeds/Hops_Seeds.png");
            case POPPY_SEEDS:
                return findTexture("cropSeeds/Poppy_Seeds.png");
            case SPANGLE_SEEDS:
                return findTexture("cropSeeds/Spangle_Seeds.png");
            case STARFRUIT_SEEDS:
                return findTexture("cropSeeds/Starfruit_Seeds.png");
            case SUNFLOWER_SEEDS:
                return findTexture("cropSeeds/Sunflower_Seeds.png");
            case CORN_SEEDS:
                return findTexture("cropSeeds/Corn_Seeds.png");
            case EGGPLANT_SEEDS:
                return findTexture("cropSeeds/Eggplant_Seeds.png");
            case PUMPKIN_SEEDS:
                return findTexture("cropSeeds/Pumpkin_Seeds.png");
            case BROCCOLI_SEEDS:
                return findTexture("cropSeeds/Broccoli_Seeds.png");
            case AMARANTH_SEEDS:
                return findTexture("cropSeeds/Amaranth_Seeds.png");
            case GRAPE_STARTER:
                return findTexture("cropSeeds/Grape_Seeds.png");
            case BEET_SEEDS:
                return findTexture("cropSeeds/Beet_Seeds.png");
            case YAM_SEEDS:
                return findTexture("cropSeeds/Yam_Seeds.png");
            case BOK_CHOY_SEEDS:
                return findTexture("cropSeeds/Bok_Choy_Seeds.png");
            case CRANBERRY_SEEDS:
                return findTexture("cropSeeds/Cranberry_Seeds.png");
            case FAIRY_SEEDS:
                return findTexture("cropSeeds/Fairy_Seeds.png");
            case RARE_SEED:
                return findTexture("cropSeeds/Rare_Seeds.png");
            case POWDERMELON_SEEDS:
                return findTexture("cropSeeds/Powdermelon_Seeds.png");
            default:
                return createPlaceholderTexture();
        }
    }


    protected Texture getCarpenterShopProductTexture(CarpenterShopProducts product) {
        switch (product) {
            case WOOD:
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMineralTexture(ForagingMineralType.WOOD);
            case STONE:
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMineralTexture(ForagingMineralType.STONE);
            case BARN:
            case BIG_BARN:
            case DELUXE_BARN:
                return findTexture("buildings/barn.png");
            case COOP:
            case BIG_COOP:
            case DELUXE_COOP:
                return findTexture("buildings/coop.png");
            case WELL:
                return findTexture("buildings/well.png");
            case SHIPPING_BIN:
                return findTexture("buildings/shipping_bin.png");
            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture getFishShopProductTexture(FishShopProducts product) {
        switch (product) {
            case TROUT_SOUP:
                return findTexture("items/food/trout_soup.png");
            case BAMBOO_POLE:
                return findTexture("items/tools/bamboo_pole.png");
            case TRAINING_ROD:
                return findTexture("items/tools/training_rod.png");
            case FIBERGLASS_ROD:
                return findTexture("items/tools/fiberglass_rod.png");
            case IRIDIUM_ROD:
                return findTexture("items/tools/iridium_rod.png");
            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture getMarnieRanchProductTexture(MarnieRanchProducts product) {
        switch (product) {
            case HAY:
                return findTexture("items/misc/Hay.png");
            case MILK_PAIL:
                return findTexture("items/tools/Milk_Pail.png");
            case SHEARS:
                return findTexture("items/tools/Shears.png");
            case HEN:
                return findTexture("animals/Hen.png");
            case COW:
                return findTexture("animals/Cow.png");
            case GOAT:
                return findTexture("animals/Goat.png");
            case DUCK:
                return findTexture("animals/Duck.png");
            case SHEEP:
                return findTexture("animals/Sheep.png");
            case RABBIT:
                return findTexture("animals/Rabbit.png");
            case DINOSAUR:
                return findTexture("animals/Dinosaur.png");
            case PIG:
                return findTexture("animals/Pig.png");
            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture getPierreGeneralStoreProductTexture(PierreGeneralStoreProducts product) {
        // For seeds, use the same textures as JojaMart
        switch (product) {
            case DEHYDRATOR:
                return findTexture("items/crafting/dehydrator.png");
            case GRASS_STARTER_RECIPE:
                return findTexture("items/crafting/recipe.png");
            case RICE_P:
                return findTexture("items/food/rice.png");
            case WHEAT_FLOUR_P:
                return findTexture("items/food/wheat_flour.png");
            case BOUQUET:
                return findTexture("items/misc/bouquet.png");
            case WEDDING_RING:
                return findTexture("items/misc/wedding_ring.png");
            case SUGAR_P:
                return findTexture("items/food/sugar.png");
            case OIL:
                return findTexture("items/food/oil.png");
            case VINEGAR:
                return findTexture("items/food/vinegar.png");
            case DELUXE_RETAINING_SOIL:
                return findTexture("items/misc/deluxe_retaining_soil.png");
            case GRASS_STARTER_P:
                return findTexture("items/misc/grass_starter.png");
            case SPEED_GRO:
                return findTexture("items/misc/speed_gro.png");
            case APPLE_SAPLING:
            case APRICOT_SAPLING:
            case CHERRY_SAPLING:
            case ORANGE_SAPLING:
            case PEACH_SAPLING:
            case POMEGRANATE_SAPLING:
                return findTexture("items/seeds/tree_sapling.png");
            case BASIC_RETAINING_SOIL:
                return findTexture("items/misc/basic_retaining_soil.png");
            case QUALITY_RETAINING_SOIL:
                return findTexture("items/misc/quality_retaining_soil.png");
            case LARGE_PACK:
                return findTexture("items/misc/backpack_upgrade.png");
            case DELUXE_PACK:
                return findTexture("items/misc/deluxe_backpack_upgrade.png");
            // For seeds, use the same textures as JojaMart
            default:
                // Try to find matching JojaMart product texture
                for (JojaMartProducts jProduct : JojaMartProducts.values()) {
                    if (jProduct.name().equals(product.name().replace("_P", ""))) {
                        return getJojaMartProductTexture(jProduct);
                    }
                }
                return createPlaceholderTexture();
        }
    }

    protected Texture getStarDropSaloonProductTexture(StarDropSaloonProducts product) {
        switch (product) {
            case HASHBROWNS:
                return findTexture("food/Hashbrowns.png");
            case OMELET:
                return findTexture("food/Omelet.png");
            case PANCAKES:
                return findTexture("food/Pancakes.png");
            case BREAD_RECIPE:
                return findTexture("crafting/recipe.png");
            case TORTILLA:
                return findTexture("food/Tortilla.png");
            case PIZZA_RECIPE:
                return findTexture("crafting/recipe.png");
            case MAKI_ROLL:
                return findTexture("food/Maki_Roll.png");
            case TRIPLE_SHOT_ESPRESSO:
                return findTexture("food/Triple_Shot_Espresso.png");
            case COOKIE:
                return findTexture("food/Cookie.png");
            case BEER:
                return findTexture("food/Beer.png");
            case SALAD:
                return findTexture("food/Salad.png");
            case BREAD:
                return findTexture("food/Bread.png");
            case SPAGHETTI:
                return findTexture("food/Spaghetti.png");
            case PIZZA:
                return findTexture("food/Pizza.png");
                case COFFEE:
                 return findTexture("food/Coffee.png");
            case WOOD_S:
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMineralTexture(ForagingMineralType.WOOD);
            case STONE_S:
                return GameAssetsManager.getInstance().getInitialAssets()
                    .getMineralTexture(ForagingMineralType.STONE);
            default:
                return createPlaceholderTexture();
        }
    }

    protected Texture findTexture(String path) {
        try {
            return new Texture(Gdx.files.internal(path));
        } catch (Exception e) {
            Gdx.app.error("StoreMenu", "Failed to load texture: " + path, e);
            return createPlaceholderTexture();
        }
    }
}
