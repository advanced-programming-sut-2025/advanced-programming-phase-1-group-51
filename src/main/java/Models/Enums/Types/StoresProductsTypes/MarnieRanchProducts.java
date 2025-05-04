package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.ItemType;
import Models.Enums.Types.ItemTypes.ToolType;

public enum MarnieRanchProducts implements StoreProducts{

    HAY("Hay","Dried grass used as animal food.", ElseType.HAY,50,50,Double.POSITIVE_INFINITY, Season.values()),
    MILK_PAIL("Milk Pail","Gather milk from your animals.",ToolType.MILK_PAIL,1000,1000,Double.POSITIVE_INFINITY, Season.values()),
    SHEARS("Shears","Use this to collect wool from sheep.", ToolType.SHEAR,1000,1000,Double.POSITIVE_INFINITY, Season.values());

    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final int outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;
    MarnieRanchProducts(String name,String description, ItemType itemType,int price, int outOfSeasonPrice, double dailyLimit , Season[] seasons) {
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
