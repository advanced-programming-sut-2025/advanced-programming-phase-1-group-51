package Models.Enums.Types.ObjectsOnMapType;

import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Items.Else;
import Models.Items.Food;
import Models.Loot;

import java.util.ArrayList;

public enum ArtisanBlockType {

    SHIPPING_BIN("Shipping Bin",),
    CHARCOAL_KLIN("Charcoal Klin",),
    FURNACE("Furnace",),
    SCARE_CROW("Scare Crow",),
    DELUXE_SCARE_CROW("Deluxe Scarecrow",),
    BEE_HOUSE("Bee House",),
    CHEESE_PRESS("Cheese Press", 100,),
    KEG("Keg"),
    LOOM("Loom"),
    MAYONNAISE_MACHINE("Mayonnaise Machine"),
    OIL_MAKER("Oil Maker"),
    PRESERVES_JAR("Preserves Jar"),
    DEHYDRATOR("Dehydrator"),
    FISH_SMOKER("Fish Smoker");


    public final String name;
    public final int usedEnergy;
    public final ArrayList<Loot> ingridients;
    public final Loot foodThatCanBeMade;

    ArtisanBlockType(String name, int usedEnergy, ArrayList<Loot> ingridients, Loot foodThatCanBeMade) {
        this.name = name;
        this.usedEnergy = usedEnergy;
        this.ingridients = ingridients;
        this.foodThatCanBeMade = foodThatCanBeMade;
    }

    public static ArtisanBlockType getArtisanBlockTypeByName(String name) {
        ArtisanBlockType[] values = ArtisanBlockType.values();
        for (ArtisanBlockType value : values) {
            if (value.name.compareToIgnoreCase(name) == 0) {
                return value;
            }
        }
        return null;
    }


}
