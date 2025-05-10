package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Types.ItemTypes.FoodType;
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
            backpackLoot.setCount(Math.min(backpackLoot.getCount() + refrigeratorLoot.getCount(), backpackLoot.getItem().getMaxSize()));
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
            return new Result(false, "You can only put edibles in the refrigerator.");
        }

        Loot refrigeratorLoot = player.getRefrigeratorLootByName(itemName);
        backpack.getLoots().remove(backpackLoot);

        if (refrigeratorLoot == null) {
            player.getRefrigeratorLoots().add(backpackLoot);
        } else {
            refrigeratorLoot.setCount(Math.min(refrigeratorLoot.getCount() + backpackLoot.getCount(), refrigeratorLoot.getItem().getMaxSize()));
        }

        return new Result(true, "Your food added to refrigerator successfully");
    }

    public Result prepareFood(String foodName){
        CookingRecipes foodRecipe = null;
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        for (CookingRecipes recipes : player.getCookingRecipes()) {
            if (recipes.name.compareToIgnoreCase(foodName) == 0) {
                foodRecipe = recipes;
                break;
            }
        }

        if (foodRecipe == null) {
            return new Result(false, "No recipe exists for: " + foodName);
        }

        BackPack backpack = player.getInventory();

        if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
            return new Result(false, "Your inventory is full.");
        }

        if (player.getCurrentTurnUsedEnergy() + 3 > 50) {
            return new Result(false, "You will exceed your max energy usage limit in this turn!");
        }

        if (player.getEnergy() - 3 < 0) {
            return new Result(false, "You don't have enough energy to cook a food.");
        }

        player.setEnergy(player.getEnergy() - 3);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + 3);

        for (Loot ingredient : foodRecipe.ingredients) {
            Loot backpackLoot = backpack.getLootByItemName(ingredient.getItem().getName());
            Loot refrigeratorLoot = player.getRefrigeratorLootByName(ingredient.getItem().getName());

            int availableAmount = 0;

            if (refrigeratorLoot != null) {
                availableAmount += refrigeratorLoot.getCount();
            }

            if (backpackLoot != null) {
                availableAmount += backpackLoot.getCount();
            }

            if (availableAmount < ingredient.getCount()) {
                return new Result(false, "You don't have enough ingredients to cook this recipe.");
            }
        }

        for (Loot ingredient : foodRecipe.ingredients) {
            Loot backpackLoot = backpack.getLootByItemName(ingredient.getItem().getName());
            Loot refrigeratorLoot = player.getRefrigeratorLootByName(ingredient.getItem().getName());

            int neededCount = ingredient.getCount();

            //first check the refrigerator

            if (refrigeratorLoot != null) {
                int availableAmountInFridge = refrigeratorLoot.getCount();

                if (availableAmountInFridge > neededCount) {
                    refrigeratorLoot.setCount(availableAmountInFridge - neededCount);
                    neededCount = 0;
                } else {
                    neededCount -= availableAmountInFridge;
                    player.getRefrigeratorLoots().remove(refrigeratorLoot);
                }
            }

            if (neededCount > 0) {
                backpackLoot.setCount(backpackLoot.getCount() - neededCount);
                if (backpackLoot.getCount() == 0) {
                    backpack.getLoots().remove(backpackLoot);
                }
                neededCount = 0;
            }
        }

        Loot cookedfoodLoot = new Loot(new Food(Quality.DEFAULT, foodRecipe.cookingResultType), 1);

        Loot destinationLoot = backpack.getLootByItemName(cookedfoodLoot.getItem().getName());

        if (destinationLoot == null) {
            backpack.getLoots().add(cookedfoodLoot);
        } else {
            destinationLoot.setCount(destinationLoot.getCount() + cookedfoodLoot.getCount());
        }

        return new Result(true, "You cooked " + foodName);
    }

    public Result Eating(String foodName){
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();

        Loot backpackLoot = backpack.getLootByItemName(foodName);

        if(backpackLoot == null){
            return new Result(false, "You can only eat foods that are in your inventory or refrigerator");
        }
        player.setEnergy(player.getEnergy() + FoodType.getEnergy(foodName));
        backpackLoot.setCount(backpackLoot.getCount() - 1);

        return new Result(false, "You ate " + foodName);
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
