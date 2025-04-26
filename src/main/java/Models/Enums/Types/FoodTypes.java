package Models.Enums.Types;

public enum FoodType {

    APRICOT("Apricot"),
    CHERRY("Cherry"),
    BANANA("Banana"),
    MANGO("Mango"),
    ORANGE("Orange"),
    PEACH("Peach"),
    APPLE("Apple"),
    POMEGRANATE("Pomegranate"),
    OAK_RESIN("Oak Resin"),
    MAPLE_SYRUP("Maple Syrup"),
    PINE_TAR("Pine Tar"),
    SAP("Sap"),
    COMMON_MUSHROOM("Common Mushroom"),
    MYSTIC_SYRUP("Mystic Syrup"),
    ;
    final public String name;

    FoodType(String name) {
        this.name = name;
    }
}
