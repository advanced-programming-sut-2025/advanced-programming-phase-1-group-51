package Controllers.Activity;

import Controllers.BaseController;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Items.Food;

public class Cooking extends BaseController {

    public Result TakeOutOfRefrigerator(String itemName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Loot refrigeratorLoot = player.getRefrigeratorLootByName(itemName);

        if (refrigeratorLoot == null) {
            return  Result.failure( "You don't have this item in your refrigerator.");
        }

        BackPack backpack = player.getInventory();
        boolean isFull = backpack.getType().getCapacity() == backpack.getLoots().size();
        if (isFull) return  Result.failure( "Your inventory is full.");

        Loot existingLoot = backpack.findItemLoot(itemName);
        if (existingLoot != null) {
            int newCount = existingLoot.getCount() + refrigeratorLoot.getCount();
            existingLoot.setCount(Math.min(newCount, existingLoot.getItem().getMaxSize()));
        } else {
            backpack.getLoots().add(refrigeratorLoot);
        }
        player.getRefrigeratorLoots().remove(refrigeratorLoot);
        return saveGameState(game)
                .combine(Result.success( "Food has been added to your backpack."));
    }

    public Result PutInRefrigerator(String itemName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack playerBackpack = player.getInventory();
        Loot itemToStore = playerBackpack.findItemLoot(itemName);

        if (itemToStore == null) {
            return  Result.failure( "This item is not in your backpack");
        }

        boolean isEdible = itemToStore.getItem() instanceof Food;
        boolean hasNegativeEnergy = itemToStore.getItem().getEnergyCost() < 0;
        if (!isEdible || !hasNegativeEnergy) {
            return  Result.failure( "You can only put edibles in the refrigerator.");
        }

        Loot fridgeItem = player.getRefrigeratorLootByName(itemName);
        playerBackpack.getLoots().remove(itemToStore);

        if (fridgeItem != null) {
            int combinedCount = fridgeItem.getCount() + itemToStore.getCount();
            fridgeItem.setCount(Math.min(combinedCount, fridgeItem.getItem().getMaxSize()));
        } else {
            player.getRefrigeratorLoots().add(itemToStore);
        }
        return saveGameState(game)
                .combine(Result.success( "Your food added to refrigerator successfully"));
    }

    public Result prepareFood(String foodName){
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        CookingRecipes desiredRecipe = null;

        for (CookingRecipes r : player.getCookingRecipes()) {
            if (r.name.equalsIgnoreCase(foodName)) desiredRecipe = r;
        }
        if (desiredRecipe == null) return  Result.failure( "No recipe found for: " + foodName);

        BackPack ingredientsContainer = player.getInventory();
        if (ingredientsContainer.getType().getCapacity() == ingredientsContainer.getLoots().size()) {
            return  Result.failure( "Your inventory is full.");
        }

        int energyCost = 3;
        if (player.getCurrentTurnUsedEnergy() + energyCost > 50) {
            return  Result.failure( "You don't have enough Energy! for this turn!");
        }
        if (player.getEnergy() - energyCost < 0) {
            return  Result.failure( "You don't have enough Energy!");
        }

        player.setEnergy(player.getEnergy() - energyCost);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + energyCost);

        for (Loot required : desiredRecipe.ingredients) {
            String ingredientName = required.getItem().getName();
            int needed = required.getCount();
            int available = 0;

            Loot inBackpack = ingredientsContainer.findItemLoot(ingredientName);
            Loot inFridge = player.getRefrigeratorLootByName(ingredientName);

            if (inBackpack != null) available += inBackpack.getCount();
            if (inFridge != null) available += inFridge.getCount();
            if (available < needed) return  Result.failure( "You don't have the ingredients to cook this recipe.");
        }

        for (Loot component : desiredRecipe.ingredients) {
            processIngredientRemoval(component, player, ingredientsContainer);
        }

        Food preparedFood = new Food(Quality.DEFAULT, desiredRecipe.craftingResultType);
        Loot foodLoot = new Loot(preparedFood, 1);
        addToInventory(foodLoot, ingredientsContainer);

        return saveGameState(game)
                .combine(Result.success( "You cooked " + foodName));
    }

    private void processIngredientRemoval(Loot ingredient, Player player, BackPack backpack) {
        int remaining = ingredient.getCount();
        Loot fridgeItem = player.getRefrigeratorLootByName(ingredient.getItem().getName());

        if (fridgeItem != null) {
            int fridgeQty = fridgeItem.getCount();
            if (fridgeQty >= remaining) {
                fridgeItem.setCount(fridgeQty - remaining);
                return;
            } else {
                remaining -= fridgeQty;
                player.getRefrigeratorLoots().remove(fridgeItem);
            }
        }

        Loot backpackItem = backpack.findItemLoot(ingredient.getItem().getName());
        if (backpackItem != null) {
            backpackItem.setCount(backpackItem.getCount() - remaining);
            if (backpackItem.getCount() <= 0) {
                backpack.getLoots().remove(backpackItem);
            }
        }
    }

    private void addToInventory(Loot item, BackPack backpack) {
        Loot existing = backpack.findItemLoot(item.getItem().getName());
        if (existing == null) {
            backpack.getLoots().add(item);
        } else {
            existing.setCount(existing.getCount() + item.getCount());
        }
    }

    public Result Eating(String foodName) {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();

        Loot loot = backpack.findItemLoot(foodName);

        if (loot == null) {
            return  Result.failure( "Item not in your inventory");
        }

        if (!(loot.getItem() instanceof Food) || loot.getItem().getEnergyCost() >= 0) {
            return  Result.failure( "its not edible.");
        }

        Food food = (Food) loot.getItem();
        loot.setCount(loot.getCount() - 1);

        if (loot.getCount() == 0) {
            backpack.getLoots().remove(loot);
        }

        player.getActiveBuffs().add(new ActiveBuff(food.getBuff()));
        player.setEnergy((int) Math.min(player.getEnergy() - food.getEnergyCost(), player.getMaxEnergy()));

        return saveGameState(game)
                .combine(Result.success( "You ate " + foodName));
    }


    public Result showCookingRecipes() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        String header = "Cooking recipes : \n";
        StringBuilder recipeList = new StringBuilder(header);

        for (CookingRecipes r : player.getCookingRecipes()) {
            recipeList.append(r.toString()).append("\n");
        }

        return saveGameState(game)
                .combine(Result.success( recipeList.toString()));
    }

}
