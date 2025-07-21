package io.github.StardewValley.Models.Enums.Types.ItemTypes;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Items.CropSeed;
import io.github.StardewValley.Models.Items.TreeSeed;
import io.github.StardewValley.Models.Slot;

public enum CropSeedType implements ItemType{
    // Spring Crops
    PARSNIP_SEEDS(Season.SPRING, "Parsnip Seeds", 20),
    BEAN_STARTER(Season.SPRING, "Bean Starter", 60),
    CAULIFLOWER_SEEDS(Season.SPRING, "Cauliflower Seeds", 80),
    POTATO_SEEDS(Season.SPRING, "Potato Seeds", 50),
    TULIP_BULB(Season.SPRING, "Tulip Bulb", 20),
    KALE_SEEDS(Season.SPRING, "Kale Seeds", 70),
    JAZZ_SEEDS(Season.SPRING, "Jazz Seeds", 30),
    GARLIC_SEEDS(Season.SPRING, "Garlic Seeds", 40),
    STRAWBERRY_SEEDS(Season.SPRING, "Strawberry Seeds", 100),
    RHUBARB_SEEDS(Season.SPRING, "Rhubarb Seeds", 100),
    CARROT_SEEDS(Season.SPRING, "Carrot Seeds", 35),  // Added back

    // Summer Crops
    MELON_SEEDS(Season.SUMMER, "Melon Seeds", 80),
    TOMATO_SEEDS(Season.SUMMER, "Tomato Seeds", 50),
    BLUEBERRY_SEEDS(Season.SUMMER, "Blueberry Seeds", 80),
    PEPPER_SEEDS(Season.SUMMER, "Pepper Seeds", 40),
    WHEAT_SEEDS(new Season[]{Season.SUMMER, Season.FALL}, "Wheat Seeds", 10),
    RADISH_SEEDS(Season.SUMMER, "Radish Seeds", 40),
    POPPY_SEEDS(Season.SUMMER, "Poppy Seeds", 100),
    SPANGLE_SEEDS(Season.SUMMER, "Spangle Seeds", 50),
    RED_CABBAGE_SEEDS(Season.SUMMER, "Red Cabbage Seeds", 100),
    STARFRUIT_SEEDS(Season.SUMMER, "Starfruit Seeds", 400),
    CORN_SEEDS(new Season[]{Season.SUMMER, Season.FALL}, "Corn Seeds", 150),
    SUNFLOWER_SEEDS(new Season[]{Season.SUMMER, Season.FALL}, "Sunflower Seeds", 200),
    HOPS_STARTER(Season.SUMMER, "Hops Starter", 60),
    PINEAPPLE_SEEDS(Season.SUMMER, "Pineapple Seeds", 240),
    TARO_ROOT(Season.SUMMER, "Taro Root", 50),
    POWDERMELON_SEEDS(Season.SUMMER, "Powdermelon Seeds", 50),  // Added back
    SUMMER_SQUASH_SEEDS(Season.SUMMER, "Summer Squash Seeds", 50),  // Added back

    // Fall Crops
    PUMPKIN_SEEDS(Season.FALL, "Pumpkin Seeds", 100),
    BOK_CHOY_SEEDS(Season.FALL, "Bok Choy Seeds", 50),
    YAM_SEEDS(Season.FALL, "Yam Seeds", 60),
    CRANBERRY_SEEDS(Season.FALL, "Cranberry Seeds", 240),
    EGGPLANT_SEEDS(Season.FALL, "Eggplant Seeds", 20),
    FAIRY_SEEDS(Season.FALL, "Fairy Seeds", 200),
    AMARANTH_SEEDS(Season.FALL, "Amaranth Seeds", 70),
    GRAPE_STARTER(Season.FALL, "Grape Starter", 60),
    ARTICHOKE_SEEDS(Season.FALL, "Artichoke Seeds", 30),
    BEET_SEEDS(Season.FALL, "Beet Seeds", 20),
    BROCCOLI_SEEDS(Season.FALL, "Broccoli Seeds", 50),  // Added back
    SWEET_GEM_BERRY_SEEDS(Season.FALL, "Rare Seed", 1000),

    // Special/Multi-season Crops
    ANCIENT_SEEDS(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Ancient Seeds", 0),
    COFFEE_BEAN_SEEDS(new Season[]{Season.SPRING, Season.SUMMER}, "Coffee Bean", 15),
    MIXED_SEEDS(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Mixed Seeds", 0),
    FIBER_SEEDS(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Fiber Seeds", 0),
    GRASS_STARTER(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Grass Starter", 100),
    UNMILLED_RICE(Season.SUMMER, "Unmilled Rice", 40),
    TEA_SAPLING(Season.values(), "Tea Sapling", 500);

    public final Season[] growthSeasons;
    public final String name;
    public final int value;

    CropSeedType(Season[] seasons, String name, int value) {
        this.growthSeasons = seasons;
        this.name = name;
        this.value = value;
    }

    CropSeedType(Season season, String name, int value) {
        this(new Season[]{season}, name, value);
    }

    public static CropSeedType findCropSeedTypeByName(String name) {
        for (CropSeedType tree : CropSeedType.values()) {
            if (tree.name.compareToIgnoreCase(name) == 0) {
                return tree;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public Season[] getGrowthSeasons() {
        return growthSeasons;
    }

    @Override
    public Slot createAmountOfItem(int amount, Quality quality) {
        return new Slot(new CropSeed(this), amount);
    }
}
