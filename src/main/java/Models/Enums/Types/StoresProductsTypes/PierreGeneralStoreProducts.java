package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.CropType;

public enum PierreGeneralStoreProducts implements StoreProducts{

    DEHYDRATOR("Dehydrator", "A recipe to make Dehydrator", null, 10000, 10000, 1, Season.values()),
    GRASS_STARTER_RECIPE("Grass Starter Recipe", "A recipe to make Grass Starter", null, 1000, 1000, 1, Season.values()),
    RICE_P("Pierre Rice", "A basic grain often served under vegetables.", FoodType.RICE, 200, 200, Double.POSITIVE_INFINITY, Season.values()),
    WHEAT_FLOUR_P("Pierre Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", FoodType.WHEAT_FLOUR, 100, 100, Double.POSITIVE_INFINITY, Season.values()),
    BOUQUET("Bouquet", "A gift that shows your romantic interest.\n(Unlocked after reaching level 2 friendship with a player)", ElseType.BOUQUET, 1000, 1000, 2, Season.values()),
    WEDDING_RING("Wedding Ring", "It's used to ask for another farmer's hand in marriage.\n(Unlocked after reaching level 3 friendship with a player)", ElseType.WEDDING_RING, 10000, 10000, 2, Season.values()),
    SUGAR_P("Pierre Sugar", "Adds sweetness to pastries and candies. Too much can be unhealthy.", FoodType.SUGAR, 100, 100, Double.POSITIVE_INFINITY, Season.values()),
    OIL("Oil", "All purpose cooking oil.", FoodType.OIL, 200, 200, Double.POSITIVE_INFINITY, Season.values()),
    VINEGAR("Vinegar", "An aged fermented liquid used in many cooking recipes.", FoodType.VINEGAR, 200, 200, Double.POSITIVE_INFINITY, Season.values()),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", "This soil has a 100% chance of staying watered overnight. Mix into tilled soil.", ElseType.DELUXE_RETAINING_SOIL, 150, 150, Double.POSITIVE_INFINITY, Season.values()),
    GRASS_STARTER_P("Pierre Grass Starter", "Place this on your farm to start a new patch of grass.", ElseType.GRASS_STARTER, 100, 100, Double.POSITIVE_INFINITY, Season.values()),
    SPEED_GRO("Speed-Gro", "Makes the plants grow 1 day earlier.", ElseType.SPEED_GRO, 100, 100, Double.POSITIVE_INFINITY, Season.values()),
    APPLE_SAPLING("Apple Sapling", "Takes 28 days to produce a mature Apple tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.APPLE_SAPLING, 4000, 4000, Double.POSITIVE_INFINITY, Season.values()),
    APRICOT_SAPLING("Apricot Sapling", "Takes 28 days to produce a mature Apricot tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.APRICOT_SAPLING, 2000, 2000, Double.POSITIVE_INFINITY, Season.values()),
    CHERRY_SAPLING("Cherry Sapling", "Takes 28 days to produce a mature Cherry tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.CHERRY_SAPLING, 3400, 3400, Double.POSITIVE_INFINITY, Season.values()),
    ORANGE_SAPLING("Orange Sapling", "Takes 28 days to produce a mature Orange tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.ORANGE_SAPLING, 4000, 4000, Double.POSITIVE_INFINITY, Season.values()),
    PEACH_SAPLING("Peach Sapling", "Takes 28 days to produce a mature Peach tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.PEACH_SAPLING, 6000, 6000, Double.POSITIVE_INFINITY, Season.values()),
    POMEGRANATE_SAPLING("Pomegranate Sapling", "Takes 28 days to produce a mature Pomegranate tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty.", ForagingTreeSeedsType.POMEGRANATE_SAPLING, 6000, 6000, Double.POSITIVE_INFINITY, Season.values()),
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "This soil has a chance of staying watered overnight. Mix into tilled soil.", ElseType.BASIC_RETAINING_SOIL, 100, 100, Double.POSITIVE_INFINITY, Season.values()),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "This soil has a good chance of staying watered overnight. Mix into tilled soil.", ElseType.QUALITY_RETAINING_SOIL, 150, 150, Double.POSITIVE_INFINITY, Season.values()),
    PARSNIP_SEEDS_P("Pierre Parsnip Seeds", "Plant these in the spring. Takes 4 days to mature.", CropType.PARSNIP, 20, 30, 5, new Season[]{Season.SPRING}),
    BEAN_STARTER_P("Pierre Bean Starter", "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis.", CropType.GREEN_BEAN, 60, 90, 5, new Season[]{Season.SPRING}),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant these in the spring. Takes 12 days to produce a large cauliflower.", CropType.CAULIFLOWER, 80, 120, 5, new Season[]{Season.SPRING}),
    POTATO_SEEDS("Potato Seeds", "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.", CropType.POTATO, 50, 75, 5, new Season[]{Season.SPRING}),
    TULIP_BULB_P("Pierre Tulip Bulb", "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.", CropType.TULIP, 20, 30, 5, new Season[]{Season.SPRING}),
    KALE_SEEDS_P("Pierre Kale Seeds", "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.", CropType.KALE, 70, 105, 5, new Season[]{Season.SPRING}),
    JAZZ_SEEDS_P("Pierre Jazz Seeds", "Plant in spring. Takes 7 days to produce a blue puffball flower.", CropType.BLUE_JAZZ, 30, 45, 5, new Season[]{Season.SPRING}),
    GARLIC_SEEDS("Garlic Seeds", "Plant these in the spring. Takes 4 days to mature.", CropType.GARLIC, 40, 60, 5, new Season[]{Season.SPRING}),
    RICE_SHOOT("Rice Shoot", "Plant these in the spring. Takes 8 days to mature. Grows faster if planted near a body of water.\nHarvest with the scythe.", CropType.UNMILLED_RICE, 40, 60, 5, new Season[]{Season.SPRING}),
    MELON_SEEDS_P("Pierre Melon Seeds", "Plant these in the summer. Takes 12 days to mature.", CropType.MELON, 80, 120, 5, new Season[]{Season.SUMMER}),
    TOMATO_SEEDS_P("Pierre Tomato Seeds", "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.", CropType.TOMATO, 50, 75, 5, new Season[]{Season.SUMMER}),
    BLUEBERRY_SEEDS("Blueberry Seeds", "Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest.", CropType.BLUEBERRY, 80, 120, 5, new Season[]{Season.SUMMER}),
    PEPPER_SEEDS_P("Pierre Pepper Seeds", "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.", CropType.HOT_PEPPER, 40, 60, 5, new Season[]{Season.SUMMER}),
    WHEAT_SEEDS_P("Pierre Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.", CropType.WHEAT, 10, 15, 5, new Season[]{Season.SUMMER, Season.FALL}),
    RADISH_SEEDS_P("Pierre Radish Seeds", "Plant these in the summer. Takes 6 days to mature.", CropType.RADISH, 40, 60, 5, new Season[]{Season.SUMMER}),
    POPPY_SEEDS_P("Pierre Poppy Seeds", "Plant in summer. Produces a bright red flower in 7 days.", CropType.POPPY, 100, 150, 5, new Season[]{Season.SUMMER}),
    SPANGLE_SEEDS_P("Pierre Spangle Seeds", "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors.", CropType.SUMMER_SPANGLE, 50, 75, 5, new Season[]{Season.SUMMER}),
    HOPS_STARTER_P("Pierre Hops Starter", "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.", CropType.HOPS, 60, 90, 5, new Season[]{Season.SUMMER}),
    CORN_SEEDS_P("Pierre Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", CropType.CORN, 150, 225, 5, new Season[]{Season.SUMMER, Season.FALL}),
    SUNFLOWER_SEEDS_P("Pierre Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", CropType.SUNFLOWER, 200, 300, 5, new Season[]{Season.SUMMER, Season.FALL}),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", "Plant these in the summer. Takes 9 days to mature.", CropType.RED_CABBAGE, 100, 150, 5, new Season[]{Season.SUMMER}),
    EGGPLANT_SEEDS_P("Pierre Eggplant Seeds", "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.", CropType.EGGPLANT, 20, 30, 5, new Season[]{Season.FALL}),
    PUMPKIN_SEEDS_P("Pierre Pumpkin Seeds", "Plant these in the fall. Takes 13 days to mature.", CropType.PUMPKIN, 100, 150, 5, new Season[]{Season.FALL}),
    BOK_CHOY_SEEDS_P("Pierre Bok Choy Seeds", "Plant these in the fall. Takes 4 days to mature.", CropType.BOK_CHOY, 50, 75, 5, new Season[]{Season.FALL}),
    YAM_SEEDS_P("Pierre Yam Seeds", "Plant these in the fall. Takes 10 days to mature.", CropType.YAM, 60, 90, 5, new Season[]{Season.FALL}),
    CRANBERRY_SEEDS_P("Pierre Cranberry Seeds", "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.", CropType.CRANBERRIES, 240, 360, 5, new Season[]{Season.FALL}),
    FAIRY_SEEDS_P("Pierre Fairy Seeds", "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors.", CropType.FAIRY_ROSE, 200, 300, 5, new Season[]{Season.FALL}),
    AMARANTH_SEEDS_P("Pierre Amaranth Seeds", "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.", CropType.AMARANTH, 70, 105, 5, new Season[]{Season.FALL}),
    GRAPE_STARTER_P("Pierre Grape Starter", "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis.", CropType.GRAPE, 60, 90, 5, new Season[]{Season.FALL}),
    ARTICHOKE_SEEDS("Artichoke Seeds", "Plant these in the fall. Takes 8 days to mature.", CropType.ARTICHOKE, 30, 45, 5, new Season[]{Season.FALL}),
    LARGE_PACK("Large Pack", "Unlocks the 2nd row of inventory (12 more slots, total 24).", null , 2000,2000,1 , Season.values()),
    DELUXE_PACK("Deluxe Pack", "Unlocks the 3rd row of inventory (infinite slots).", null , 10000,10000,1 , Season.values());

    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;

    PierreGeneralStoreProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.outOfSeasonPrice = outOfSeasonPrice;
        this.dailyLimit = dailyLimit;
        this.seasons = seasons;
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public double getOutOfSeasonPrice() {
        return outOfSeasonPrice;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
