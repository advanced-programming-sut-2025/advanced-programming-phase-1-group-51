package Models.Enums.Types.StoresProductsTypes;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ItemTypes.ItemType;

public enum BlackSmithProducts {

    COPPER_ORE("Copper Ore" , "A common ore that can be smelted into bars.", ForagingMineralType.COPPER_ORE,75,75,Double.POSITIVE_INFINITY, Season.values()),
    IRON_ORE("Iron Ore","A fairly common ore that can be smelted into bars.",ForagingMineralType.IRON_ORE,150,150,Double.POSITIVE_INFINITY,Season.values()),
    COAL("Coal","A combustible rock that is useful for crafting and smelting.",ForagingMineralType.COAL, 150,150,Double.POSITIVE_INFINITY,Season.values()),
    GOLD_ORE("Gold Ore" , "A precious ore that can be smelted into bars." , ForagingMineralType.GOLD_ORE , 400,400,Double.POSITIVE_INFINITY,Season.values());

    private final String name;
    private final String description;
    private final ItemType itemType;
    private final int price;
    private final int outOfSeasonPrice;
    private final double dailyLimit;
    private Season[] seasons;
    BlackSmithProducts(String name,String description, ItemType itemType,int price, int outOfSeasonPrice, double dailyLimit , Season[] seasons) {
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
