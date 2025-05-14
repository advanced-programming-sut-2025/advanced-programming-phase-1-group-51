package Models.Enums.Types.ItemTypes;

import Models.Buff;
import Models.Enums.Others.Quality;
import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;
import Models.Loot;

public enum FoodType implements ItemType{

    APRICOT("Apricot", 38,   59,new Buff(0,0,"")),
    CHERRY("Cherry", 38,   80,new Buff(0,0,"")),
    BANANA("Banana", 75,   150,new Buff(0,0,"")),
    MANGO("Mango", 100,   130,new Buff(0,0,"")),
    ORANGE("Orange", 38,   100,new Buff(0,0,"")),
    PEACH("Peach", 38,   140,new Buff(0,0,"")),
    APPLE("Apple", 38,   100,new Buff(0,0,"")),
    POMEGRANATE("Pomegranate", 38,   140,new Buff(0,0,"")),
    OAK_RESIN("Oak Resin", 0,   150,new Buff(0,0,"")),
    MAPLE_SYRUP("Maple Syrup", 0,   200,new Buff(0,0,"")),
    PINE_TAR("Pine Tar", 0,   100,new Buff(0,0,"")),
    SAP("Sap", -2,   2,new Buff(0,0,"")),
    COMMON_MUSHROOM("Common Mushroom", 38,   40,new Buff(0,0,"")),
    MYSTIC_SYRUP("Mystic Syrup", 500,   1000,new Buff(0,0,"")),
    FRIED_EGG("Fried Egg", 50,   35,new Buff(0,0,"")),
    BAKED_FISH("Baked Fish", 75,   100,new Buff(0,0,"")),
    SALAD("Salad", 113,   110,new Buff(0,0,"")),
    OMELETTE("Omelette", 100,   125,new Buff(0,0,"")),
    PUMPKIN_PIE("Pumpkin Pie", 225,   385,new Buff(0,0,"")),
    SPAGHETTI("Spaghetti", 75,   120,new Buff(0,0,"")),
    PIZZA("Pizza", 150,   300,new Buff(0,0,"")),
    TORTILLA("Tortilla", 50,   50,new Buff(0,0,"")),
    MAKI_ROLL("Maki Roll", 100,   100,new Buff(0,0,"")),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", 200, 450,new Buff(100,5,"maxEnergy")),
    COOKIE("Cookie", 90,   140,new Buff(0,0,"")),
    HASH_BROWNS("Hash Browns", 90, 120,new Buff(0,5,"farming")),
    PANCAKES("Pancakes", 90, 80,new Buff(0,11,"foraging")),
    FRUIT_SALAD("Fruit Salad", 263,   450,new Buff(0,0,"")),
    RED_PLATE("Red Plate", 240,  400,new Buff(50,3,"maxEnergy")),
    BREAD("Bread", 50,   60,new Buff(0,0,"")),
    SALMON_DINNER("Salmon Dinner", 125,   300,new Buff(0,0,"")),
    VEGETABLE_MEDLEY("Vegetable Medley", 165,   120,new Buff(0,0,"")),
    FARMERS_LUNCH("Farmer's Lunch", 200, 150,new Buff(0,5,"farming")),
    SURVIVAL_BURGER("Survival Burger", 125, 180,new Buff(0,5,"foraging")),
    DISH_OF_THE_SEA("Dish O' The Sea", 150,  220,new Buff(0,5,"fishing")),
    SEAFORM_PUDDING("Seaform Pudding", 175, 300,new Buff(0,10,"fishing")),
    MINERS_TREAT("Miner's Treat", 125, 200,new Buff(0,5,"mining")),
    BLUE_JAZZ("Blue Jazz", 45,   50,new Buff(0,0,"")),
    CARROT("Carrot", 75,   35,new Buff(0,0,"")),
    CAULIFLOWER("Cauliflower", 75,   175,new Buff(0,0,"")),
    COFFEE_BEAN("Coffee Bean", 0,   15,new Buff(0,0,"")),
    GARLIC("Garlic", 20,   60,new Buff(0,0,"")),
    GREEN_BEAN("Green Bean", 25,   40,new Buff(0,0,"")),
    KALE("Kale", 50,   110,new Buff(0,0,"")),
    PARSNIP("Parsnip", 25,   35,new Buff(0,0,"")),
    POTATO("Potato", 25,   80,new Buff(0,0,"")),
    RHUBARB("Rhubarb", 0,   220,new Buff(0,0,"")),
    STRAWBERRY("Strawberry", 50,   120,new Buff(0,0,"")),
    TULIP("Tulip", 45,   30,new Buff(0,0,"")),
    UNMILLED_RICE("Unmilled Rice", 3,   30,new Buff(0,0,"")),
    BLUEBERRY("Blueberry", 25,   50,new Buff(0,0,"")),
    CORN("Corn", 25,   50,new Buff(0,0,"")),
    HOPS("Hops", 45,   25,new Buff(0,0,"")),
    HOT_PEPPER("Hot Pepper", 13,   40,new Buff(0,0,"")),
    MELON("Melon", 113,   250,new Buff(0,0,"")),
    POPPY("Poppy", 45,   140,new Buff(0,0,"")),
    RADISH("Radish", 45,   90,new Buff(0,0,"")),
    RED_CABBAGE("Red Cabbage", 75,   260,new Buff(0,0,"")),
    STARFRUIT("Starfruit", 125,   750,new Buff(0,0,"")),
    SUMMER_SPANGLE("Summer Spangle", 45,   90,new Buff(0,0,"")),
    SUMMER_SQUASH("Summer Squash", 63,   45,new Buff(0,0,"")),
    SUNFLOWER("Sunflower", 45,   80,new Buff(0,0,"")),
    TOMATO("Tomato", 20,   60,new Buff(0,0,"")),
    WHEAT("Wheat", 0,   25,new Buff(0,0,"")),
    AMARANTH("Amaranth", 50,   150,new Buff(0,0,"")),
    ARTICHOKE("Artichoke", 30,   160,new Buff(0,0,"")),
    BEET("Beet", 30,   100,new Buff(0,0,"")),
    BOK_CHOY("Bok Choy", 25,   80,new Buff(0,0,"")),
    BROCCOLI("Broccoli", 63,   70,new Buff(0,0,"")),
    CRANBERRIES("Cranberries", 38,   75,new Buff(0,0,"")),
    EGGPLANT("Eggplant", 20,   60,new Buff(0,0,"")),
    FAIRY_ROSE("Fairy Rose", 45,   290,new Buff(0,0,"")),
    GRAPE("Grape", 38,   80,new Buff(0,0,"")),
    PUMPKIN("Pumpkin", 0,   320,new Buff(0,0,"")),
    YAM("Yam", 45,   160,new Buff(0,0,"")),
    SWEET_GEM_BERRY("Sweet Gem Berry", 0,   3000,new Buff(0,0,"")),
    POWDER_MELON("Powder Melon", 63,   60,new Buff(0,0,"")),
    ANCIENT_FRUIT("Ancient Fruit", 0,   550,new Buff(0,0,"")),
    DAFFODIL("Daffodil", 0,   30,new Buff(0,0,"")),
    DANDELION("Dandelion", 25,   40,new Buff(0,0,"")),
    LEEK("Leek", 40,   60,new Buff(0,0,"")),
    MOREL("Morel", 20,   150,new Buff(0,0,"")),
    SALMON_BERRY("Salmon Berry", 13,   8,new Buff(0,0,"")),
    SPRING_ONION("Spring Onion", 13,   8,new Buff(0,0,"")),
    WILD_HORSERADISH("Wild Horseradish", 13,   50,new Buff(0,0,"")),
    FIDDLE_HEAD_FERN("Fiddle Head Fern", 25,   90,new Buff(0,0,"")),
    RED_MUSHROOM("Red Mushroom", -50,   75,new Buff(0,0,"")),
    SPICE_BERRY("Spice Berry", 25,   80,new Buff(0,0,"")),
    SWEET_PEA("Sweet Pea", 0,   50,new Buff(0,0,"")),
    BLACKBERRY("Black Berry", 25,   25,new Buff(0,0,"")),
    CHANTERELLE("Chanterelle", 75,   160,new Buff(0,0,"")),
    HAZELNUT("Hazelnut", 38,   40,new Buff(0,0,"")),
    PURPLE_MUSHROOM("Purple Mushroom", 30,   90,new Buff(0,0,"")),
    WILD_PLUM("Wild Plum", 25,   80,new Buff(0,0,"")),
    CROCUS("Crocus", 0,   60,new Buff(0,0,"")),
    CRYSTAL_FRUIT("Crystal Fruit", 63,   150,new Buff(0,0,"")),
    HOLLY("Holly", -37,   80,new Buff(0,0,"")),
    SNOW_YAM("Snow Yam", 30,   100,new Buff(0,0,"")),
    WINTER_ROOT("Winter Root", 25,   70,new Buff(0,0,"")),
    BEER("Beer", 50,   200,new Buff(0,0,"")),
    COFFEE("Coffee", 3,   150,new Buff(0,0,"")),
    JOJA_COLA("Joja Cola", 13,   25,new Buff(0,0,"")),
    SUGAR("Sugar", 25,   50,new Buff(0,0,"")),
    WHEAT_FLOUR("Wheat Flour", 13,   50,new Buff(0,0,"")),
    RICE("Rice", 13,   100,new Buff(0,0,"")),
    OIL("Oil", 13,   100,new Buff(0,0,"")),
    VINEGAR("Vinegar", 13,   100,new Buff(0,0,"")),
    TROUT_SOUP("Trout Soup", 100,   100,new Buff(0,0,"")),
    HONEY("Honey", 0,   100,new Buff(0,0,"")),
    MAYONNAISE("Mayonnaise", 50,   190,new Buff(0,0,"")),
    DUCK_MAYONNAISE("Duck Mayonnaise", 75,   375,new Buff(0,0,"")),
    DINOSAUR_MAYONNAISE("Dinosaur Mayonnaise", 125,   800,new Buff(0,0,"")),
    TRUFFLE_OIL("Truffle Oil", 38,   1065,new Buff(0,0,"")),
    CHEESE("Cheese", 125,   230,new Buff(0,0,"")),
    LARGE_CHEESE("large cgeese",100, 325,new Buff(0,0,"")),
    GOAT_CHEESE("Goat Cheese", 125,   400,new Buff(0,0,"")),
    MEAD("Mead", 75,   300,new Buff(0,0,"")),
    PALE_ALE("Pale Ale", 50,   300,new Buff(0,0,"")),
    RAISINS("Raisins", 125,   600,new Buff(0,0,"")),
    //Determined based on base ingredient.
    WINE("Wine", null, null,new Buff(0,0,"")),
    JUICE("Juice", null, null,new Buff(0,0,"")),
    DRIED_MUSHROOMS("Dried Mushrooms", 50, null,new Buff(0,0,"")),
    DRIED_FRUIT("Dried Fruit", 75,  null,new Buff(0,0,"")),
    PICKLES("Pickles", null, null,new Buff(0,0,"")),
    JELLY("Jelly", null,  null,new Buff(0,0,"")),
    SMOKED_FISH("Smoked Fish", null,  null,new Buff(0,0,""))
    ;

    final public String name;
    final public Integer energy;
    final public Integer price;
    final public Buff foodBuff;

    FoodType(String name, Integer energy, Integer price, Buff foodBuff) {
        this.name = name;
        this.energy = energy;
        this.price = price;
        this.foodBuff = foodBuff;
    }

    public static int getEnergy(String foodName) {
        for (FoodType type : FoodType.values()) {
            if(type.name.equals(foodName))
                return type.energy;
        }
        return 0;
    }

    public static int getPrice(String itemName) {
        for(FoodType p : FoodType.values()) {
            if(p.name.equals(itemName))
                return p.price;
        }
        return 0;
    }

    public String getName(){
        return name;
    }

    public static FoodType findFoodByName(String name) {
        FoodType[] values = FoodType.values();
        for (FoodType value : values) {
            if (value.name.equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }


    @Override
    public Loot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

}
