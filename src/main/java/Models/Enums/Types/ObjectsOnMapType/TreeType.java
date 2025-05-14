package Models.Enums.Types.ObjectsOnMapType;

import Models.Enums.Others.Quality;
import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Loot;

import java.util.Arrays;

public enum TreeType implements ItemType{
    TREE_BARK("Tree Bark", "nothing", -1, ElseType.WOOD, -1, -1, false, -1, (Season[]) null),
    BURNT_TREE("Burnt Tree", "nothing", -1, ForagingMineralType.COAL, -1, -1, false, -1, (Season[]) null),
    NORMAL_TREE("Normal Tree", "nothing", -1, ElseType.WOOD, -1, -1, false, -1, (Season[]) null),
    // source
    APRICOT_TREE("Apricot Tree", "Apricot Sapling", 7, FoodType.APRICOT, 1, 59, true, 38, Season.SPRING),
    CHERRY_TREE("Cherry Tree", "Cherry Sapling", 7, FoodType.CHERRY, 1, 80, true, 38, Season.SPRING),
    BANANA_TREE("Banana Tree", "Banana Sapling", 7, FoodType.BANANA, 1, 150, true, 75, Season.SUMMER),
    MANGO_TREE("Mango Tree", "Mango Sapling", 7, FoodType.MANGO, 1, 130, true, 100, Season.SUMMER),
    ORANGE_TREE("Orange Tree", "Orange Sapling", 7, FoodType.ORANGE, 1, 100, true, 38, Season.SUMMER),
    PEACH_TREE("Peach Tree", "Peach Sapling", 7, FoodType.PEACH, 1, 140, true, 38, Season.SUMMER),
    APPLE_TREE("Apple Tree", "Apple Sapling", 7, FoodType.APPLE, 1, 100, true, 38, Season.FALL),
    POMEGRANATE_TREE("Pomegranate Tree", "Pomegranate Sapling", 7, FoodType.POMEGRANATE, 1, 100, true, 38, Season.FALL),
    OAK_TREE("Oak Tree", "Acorns", 7, FoodType.OAK_RESIN, 7, 150, false, 0, Season.values()),
    MAPLE_TREE("Maple Tree", "Maple Seeds", 7, FoodType.MAPLE_SYRUP, 9, 200, false, 0, Season.values()),
    PINE_TREE("Pine Tree", "Pine Cones", 7, FoodType.PINE_TAR, 5, 100, false, 0, Season.values()),
    MAHOGANY_TREE("Mahogany Tree", "Mahogany Seeds", 7, FoodType.SAP, 1, 2, true, -2, Season.values()),
    MUSHROOM_TREE("Mushroom Tree", "Mushroom Tree Seeds", 7, FoodType.COMMON_MUSHROOM, 1, 40, true, 38, Season.values()),
    MYSTIC_TREE("Mystic Tree", "Mystic Tree Seeds", 7, FoodType.MYSTIC_SYRUP, 7, 1000, true, 500, Season.values());

    final public String name;
    final public String source;
    final public int stageOneTime;
    final public int stageTwoTime;
    final public int stageThreeTime;
    final public int stageFourTime;
    final public ItemType fruitItem;
    final public int harvestCycleTime;
    final public int fruitSellPrice;
    final public boolean isFruitEdible;
    final public int fruitEnergy;
    final public Season[] seasonsOfGrowth;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append("\n");
        str.append("Source: ").append(source).append("\n");
        str.append("Stage One Time: ").append(stageOneTime).append("\n");
        str.append("Stage Two Time: ").append(stageTwoTime).append("\n");
        str.append("Stage Three Time: ").append(stageThreeTime).append("\n");
        str.append("Stage Four Time: ").append(stageFourTime).append("\n");
        str.append("Fruit Item: ").append(fruitItem.getName()).append("\n");
        str.append("Harvest Cycle Time: ").append(harvestCycleTime).append("\n");
        str.append("Fruit Sell Price: ").append(fruitSellPrice).append("\n");
        str.append("Is Fruit Edible: ").append(isFruitEdible).append("\n");
        str.append("Fruit Energy: ").append(fruitEnergy).append("\n");
        str.append("Seasons Of Growth: ").append(Arrays.toString(seasonsOfGrowth)).append("\n");
        return str.toString();
    }


    TreeType(String name, String source, int stagesTime, ItemType fruitItem, int harvestCycleTime, int fruitSellPrice
            , boolean isFruitEdible, int fruitEnergy, Season[] seasonsOfGrowth) {
        this.name = name;
        this.source = source;
        this.stageOneTime = stagesTime;
        this.stageTwoTime = stagesTime;
        this.stageThreeTime = stagesTime;
        this.stageFourTime = stagesTime;
        this.fruitItem = fruitItem;
        this.harvestCycleTime = harvestCycleTime;
        this.fruitSellPrice = fruitSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.seasonsOfGrowth = seasonsOfGrowth;
    }

    public static TreeType findTreeByName(String name) {
        for (TreeType tree : TreeType.values()) {
            if (tree.name.equalsIgnoreCase(name)) {
                return tree;
            }
        }
        return null;
    }
    public static TreeType findTreeBySeed(String seedType) {
        for (TreeType tree : TreeType.values()) {
            if (tree.source.equalsIgnoreCase(seedType)) {
                return tree;
            }
        }
        return null;
    }



    TreeType(String name, String source, int stagesTime, FoodType FoodType, int harvestCycleTime, int fruitSellPrice, boolean b, int fruitEnergy, Season season) {
        this.name = name;
        this.source = source;
        this.stageOneTime = stagesTime;
        this.stageTwoTime = stagesTime;
        this.stageThreeTime = stagesTime;
        this.stageFourTime = stagesTime;
        this.fruitItem = FoodType;
        this.fruitEnergy = fruitEnergy;
        this.harvestCycleTime = harvestCycleTime;
        this.fruitSellPrice = fruitSellPrice;
        this.isFruitEdible = b;
        this.seasonsOfGrowth = new Season[]{season};
    }



    @Override
    public Loot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

    public String getName(){
        return name;
    }




}
