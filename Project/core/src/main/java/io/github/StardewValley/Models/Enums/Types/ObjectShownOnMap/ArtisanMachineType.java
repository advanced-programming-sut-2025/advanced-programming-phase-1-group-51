package io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap;

public enum ArtisanMachineType {
    SHIPPING_BIN("Shipping Bin"),
    CHARCOAL_KLIN("Charcoal Klin"),
    FURNACE("Furnace"),
    SCARE_CROW("Scare Crow"),
    DELUXE_SCARE_CROW("Deluxe Scarecrow"),
    BEE_HOUSE("Bee House"),
    CHEESE_PRESS("Cheese Press"),
    KEG("Keg"),
    LOOM("Loom"),
    MAYONNAISE_MACHINE("Mayonnaise Machine"),
    OIL_MAKER("Oil Maker"),
    PRESERVES_JAR("Preserves Jar"),
    DEHYDRATOR("Dehydrator"),
    FISH_SMOKER("Fish Smoker");


    public final String name;

    ArtisanMachineType(String name) {
        this.name = name;
    }

    public static ArtisanMachineType findArtisanTypeByName(String name) {
        ArtisanMachineType[] values = ArtisanMachineType.values();
        for (ArtisanMachineType value : values) {
            if (value.name.compareToIgnoreCase(name) == 0) {
                return value;
            }
        }
        return null;
    }
}
