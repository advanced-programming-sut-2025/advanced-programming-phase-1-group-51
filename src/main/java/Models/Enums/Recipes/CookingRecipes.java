package Models.Enums.Recipes;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FishType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Items.Fish;
import Models.Items.Else;
import Models.Items.Food;
import Models.Loot;

public enum CookingRecipes {


    FRIED_EGG("Fried Egg", 0, 0, 0, 0, FoodType.FRIED_EGG,
            new Loot(new Else(ElseType.EGG), 1)),
    BAKED_FISH("Baked Fish", 0, 0, 0, 0, FoodType.BAKED_FISH,
            new Loot[]{new Loot(new Fish(Quality.DEFAULT, FishType.SARDINE), 1), new Loot(new Fish(Quality.DEFAULT, FishType.SALMON), 1), new Loot(new Food(FoodType.WHEAT), 1)}),
    SALAD("Salad", 0, 0, 0, 0, FoodType.SALAD,
            new Loot[]{new Loot(new Food(FoodType.LEEK), 1), new Loot(new Food(FoodType.DANDELION), 1)}),
    OMELETTE("Omelette", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.OMELETTE,
            new Loot[]{new Loot(new Else(ElseType.EGG), 1), new Loot(new Else(ElseType.MILK), 1)}),
    PUMPKIN_PIE("Pumpkin Pie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PUMPKIN_PIE,
            new Loot[]{new Loot(new Food(FoodType.PUMPKIN), 1), new Loot(new Else(ElseType.MILK), 1), new Loot(new Food(FoodType.SUGAR), 1), new Loot(new Food(FoodType.WHEAT_FLOUR), 1)}),
    SPAGHETTI("Spaghetti", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.SPAGHETTI,
            new Loot[]{new Loot(new Food(FoodType.WHEAT_FLOUR), 1), new Loot(new Food(FoodType.TOMATO), 1)}),
    PIZZA("Pizza", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PIZZA,
            new Loot[]{new Loot(new Food(FoodType.WHEAT_FLOUR), 1), new Loot(new Food(FoodType.TOMATO), 1), new Loot(new Food(FoodType.CHEESE), 1)}),
    TORTILLA("Tortilla", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.TORTILLA,
            new Loot(new Food(FoodType.CORN), 1)),
    // 1 Fish handled hard coded in Controller.
    MAKI_ROLL("Maki Roll", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.MAKI_ROLL,
            new Loot[]{new Loot(new Else(ElseType.FIBER), 1), new Loot(new Food(FoodType.RICE), 1)}),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.TRIPLE_SHOT_ESPRESSO,
            new Loot(new Food(FoodType.COFFEE), 3)),
    COOKIE("Cookie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.COOKIE,
            new Loot[]{new Loot(new Food(FoodType.WHEAT_FLOUR), 1), new Loot(new Else(ElseType.EGG), 1), new Loot(new Food(FoodType.SUGAR), 1)}),
    HASH_BROWNS("Hash Browns", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.HASH_BROWNS,
            new Loot[]{new Loot(new Food(FoodType.POTATO), 1), new Loot(new Food(FoodType.OIL), 1)}),
    PANCAKES("Pancakes", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PANCAKES,
            new Loot[]{new Loot(new Food(FoodType.WHEAT_FLOUR), 1), new Loot(new Else(ElseType.EGG), 1)}),
    FRUIT_SALAD("Fruit Salad", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.FRUIT_SALAD,
            new Loot[]{new Loot(new Food(FoodType.BLUEBERRY), 1), new Loot(new Food(FoodType.MELON), 1), new Loot(new Food(FoodType.APRICOT), 1)}),
    RED_PLATE("Red Plate", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.RED_PLATE,
            new Loot[]{new Loot(new Food(FoodType.RED_CABBAGE), 1), new Loot(new Food(FoodType.RADISH), 1)}),
    BREAD("Bread", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.BREAD,
            new Loot(new Food(FoodType.WHEAT_FLOUR), 1)),
    SALMON_DINNER("Salmon Dinner", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.SALMON_DINNER,
            new Loot[]{new Loot(new Fish(Quality.DEFAULT, FishType.SALMON), 1), new Loot(new Food(FoodType.AMARANTH), 1), new Loot(new Food(FoodType.KALE), 1)}),
    VEGETABLE_MEDLEY("Vegetable Medley", 0, 0, 2, 0, FoodType.VEGETABLE_MEDLEY,
            new Loot[]{new Loot(new Food(FoodType.TOMATO), 1), new Loot(new Food(FoodType.BEET), 1)}),
    FARMERS_LUNCH("Farmer's Lunch", 1, 0, 0, 0, FoodType.FARMERS_LUNCH,
            new Loot[]{new Loot(new Food(FoodType.OMELETTE), 1), new Loot(new Food(FoodType.PARSNIP), 1)}),
    SURVIVAL_BURGER("Survival Burger", 0, 0, 3, 0, FoodType.SURVIVAL_BURGER,
            new Loot[]{new Loot(new Food(FoodType.BREAD), 1), new Loot(new Food(FoodType.CARROT), 1), new Loot(new Food(FoodType.EGGPLANT), 1)}),
    DISH_OF_THE_SEA("Dish 'O The Sea", 0, 0, 0, 2, FoodType.DISH_OF_THE_SEA,
            new Loot[]{new Loot(new Fish(Quality.DEFAULT, FishType.SARDINE), 2), new Loot(new Food(FoodType.HASH_BROWNS), 1)}),
    SEAFORM_PUDDING("Seaform Pudding", 0, 0, 0, 3, FoodType.SEAFORM_PUDDING,
            new Loot[]{new Loot(new Fish(Quality.DEFAULT, FishType.FLOUNDER), 1), new Loot(new Fish(Quality.DEFAULT, FishType.MIDNIGHT_CARP), 1)}),
    MINERS_TREAT("Miner's Treat", 0, 1, 0, 0, FoodType.MINERS_TREAT,
            new Loot[]{new Loot(new Else(ElseType.MILK), 1), new Loot(new Food(FoodType.SUGAR), 1), new Loot(new Food(FoodType.CARROT), 2)});


    public final String name;
    public final int farmingLevel;
    public final int miningLevel;
    public final int foragingLevel;
    public final int fishingLevel;
    public final FoodType cookingResultType;
    public final Loot[] ingredients;

    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
            , FoodType cookingResultType, Loot[] ingredients) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.cookingResultType = cookingResultType;
        this.ingredients = ingredients;
    }

    /// For single ingredient cooking recipes.
    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
            , FoodType cookingResultType, Loot ingredient) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.cookingResultType = cookingResultType;
        this.ingredients = new Loot[]{ingredient};
    }

    @Override
    public String toString() {
        return name;
    }
}
