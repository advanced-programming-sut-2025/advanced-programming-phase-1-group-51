package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;

public enum StarDropSaloonProducts implements StoreProducts{
    HASHBROWNS("Hashbrowns", "A recipe to make Hashbrowns", null, 50, 50, 1, Season.values()),
    OMELET("Omelette", "A recipe to make Omelet", null, 100, 100, 1, Season.values()),
    PANCAKES("Pancakes", "A recipe to make Pancakes", null, 100, 100, 1, Season.values()),
    BREAD_RECIPE("Bread Recipe", "A recipe to make Bread", null, 100, 100, 1, Season.values()),
    TORTILLA("Tortilla", "A recipe to make Tortilla", null, 100, 100, 1, Season.values()),
    PIZZA_RECIPE("Pizza Recipe", "A recipe to make Pizza", null, 150, 150, 1, Season.values()),
    MAKI_ROLL("Maki Roll", "A recipe to make Maki Roll", null, 300, 300, 1, Season.values()),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", "A recipe to make Triple Shot Espresso", null, 5000, 5000, 1, Season.values()),
    COOKIE("Cookie", "A recipe to make Cookie", null, 300, 300, 1, Season.values()),
    BEER("Beer", "Drink in moderation.", FoodType.BEER, 400, 400, Double.POSITIVE_INFINITY, Season.values()),
    SALAD("Salad", "A healthy garden salad.", FoodType.SALAD, 220, 220, Double.POSITIVE_INFINITY, Season.values()),
    BREAD("Bread", "A crusty baguette.", FoodType.BREAD, 120, 120, Double.POSITIVE_INFINITY, Season.values()),
    SPAGHETTI("Spaghetti", "An old favorite.", FoodType.SPAGHETTI, 240, 240, Double.POSITIVE_INFINITY, Season.values()),
    PIZZA("Pizza", "It's popular for all the right reasons.", FoodType.PIZZA, 600, 600, Double.POSITIVE_INFINITY, Season.values()),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", FoodType.COFFEE, 300, 300, Double.POSITIVE_INFINITY, Season.values()),
    WOOD_S("Stardrop Wood", "A sturdy, yet flexible plant material with a wide variety of uses.", ElseType.WOOD, 10, 10, Double.POSITIVE_INFINITY, Season.values()),
    STONE_S("Stardrop Stone", "A common material with many uses in crafting and building.", ForagingMineralType.STONE, 20, 20, Double.POSITIVE_INFINITY, Season.values());

    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;

    StarDropSaloonProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
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
