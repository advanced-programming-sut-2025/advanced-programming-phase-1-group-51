package Models.Enums.Types.ItemTypes;


import Models.Enums.Others.Quality;
import Models.Loot;

public enum ForagingMineralType implements ItemType{

    STONE(2, "Stone"),
    QUARTZ(25, "Quartz"),
    EARTH_CRYSTAL(50, "Earth Crystal"),
    FROZEN_TEAR(75, "Frozen Tear"),
    FIRE_QUARTZ(100, "Fire Quartz"),
    EMERALD(250, "Emerald"),
    AQUAMARINE(180, "Aquamarine"),
    RUBY(250, "Ruby"),
    AMETHYST(100, "Amethyst"),
    TOPAZ(80, "Topaz"),
    JADE(200, "Jade"),
    DIAMOND(750, "Diamond"),
    PRISMATIC_SHARD(2000, "Prismatic Shard"),
    COPPER_ORE(5, "Copper Ore"),
    IRON_ORE(10, "Iron Ore"),
    GOLD_ORE(25, "Gold Ore"),
    IRIDIUM_ORE(100, "Iridium Ore"),
    COAL(15, "Coal");

    private final int sellPrice;
    public final String name;

    ForagingMineralType(int sellPrice, String name) {
        this.sellPrice = sellPrice;
        this.name = name;
    }

    public static int getPriceByName(String name) {
        for (ForagingMineralType type : ForagingMineralType.values()) {
            if (type.name.equals(name)) {
                return type.sellPrice;
            }
        }
        return 0;
    }


    public String getName(){
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }


    @Override
    public Loot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

}
