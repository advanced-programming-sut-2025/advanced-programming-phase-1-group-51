package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import io.github.StardewValley.Models.Enums.Types.AnimalType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.*;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.TrashcanType;
import io.github.StardewValley.Models.Items.*;

import java.util.HashMap;
import java.util.Map;

public class MapInitialAssets implements Disposable {
    private static MapInitialAssets instance;

    // Individual textures for each type
    private Map<ForagingTreeType, Texture> foragingTreeTextures;
    private Map<ForagingCropType, Texture> foragingCropTexture;
    private Map<CropType, Texture>  cropTexture;
    private Map<ForagingMineralType, Texture> mineralTextures;
    private Map<ToolType, Texture> toolTextures;
    private Map<FishType, Texture> fishTextures;
    private Map<FoodType, Texture> foodTextures;
    private Map<MiscType, Texture> miscTextures;
    private Map<TreeSeedType, Texture> treeSeedTextures;
    private Map<CropSeedType, Texture> cropSeedTextures;
    private Map<TrashcanType, Texture> trashcanTexture;
    private Map<AnimalType, Texture> animalTextures;
    // Add to ForagingAssets class

    private MapInitialAssets() {
        loadTextures();
    }

    private void loadTextures() {

        loadForagingTreeTextures();

        loadForagingCropTextures();

        loadCropTextures();

        loadMineralTextures();

        loadToolTextures();

        loadTrashcanTextures();

        loadFoodTextures();

        loadMiscTextures();

        loadCropSeedTextures();

        loadTreeSeedTextures();

        loadFishTextures();

        loadAnimaTextures();

    }

    private Texture findTexture(String path) {
        try {
            // First try to load the requested texture
            Texture texture = new Texture(Gdx.files.internal(path));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            return texture;
        } catch (Exception e) {
            Gdx.app.error("ForagingAssetsManager", "Failed to load texture: " + path, e);

            // Try to load a default placeholder
            try {
                return new Texture(Gdx.files.internal("foraging/placeholder.png"));
            } catch (Exception e2) {
                // If even placeholder fails, create a blank texture
                Gdx.app.error("ForagingAssetsManager", "Failed to load placeholder texture", e2);
                return createBlankTexture(32, 32); // Create a fallback
            }
        }
    }

