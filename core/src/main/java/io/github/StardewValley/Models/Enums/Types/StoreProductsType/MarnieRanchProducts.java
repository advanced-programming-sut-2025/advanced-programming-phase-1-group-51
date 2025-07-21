package io.github.StardewValley.Models.Enums.Types.StoreProductsType;

import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Types.AnimalType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ToolType;

public enum MarnieRanchProducts implements StoreProducts {
    // Animal Products
    HAY("Hay", "Dried grass used as animal food.", MiscType.HAY, 50, 50, Double.POSITIVE_INFINITY, Season.values()),
    MILK_PAIL("Milk Pail", "Gather milk from your animals.", ToolType.MILK_PAIL, 1000, 1000, 1, Season.values()),
    SHEARS("Shears", "Use this to collect wool from sheep", ToolType.SHEARS, 1000, 1000, 1, Season.values()),

    // Animals
    HEN("Chicken", "Well cared-for chickens lay eggs every day. Lives in the coop.",
        AnimalType.HEN, 800, Season.values()),
    COW("Cow", "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn.",
        AnimalType.COW, 1500, Season.values()),
    GOAT("Goat", "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn.",
        AnimalType.GOAT, 2000, Season.values()),
    DUCK("Duck", "Happy lay duck eggs every other day. Lives in the coop.",
        AnimalType.DUCK,  1200, Season.values()),
    SHEEP("Sheep", "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn.",
        AnimalType.SHEEP, 2500, Season.values()),
    RABBIT("Rabbit", "Produces wool and occasionally rabbit's foot.",
        AnimalType.RABBIT, 3000, Season.values()),
    DINOSAUR("Dinosaur", "The Dinosaur is a farm animal that lives in a Big Coop",
        AnimalType.DINOSAUR, 5000, Season.values()),
    PIG("Pig", "These pigs are trained to find truffles! Lives in the barn.",
        AnimalType.PIG, 4000, Season.values());

    private final String name;
    private final String description;
    private final Season[] seasons;

    // Fields for products
    private final ItemType itemType;
    private final int price;
    private final double outOfSeasonPrice;
    private final double dailyLimit;

    // Fields for animals
    private final AnimalType animalType;
    private final int animalPrice;

    // Constructor for products
    MarnieRanchProducts(String name, String description, ItemType itemType,
                        int price, double outOfSeasonPrice, double dailyLimit,
                        Season[] seasons) {
        this.name = name;
        this.description = description;
        this.seasons = seasons;
        this.itemType = itemType;
        this.price = price;
        this.outOfSeasonPrice = outOfSeasonPrice;
        this.dailyLimit = dailyLimit;

        // Animal-specific fields
        this.animalType = null;
        this.animalPrice = -1;
    }

    // Constructor for animals
    MarnieRanchProducts(String name, String description, AnimalType animalType, int animalPrice, Season[] seasons) {
        this.name = name;
        this.description = description;
        this.seasons = seasons;
        this.animalType = animalType;
        this.animalPrice = animalPrice;

        // Product-specific fields
        this.itemType = null;
        this.price = -1;
        this.outOfSeasonPrice = -1;
        this.dailyLimit = -1;
    }

    public boolean isAnimal() {
        return animalType != null;
    }

    public boolean isProduct() {
        return itemType != null;
    }

    // Getters for all fields
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return isProduct() ? price : animalPrice;
    }

    public double getOutOfSeasonPrice() {
        return isProduct() ? outOfSeasonPrice : animalPrice;
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

    public AnimalType getAnimalType() {
        return animalType;
    }

}
