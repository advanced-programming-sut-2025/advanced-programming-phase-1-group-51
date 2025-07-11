package io.github.StardewValley.Models.Enums.Others;

public enum Quality {

    /// Only for a few tools, not items.
    DEFAULT("Default"),
    /// all below for fishing rod corresponds to : training - bamboo - fiberglass - iridium rods.
    COPPER("Copper"),
    SILVER("Silver"),
    GOLD("Gold"),
    IRIDIUM("Iridium");

    private final String name;
    private final int qualityLevel;

    /// returns ordinal value.
    public int getQualityLevel() {
        return qualityLevel;
    }

    Quality(String name) {
        this.name = name;
        this.qualityLevel = ordinal();
    }

    public static Quality getQualityByName(final String name) {
        for (Quality quality : values()) {
            if (quality.name.compareToIgnoreCase(name) == 0) {
                return quality;
            }
        }

        if (name.compareToIgnoreCase("Training") == 0) {
            return Quality.COPPER;
        }
        if (name.compareToIgnoreCase("Bamboo") == 0) {
            return Quality.SILVER;
        }
        if (name.compareToIgnoreCase("Fiberglass") == 0) {
            return Quality.GOLD;
        }

        return null;
    }
}
