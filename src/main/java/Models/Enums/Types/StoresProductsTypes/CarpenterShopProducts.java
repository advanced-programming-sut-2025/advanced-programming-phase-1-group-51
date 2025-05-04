package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Enums.Types.ItemTypes.ToolType;

public enum CarpenterShopProducts implements StoreProducts{

    WOOD("Wood","A sturdy, yet flexible plant material with a wide variety of uses.",ElseType.WOOD,10,10,Double.POSITIVE_INFINITY, Season.values()),
    STONE("Stone","A common material with many uses in crafting and building.",ForagingMineralType.STONE,20,20,Double.POSITIVE_INFINITY, Season.values());



    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final int outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;
    CarpenterShopProducts(String name,String description, ItemType itemType,int price, int outOfSeasonPrice, double dailyLimit , Season[] seasons) {
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
