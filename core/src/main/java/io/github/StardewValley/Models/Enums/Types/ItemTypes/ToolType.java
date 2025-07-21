package io.github.StardewValley.Models.Enums.Types.ItemTypes;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Slot;

public enum ToolType implements ItemType {
    // Hoes
    HOE(0, "Hoe"),
    COPPER_HOE(0, "Copper Hoe"),
    STEEL_HOE(0, "Steel Hoe"),
    GOLD_HOE(0, "Gold Hoe"),
    IRIDIUM_HOE(0, "Iridium Hoe"),

    // Pickaxes
    PICKAXE(0, "Pickaxe"),
    COPPER_PICKAXE(0, "Copper Pickaxe"),
    STEEL_PICKAXE(0, "Steel Pickaxe"),
    GOLD_PICKAXE(0, "Gold Pickaxe"),
    IRIDIUM_PICKAXE(0, "Iridium Pickaxe"),

    // Axes
    AXE(0, "Axe"),
    COPPER_AXE(0, "Copper Axe"),
    STEEL_AXE(0, "Steel Axe"),
    GOLD_AXE(0, "Gold Axe"),
    IRIDIUM_AXE(0, "Iridium Axe"),

    // Watering Cans
    WATERING_CAN(40, "Watering Can"),
    WATERING_CAN_COPPER(55, "Copper Watering Can"),
    WATERING_CAN_STEEL(70, "Steel Watering Can"),
    WATERING_CAN_GOLD(85, "Gold Watering Can"),
    WATERING_CAN_IRIDIUM(100, "Iridium Watering Can"),

    // Fishing Rods
    BAMBOO_POLE(0, "Bamboo Pole"),
    FIBERGLASS_ROD(0, "Fiberglass Rod"),
    IRIDIUM_ROD(0, "Iridium Rod"),
    TRAINING_ROD(0, "Training Rod"),

    // Other Tools
    SCYTHE(0, "Scythe"),
    COPPER_SCYTHE(0, "Copper Scythe"),
    STEEL_SCYTHE(0, "Steel Scythe"),
    GOLD_SCYTHE(0, "Gold Scythe"),
    IRIDIUM_SCYTHE(0, "Iridium Scythe"),

    MILK_PAIL(0, "Milk Pail"),
    SHEARS(0, "Shears");

    public final int waterCapacity;
    public final String name;

    ToolType(int waterCapacity, String name) {
        this.waterCapacity = waterCapacity;
        this.name = name;
    }

    public static ToolType findToolTypeByName(String name) {
        for (ToolType tool : ToolType.values()) {
            if (tool.name.equalsIgnoreCase(name)) {
                return tool;
            }
        }
        return null;
    }

    @Override
    public Slot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

    public String getName() {
        return name;
    }
}
