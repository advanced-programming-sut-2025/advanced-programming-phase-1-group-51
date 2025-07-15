package io.github.StardewValley.Models.Enums.Recipes;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FishType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FoodType;
import io.github.StardewValley.Models.Items.Misc;
import io.github.StardewValley.Models.Items.Fish;
import io.github.StardewValley.Models.Items.Food;
import io.github.StardewValley.Models.Slot;

import java.util.Arrays;

public enum CookingRecipes {

    FRIED_EGG("Fried Egg", 0, 0, 0, 0, FoodType.FRIED_EGG,
        new Slot(new Misc(MiscType.EGG), 1)),
    BAKED_FISH("Baked Fish", 0, 0, 0, 0, FoodType.BAKED_FISH,
        new Slot[]{new Slot(new Fish(FishType.SARDINE), 1), new Slot(new Fish(FishType.SALMON), 1), new Slot(new Food(FoodType.WHEAT), 1)}),
    SALAD("Salad", 0, 0, 0, 0, FoodType.SALAD,
        new Slot[]{new Slot(new Food(FoodType.LEEK), 1), new Slot(new Food(FoodType.DANDELION), 1)}),
    OMELETTE("Omelette", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.OMELETTE,
        new Slot[]{new Slot(new Misc(MiscType.EGG), 1), new Slot(new Misc(MiscType.MILK), 1)}),
    PUMPKIN_PIE("Pumpkin Pie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PUMPKIN_PIE,
        new Slot[]{new Slot(new Food(FoodType.PUMPKIN), 1), new Slot(new Misc(MiscType.MILK), 1), new Slot(new Food(FoodType.SUGAR), 1), new Slot(new Food(FoodType.WHEAT_FLOUR), 1)}),
    SPAGHETTI("Spaghetti", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.SPAGHETTI,
        new Slot[]{new Slot(new Food(FoodType.WHEAT_FLOUR), 1), new Slot(new Food(FoodType.TOMATO), 1)}),
    PIZZA("Pizza", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PIZZA,
        new Slot[]{new Slot(new Food(FoodType.WHEAT_FLOUR), 1), new Slot(new Food(FoodType.TOMATO), 1), new Slot(new Food(FoodType.CHEESE), 1)}),
    TORTILLA("Tortilla", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.TORTILLA,
        new Slot(new Food(FoodType.CORN), 1)),
    // 1 Fish handled hard coded in Controller.
    MAKI_ROLL("Maki Roll", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.MAKI_ROLL,
        new Slot[]{new Slot(new Misc(MiscType.FIBER), 1), new Slot(new Food(FoodType.RICE), 1)}),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.TRIPLE_SHOT_ESPRESSO,
        new Slot(new Food(FoodType.COFFEE), 3)),
    COOKIE("Cookie", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.COOKIE,
        new Slot[]{new Slot(new Food(FoodType.WHEAT_FLOUR), 1), new Slot(new Misc(MiscType.EGG), 1), new Slot(new Food(FoodType.SUGAR), 1)}),
    HASH_BROWNS("Hash Browns", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.HASH_BROWNS,
        new Slot[]{new Slot(new Food(FoodType.POTATO), 1), new Slot(new Food(FoodType.OIL), 1)}),
    PANCAKES("Pancakes", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.PANCAKES,
        new Slot[]{new Slot(new Food(FoodType.WHEAT_FLOUR), 1), new Slot(new Misc(MiscType.EGG), 1)}),
    FRUIT_SALAD("Fruit Salad", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.FRUIT_SALAD,
        new Slot[]{new Slot(new Food(FoodType.BLUEBERRY), 1), new Slot(new Food(FoodType.MELON), 1), new Slot(new Food(FoodType.APRICOT), 1)}),
    RED_PLATE("Red Plate", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.RED_PLATE,
        new Slot[]{new Slot(new Food(FoodType.RED_CABBAGE), 1), new Slot(new Food(FoodType.RADISH), 1)}),
    BREAD("Bread", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.BREAD,
        new Slot(new Food(FoodType.WHEAT_FLOUR), 1)),
    SALMON_DINNER("Salmon Dinner", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, FoodType.SALMON_DINNER,
        new Slot[]{new Slot(new Fish(FishType.SALMON), 1), new Slot(new Food(FoodType.AMARANTH), 1), new Slot(new Food(FoodType.KALE), 1)}),
    VEGETABLE_MEDLEY("Vegetable Medley", 0, 0, 2, 0, FoodType.VEGETABLE_MEDLEY,
        new Slot[]{new Slot(new Food(FoodType.TOMATO), 1), new Slot(new Food(FoodType.BEET), 1)}),
    FARMERS_LUNCH("Farmer's Lunch", 1, 0, 0, 0, FoodType.FARMERS_LUNCH,
        new Slot[]{new Slot(new Food(FoodType.OMELETTE), 1), new Slot(new Food(FoodType.PARSNIP), 1)}),
    SURVIVAL_BURGER("Survival Burger", 0, 0, 3, 0, FoodType.SURVIVAL_BURGER,
        new Slot[]{new Slot(new Food(FoodType.BREAD), 1), new Slot(new Food(FoodType.CARROT), 1), new Slot(new Food(FoodType.EGGPLANT), 1)}),
    DISH_OF_THE_SEA("Dish 'O The Sea", 0, 0, 0, 2, FoodType.DISH_OF_THE_SEA,
        new Slot[]{new Slot(new Fish(FishType.SARDINE), 2), new Slot(new Food(FoodType.HASH_BROWNS), 1)}),
    SEAFORM_PUDDING("Seaform Pudding", 0, 0, 0, 3, FoodType.SEAFORM_PUDDING,
        new Slot[]{new Slot(new Fish(FishType.FLOUNDER), 1), new Slot(new Fish(FishType.MIDNIGHT_CARP), 1)}),
    MINERS_TREAT("Miner's Treat", 0, 1, 0, 0, FoodType.MINERS_TREAT,
        new Slot[]{new Slot(new Misc(MiscType.MILK), 1), new Slot(new Food(FoodType.SUGAR), 1), new Slot(new Food(FoodType.CARROT), 2)});


    public final String name;
    public final int farmingLevel;
    public final int miningLevel;
    public final int foragingLevel;
    public final int fishingLevel;
    public final FoodType craftingResultType;
    public final Slot[] ingredients;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n");
        stringBuilder.append("Farming Level: ").append(farmingLevel).append("\n");
        stringBuilder.append("Mining Level: ").append(miningLevel).append("\n");
        stringBuilder.append("Foraging Level: ").append(foragingLevel).append("\n");
        stringBuilder.append("Fishing Level: ").append(fishingLevel).append("\n");
        stringBuilder.append(Arrays.toString(ingredients)).append("\n");
        return stringBuilder.toString();
    }

    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
        , FoodType craftingResultType, Slot[] ingredients) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.craftingResultType = craftingResultType;
        this.ingredients = ingredients;
    }

    /// For single ingredient crafting recipes.
    CookingRecipes(String name, int farmingLevel, int miningLevel, int foragingLevel, int fishingLevel
        , FoodType craftingResultType, Slot ingredient) {
        this.name = name;
        this.farmingLevel = farmingLevel;
        this.miningLevel = miningLevel;
        this.foragingLevel = foragingLevel;
        this.fishingLevel = fishingLevel;
        this.craftingResultType = craftingResultType;
        this.ingredients = new Slot[]{ingredient};
    }

    public static CookingRecipes getCookingRecipe(String name) {
        for (CookingRecipes cr : CookingRecipes.values()) {
            if (cr.name.equalsIgnoreCase(name)) {
                return cr;
            }
        }
        return null;
    }
}
