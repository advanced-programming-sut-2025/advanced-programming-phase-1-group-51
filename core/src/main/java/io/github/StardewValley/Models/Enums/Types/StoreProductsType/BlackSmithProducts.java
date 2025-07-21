package io.github.StardewValley.Models.Enums.Types.StoreProductsType;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.*;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.TrashcanType;

public enum BlackSmithProducts implements StoreProducts {
    // Ores and basic materials
    COPPER_ORE("Copper Ore", "A common ore that can be smelted into bars.", ForagingMineralType.COPPER_ORE, 75, 75, Double.POSITIVE_INFINITY, Season.values()),
    IRON_ORE("Iron Ore", "A fairly common ore that can be smelted into bars.", ForagingMineralType.IRON_ORE, 150, 150, Double.POSITIVE_INFINITY, Season.values()),
    COAL("Coal", "A combustible rock that is useful for crafting and smelting.", ForagingMineralType.COAL, 150, 150, Double.POSITIVE_INFINITY, Season.values()),
    GOLD_ORE("Gold Ore", "A precious ore that can be smelted into bars.", ForagingMineralType.GOLD_ORE, 400, 400, Double.POSITIVE_INFINITY, Season.values()),
    IRIDIUM_ORE("Iridium Ore", "An exotic ore with many curious properties.", ForagingMineralType.IRIDIUM_ORE, 1000, 1000, Double.POSITIVE_INFINITY, Season.values()),

    // Trash Can Upgrades (still using MiscType since they're not tools)
    COPPER_TRASH_CAN("Copper Trash Can", TrashcanType.COPPER, 1000, 1000, 1, Season.values()),
    STEEL_TRASH_CAN("Steel Trash Can", TrashcanType.STEEL, 2500, 2500, 1, Season.values()),
    GOLD_TRASH_CAN("Gold Trash Can", TrashcanType.GOLD, 5000, 5000, 1, Season.values()),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", TrashcanType.IRIDIUM, 12500, 12500, 1, Season.values()),

    // Hoe Upgrades (now using ToolType)
    COPPER_HOE("Copper Hoe", ToolType.COPPER_HOE, 2000, 2000, 1, Season.values()),
    STEEL_HOE("Steel Hoe", ToolType.STEEL_HOE, 5000, 5000, 1, Season.values()),
    GOLD_HOE("Gold Hoe", ToolType.GOLD_HOE, 10000, 10000, 1, Season.values()),
    IRIDIUM_HOE("Iridium Hoe", ToolType.IRIDIUM_HOE, 25000, 25000, 1, Season.values()),

    // Pickaxe Upgrades (now using ToolType)
    COPPER_PICKAXE("Copper Pickaxe", ToolType.COPPER_PICKAXE, 2000, 2000, 1, Season.values()),
    STEEL_PICKAXE("Steel Pickaxe", ToolType.STEEL_PICKAXE, 5000, 5000, 1, Season.values()),
    GOLD_PICKAXE("Gold Pickaxe", ToolType.GOLD_PICKAXE, 10000, 10000, 1, Season.values()),
    IRIDIUM_PICKAXE("Iridium Pickaxe", ToolType.IRIDIUM_PICKAXE, 25000, 25000, 1, Season.values()),

    // Axe Upgrades (now using ToolType)
    COPPER_AXE("Copper Axe", ToolType.COPPER_AXE, 2000, 2000, 1, Season.values()),
    STEEL_AXE("Steel Axe", ToolType.STEEL_AXE, 5000, 5000, 1, Season.values()),
    GOLD_AXE("Gold Axe", ToolType.GOLD_AXE, 10000, 10000, 1, Season.values()),
    IRIDIUM_AXE("Iridium Axe", ToolType.IRIDIUM_AXE, 25000, 25000, 1, Season.values()),

    // Watering Can Upgrades (now using ToolType)
    WATERING_CAN_COPPER("Copper Watering Can", ToolType.WATERING_CAN_COPPER, 2000, 2000, 1, Season.values()),
    WATERING_CAN_STEEL("Steel Watering Can", ToolType.WATERING_CAN_STEEL, 5000, 5000, 1, Season.values()),
    WATERING_CAN_GOLD("Gold Watering Can", ToolType.WATERING_CAN_GOLD, 10000, 10000, 1, Season.values()),
    WATERING_CAN_IRIDIUM("Iridium Watering Can", ToolType.WATERING_CAN_IRIDIUM, 25000, 25000, 1, Season.values()),

    // Scythe Upgrades (now using ToolType)
    COPPER_SCYTHE("Copper Scythe", ToolType.COPPER_SCYTHE, 2000, 2000, 1, Season.values()),
    STEEL_SCYTHE("Steel Scythe", ToolType.STEEL_SCYTHE, 5000, 5000, 1, Season.values()),
    GOLD_SCYTHE("Gold Scythe", ToolType.GOLD_SCYTHE, 10000, 10000, 1, Season.values()),
    IRIDIUM_SCYTHE("Iridium Scythe", ToolType.IRIDIUM_SCYTHE, 25000, 25000, 1, Season.values());

    private final String name;
    private String description = "";
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;
    private final Season[] seasons;

    BlackSmithProducts(String name, String description, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.price = price;
        this.outOfSeasonPrice = outOfSeasonPrice;
        this.dailyLimit = dailyLimit;
        this.seasons = seasons;
    }

    BlackSmithProducts(String name, ItemType itemType, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
        this(name, "", itemType, price, outOfSeasonPrice, dailyLimit, seasons);
    }

    BlackSmithProducts(String name, String description, int price, double outOfSeasonPrice, double dailyLimit, Season[] seasons) {
        this(name, description, null, price, outOfSeasonPrice, dailyLimit, seasons);
    }

    // ... (keep all existing getter methods)

    public static BlackSmithProducts findToolUpgrade(String name) {
        for (BlackSmithProducts product : values()) {
            if (product.name.equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public Quality getToolQuality() {
        if (name.contains("Copper")) return Quality.COPPER;
        if (name.contains("Steel")) return Quality.SILVER;
        if (name.contains("Gold")) return Quality.GOLD;
        if (name.contains("Iridium")) return Quality.IRIDIUM;
        return Quality.DEFAULT;
    }

    public ToolType getToolType() {
        if (name.contains("Hoe")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Pickaxe")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Axe")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Watering Can")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Scythe")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Milk Pail")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        if (name.contains("Shears")) return ToolType.valueOf(name.toUpperCase().replace(" ", "_"));
        return null;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public double getOutOfSeasonPrice() {
        return outOfSeasonPrice;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

}
