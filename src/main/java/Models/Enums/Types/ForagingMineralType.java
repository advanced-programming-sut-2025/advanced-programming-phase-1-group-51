package Models.Enums.Types;

public enum MineralType {

    STONE("Stone",0),
    QUARTZ("Quartz",25),
    EARTH_CRYSTAL("Earth Crystal",50),
    FROZEN_TEAR("Frozen Tear",75),
    FIRE_QUARTZ("Fire Quartz",100),
    EMERALD("Emerald",250),
    AQUAMARINE("Aquamarine",180),
    RUBY("Ruby",250),
    AMETHYST("Amethyst",100),
    TOPAZ("Topaz",80),
    JADE("Jade",200),
    DIAMOND("Diamond",750),
    PRISMATIC_SHARD("Prismatic Shard",2000),
    COPPER("Copper",5),
    IRON("Iron",10),
    GOLD("Gold",25),
    IRIDIUM("Iridium",100),
    COAL("Coal",15);

    private final int sellPrice;
    public final String name;

    MineralType(String name ,int sellPrice) {
        this.sellPrice = sellPrice;
        this.name = name;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
