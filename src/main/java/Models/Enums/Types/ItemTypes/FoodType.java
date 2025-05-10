package Models.Enums.Types.ItemTypes;

import Models.Loot;

public enum FoodType implements ItemType{

    APRICOT("Apricot", 38,   59),
    CHERRY("Cherry", 38,   80),
    BANANA("Banana", 75,   150),
    MANGO("Mango", 100,   130),
    ORANGE("Orange", 38,   100),
    PEACH("Peach", 38,   140),
    APPLE("Apple", 38,   100),
    POMEGRANATE("Pomegranate", 38,   140),
    OAK_RESIN("Oak Resin", 0,   150),
    MAPLE_SYRUP("Maple Syrup", 0,   200),
    PINE_TAR("Pine Tar", 0,   100),
    SAP("Sap", -2,   2),
    COMMON_MUSHROOM("Common Mushroom", 38,   40),
    MYSTIC_SYRUP("Mystic Syrup", 500,   1000),
    FRIED_EGG("Fried Egg", 50,   35),
    BAKED_FISH("Baked Fish", 75,   100),
    SALAD("Salad", 113,   110),
    OMELETTE("Omelette", 100,   125),
    PUMPKIN_PIE("Pumpkin Pie", 225,   385),
    SPAGHETTI("Spaghetti", 75,   120),
    PIZZA("Pizza", 150,   300),
    TORTILLA("Tortilla", 50,   50),
    MAKI_ROLL("Maki Roll", 100,   100),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", 200, 450),
    COOKIE("Cookie", 90,   140),
    HASH_BROWNS("Hash Browns", 90, 120),
    PANCAKES("Pancakes", 90, 80),
    FRUIT_SALAD("Fruit Salad", 263,   450),
    RED_PLATE("Red Plate", 240,  400),
    BREAD("Bread", 50,   60),
    SALMON_DINNER("Salmon Dinner", 125,   300),
    VEGETABLE_MEDLEY("Vegetable Medley", 165,   120),
    FARMERS_LUNCH("Farmer's Lunch", 200, 150),
    SURVIVAL_BURGER("Survival Burger", 125, 180),
    DISH_OF_THE_SEA("Dish O' The Sea", 150,  220),
    SEAFORM_PUDDING("Seaform Pudding", 175, 300),
    MINERS_TREAT("Miner's Treat", 125, 200),
    BLUE_JAZZ("Blue Jazz", 45,   50),
    CARROT("Carrot", 75,   35),
    CAULIFLOWER("Cauliflower", 75,   175),
    COFFEE_BEAN("Coffee Bean", 0,   15),
    GARLIC("Garlic", 20,   60),
    GREEN_BEAN("Green Bean", 25,   40),
    KALE("Kale", 50,   110),
    PARSNIP("Parsnip", 25,   35),
    POTATO("Potato", 25,   80),
    RHUBARB("Rhubarb", 0,   220),
    STRAWBERRY("Strawberry", 50,   120),
    TULIP("Tulip", 45,   30),
    UNMILLED_RICE("Unmilled Rice", 3,   30),
    BLUEBERRY("Blueberry", 25,   50),
    CORN("Corn", 25,   50),
    HOPS("Hops", 45,   25),
    HOT_PEPPER("Hot Pepper", 13,   40),
    MELON("Melon", 113,   250),
    POPPY("Poppy", 45,   140),
    RADISH("Radish", 45,   90),
    RED_CABBAGE("Red Cabbage", 75,   260),
    STARFRUIT("Starfruit", 125,   750),
    SUMMER_SPANGLE("Summer Spangle", 45,   90),
    SUMMER_SQUASH("Summer Squash", 63,   45),
    SUNFLOWER("Sunflower", 45,   80),
    TOMATO("Tomato", 20,   60),
    WHEAT("Wheat", 0,   25),
    AMARANTH("Amaranth", 50,   150),
    ARTICHOKE("Artichoke", 30,   160),
    BEET("Beet", 30,   100),
    BOK_CHOY("Bok Choy", 25,   80),
    BROCCOLI("Broccoli", 63,   70),
    CRANBERRIES("Cranberries", 38,   75),
    EGGPLANT("Eggplant", 20,   60),
    FAIRY_ROSE("Fairy Rose", 45,   290),
    GRAPE("Grape", 38,   80),
    PUMPKIN("Pumpkin", 0,   320),
    YAM("Yam", 45,   160),
    SWEET_GEM_BERRY("Sweet Gem Berry", 0,   3000),
    POWDER_MELON("Powder Melon", 63,   60),
    ANCIENT_FRUIT("Ancient Fruit", 0,   550),
    DAFFODIL("Daffodil", 0,   30),
    DANDELION("Dandelion", 25,   40),
    LEEK("Leek", 40,   60),
    MOREL("Morel", 20,   150),
    SALMON_BERRY("Salmon Berry", 13,   8),
    SPRING_ONION("Spring Onion", 13,   8),
    WILD_HORSERADISH("Wild Horseradish", 13,   50),
    FIDDLE_HEAD_FERN("Fiddle Head Fern", 25,   90),
    RED_MUSHROOM("Red Mushroom", -50,   75),
    SPICE_BERRY("Spice Berry", 25,   80),
    SWEET_PEA("Sweet Pea", 0,   50),
    BLACKBERRY("Black Berry", 25,   25),
    CHANTERELLE("Chanterelle", 75,   160),
    HAZELNUT("Hazelnut", 38,   40),
    PURPLE_MUSHROOM("Purple Mushroom", 30,   90),
    WILD_PLUM("Wild Plum", 25,   80),
    CROCUS("Crocus", 0,   60),
    CRYSTAL_FRUIT("Crystal Fruit", 63,   150),
    HOLLY("Holly", -37,   80),
    SNOW_YAM("Snow Yam", 30,   100),
    WINTER_ROOT("Winter Root", 25,   70),
    BEER("Beer", 50,   200),
    COFFEE("Coffee", 3,   150),
    JOJA_COLA("Joja Cola", 13,   25),
    SUGAR("Sugar", 25,   50),
    WHEAT_FLOUR("Wheat Flour", 13,   50),
    RICE("Rice", 13,   100),
    OIL("Oil", 13,   100),
    VINEGAR("Vinegar", 13,   100),
    TROUT_SOUP("Trout Soup", 100,   100),
    HONEY("Honey", 0,   100),
    MAYONNAISE("Mayonnaise", 50,   190),
    DUCK_MAYONNAISE("Duck Mayonnaise", 75,   375),
    DINOSAUR_MAYONNAISE("Dinosaur Mayonnaise", 125,   800),
    TRUFFLE_OIL("Truffle Oil", 38,   1065),
    CHEESE("Cheese", 125,   230),
    LARGE_CHEESE("large cgeese",),
    GOAT_CHEESE("Goat Cheese", 125,   400),
    MEAD("Mead", 75,   300),
    PALE_ALE("Pale Ale", 50,   300),
    RAISINS("Raisins", 125,   600),
    //Determined based on base ingredient.
    WINE("Wine", null, null),
    JUICE("Juice", null, null),
    DRIED_MUSHROOMS("Dried Mushrooms", 50, null),
    DRIED_FRUIT("Dried Fruit", 75,  null),
    PICKLES("Pickles", null, null),
    JELLY("Jelly", null,  null),
    SMOKED_FISH("Smoked Fish", null,  null)
    ;

    final public String name;
    final public Integer energy;
    final public Integer price;

    FoodType(String name, Integer energy, Integer price) {
        this.name = name;
        this.energy = energy;
        this.price = price;
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


    @Override
    public Loot createAmountOfItem(int amount) {
        return null;
    }

}
