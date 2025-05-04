package Models.Enums.Types.ItemTypes;

public enum ToolType implements ItemType{

    HOE(0, "Hoe"),
    PICKAXE(0, "Pickaxe"),
    AXE(0, "Axe"),
    WATERING_CAN_DEFAULT(40, "Watering Can Default"),
    WATERING_CAN_COPPER(55, "Watering Can Copper"),
    WATERING_CAN_IRON(70, "Watering Can Iron"),
    WATERING_CAN_GOLD(85, "Watering Can Gold"),
    WATERING_CAN_IRIDIUM(100, "Watering Can Iridium"),
    FISHING_ROD(0, "Fishing Rod"),
    SCYTHE(0, "Scythe"),
    MILK_PAIL(0, "Milk Pail"),
    SHEAR(0, "Shear"),
    ;

    public  int waterCapacity;
    public  String name;


    ToolType(int waterCapacity, String name) {
        this.waterCapacity = waterCapacity;
        this.name = name;
    }

    ToolType() {
    }

    public String getName(){
        return name;
    }

}
