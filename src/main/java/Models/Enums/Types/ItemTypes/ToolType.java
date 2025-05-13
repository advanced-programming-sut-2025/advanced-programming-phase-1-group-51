package Models.Enums.Types.ItemTypes;

import Models.Enums.Others.Quality;
import Models.Loot;

public enum ToolType implements ItemType{

    HOE(0, "Hoe"),
    PICKAXE(0, "Pickaxe"),
    AXE(0, "Axe"),
    WATERING_CAN_DEFAULT(40, "Default Watering Can"),
    WATERING_CAN_COPPER(55, "Copper Watering Can"),
    WATERING_CAN_IRON(70, "Iron Watering Can"),
    WATERING_CAN_GOLD(85, "Gold Watering Can"),
    WATERING_CAN_IRIDIUM(100, "Iridium Watering Can"),
    FISHING_ROD(0, "Fishing Rod"),
    SCYTHE(0, "Scythe"),
    MILK_PAIL(0, "Milk Pail"),
    SHEAR(0, "Shear");

    public  int waterCapacity;
    public  String name;


    ToolType(int waterCapacity, String name) {
        this.waterCapacity = waterCapacity;
        this.name = name;
    }

        public static ToolType findToolTypeByName(String name) {
        ToolType[] values = ToolType.values();
        for (ToolType value : values) {
            if (value.name.compareToIgnoreCase(name) == 0) {
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
