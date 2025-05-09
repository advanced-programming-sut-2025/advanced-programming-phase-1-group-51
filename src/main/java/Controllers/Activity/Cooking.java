package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Items.Food;

public class Cooking extends Controller {


    public Result TakeOutOfRefrigerator(String itemName) {
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();

        Loot refrigeratorLoot = player.getRefrigeratorLootByName(itemName);

        if (refrigeratorLoot == null) {
            return new Result(false, "You don't have this item in your refrigerator.");
        }

        if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
            return new Result(false, "Your inventory is full.");
        }

        Loot backpackLoot = backpack.getLootByItemName(itemName);

        if (backpackLoot == null) {
            backpack.getLoots().add(refrigeratorLoot);
            player.getRefrigeratorLoots().remove(refrigeratorLoot);
        } else {
            player.getRefrigeratorLoots().remove(refrigeratorLoot);
            backpackLoot.setCount(Math.min(backpackLoot.getCount() + refrigeratorLoot.getCount(), backpackLoot.getItem().getMaxStackSize()));
        }

        return new Result(true, "Food has been added to your backpack.");
    }

    public Result PutInRefrigerator(String itemName) {
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();

        Loot backpackLoot = backpack.getLootByItemName(itemName);

        if (backpackLoot == null) {
            return new Result(false, "This item is not in your backpack");
        }

        if (!(backpackLoot.getItem() instanceof Food) || backpackLoot.getItem().getEnergyCost() >= 0) {
            return new Result(false, "You can't put anything other than edible food in the refrigerator.");
        }

        Loot refrigeratorLoot = player.getRefrigeratorLootByName(itemName);

        backpack.getLoots().remove(backpackLoot);

        if (refrigeratorLoot == null) {
            player.getRefrigeratorLoots().add(backpackLoot);
        } else {
            refrigeratorLoot.setCount(Math.min(refrigeratorLoot.getCount() + backpackLoot.getCount(), refrigeratorLoot.getItem().getMaxStackSize()));
        }

        return new Result(true, "Your food added to refrigerator successfully");
    }

    public Result prepareFood(String itemName){
        CookingRecipes targetRecipe = null;
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();

        for (CookingRecipes recipes : player.getCookingRecipes()) {
            if (recipes.name.compareToIgnoreCase(itemName) == 0) {
                targetRecipe = recipes;
                break;
            }
        }

        if (targetRecipe == null) {
            return new Result(false, "No recipe found for: " + itemName);
        }

        BackPack backpack = player.getInventory();

        if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
            return new Result(false, "Your inventory is full.");
        }

        if (player.getUsedEnergy() + 3 > 50) {
            return new Result(false, "You will exceed your max energy usage limit!");
        }

        if (player.getEnergy() - 3 < 0) {
            return new Result(false, "You don't have enough energy to craft an item.");
        }

        player.setEnergy(player.getEnergy() - 3);
        player.setUsedEnergy(player.getUsedEnergy() + 3);

        for (Loot ingredient : targetRecipe.ingredients) {
            Loot backpackLoot = backpack.getLootByItemName(ingredient.getItem().getName());
            Loot refrigeratorLoot = player.getRefrigeratorLootByName(ingredient.getItem().getName());

            int cumulativeCount = 0;

            if (refrigeratorLoot != null) {
                cumulativeCount += refrigeratorLoot.getCount();
            }

            if (backpackLoot != null) {
                cumulativeCount += backpackLoot.getCount();
            }

            if (cumulativeCount < ingredient.getCount()) {
                return new Result(false, "You don't have the ingredients to cook this recipe.");
            }
        }



        //deduct ingredients.
        for (Loot ingredient : targetRecipe.ingredients) {
            Loot backpackLoot = backpack.getLootByItemName(ingredient.getItem().getName());
            Loot refrigeratorLoot = player.getRefrigeratorLootByName(ingredient.getItem().getName());

            int countToReduce = ingredient.getCount();

            //prioritize the refrigerator count.

            if (refrigeratorLoot != null) {
                int howMuchInrefrigerator = refrigeratorLoot.getCount();

                if (howMuchInrefrigerator > countToReduce) {
                    refrigeratorLoot.setCount(howMuchInrefrigerator - countToReduce);
                    countToReduce = 0;
                } else {
                    countToReduce -= howMuchInrefrigerator;
                    player.getRefrigeratorLoots().remove(refrigeratorLoot);
                }
            }

            if (countToReduce > 0) {
                backpackLoot.setCount(backpackLoot.getCount() - countToReduce);
                if (backpackLoot.getCount() == 0) {
                    backpack.getLoots().remove(backpackLoot);
                }
                countToReduce = 0;
            }
        }

        Loot cookedItemLoot = new Loot(new Food(Quality.DEFAULT, targetRecipe.craftingResultType), 1);

        Loot destinationLoot = backpack.getLootByItemName(cookedItemLoot.getItem().getName());

        if (destinationLoot == null) {
            backpack.getLoots().add(cookedItemLoot);
        } else {
            destinationLoot.setCount(destinationLoot.getCount() + cookedItemLoot.getCount());
        }

        return new Result(true, "You cooked " + itemName);
    }

    public Result Eating(String foodName){
        return null;
    }

    public Result showCookingRecipes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cooking recipes : \n");

        Game game = Game.getCurrentUser().getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        for (CookingRecipes recipe : currentPlayer.getCookingRecipes()) {
            stringBuilder.append(recipe.toString()).append("\n");
        }

        return new Result(true, stringBuilder.toString());
    }

}