    private Texture createBlankTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAGENTA); // Easy-to-spot debug color
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static MapInitialAssets getInstance() {
        if (instance == null) {
            instance = new MapInitialAssets();
        }
        return instance;
    }

    private void loadToolTextures() {
        toolTextures = new HashMap<>();
        toolTextures.put(ToolType.HOE, findTexture("items/tools/Hoe.png"));
        toolTextures.put(ToolType.STEEL_HOE, findTexture("items/tools/Steel_Hoe.png"));
        toolTextures.put(ToolType.COPPER_HOE, findTexture("items/tools/Copper_Hoe.png"));
        toolTextures.put(ToolType.IRIDIUM_HOE, findTexture("items/tools/Iridium_Hoe.png"));
        toolTextures.put(ToolType.GOLD_HOE, findTexture("items/tools/Gold_Hoe.png"));
        toolTextures.put(ToolType.PICKAXE, findTexture("items/tools/Pickaxe.png"));
        toolTextures.put(ToolType.STEEL_PICKAXE, findTexture("items/tools/Steel_Pickaxe.png"));
        toolTextures.put(ToolType.COPPER_PICKAXE, findTexture("items/tools/Copper_Pickaxe.png"));
        toolTextures.put(ToolType.GOLD_PICKAXE, findTexture("items/tools/Gold_Pickaxe.png"));
        toolTextures.put(ToolType.IRIDIUM_PICKAXE, findTexture("items/tools/Iridium_Pickaxe.png"));
        toolTextures.put(ToolType.AXE, findTexture("items/tools/Axe.png"));
        toolTextures.put(ToolType.STEEL_AXE, findTexture("items/tools/Steel_Axe.png"));
        toolTextures.put(ToolType.COPPER_AXE, findTexture("items/tools/Copper_Axe.png"));
        toolTextures.put(ToolType.GOLD_AXE, findTexture("items/tools/Gold_Axe.png"));
        toolTextures.put(ToolType.IRIDIUM_AXE, findTexture("items/tools/Iridium_Axe.png"));
        toolTextures.put(ToolType.WATERING_CAN, findTexture("items/tools/Watering_Can.png"));
        toolTextures.put(ToolType.WATERING_CAN_STEEL, findTexture("items/tools/Steel_Watering_Can.png"));
        toolTextures.put(ToolType.WATERING_CAN_COPPER, findTexture("items/tools/Copper_Watering_Can.png"));
        toolTextures.put(ToolType.WATERING_CAN_GOLD, findTexture("items/tools/Gold_Watering_Can.png"));
        toolTextures.put(ToolType.WATERING_CAN_IRIDIUM, findTexture("items/tools/Iridium_Watering_Can.png"));
        toolTextures.put(ToolType.SCYTHE, findTexture("items/tools/Scythe.png"));
        toolTextures.put(ToolType.STEEL_SCYTHE, findTexture("items/tools/Steel_Scythe.png"));
        toolTextures.put(ToolType.COPPER_SCYTHE, findTexture("items/tools/Copper_Scythe.png"));
        toolTextures.put(ToolType.GOLD_SCYTHE, findTexture("items/tools/Gold_Scythe.png"));
        toolTextures.put(ToolType.IRIDIUM_SCYTHE, findTexture("items/tools/Iridium_Scythe.png"));
        toolTextures.put(ToolType.BAMBOO_POLE, findTexture("items/tools/Bamboo_Pole.png"));
        toolTextures.put(ToolType.FIBERGLASS_ROD, findTexture("items/tools/Fiberglass_Rod.png"));
        toolTextures.put(ToolType.IRIDIUM_ROD, findTexture("items/tools/Iridium_Rod.png"));
        toolTextures.put(ToolType.TRAINING_ROD, findTexture("items/tools/Training_Rod.png"));
        toolTextures.put(ToolType.SHEARS, findTexture("items/tools/Shears.png"));
        toolTextures.put(ToolType.MILK_PAIL, findTexture("items/tools/Milk_Pail.png"));
        // ... other tools
    }
    private void loadTrashcanTextures() {
        trashcanTexture = new HashMap<>();

        trashcanTexture.put(TrashcanType.DEFAULT, findTexture("items/tools/Trash_Can_Steel.png"));
        trashcanTexture.put(TrashcanType.STEEL, findTexture("items/tools/Trash_Can_Steel.png"));
        trashcanTexture.put(TrashcanType.COPPER, findTexture("items/tools/Trash_Can_Copper.png"));
        trashcanTexture.put(TrashcanType.GOLD, findTexture("items/tools/Trash_Can_Gold.png"));
        trashcanTexture.put(TrashcanType.IRIDIUM, findTexture("items/tools/Trash_Can_Iridium.png"));
    }

    private void loadAnimaTextures() {
        animalTextures = new HashMap<>();
        animalTextures.put(AnimalType.HEN, findTexture("animals/Hen.png"));
        animalTextures.put(AnimalType.COW, findTexture("animals/Cow.png"));
        animalTextures.put(AnimalType.DINOSAUR, findTexture("animals/Dinosaur.png"));
        animalTextures.put(AnimalType.DUCK, findTexture("animals/Duck.png"));
        animalTextures.put(AnimalType.GOAT, findTexture("animals/Goat.png"));
        animalTextures.put(AnimalType.PIG, findTexture("animals/Pig.png"));
        animalTextures.put(AnimalType.RABBIT, findTexture("animals/Rabbit.png"));
        animalTextures.put(AnimalType.SHEEP, findTexture("animals/Sheep.png"));
        // ... other tools
    }
    private void loadForagingTreeTextures() {
        foragingTreeTextures = new HashMap<>();

        foragingTreeTextures.put(ForagingTreeType.OAK_TREE, findTexture("foraging/trees/oak_tree.png"));
        foragingTreeTextures.put(ForagingTreeType.PINE_TREE, findTexture("foraging/trees/pine_tree.png"));
//        treeTextures.put(ForagingTreeType.MAPLE_TREE, loadTexture("foraging/trees/maple_tree.png"));
//        treeTextures.put(ForagingTreeType.MAHOGANY_TREE, loadTexture("foraging/trees/mahogany_tree.png"));
//        treeTextures.put(ForagingTreeType.NORMAL_TREE, loadTexture("foraging/trees/normal_tree.png"));
//        treeTextures.put(ForagingTreeType.BURNT_TREE, loadTexture("foraging/trees/burnt_tree.png"));
//        treeTextures.put(ForagingTreeType.TREE_BARK, loadTexture("foraging/trees/tree_bark.png"));
//        treeTextures.put(ForagingTreeType.APRICOT_TREE, loadTexture("foraging/trees/apricot_tree.png"));
//        treeTextures.put(ForagingTreeType.CHERRY_TREE, loadTexture("foraging/trees/cherry_tree.png"));
//        treeTextures.put(ForagingTreeType.BANANA_TREE, loadTexture("foraging/trees/banana_tree.png"));
//        treeTextures.put(ForagingTreeType.MANGO_TREE, loadTexture("foraging/trees/mango_tree.png"));
//        treeTextures.put(ForagingTreeType.ORANGE_TREE, loadTexture("foraging/trees/orange_tree.png"));
//        treeTextures.put(ForagingTreeType.PEACH_TREE, loadTexture("foraging/trees/peach_tree.png"));
//        treeTextures.put(ForagingTreeType.APPLE_TREE, loadTexture("foraging/trees/apple_tree.png"));
//        treeTextures.put(ForagingTreeType.POMEGRANATE_TREE, loadTexture("foraging/trees/pomegranate_tree.png"));
//        treeTextures.put(ForagingTreeType.MUSHROOM_TREE, loadTexture("foraging/trees/mushroom_tree.png"));
//        treeTextures.put(ForagingTreeType.MYSTIC_TREE, loadTexture("foraging/trees/mystic_tree.png"));
    }

    private void loadForagingCropTextures() {
        foragingCropTexture = new HashMap<>();

        // Year-round crops
        foragingCropTexture.put(ForagingCropType.GRASS, findTexture("foraging/crops/grass.png"));
        foragingCropTexture.put(ForagingCropType.COMMON_MUSHROOM, findTexture("foraging/crops/common_mushroom.png"));

        // Spring crops
        foragingCropTexture.put(ForagingCropType.DAFFODIL, findTexture("foraging/crops/daffodil.png"));
        foragingCropTexture.put(ForagingCropType.DANDELION, findTexture("foraging/crops/dandelion.png"));
        foragingCropTexture.put(ForagingCropType.LEEK, findTexture("foraging/crops/leek.png"));
        foragingCropTexture.put(ForagingCropType.MOREL, findTexture("foraging/crops/morel.png"));
        foragingCropTexture.put(ForagingCropType.SALMON_BERRY, findTexture("foraging/crops/salmonberry.png"));
        foragingCropTexture.put(ForagingCropType.SPRING_ONION, findTexture("foraging/crops/spring_onion.png"));
        foragingCropTexture.put(ForagingCropType.WILD_HORSERADISH, findTexture("foraging/crops/wild_horseradish.png"));

        // Summer crops
        foragingCropTexture.put(ForagingCropType.FIDDLE_HEAD_FERN, findTexture("foraging/crops/fiddlehead_fern.png"));
        foragingCropTexture.put(ForagingCropType.GRAPE, findTexture("foraging/crops/grape.png"));
        foragingCropTexture.put(ForagingCropType.RED_MUSHROOM, findTexture("foraging/crops/red_mushroom.png"));
        foragingCropTexture.put(ForagingCropType.SPICE_BERRY, findTexture("foraging/crops/spice_berry.png"));
        foragingCropTexture.put(ForagingCropType.SWEET_PEA, findTexture("foraging/crops/sweet_pea.png"));

        // Fall crops
        foragingCropTexture.put(ForagingCropType.BLACKBERRY, findTexture("foraging/crops/blackberry.png"));
        foragingCropTexture.put(ForagingCropType.CHANTERELLE, findTexture("foraging/crops/chanterelle.png"));
        foragingCropTexture.put(ForagingCropType.HAZELNUT, findTexture("foraging/crops/hazelnut.png"));
        foragingCropTexture.put(ForagingCropType.PURPLE_MUSHROOM, findTexture("foraging/crops/purple_mushroom.png"));
        foragingCropTexture.put(ForagingCropType.WILD_PLUM, findTexture("foraging/crops/wild_plum.png"));

        // Winter crops
        foragingCropTexture.put(ForagingCropType.CROCUS, findTexture("foraging/crops/crocus.png"));
        foragingCropTexture.put(ForagingCropType.CRYSTAL_FRUIT, findTexture("foraging/crops/crystal_fruit.png"));
        foragingCropTexture.put(ForagingCropType.HOLLY, findTexture("foraging/crops/holly.png"));
        foragingCropTexture.put(ForagingCropType.SNOW_YAM, findTexture("foraging/crops/snow_yam.png"));
        foragingCropTexture.put(ForagingCropType.WINTER_ROOT, findTexture("foraging/crops/winter_root.png"));
    }

    private void loadCropTextures() {
        cropTexture = new HashMap<>();

        // Spring crops
        cropTexture.put(CropType.BLUE_JAZZ, findTexture("crops/Blue_Jazz.png"));
        cropTexture.put(CropType.CARROT, findTexture("crops/Carrot.png"));
        cropTexture.put(CropType.CAULIFLOWER, findTexture("crops/Cauliflower.png"));
        cropTexture.put(CropType.COFFEE_BEAN, findTexture("crops/Coffee_Bean.png"));
        cropTexture.put(CropType.GARLIC, findTexture("crops/Garlic.png"));
        cropTexture.put(CropType.GREEN_BEAN, findTexture("crops/Green_Bean.png"));
        cropTexture.put(CropType.KALE, findTexture("crops/Kale.png"));
        cropTexture.put(CropType.PARSNIP, findTexture("crops/Parsnip.png"));
        cropTexture.put(CropType.POTATO, findTexture("crops/Potato.png"));
        cropTexture.put(CropType.RHUBARB, findTexture("crops/Rhubarb.png"));
        cropTexture.put(CropType.STRAWBERRY, findTexture("crops/Strawberry.png"));
        cropTexture.put(CropType.TULIP, findTexture("crops/Tulip.png"));
        cropTexture.put(CropType.UNMILLED_RICE, findTexture("crops/Unmilled_Rice.png"));

        // Summer crops
        cropTexture.put(CropType.BLUEBERRY, findTexture("crops/Blueberry.png"));
        cropTexture.put(CropType.CORN, findTexture("crops/Corn.png"));
        cropTexture.put(CropType.HOPS, findTexture("crops/Hops.png"));
        cropTexture.put(CropType.HOT_PEPPER, findTexture("crops/Hot_Pepper.png"));
        cropTexture.put(CropType.MELON, findTexture("crops/Melon.png"));
        cropTexture.put(CropType.POPPY, findTexture("crops/Poppy.png"));
        cropTexture.put(CropType.RADISH, findTexture("crops/Radish.png"));
        cropTexture.put(CropType.RED_CABBAGE, findTexture("crops/Red_Cabbage.png"));
        cropTexture.put(CropType.STARFRUIT, findTexture("crops/Starfruit.png"));
        cropTexture.put(CropType.SUMMER_SPANGLE, findTexture("crops/Summer_Spangle.png"));
        cropTexture.put(CropType.SUMMER_SQUASH, findTexture("crops/Summer_Squash.png"));
        cropTexture.put(CropType.SUNFLOWER, findTexture("crops/Sunflower.png"));
        cropTexture.put(CropType.TOMATO, findTexture("crops/Tomato.png"));
        cropTexture.put(CropType.WHEAT, findTexture("crops/Wheat.png"));

        // Fall crops
        cropTexture.put(CropType.AMARANTH, findTexture("crops/Amaranth.png"));
        cropTexture.put(CropType.ARTICHOKE, findTexture("crops/Artichoke.png"));
        cropTexture.put(CropType.BEET, findTexture("crops/Beet.png"));
        cropTexture.put(CropType.BOK_CHOY, findTexture("crops/Bok_Choy.png"));
        cropTexture.put(CropType.BROCCOLI, findTexture("crops/Broccoli.png"));
        cropTexture.put(CropType.CRANBERRIES, findTexture("crops/Cranberries.png"));
        cropTexture.put(CropType.EGGPLANT, findTexture("crops/Eggplant.png"));
        cropTexture.put(CropType.FAIRY_ROSE, findTexture("crops/Fairy_Rose.png"));
        cropTexture.put(CropType.GRAPE, findTexture("crops/Grape.png"));
        cropTexture.put(CropType.PUMPKIN, findTexture("crops/Pumpkin.png"));
        cropTexture.put(CropType.YAM, findTexture("crops/Yam.png"));
        cropTexture.put(CropType.SWEET_GEM_BERRY, findTexture("crops/Sweet_Gem_Berry.png"));

        // Special crops
        cropTexture.put(CropType.POWDER_MELON, findTexture("crops/Powder_Melon.png"));
        cropTexture.put(CropType.ANCIENT_FRUIT, findTexture("crops/Ancient_Fruit.png"));
        cropTexture.put(CropType.RANDOM_CROP, findTexture("crops/Random_Crop.png"));
    }

    private void loadMineralTextures() {
        mineralTextures = new HashMap<>();

        mineralTextures.put(ForagingMineralType.STONE, findTexture("foraging/minerals/Stone.png"));
        mineralTextures.put(ForagingMineralType.WOOD, findTexture("foraging/minerals/Wood.png"));
        mineralTextures.put(ForagingMineralType.COAL, findTexture("foraging/minerals/Coal.png"));
        mineralTextures.put(ForagingMineralType.IRON_ORE, findTexture("foraging/minerals/Iron_Ore.png"));
        mineralTextures.put(ForagingMineralType.COPPER_ORE, findTexture("foraging/minerals/Copper_Ore.png"));
        mineralTextures.put(ForagingMineralType.GOLD_ORE, findTexture("foraging/minerals/Gold_Ore.png"));
        mineralTextures.put(ForagingMineralType.IRIDIUM_ORE, findTexture("foraging/minerals/Iridium_Ore.png"));
        // Add all other mineral types...
    }

    private void loadCropSeedTextures() {
        cropSeedTextures = new HashMap<>();

        cropSeedTextures.put(CropSeedType.JAZZ_SEEDS, findTexture("cropSeeds/Jazz_Seeds.png"));
        cropSeedTextures.put(CropSeedType.PARSNIP_SEEDS, findTexture("cropSeeds/Parsnip_Seeds.png"));
        cropSeedTextures.put(CropSeedType.BEAN_STARTER, findTexture("cropSeeds/Bean_Starter.png"));
        cropSeedTextures.put(CropSeedType.CAULIFLOWER_SEEDS, findTexture("cropSeeds/Cauliflower_Seeds.png"));
        cropSeedTextures.put(CropSeedType.POTATO_SEEDS, findTexture("cropSeeds/Potato_Seeds.png"));
        cropSeedTextures.put(CropSeedType.TULIP_BULB, findTexture("cropSeeds/Tulip_Bulb.png"));
        cropSeedTextures.put(CropSeedType.KALE_SEEDS, findTexture("cropSeeds/Kale_Seeds.png"));
        cropSeedTextures.put(CropSeedType.GARLIC_SEEDS, findTexture("cropSeeds/Garlic_Seeds.png"));
        cropSeedTextures.put(CropSeedType.UNMILLED_RICE, findTexture("cropSeeds/Unmilled_Rice.png"));
        cropSeedTextures.put(CropSeedType.MELON_SEEDS, findTexture("cropSeeds/Melon_Seeds.png"));
        cropSeedTextures.put(CropSeedType.TOMATO_SEEDS, findTexture("cropSeeds/Tomato_Seeds.png"));
        cropSeedTextures.put(CropSeedType.BLUEBERRY_SEEDS, findTexture("cropSeeds/Blueberry_Seeds.png"));
        cropSeedTextures.put(CropSeedType.PEPPER_SEEDS, findTexture("cropSeeds/Pepper_Seeds.png"));
        cropSeedTextures.put(CropSeedType.WHEAT_SEEDS, findTexture("cropSeeds/Wheat_Seeds.png"));
        cropSeedTextures.put(CropSeedType.RADISH_SEEDS, findTexture("cropSeeds/Radish_Seeds.png"));
        cropSeedTextures.put(CropSeedType.POPPY_SEEDS, findTexture("cropSeeds/Poppy_Seeds.png"));
        cropSeedTextures.put(CropSeedType.SPANGLE_SEEDS, findTexture("cropSeeds/Spangle_Seeds.png"));
        cropSeedTextures.put(CropSeedType.HOPS_STARTER, findTexture("cropSeeds/Hops_Starter.png"));
        cropSeedTextures.put(CropSeedType.CORN_SEEDS, findTexture("cropSeeds/Corn_Seeds.png"));
        cropSeedTextures.put(CropSeedType.SUNFLOWER_SEEDS, findTexture("cropSeeds/Sunflower_Seeds.png"));
        cropSeedTextures.put(CropSeedType.RED_CABBAGE_SEEDS, findTexture("cropSeeds/Red_Cabbage_Seeds.png"));
        cropSeedTextures.put(CropSeedType.EGGPLANT_SEEDS, findTexture("cropSeeds/Eggplant_Seeds.png"));
        cropSeedTextures.put(CropSeedType.PUMPKIN_SEEDS, findTexture("cropSeeds/Pumpkin_Seeds.png"));
        cropSeedTextures.put(CropSeedType.BOK_CHOY_SEEDS, findTexture("cropSeeds/Bok_Choy_Seeds.png"));
        cropSeedTextures.put(CropSeedType.YAM_SEEDS, findTexture("cropSeeds/Yam_Seeds.png"));
        cropSeedTextures.put(CropSeedType.CRANBERRY_SEEDS, findTexture("cropSeeds/Cranberry_Seeds.png"));
        cropSeedTextures.put(CropSeedType.FAIRY_SEEDS, findTexture("cropSeeds/Fairy_Seeds.png"));
        cropSeedTextures.put(CropSeedType.AMARANTH_SEEDS, findTexture("cropSeeds/Amaranth_Seeds.png"));
        cropSeedTextures.put(CropSeedType.GRAPE_STARTER, findTexture("cropSeeds/Grape_Starter.png"));
        cropSeedTextures.put(CropSeedType.ARTICHOKE_SEEDS, findTexture("cropSeeds/Artichoke_Seeds.png"));
        cropSeedTextures.put(CropSeedType.ANCIENT_SEEDS, findTexture("cropSeeds/Ancient_Seeds.png"));
        cropSeedTextures.put(CropSeedType.CARROT_SEEDS, findTexture("cropSeeds/Carrot_Seeds.png"));
        cropSeedTextures.put(CropSeedType.SUMMER_SQUASH_SEEDS, findTexture("cropSeeds/Summer_Squash_Seeds.png"));
        cropSeedTextures.put(CropSeedType.STARFRUIT_SEEDS, findTexture("cropSeeds/Starfruit_Seeds.png"));
        cropSeedTextures.put(CropSeedType.PINEAPPLE_SEEDS, findTexture("cropSeeds/Pineapple_Seeds.png"));
        cropSeedTextures.put(CropSeedType.RHUBARB_SEEDS, findTexture("cropSeeds/Rhubarb_Seeds.png"));
        cropSeedTextures.put(CropSeedType.FIBER_SEEDS, findTexture("cropSeeds/Fiber_Seeds.png"));
        cropSeedTextures.put(CropSeedType.BEET_SEEDS, findTexture("cropSeeds/Beet_Seeds.png"));
        cropSeedTextures.put(CropSeedType.STRAWBERRY_SEEDS, findTexture("cropSeeds/Strawberry_Seeds.png"));
        cropSeedTextures.put(CropSeedType.POWDERMELON_SEEDS, findTexture("cropSeeds/Powdermelon_Seeds.png"));
        cropSeedTextures.put(CropSeedType.SWEET_GEM_BERRY_SEEDS, findTexture("cropSeeds/Sweet_Gem_Berry_Seeds.png"));
        cropSeedTextures.put(CropSeedType.BROCCOLI_SEEDS, findTexture("cropSeeds/Broccoli_Seeds.png"));
        cropSeedTextures.put(CropSeedType.COFFEE_BEAN_SEEDS, findTexture("cropSeeds/Coffee_Bean.png"));
        cropSeedTextures.put(CropSeedType.GRASS_STARTER, findTexture("cropSeeds/Grass_Starter.png"));
    }

    private void loadFishTextures() {
        fishTextures = new HashMap<>();

        fishTextures.put(FishType.SALMON, findTexture("fishes/Salmon.png"));
        fishTextures.put(FishType.SARDINE, findTexture("fishes/Sardine.png"));
        fishTextures.put(FishType.SHAD, findTexture("fishes/Shad.png"));
        fishTextures.put(FishType.BLUE_DISCUS, findTexture("fishes/Blue_Discus.png"));
        fishTextures.put(FishType.MIDNIGHT_CARP, findTexture("fishes/Midnight_Carp.png"));
        fishTextures.put(FishType.SQUID, findTexture("fishes/Squid.png"));
        fishTextures.put(FishType.TUNA, findTexture("fishes/Tuna.png"));
        fishTextures.put(FishType.PERCH, findTexture("fishes/Perch.png"));
        fishTextures.put(FishType.FLOUNDER, findTexture("fishes/Flounder.png"));
        fishTextures.put(FishType.LIONFISH, findTexture("fishes/Lionfish.png"));
        fishTextures.put(FishType.HERRING, findTexture("fishes/Herring.png"));
        fishTextures.put(FishType.GHOSTFISH, findTexture("fishes/Ghostfish.png"));
        fishTextures.put(FishType.TILAPIA, findTexture("fishes/Tilapia.png"));
        fishTextures.put(FishType.DORADO, findTexture("fishes/Dorado.png"));
        fishTextures.put(FishType.SUNFISH, findTexture("fishes/Sunfish.png"));
        fishTextures.put(FishType.RAINBOW_TROUT, findTexture("fishes/Rainbow_Trout.png"));
        fishTextures.put(FishType.LEGEND, findTexture("fishes/Legend.png"));
        fishTextures.put(FishType.GLACIERFISH, findTexture("fishes/Glacierfish.png"));
        fishTextures.put(FishType.ANGLER, findTexture("fishes/Angler.png"));
        fishTextures.put(FishType.CRIMSONFISH, findTexture("fishes/Crimsonfish.png"));
    }

    private void loadFoodTextures() {
        foodTextures = new HashMap<>();

        // Fruits
        foodTextures.put(FoodType.APRICOT, findTexture("food/Apricot.png"));
        foodTextures.put(FoodType.CHERRY, findTexture("food/Cherry.png"));
        foodTextures.put(FoodType.BANANA, findTexture("food/Banana.png"));
        foodTextures.put(FoodType.MANGO, findTexture("food/Mango.png"));
        foodTextures.put(FoodType.ORANGE, findTexture("food/Orange.png"));
        foodTextures.put(FoodType.PEACH, findTexture("food/Peach.png"));
        foodTextures.put(FoodType.APPLE, findTexture("food/Apple.png"));
        foodTextures.put(FoodType.POMEGRANATE, findTexture("food/Pomegranate.png"));

        // Tree products
        foodTextures.put(FoodType.OAK_RESIN, findTexture("food/Oak_Resin.png"));
        foodTextures.put(FoodType.MAPLE_SYRUP, findTexture("food/Maple_Syrup.png"));
        foodTextures.put(FoodType.PINE_TAR, findTexture("food/Pine_Tar.png"));
        foodTextures.put(FoodType.SAP, findTexture("food/Sap.png"));

        // Mushrooms
        foodTextures.put(FoodType.COMMON_MUSHROOM, findTexture("food/Common_Mushroom.png"));
        foodTextures.put(FoodType.MOREL, findTexture("food/Morel.png"));
        foodTextures.put(FoodType.RED_MUSHROOM, findTexture("food/Red_Mushroom.png"));
        foodTextures.put(FoodType.PURPLE_MUSHROOM, findTexture("food/Purple_Mushroom.png"));
        foodTextures.put(FoodType.CHANTERELLE, findTexture("food/Chanterelle.png"));

        // Cooked meals
        foodTextures.put(FoodType.FRIED_EGG, findTexture("food/Fried_Egg.png"));
        foodTextures.put(FoodType.BAKED_FISH, findTexture("food/Baked_Fish.png"));
        foodTextures.put(FoodType.SALAD, findTexture("food/Salad.png"));
        foodTextures.put(FoodType.OMELETTE, findTexture("food/Omelette.png"));
        foodTextures.put(FoodType.PUMPKIN_PIE, findTexture("food/Pumpkin_Pie.png"));
        foodTextures.put(FoodType.SPAGHETTI, findTexture("food/Spaghetti.png"));
        foodTextures.put(FoodType.PIZZA, findTexture("food/Pizza.png"));
        foodTextures.put(FoodType.TORTILLA, findTexture("food/Tortilla.png"));
        foodTextures.put(FoodType.MAKI_ROLL, findTexture("food/Maki_Roll.png"));
        foodTextures.put(FoodType.TRIPLE_SHOT_ESPRESSO, findTexture("food/Triple_Shot_Espresso.png"));
        foodTextures.put(FoodType.COOKIE, findTexture("food/Cookie.png"));
        foodTextures.put(FoodType.HASH_BROWNS, findTexture("food/Hash_Browns.png"));
        foodTextures.put(FoodType.PANCAKES, findTexture("food/Pancakes.png"));
        foodTextures.put(FoodType.FRUIT_SALAD, findTexture("food/Fruit_Salad.png"));
        foodTextures.put(FoodType.RED_PLATE, findTexture("food/Red_Plate.png"));
        foodTextures.put(FoodType.BREAD, findTexture("food/Bread.png"));
        foodTextures.put(FoodType.SALMON_DINNER, findTexture("food/Salmon_Dinner.png"));
        foodTextures.put(FoodType.VEGETABLE_MEDLEY, findTexture("food/Vegetable_Medley.png"));
        foodTextures.put(FoodType.FARMERS_LUNCH, findTexture("food/Farmers_Lunch.png"));
        foodTextures.put(FoodType.SURVIVAL_BURGER, findTexture("food/Survival_Burger.png"));
        foodTextures.put(FoodType.DISH_OF_THE_SEA, findTexture("food/Dish_O_The_Sea.png"));
        foodTextures.put(FoodType.SEAFOAM_PUDDING, findTexture("food/Seaform_Pudding.png"));
        foodTextures.put(FoodType.MINERS_TREAT, findTexture("food/Miners_Treat.png"));

        // Crops and forageables
        foodTextures.put(FoodType.CARROT, findTexture("food/Carrot.png"));
        foodTextures.put(FoodType.CAULIFLOWER, findTexture("food/Cauliflower.png"));
        foodTextures.put(FoodType.GARLIC, findTexture("food/Garlic.png"));
        foodTextures.put(FoodType.GREEN_BEAN, findTexture("food/Green_Bean.png"));
        foodTextures.put(FoodType.KALE, findTexture("food/Kale.png"));
        foodTextures.put(FoodType.PARSNIP, findTexture("food/Parsnip.png"));
        foodTextures.put(FoodType.POTATO, findTexture("food/Potato.png"));
        foodTextures.put(FoodType.RHUBARB, findTexture("food/Rhubarb.png"));
        foodTextures.put(FoodType.STRAWBERRY, findTexture("food/Strawberry.png"));
        foodTextures.put(FoodType.BLUEBERRY, findTexture("food/Blueberry.png"));
        foodTextures.put(FoodType.CORN, findTexture("food/Corn.png"));
        foodTextures.put(FoodType.HOPS, findTexture("food/Hops.png"));
        foodTextures.put(FoodType.HOT_PEPPER, findTexture("food/Hot_Pepper.png"));
        foodTextures.put(FoodType.MELON, findTexture("food/Melon.png"));
        foodTextures.put(FoodType.RADISH, findTexture("food/Radish.png"));
        foodTextures.put(FoodType.RED_CABBAGE, findTexture("food/Red_Cabbage.png"));
        foodTextures.put(FoodType.STARFRUIT, findTexture("food/Starfruit.png"));
        foodTextures.put(FoodType.SUMMER_SQUASH, findTexture("food/Summer_Squash.png"));
        foodTextures.put(FoodType.TOMATO, findTexture("food/Tomato.png"));
        foodTextures.put(FoodType.AMARANTH, findTexture("food/Amaranth.png"));
        foodTextures.put(FoodType.ARTICHOKE, findTexture("food/Artichoke.png"));
        foodTextures.put(FoodType.BEET, findTexture("food/Beet.png"));
        foodTextures.put(FoodType.BOK_CHOY, findTexture("food/Bok_Choy.png"));
        foodTextures.put(FoodType.BROCCOLI, findTexture("food/Broccoli.png"));
        foodTextures.put(FoodType.CRANBERRIES, findTexture("food/Cranberries.png"));
        foodTextures.put(FoodType.EGGPLANT, findTexture("food/Eggplant.png"));
        foodTextures.put(FoodType.GRAPE, findTexture("food/Grape.png"));
        foodTextures.put(FoodType.PUMPKIN, findTexture("food/Pumpkin.png"));
        foodTextures.put(FoodType.YAM, findTexture("food/Yam.png"));
        foodTextures.put(FoodType.SWEET_GEM_BERRY, findTexture("food/Sweet_Gem_Berry.png"));
        foodTextures.put(FoodType.POWDER_MELON, findTexture("food/Powder_Melon.png"));
        foodTextures.put(FoodType.ANCIENT_FRUIT, findTexture("food/Ancient_Fruit.png"));

        // Forageables
        foodTextures.put(FoodType.DAFFODIL, findTexture("food/Daffodil.png"));
        foodTextures.put(FoodType.DANDELION, findTexture("food/Dandelion.png"));
        foodTextures.put(FoodType.LEEK, findTexture("food/Leek.png"));
        foodTextures.put(FoodType.SALMON_BERRY, findTexture("food/Salmon_Berry.png"));
        foodTextures.put(FoodType.SPRING_ONION, findTexture("food/Spring_Onion.png"));
        foodTextures.put(FoodType.WILD_HORSERADISH, findTexture("food/Wild_Horseradish.png"));
        foodTextures.put(FoodType.FIDDLE_HEAD_FERN, findTexture("food/Fiddle_Head_Fern.png"));
        foodTextures.put(FoodType.SPICE_BERRY, findTexture("food/Spice_Berry.png"));
        foodTextures.put(FoodType.SWEET_PEA, findTexture("food/Sweet_Pea.png"));
        foodTextures.put(FoodType.BLACKBERRY, findTexture("food/Blackberry.png"));
        foodTextures.put(FoodType.HAZELNUT, findTexture("food/Hazelnut.png"));
        foodTextures.put(FoodType.WILD_PLUM, findTexture("food/Wild_Plum.png"));
        foodTextures.put(FoodType.CROCUS, findTexture("food/Crocus.png"));
        foodTextures.put(FoodType.CRYSTAL_FRUIT, findTexture("food/Crystal_Fruit.png"));
        foodTextures.put(FoodType.HOLLY, findTexture("food/Holly.png"));
        foodTextures.put(FoodType.SNOW_YAM, findTexture("food/Snow_Yam.png"));
        foodTextures.put(FoodType.WINTER_ROOT, findTexture("food/Winter_Root.png"));

        // Processed goods
        foodTextures.put(FoodType.BEER, findTexture("food/Beer.png"));
        foodTextures.put(FoodType.COFFEE, findTexture("food/Coffee.png"));
        foodTextures.put(FoodType.JOJA_COLA, findTexture("food/Joja_Cola.png"));
        foodTextures.put(FoodType.SUGAR, findTexture("food/Sugar.png"));
        foodTextures.put(FoodType.WHEAT_FLOUR, findTexture("food/Wheat_Flour.png"));
        foodTextures.put(FoodType.RICE, findTexture("food/Rice.png"));
        foodTextures.put(FoodType.OIL, findTexture("food/Oil.png"));
        foodTextures.put(FoodType.VINEGAR, findTexture("food/Vinegar.png"));
        foodTextures.put(FoodType.TROUT_SOUP, findTexture("food/Trout_Soup.png"));
        foodTextures.put(FoodType.HONEY, findTexture("food/Honey.png"));
        foodTextures.put(FoodType.MAYONNAISE, findTexture("food/Mayonnaise.png"));
        foodTextures.put(FoodType.DUCK_MAYONNAISE, findTexture("food/Duck_Mayonnaise.png"));
        foodTextures.put(FoodType.DINOSAUR_MAYONNAISE, findTexture("food/Dinosaur_Mayonnaise.png"));
        foodTextures.put(FoodType.TRUFFLE_OIL, findTexture("food/Truffle_Oil.png"));
        foodTextures.put(FoodType.CHEESE, findTexture("food/Cheese.png"));
        foodTextures.put(FoodType.LARGE_CHEESE, findTexture("food/Large_Cheese.png"));
        foodTextures.put(FoodType.GOAT_CHEESE, findTexture("food/Goat_Cheese.png"));
        foodTextures.put(FoodType.MEAD, findTexture("food/Mead.png"));
        foodTextures.put(FoodType.PALE_ALE, findTexture("food/Pale_Ale.png"));
        foodTextures.put(FoodType.RAISINS, findTexture("food/Raisins.png"));
        foodTextures.put(FoodType.WINE, findTexture("food/Wine.png"));
        foodTextures.put(FoodType.JUICE, findTexture("food/Juice.png"));
        foodTextures.put(FoodType.DRIED_MUSHROOMS, findTexture("food/Dried_Mushrooms.png"));
        foodTextures.put(FoodType.DRIED_FRUIT, findTexture("food/Dried_Fruit.png"));
        foodTextures.put(FoodType.PICKLES, findTexture("food/Pickles.png"));
        foodTextures.put(FoodType.JELLY, findTexture("food/Jelly.png"));
        foodTextures.put(FoodType.SMOKED_FISH, findTexture("food/Smoked_Fish.png"));
    }

    private void loadMiscTextures() {
        miscTextures = new HashMap<>();

        miscTextures.put(MiscType.FIBER, findTexture("miscs/Fiber.png"));
        miscTextures.put(MiscType.BASIC_FERTILIZER, findTexture("miscs/Basic_Fertilizer.png"));
        miscTextures.put(MiscType.QUALITY_FERTILIZER, findTexture("miscs/Quality_Fertilizer.png"));
        miscTextures.put(MiscType.SPEED_GRO, findTexture("miscs/Speed_Gro.png"));
        miscTextures.put(MiscType.DELUXE_SPEED_GRO, findTexture("miscs/Deluxe_Speed_Gro.png"));
        miscTextures.put(MiscType.EGG, findTexture("miscs/Egg.png"));
        miscTextures.put(MiscType.LARGE_EGG, findTexture("miscs/Big_Egg.png"));
        miscTextures.put(MiscType.DUCK_EGG, findTexture("miscs/Duck_Egg.png"));
        miscTextures.put(MiscType.DUCK_FEATHER, findTexture("miscs/Duck_Feather.png"));
        miscTextures.put(MiscType.WOOL, findTexture("miscs/Wool.png"));
        miscTextures.put(MiscType.RABBITS_FOOT, findTexture("miscs/Rabbits_Foot.png"));
        miscTextures.put(MiscType.DINOSAUR_EGG, findTexture("miscs/Dinosaur.png"));
        miscTextures.put(MiscType.MILK, findTexture("miscs/Milk.png"));
        miscTextures.put(MiscType.LARGE_MILK, findTexture("miscs/Large_Milk.png"));
        miscTextures.put(MiscType.GOAT_MILK, findTexture("miscs/Goat_Milk.png"));
        miscTextures.put(MiscType.LARGE_GOAT_MILK, findTexture("miscs/Large_Goat_Milk.png"));
        miscTextures.put(MiscType.TRUFFLE, findTexture("miscs/Truffle.png"));
        miscTextures.put(MiscType.HAY, findTexture("miscs/Hay.png"));
        miscTextures.put(MiscType.COPPER_BAR, findTexture("miscs/Copper_Bar.png"));
        miscTextures.put(MiscType.IRON_BAR, findTexture("miscs/Iron_Bar.png"));
        miscTextures.put(MiscType.GOLD_BAR, findTexture("miscs/Gold_Bar.png"));
        miscTextures.put(MiscType.IRIDIUM_BAR, findTexture("miscs/Iridium_Bar.png"));
        miscTextures.put(MiscType.BOUQUET, findTexture("miscs/Bouquet.png"));
        miscTextures.put(MiscType.WEDDING_RING, findTexture("miscs/Wedding_Ring.png"));
        miscTextures.put(MiscType.BASIC_RETAINING_SOIL, findTexture("miscs/Basic_Retaining_Soil.png"));
        miscTextures.put(MiscType.QUALITY_RETAINING_SOIL, findTexture("miscs/Quality_Retaining_Soil.png"));
        miscTextures.put(MiscType.DELUXE_RETAINING_SOIL, findTexture("miscs/Deluxe_Retaining_Soil.png"));
        miscTextures.put(MiscType.CLOTH, findTexture("miscs/Cloth.png"));
        miscTextures.put(MiscType.CHERRY_BOMB, findTexture("miscs/Cherry_Bomb.png"));
        miscTextures.put(MiscType.BOMB, findTexture("miscs/Bomb.png"));
        miscTextures.put(MiscType.MEGA_BOMB, findTexture("miscs/Mega_Bomb.png"));
        miscTextures.put(MiscType.SPRINKLER, findTexture("miscs/Sprinkler.png"));
        miscTextures.put(MiscType.QUALITY_SPRINKLER, findTexture("miscs/Quality_Sprinkler.png"));
        miscTextures.put(MiscType.IRIDIUM_SPRINKLER, findTexture("miscs/Iridium_Sprinkler.png"));
        miscTextures.put(MiscType.CHARCOAL_KLIN, findTexture("miscs/CharcoalKlin.png"));
        miscTextures.put(MiscType.FURNACE, findTexture("miscs/Furnace.png"));
        miscTextures.put(MiscType.SCARE_CROW, findTexture("miscs/ScareCrow.png"));
        miscTextures.put(MiscType.DELUXE_SCARE_CROW, findTexture("miscs/Deluxe_Scarecrow.png"));
        miscTextures.put(MiscType.BEE_HOUSE, findTexture("miscs/BeeHouse.png"));
        miscTextures.put(MiscType.CHEESE_PRESS, findTexture("miscs/Cheese_Press.png"));
        miscTextures.put(MiscType.KEG, findTexture("miscs/Keg.png"));
        miscTextures.put(MiscType.LOOM, findTexture("miscs/Loom.png"));
        miscTextures.put(MiscType.MAYONNAISE_MACHINE, findTexture("miscs/Mayonnaise_Machine.png"));
        miscTextures.put(MiscType.OIL_MAKER, findTexture("miscs/Oil_Maker.png"));
        miscTextures.put(MiscType.PRESERVES_JAR, findTexture("miscs/Preserves_Jar.png"));
        miscTextures.put(MiscType.DEHYDRATOR, findTexture("miscs/Dehydrator.png"));
        miscTextures.put(MiscType.FISH_SMOKER, findTexture("miscs/Fish_Smoker.png"));
    }

    private void loadTreeSeedTextures() {
        treeSeedTextures = new HashMap<>();

        treeSeedTextures.put(TreeSeedType.MUSHROOM_TREE_SEEDS, findTexture("foraging/minerals/stone.png"));
        treeSeedTextures.put(TreeSeedType.MYSTIC_TREE_SEED, findTexture("foraging/minerals/wood.png"));
//        mineralTextures.put(ForagingMineralType.QUARTZ, loadTexture("foraging/minerals/quartz.png"));
//        mineralTextures.put(ForagingMineralType.EARTH_CRYSTAL, loadTexture("foraging/minerals/earth_crystal.png"));
//        mineralTextures.put(ForagingMineralType.FROZEN_TEAR, loadTexture("foraging/minerals/frozen_tear.png"));
        // Add all other mineral types...
    }




    public Texture getToolTexture(ToolType type) {
        return toolTextures.getOrDefault(type, createBlankTexture(32, 32));
    }

    public Texture getItemTexture(Item item) {
        if (item instanceof Tool) {
            return getToolTexture(((Tool) item).getType());
        }
        if (item instanceof Mineral) {
            return getMineralTexture(((Mineral) item).getType());
        }
        if (item instanceof ForagingCrop) {
            return getForagingCropTexture(((ForagingCrop) item).getType());
        }
        if (item instanceof Crop) {
            return getCropTexture(((Crop) item).getType());
        }
        if (item instanceof CropSeed) {
            return getCropSeedTexture(((CropSeed) item).getType());
        }
        if (item instanceof Fish) {
            return getFishTexture(((Fish) item).getType());
        }
        if (item instanceof Food) {
            return getFoodTexture(((Food) item).getType());
        }
        if (item instanceof Misc) {
            return getMiscTexture(((Misc) item).getType());
        }
        if (item instanceof TreeSeed) {
            return getTreeSeedTexture(((TreeSeed) item).getType());
        }
        // Add other item types here as needed
        return createBlankTexture(32, 32);
    }

    public Texture getTreeTexture(ForagingTreeType type) {
        Texture tex = foragingTreeTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 64); // Trees are taller
    }

    public Texture getForagingCropTexture(ForagingCropType type) {
        Texture tex = foragingCropTexture.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getCropSeedTexture(CropSeedType type) {
        Texture tex = cropSeedTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getCropTexture(CropType type) {
        Texture tex = cropTexture.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getFishTexture(FishType type) {
        Texture tex = fishTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getFoodTexture(FoodType type) {
        Texture tex = foodTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getMiscTexture(MiscType type) {
        Texture tex = miscTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getTreeSeedTexture(TreeSeedType type) {
        Texture tex = treeSeedTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getMineralTexture(ForagingMineralType type) {
        Texture tex = mineralTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getTrashcanTexture(TrashcanType type) {
        Texture tex = trashcanTexture.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getAnimalTextures(AnimalType type) {
        Texture tex = animalTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    @Override
    public void dispose() {
        // Dispose all textures
        foragingTreeTextures.values().forEach(Texture::dispose);
        foragingCropTexture.values().forEach(Texture::dispose);
        mineralTextures.values().forEach(Texture::dispose);
        cropTexture.values().forEach(Texture::dispose);
        foodTextures.values().forEach(Texture::dispose);
        fishTextures.values().forEach(Texture::dispose);
        miscTextures.values().forEach(Texture::dispose);
        cropSeedTextures.values().forEach(Texture::dispose);
        treeSeedTextures.values().forEach(Texture::dispose);
        toolTextures.values().forEach(Texture::dispose);
    }
}
