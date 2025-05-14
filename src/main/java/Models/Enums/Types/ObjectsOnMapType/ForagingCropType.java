package Models.Enums.Types.ObjectsOnMapType;

import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Enums.Types.ItemTypes.ItemType;

import java.util.Arrays;

public enum ForagingCropType {

    GRASS(Season.values(), 0, 0, ElseType.FIBER, "Grass"),
    COMMON_MUSHROOM(Season.values(), 40, 38, FoodType.COMMON_MUSHROOM, "Common Mushroom"),
    DAFFODIL(Season.SPRING, 30, 0, FoodType.DAFFODIL, "Daffodil"),
    DANDELION(Season.SPRING, 40, 25, FoodType.DANDELION, "Dandelion"),
    LEEK(Season.SPRING, 60, 40, FoodType.LEEK, "Leek"),
    MOREL(Season.SPRING, 150, 20, FoodType.MOREL, "Morel"),
    SALMON_BERRY(Season.SPRING, 8, 13, FoodType.SALMON_BERRY, "Salmon Berry"),
    SPRING_ONION(Season.SPRING, 8, 13, FoodType.SPRING_ONION, "Spring Onion"),
    WILD_HORSERADISH(Season.SPRING, 50, 13, FoodType.WILD_HORSERADISH, "Wild Horseradish"),
    FIDDLE_HEAD_FERN(Season.SUMMER, 90, 25, FoodType.FIDDLE_HEAD_FERN, "Fiddle Head Fern"),
    GRAPE(Season.SUMMER, 80, 38, FoodType.GRAPE, "Grape"),
    RED_MUSHROOM(Season.SUMMER, 75, -50, FoodType.RED_MUSHROOM, "Red Mushroom"),
    SPICE_BERRY(Season.SUMMER, 80, 25, FoodType.SPICE_BERRY, "Spice Berry"),
    SWEET_PEA(Season.SUMMER, 50, 0, FoodType.SWEET_PEA, "Sweet Pea"),
    BLACKBERRY(Season.FALL, 25, 25, FoodType.BLACKBERRY, "Black Berry"),
    CHANTERELLE(Season.FALL, 160, 75, FoodType.CHANTERELLE, "Chernelle"),
    HAZELNUT(Season.FALL, 40, 38, FoodType.HAZELNUT, "Hazelnut"),
    PURPLE_MUSHROOM(Season.FALL, 90, 30, FoodType.PURPLE_MUSHROOM, "Purple Mushroom"),
    WILD_PLUM(Season.FALL, 80, 25, FoodType.WILD_PLUM, "Wild Plum"),
    CROCUS(Season.WINTER, 60, 0, FoodType.CROCUS, "Crocus"),
    CRYSTAL_FRUIT(Season.WINTER, 150, 63, FoodType.CRYSTAL_FRUIT, "Crystal Fruit"),
    HOLLY(Season.WINTER, 80, -37, FoodType.HOLLY, "Holly"),
    SNOW_YAM(Season.WINTER, 100, 30, FoodType.SNOW_YAM, "Snow Yam"),
    WINTER_ROOT(Season.WINTER, 70, 25, FoodType.WINTER_ROOT, "Winter Root"),
    ;

    private final Season[] seasons;
    private final int cost;
    private final int energy;
    private final ItemType harvestedItemType;
    public final String name;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append("\n");
        str.append("Value: ").append(cost).append("\n");
        str.append("Energy: ").append(energy).append("\n");
        str.append("Seasons: ").append(Arrays.toString(seasons)).append("\n");
        return str.toString();
    }

    ForagingCropType(Season[] seasons, int cost, int energy, ItemType harvestedItemType, String name) {
        this.seasons = seasons;
        this.cost = cost;
        this.energy = energy;
        this.harvestedItemType = harvestedItemType;
        this.name = name;
    }

    ForagingCropType(Season season, int cost, int energy, ItemType harvestedItemType, String name) {
        this.seasons = new Season[]{season};
        this.cost = cost;
        this.energy = energy;
        this.harvestedItemType = harvestedItemType;
        this.name = name;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public int getCost() {
        return cost;
    }

    public int getEnergy() {
        return energy;
    }

    public ItemType getHarvestedItemType() {
        return harvestedItemType;
    }


}
