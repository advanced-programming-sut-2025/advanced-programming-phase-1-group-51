package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Enums.Types.ItemTypes.ToolType;

public enum CarpenterShopProducts implements StoreProducts{

    WOOD("Wood", "A sturdy, yet flexible plant material with a wide variety of uses.", ElseType.WOOD, 10, 10, Double.POSITIVE_INFINITY, Season.values()),
    STONE("Stone", "A common material with many uses in crafting and building.", ForagingMineralType.STONE, 20, 20, Double.POSITIVE_INFINITY, Season.values()),
    BARN("Barn", "Houses 4 barn-dwelling animals.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    BIG_BARN("Big Barn", "Houses 8 barn-dwelling animals. Unlocks goats.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    DELUXE_BARN("Deluxe Barn", "Houses 12 barn-dwelling animals. Unlocks sheep and pigs.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    COOP("Coop", "Houses 4 coop-dwelling animals.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    BIG_COOP("Big Coop", "Houses 8 coop-dwelling animals. Unlocks ducks.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    DELUXE_COOP("Deluxe Coop", "Houses 12 coop-dwelling animals. Unlocks rabbits.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    WELL("Well", "Provides a place for you to refill your watering can.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    SHIPPING_BIN("Shipping Bin", "Items placed in it will be included in the nightly shipment.", ForagingMineralType.STONE, -1, -1, 1, Season.values()),
    ;
    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private final Season[] seasons;

    CarpenterShopProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] values) {
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
