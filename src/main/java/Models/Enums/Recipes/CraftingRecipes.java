package Models.Enums.Recipes;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Items.Else;
import Models.Items.Mineral;
import Models.Items.TreeSeed;
import Models.Loot;

import java.util.Arrays;

public enum CraftingRecipes {

    CHERRY_BOMB("Cherry Bomb", "Destroys everything in a 3 tile radius.", 0, 1, 0, 0, 50,
            new Loot[]{new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COPPER_ORE), 4), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 1)},
            ElseType.CHERRY_BOMB),
    BOMB("Bomb", "Destroys everything in a 5 tile radius.", 0, 2, 0, 0, 50,
            new Loot[]{new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.IRON_ORE), 4), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 1)},
            ElseType.BOMB),
    MEGA_BOMB("Mega Bomb", "Destroys everything in a 7 tile radius.", 0, 3, 0, 0, 50,
            new Loot[]{new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.GOLD_ORE), 4), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 1)},
            ElseType.MEGA_BOMB),
    SPRINKLER("Sprinkler", "Waters 4 adjacent crops.", 1, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.COPPER_BAR), 1), new Loot(new Else(ElseType.IRON_BAR), 1)},
            ElseType.SPRINKLER),
    QUALITY_SPRINKLER("Quality Sprinkler", "Waters 8 adjacent crops.", 2, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.GOLD_BAR), 1), new Loot(new Else(ElseType.IRON_BAR), 1)},
            ElseType.QUALITY_SPRINKLER),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", "Waters 24 adjacent crops.", 3, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.GOLD_BAR), 1), new Loot(new Else(ElseType.IRIDIUM_BAR), 1)},
            ElseType.IRIDIUM_SPRINKLER),
    CHARCOAL_KLIN("Charcoal Klin", "Converts 10 wood to 1 coal.", 1, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.COPPER_BAR), 2), new Loot(new Else(ElseType.WOOD), 20)},
            ElseType.CHARCOAL_KLIN),
    FURNACE("Furnace", "Convert ores and coal into ingots.", 0, 0, 0, 0, 0,
            new Loot[]{new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.STONE), 25), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COPPER_ORE), 20)},
            ElseType.FURNACE),
    SCARE_CROW("Scare Crow", "Prevents crow attacks in an 8 tile radius.", 0, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.FIBER), 20), new Loot(new Else(ElseType.WOOD), 50), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 1)},
            ElseType.SCARE_CROW),
    DELUXE_SCARE_CROW("Deluxe Scarecrow", "Prevents crow attacks in a 12 tile radius.", 2, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.FIBER), 20), new Loot(new Else(ElseType.WOOD), 50), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 1), new Loot(new Else(ElseType.IRIDIUM_BAR), 1)},
            ElseType.DELUXE_SCARE_CROW),
    BEE_HOUSE("Bee House", "Produces honey.", 1, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.IRON_BAR), 1), new Loot(new Else(ElseType.WOOD), 40), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 8)},
            ElseType.BEE_HOUSE),
    CHEESE_PRESS("Cheese Press", "Produces cheese from milk.", 2, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.COPPER_BAR), 1), new Loot(new Else(ElseType.WOOD), 45), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.STONE), 45)},
            ElseType.CHEESE_PRESS),
    KEG("Keg", "Ferments fruits and vegetables into drinks.", 3, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.IRON_BAR), 1), new Loot(new Else(ElseType.COPPER_BAR), 1), new Loot(new Else(ElseType.WOOD), 30)},
            ElseType.KEG),
    LOOM("Loom", "Processes wool.", 3, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.FIBER), 30), new Loot(new Else(ElseType.WOOD), 60)},
            ElseType.LOOM),
    MAYONNAISE_MACHINE("Mayonnaise Machine", "Produces mayonnaise from eggs.", 0, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.COPPER_BAR), 1), new Loot(new Else(ElseType.WOOD), 15), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.STONE), 15)},
            ElseType.MAYONNAISE_MACHINE),
    OIL_MAKER("Oil Maker", "Produces oil from truffles.", 3, 0, 0, 0, 0,
            new Loot[]{new Loot(new Else(ElseType.IRON_BAR), 1), new Loot(new Else(ElseType.GOLD_BAR), 1), new Loot(new Else(ElseType.WOOD), 100)},
            ElseType.OIL_MAKER),
    PRESERVES_JAR("Preserves Jar", "Produces jam from fruits and vegetables.", 2, 0, 0, 0, 0,
            new Loot[]{new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 8), new Loot(new Else(ElseType.WOOD), 50), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.STONE), 40)},
            ElseType.PRESERVES_JAR),
    DEHYDRATOR("Dehydrator", "Dries fruits and mushrooms.", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0,
            new Loot[]{new Loot(new Else(ElseType.FIBER), 30), new Loot(new Else(ElseType.WOOD), 30), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.STONE), 20)},
            ElseType.DEHYDRATOR),
    FISH_SMOKER("Fish Smoker", "Smokes fish while preserving their quality.", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0,
            new Loot[]{new Loot(new Else(ElseType.IRON_BAR), 3), new Loot(new Else(ElseType.WOOD), 50), new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType.COAL), 10)},
            ElseType.FISH_SMOKER);
//    MYSTIC_TREE_SEED("Mystic tree seed", "Can be planted to grow into a mystic tree.", 0, 0, 4, 0, 100,
//            new Loot[]{new Loot(new TreeSeed(TreeType.ACORNS), 5), new Loot(new TreeSeed(TreeType.MAPLE_SEEDS), 5), new Loot(new TreeSeed(TreeType.PINE_CONES), 5), new Loot(new TreeSeed(TreeType.MAHOGANY_SEEDS), 5)},
//            TreeType.MYSTIC_TREE_SEED);
    public final String name;
    public final String description;
    public final int farmingLevel;
    public final int miningLevel;
    public final int foragingLevel;
    public final int sellingPrice;
    public final int fishingLevel;
    public final Loot[] ingredients;
    public final ItemType resultItemType;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(": ");
        stringBuilder.append(description).append("\n");
        stringBuilder.append("Farming level: ").append(farmingLevel).append("\n");
        stringBuilder.append("Mining level: ").append(miningLevel).append("\n");
        stringBuilder.append("Foraging level: ").append(foragingLevel).append("\n");
        stringBuilder.append("Fishing level: ").append(fishingLevel).append("\n");
        stringBuilder.append("Selling price: ").append(sellingPrice).append("\n");
        stringBuilder.append(Arrays.toString(ingredients)).append("\n");
        return stringBuilder.toString();
    }

    CraftingRecipes(String name, String description, int farmingLevel, int miningLevel, int foragingLevel
            , int fishingLevel, int sellingPrice, Loot[] ingredients, ItemType resultItemType) {
        this.name = name;
        this.description = description;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.sellingPrice = sellingPrice;
        this.fishingLevel = fishingLevel;
        this.ingredients = ingredients;
        this.resultItemType = resultItemType;
    }

    public static CraftingRecipes getCraftingRecipe(String name) {
        for (CraftingRecipes cr : CraftingRecipes.values()) {
            if (cr.name.equalsIgnoreCase(name)) {
                return cr;
            }
        }
        return null;
    }
}
