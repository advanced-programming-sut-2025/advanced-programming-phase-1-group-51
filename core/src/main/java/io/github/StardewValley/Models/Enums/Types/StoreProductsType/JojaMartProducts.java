package io.github.StardewValley.Models.Enums.Types.StoreProductsType;

import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FoodType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.CropSeedType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;

public enum JojaMartProducts implements StoreProducts{
    JOJA_COLA("Joja Cola", "The flagship product of Joja corporation.", FoodType.JOJA_COLA, 75, 75, Double.POSITIVE_INFINITY, Season.values()),
    ANCIENT_SEED("Ancient Seed", "Could this still grow?", CropSeedType.ANCIENT_SEEDS, 500, 500, 1, Season.values()),
    GRASS_STARTER("Grass Starter", "Place this on your farm to start a new patch of grass.", CropSeedType.GRASS_STARTER, 125, 125, Double.POSITIVE_INFINITY, Season.values()),
    SUGAR("Sugar", "Adds sweetness to pastries and candies. Too much can be unhealthy.", FoodType.SUGAR, 125, 125, Double.POSITIVE_INFINITY, Season.values()),
    WHEAT_FLOUR("Wheat Flour", "A common cooking ingredient made from crushed wheat seeds.", FoodType.WHEAT_FLOUR, 125, 125, Double.POSITIVE_INFINITY, Season.values()),
    RICE("Rice", "A basic grain often served under vegetables.", FoodType.RICE, 250, 250, Double.POSITIVE_INFINITY, Season.values()),
    PARSNIP_SEEDS("Parsnip Seeds", "Plant these in the spring. Takes 4 days to mature.", CropSeedType.PARSNIP_SEEDS, 25, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    BEAN_STARTER("Bean Starter", "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis.", CropSeedType.BEAN_STARTER, 75, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", "Plant these in the spring. Takes 12 days to produce a large cauliflower.", CropSeedType.CAULIFLOWER_SEEDS, 100, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    POTATO_SEEDS("Potato Seeds", "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.", CropSeedType.POTATO_SEEDS, 62, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    STRAWBERRY_SEEDS("Strawberry Seeds", "Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that.", CropSeedType.STRAWBERRY_SEEDS, 100, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    TULIP_BULB("Tulip Bulb", "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.", CropSeedType.TULIP_BULB, 25, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    KALE_SEEDS("Kale Seeds", "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.", CropSeedType.KALE_SEEDS, 87, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    COFFEE_BEANS("Coffee Beans", "Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day.", CropSeedType.COFFEE_BEAN_SEEDS, 200, Double.POSITIVE_INFINITY, 1, new Season[]{Season.SPRING, Season.SUMMER}),
    CARROT_SEEDS("Carrot Seeds", "Plant in the spring. Takes 3 days to grow.", CropSeedType.CARROT_SEEDS, 5, Double.POSITIVE_INFINITY, 10, new Season[]{Season.SPRING}),
    RHUBARB_SEEDS("Rhubarb Seeds", "Plant these in the spring. Takes 13 days to mature.", CropSeedType.RHUBARB_SEEDS, 100, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    JAZZ_SEEDS("Jazz Seeds", "Plant in spring. Takes 7 days to produce a blue puffball flower.", CropSeedType.JAZZ_SEEDS, 37, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SPRING}),
    TOMATO_SEEDS("Tomato Seeds", "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.", CropSeedType.TOMATO_SEEDS, 62, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    PEPPER_SEEDS("Pepper Seeds", "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.", CropSeedType.PEPPER_SEEDS, 50, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    WHEAT_SEEDS("Wheat Seeds", "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.", CropSeedType.WHEAT_SEEDS, 12, Double.POSITIVE_INFINITY, 10, new Season[]{Season.SUMMER, Season.FALL}),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", "Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest.", CropSeedType.SUMMER_SQUASH_SEEDS, 10, Double.POSITIVE_INFINITY, 10, new Season[]{Season.SUMMER}),
    RADISH_SEEDS("Radish Seeds", "Plant these in the summer. Takes 6 days to mature.", CropSeedType.RADISH_SEEDS, 50, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    MELON_SEEDS("Melon Seeds", "Plant these in the summer. Takes 12 days to mature.", CropSeedType.MELON_SEEDS, 100, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    HOPS_STARTER("Hops Starter", "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.", CropSeedType.HOPS_STARTER, 75, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    POPPY_SEEDS("Poppy Seeds", "Plant in summer. Produces a bright red flower in 7 days.", CropSeedType.POPPY_SEEDS, 125, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    SPANGLE_SEEDS("Spangle Seeds", "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors.", CropSeedType.SPANGLE_SEEDS, 62, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    STARFRUIT_SEEDS("Starfruit Seeds", "Plant these in the summer. Takes 13 days to mature.", CropSeedType.STARFRUIT_SEEDS, 400, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER}),
    SUNFLOWER_SEEDS("Sunflower Seeds", "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", CropSeedType.SUNFLOWER_SEEDS, 125, Double.POSITIVE_INFINITY, 5, new Season[]{Season.SUMMER, Season.FALL}),
    CORN_SEEDS("Corn Seeds", "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", CropSeedType.CORN_SEEDS, 187, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    EGGPLANT_SEEDS("Eggplant Seeds", "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.", CropSeedType.EGGPLANT_SEEDS, 25, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    PUMPKIN_SEEDS("Pumpkin Seeds", "Plant these in the fall. Takes 13 days to mature.", CropSeedType.PUMPKIN_SEEDS, 125, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    BROCCOLI_SEEDS("Broccoli Seeds", "Plant in the fall. Takes 8 days to mature, and continues to produce after first harvest.", CropSeedType.BROCCOLI_SEEDS, 15, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    AMARANTH_SEEDS("Amaranth Seeds", "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.", CropSeedType.AMARANTH_SEEDS, 87, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    GRAPE_STARTER("Grape Starter", "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis.", CropSeedType.GRAPE_STARTER, 75, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    BEET_SEEDS("Beet Seeds", "Plant these in the fall. Takes 6 days to mature.", CropSeedType.BEET_SEEDS, 20, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    YAM_SEEDS("Yam Seeds", "Plant these in the fall. Takes 10 days to mature.", CropSeedType.YAM_SEEDS, 75, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    BOK_CHOY_SEEDS("Bok Choy Seeds", "Plant these in the fall. Takes 4 days to mature.", CropSeedType.BOK_CHOY_SEEDS, 62, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    CRANBERRY_SEEDS("Cranberry Seeds", "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.", CropSeedType.CRANBERRY_SEEDS, 300, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    FAIRY_SEEDS("Fairy Seeds", "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors.", CropSeedType.FAIRY_SEEDS, 250, Double.POSITIVE_INFINITY, 5, new Season[]{Season.FALL}),
    RARE_SEED("Rare Seed", "Sow in fall. Takes all season to grow.", CropSeedType.SWEET_GEM_BERRY_SEEDS, 1000, Double.POSITIVE_INFINITY, 1, new Season[]{Season.FALL}),
    POWDERMELON_SEEDS("Powdermelon Seeds", "This special melon grows in the winter. Takes 7 days to grow.", CropSeedType.POWDERMELON_SEEDS, 20, Double.POSITIVE_INFINITY, 10, new Season[]{Season.WINTER});
    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;

    JojaMartProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
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
