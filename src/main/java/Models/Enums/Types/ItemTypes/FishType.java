package Models.Enums.Types.ItemTypes;

import Models.Enums.Others.Quality;
import Models.Enums.Others.Season;
import Models.Loot;

public enum FishType implements ItemType{

    SALMON("Salmon", 75, Season.FALL, false),
    SARDINE("Sardine", 40, Season.FALL, false),
    SHAD("Shad", 60, Season.FALL, false),
    BLUE_DISCUS("Blue Discus", 120, Season.FALL, false),
    MIDNIGHT_CARP("Midnight Carp", 150, Season.WINTER, false),
    SQUID("Squid", 80, Season.WINTER, false),
    TUNA("Tuna", 100, Season.WINTER, false),
    PERCH("Perch",55, Season.WINTER, false),
    FLOUNDER("Flounder", 100, Season.SPRING, false),
    LIONFISH("Lionfish", 100, Season.SPRING, false),
    HERRING("Herring", 30, Season.SPRING, false),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, false),
    TILAPIA("Tilapia", 75, Season.SUMMER, false),
    DORADO("Dorado", 100, Season.SUMMER, false),
    SUNFISH("Sunfish", 30, Season.SUMMER, false),
    RAINBOW_TROUT("Rainbow Trout", 65, Season.SUMMER, false),
    LEGEND("Legend", 5000, Season.SPRING, true),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, true),
    ANGLER("Angler",900, Season.FALL, true),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, true);

    public final String name;
    public final int price;
    public final Season season;
    public final boolean isLegendary;

    FishType(String name, int price, Season season, boolean isLegendary) {
        this.name = name;
        this.price = price;
        this.season = season;
        this.isLegendary = isLegendary;
    }

    public static FishType findFishByName(String name) {
        FishType[] values = FishType.values();
        for (FishType value : values) {
            if (value.name.equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }


    @Override
    public Loot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

    public String getName(){
        return name;
    }
}
