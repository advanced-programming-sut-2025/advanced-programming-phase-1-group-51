package Controllers.Activity;

import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Items.Food;

public class Cooking {

    public Result TakeOutOfRefrigerator(String itemName) {
        Player player = App.getCurrentUser().getCurrentGame().getCurrentPlayer();
        Loot refrigeratorLoot = player.getRefrigeratorLootByName(itemName);

        if (refrigeratorLoot == null) {
            return new Result(false, "You don't have this item in your refrigerator.");
        }

        BackPack backpack = player.getInventory();
        boolean isFull = backpack.getType().getCapacity() == backpack.getLoots().size();
        if (isFull) return new Result(false, "Your inventory is full.");

        Loot existingLoot = backpack.findItemLoot(itemName);
        if (existingLoot != null) {
            int newCount = existingLoot.getCount() + refrigeratorLoot.getCount();
            existingLoot.setCount(Math.min(newCount, existingLoot.getItem().getMaxSize()));
        } else {
            backpack.getLoots().add(refrigeratorLoot);
        }
        player.getRefrigeratorLoots().remove(refrigeratorLoot);
        return new Result(true, "Food has been added to your backpack.");
    }

    public Result PutInRefrigerator(String itemName) {
        Game currentGame = App.getCurrentUser().getCurrentGame();
        Player currentPlayer = currentGame.getCurrentPlayer();
        BackPack playerBackpack = currentPlayer.getInventory();
        Loot itemToStore = playerBackpack.findItemLoot(itemName);

        if (itemToStore == null) {
            return new Result(false, "This item is not in your backpack");
        }

        boolean isEdible = itemToStore.getItem() instanceof Food;
        boolean hasNegativeEnergy = itemToStore.getItem().getEnergyCost() < 0;
        if (!isEdible || !hasNegativeEnergy) {
            return new Result(false, "You can only put edibles in the refrigerator.");
        }

        Loot fridgeItem = currentPlayer.getRefrigeratorLootByName(itemName);
        playerBackpack.getLoots().remove(itemToStore);

        if (fridgeItem != null) {
            int combinedCount = fridgeItem.getCount() + itemToStore.getCount();
            fridgeItem.setCount(Math.min(combinedCount, fridgeItem.getItem().getMaxSize()));
        } else {
            currentPlayer.getRefrigeratorLoots().add(itemToStore);
        }
        return new Result(true, "Your food added to refrigerator successfully");
    }

    public Result prepareFood(String foodName){
        Player chef = App.getCurrentUser().getCurrentGame().getCurrentPlayer();
        CookingRecipes desiredRecipe = null;

        for (CookingRecipes r : chef.getCookingRecipes()) {
            if (r.name.equalsIgnoreCase(foodName)) desiredRecipe = r;
        }
        if (desiredRecipe == null) return new Result(false, "No recipe found for: " + foodName);

        BackPack ingredientsContainer = chef.getInventory();
        if (ingredientsContainer.getType().getCapacity() == ingredientsContainer.getLoots().size()) {
            return new Result(false, "Your inventory is full.");
        }

        int energyCost = 3;
        if (chef.getCurrentTurnUsedEnergy() + energyCost > 50) {
            return new Result(false, "You don't have enough Energy! for this turn!");
        }
        if (chef.getEnergy() - energyCost < 0) {
            return new Result(false, "You don't have enough Energy!");
        }

        chef.setEnergy(chef.getEnergy() - energyCost);
        chef.setCurrentTurnUsedEnergy(chef.getCurrentTurnUsedEnergy() + energyCost);

        for (Loot required : desiredRecipe.ingredients) {
            String ingredientName = required.getItem().getName();
            int needed = required.getCount();
            int available = 0;

            Loot inBackpack = ingredientsContainer.findItemLoot(ingredientName);
            Loot inFridge = chef.getRefrigeratorLootByName(ingredientName);

            if (inBackpack != null) available += inBackpack.getCount();
            if (inFridge != null) available += inFridge.getCount();
            if (available < needed) return new Result(false, "You don't have the ingredients to cook this recipe.");
        }

        for (Loot component : desiredRecipe.ingredients) {
            processIngredientRemoval(component, chef, ingredientsContainer);
        }

        Food preparedFood = new Food(Quality.DEFAULT, desiredRecipe.craftingResultType);
        Loot foodLoot = new Loot(preparedFood, 1);
        addToInventory(foodLoot, ingredientsContainer);

        return new Result(true, "You cooked " + foodName);
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
            return new Result(false, "Item not in your inventory");
        }

        if (!(loot.getItem() instanceof Food) || loot.getItem().getEnergyCost() >= 0) {
            return new Result(false, "its not edible.");
        }

        Food food = (Food) loot.getItem();
        loot.setCount(loot.getCount() - 1);

        if (loot.getCount() == 0) {
            backpack.getLoots().remove(loot);
        }

        player.getActiveBuffs().add(new ActiveBuff(food.getBuff()));
        player.setEnergy((int) Math.min(player.getEnergy() - food.getEnergyCost(), player.getMaxEnergy()));

        return new Result(true, "You ate " + foodName);
    }


    public Result showCookingRecipes() {
        Player recipeOwner = App.getCurrentUser().getCurrentGame().getCurrentPlayer();
        String header = "Cooking recipes : \n";
        StringBuilder recipeList = new StringBuilder(header);

        for (CookingRecipes r : recipeOwner.getCookingRecipes()) {
            recipeList.append(r.toString()).append("\n");
        }

        return new Result(true, recipeList.toString());
    }

}
