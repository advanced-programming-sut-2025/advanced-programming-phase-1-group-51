package Models.Enums.Recipes;

import Models.Enums.Types.ItemTypes.FoodTypes;
import Models.Loot;

public enum CookingRecipes {

    FRIED_EGG("Fried Egg", 0, 0, 0, 0, FoodTypes.FRIED_EGG, (Loot[]) null),
    BAKED_FISH("Baked Fish", 0, 0, 0, 0, FoodTypes.BAKED_FISH, (Loot[]) null),
    SALAD("Salad", 0, 0, 0, 0, FoodTypes.SALAD, (Loot[]) null),
    OMELETTE("Omelette", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.OMELETTE, (Loot[]) null),
    PUMPKIN_PIE("Pumpkin Pie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.PUMPKIN_PIE, (Loot[]) null),
    SPAGHETTI("Spaghetti", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.SPAGHETTI, (Loot[]) null),
    PIZZA("Pizza", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.PIZZA, (Loot[]) null),
    TORTILLA("Tortilla", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.TORTILLA, (Loot[]) null),
    MAKI_ROLL("Maki Roll", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.MAKI_ROLL, (Loot[]) null),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.TRIPLE_SHOT_ESPRESSO, (Loot[]) null),
    COOKIE("Cookie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.COOKIE, (Loot[]) null),
    HASH_BROWNS("Hash Browns", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.HASH_BROWNS, (Loot[]) null),
    PANCAKES("Pancakes", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.PANCAKES, (Loot[]) null),
    FRUIT_SALAD("Fruit Salad", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.FRUIT_SALAD, (Loot[]) null),
    RED_PLATE("Red Plate", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.RED_PLATE, (Loot[]) null),
    BREAD("Bread", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.BREAD, (Loot[]) null),
    SALMON_DINNER("Salmon Dinner", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodTypes.SALMON_DINNER, (Loot[]) null),
    FARMERS_LUNCH("Farmer's Lunch", 1, 0, 0, 0, FoodTypes.FARMERS_LUNCH, (Loot[]) null),
    SURVIVAL_BURGER("Survival Burger", 0, 0, 3, 0, FoodTypes.SURVIVAL_BURGER, (Loot[]) null),
    DISH_OF_THE_SEA("Dish 'O The Sea", 0, 0, 0, 2, FoodTypes.DISH_OF_THE_SEA, (Loot[]) null),
    SEAFORM_PUDDING("Seaform Pudding", 0, 0, 0, 3, FoodTypes.SEAFORM_PUDDING, (Loot[]) null),
    MINERS_TREAT("Miner's Treat", 0, 1, 0, 0, FoodTypes.MINERS_TREAT, (Loot[]) null);


    public final String name;
    public final int farmingLevel;
    public final int miningLevel;
    public final int foragingLevel;
    public final int fishingLevel;
    public final FoodTypes craftingResult;
    public final Loot[] ingredients;

    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
            , FoodTypes craftingResult, Loot[] ingredients) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.craftingResult = craftingResult;
        this.ingredients = ingredients;
    }

    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
            , FoodTypes craftingResult, Loot ingredient) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.craftingResult = craftingResult;
        this.ingredients = new Loot[]{ingredient};
    }
}
