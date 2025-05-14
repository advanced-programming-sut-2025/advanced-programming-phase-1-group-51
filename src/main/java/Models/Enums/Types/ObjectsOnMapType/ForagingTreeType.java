package Models.Enums.Types.ObjectsOnMapType;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.FoodType;

public enum ForagingTreeType {

    TREE_BARK("Tree Bark", -1, null, -1, -1, false, -1,new Season[]{null}," "),
    BURNT_TREE("Burnt Tree", -1, null, -1, -1, false, -1, (Season[]) null," "),
    NORMAL_TREE("Normal Tree",-1, null, -1, -1, false, -1, (Season[]) null," "),
    APRICOT_TREE("Apricot Tree", 7, FoodType.APRICOT, 1, 59, true, 38,  new Season[]{Season.SPRING},"Apricot Sapling"),
    CHERRY_TREE("Cherry Tree", 7, FoodType.CHERRY, 1, 80, true, 38,  new Season[]{Season.SPRING},"Cherry Sapling"),
    BANANA_TREE("Banana Tree", 7, FoodType.BANANA, 1, 150, true, 75,  new Season[]{Season.SUMMER},"Banana Sapling"),
    MANGO_TREE("Mango Tree", 7, FoodType.MANGO, 1, 130, true, 100,  new Season[]{Season.SUMMER},"Mango Sapling"),
    ORANGE_TREE("Orange Tree", 7, FoodType.ORANGE, 1, 100, true, 38,  new Season[]{Season.SUMMER},"Orange Sapling"),
    PEACH_TREE("Peach Tree", 7, FoodType.PEACH, 1, 140, true, 38,  new Season[]{Season.SUMMER},"Peach Sapling"),
    APPLE_TREE("Apple Tree", 7, FoodType.APPLE, 1, 100, true, 38, new Season[]{Season.FALL},"Apple Sapling"),
    POMEGRANATE_TREE("Pomegranate Tree", 7, FoodType.POMEGRANATE,1, 100, true, 38, new Season[]{Season.FALL},"Pomegranate Sapling"),
    OAK_TREE("Oak Tree", 7, FoodType.OAK_RESIN, 7, 150, false, 0, Season.values(),"Acorns"),
    MAPLE_TREE("Maple Tree", 7, FoodType.MAPLE_SYRUP, 9, 200, false, 0, Season.values(),"Maple Seeds"),
    PINE_TREE("Pine Tree", 7, FoodType.PINE_TAR, 5, 100, false, 0, Season.values(),"Pine Cones"),
    MAHOGANY_TREE("Mahogany Tree", 7, FoodType.SAP, 1, 2, true, -2, Season.values(),"Mahogany seeds"),
    MUSHROOM_TREE("Mushroom Tree", 7, FoodType.COMMON_MUSHROOM, 1, 40, true, 38, Season.values(),"Mushroom Tree seeds"),
    MYSTIC_TREE("Mystic Tree", 7, FoodType.MYSTIC_SYRUP, 7, 1000, true, 500, Season.values(),"Mystic Tree Seeds");

    final public String name;
    final public int stageOneTime;
    final public int stageTwoTime;
    final public int stageThreeTime;
    final public int stageFourTime;
    final public FoodType fruitItem;
    final public int harvestCycleTime;
    final public int fruitSellPrice;
    final public boolean isFruitEdible;
    final public int fruitEnergy;
    final public Season[] seasons;
    final public String seed;


    ForagingTreeType(String name, int stagesTime, FoodType fruitItem, int harvestCycleTime, int fruitSellPrice
            , boolean isFruitEdible, int fruitEnergy, Season[] seasons, String seed) {
        this.name = name;
        this.stageOneTime = stagesTime;
        this.stageTwoTime = stagesTime;
        this.stageThreeTime = stagesTime;
        this.stageFourTime = stagesTime;
        this.fruitItem = fruitItem;
        this.harvestCycleTime = harvestCycleTime;
        this.fruitSellPrice = fruitSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.seasons = seasons;
        this.seed = seed;
    }

    public static ForagingTreeType findTreeBySeed(String seed) {
        for (ForagingTreeType tree : ForagingTreeType.values()) {
            if (tree.seed.equalsIgnoreCase(seed)) {
                return tree;
            }
        }
        return null;
    }

}
