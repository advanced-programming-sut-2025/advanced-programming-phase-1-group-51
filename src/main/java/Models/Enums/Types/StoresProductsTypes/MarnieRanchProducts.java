package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Enums.Types.ItemTypes.ToolType;

public enum MarnieRanchProducts implements StoreProducts{

    HAY("Hay", "Dried grass used as animal food.", ElseType.WOOD, 50, 50, Double.POSITIVE_INFINITY, Season.values()),
    MILK_PAIL("Milk Pail", "Gather milk from your animals.", ForagingMineralType.STONE, 1000, 1000, 1, Season.values()),
    SHEARS("Shears", "Use this to collect wool from sheep", ForagingMineralType.STONE, 1000, 1000, 1, Season.values()),
    CHICKEN("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    COW("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    GOAT("Goat", "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    DUCK("Duck", "Happy lay duck eggs every other day. Lives in the coop.", ForagingMineralType.STONE, 20, 20, 2, Season.values()),
    SHEEP("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    RABBIT("Rabbit", "Provides a place for you to refill your watering can.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    DINOSAUR("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    PIG("Pig", "These pigs are trained to find truffles! Lives in the barn.", ForagingMineralType.STONE, -1, -1, 2, Season.values()),
    ;
    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private final Season[] seasons;

    MarnieRanchProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] values) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.price = price;
        this.outOfSeasonPrice = outOfSeasonPrice;
        this.dailyLimit = dailyLimit;
        this.seasons = values;
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
