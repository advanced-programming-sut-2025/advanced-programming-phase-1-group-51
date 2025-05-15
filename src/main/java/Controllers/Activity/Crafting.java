package Controllers.Activity;
import Controllers.BaseController;
import Models.*;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.Maps.Village;

public class Crafting extends BaseController {



    public Result showCraftingRecipes() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        String header = "Crafting recipes : \n";
        StringBuilder recipeList = new StringBuilder(header);
        if(player.isInHouse()){

        for (CraftingRecipes r : player.getCraftingRecipes()) {
            recipeList.append(r.toString()).append("\n");
        }

        return saveGameState(game)
                .combine(Result.success( recipeList.toString()));
        }
        return  Result.failure( "You are not in house!");
    }

    public Result craftingCraft(String itemName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        CraftingRecipes craftingRecipes = null;
        if (player.isInHouse()) {
            for (CraftingRecipes recipes : player.getCraftingRecipes()) {
                if (recipes.name.equalsIgnoreCase(itemName)) {
                    craftingRecipes = recipes;
                    break;
                }
            }

            if (craftingRecipes == null) {
                return  Result.failure( "No recipe exists for: " + craftingRecipes);
            }

            BackPack backpack = player.getInventory();

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                return  Result.failure( "Your inventory is full.");
            }

            if (player.getEnergy() - 2 < 0) {
                return  Result.failure( "You don't have enough energy to craft Item");
            }

            if (player.getCurrentTurnUsedEnergy() + 2 > 50) {
                return  Result.failure( "You don't have enough energy in this turn");
            }


            for (Loot ingredient : craftingRecipes.ingredients) {
                Loot backpackLoot = backpack.findItemLoot(ingredient.getItem().getName());
                int availableAmount = 0;

                if (backpackLoot != null) {
                    availableAmount += backpackLoot.getCount();
                }

                if (availableAmount < ingredient.getCount()) {
                    return  Result.failure( "You don't have enough ingredients to craft this Item");
                }

                int neededCount = ingredient.getCount();

                backpackLoot.setCount(backpackLoot.getCount() - neededCount);
                if (backpackLoot.getCount() == 0) {
                    backpack.getLoots().remove(backpackLoot);
                }
                neededCount = 0;
            }
            player.setEnergy(player.getEnergy() - 2);
            player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + 2);


            //Loot craftedItemLoot = new Loot(new ArtisanBlock(craftingRecipes.), 1);

//        Loot destinationLoot = backpack.getLootByItemName(craftedItemLoot.getItem().getName());
//
//        if (destinationLoot == null) {
//            backpack.getLoots().add(craftedItemLoot);
//        } else {
//            destinationLoot.setCount(destinationLoot.getCount() + craftedItemLoot.getCount());
//        }

            return saveGameState(game)
                .combine(Result.success( "You crafted " + itemName));

        }
        return  Result.failure( "You are not in house!");

    }

    public Result placeItem(String name, String direction) {;
        CraftingRecipes craftsRecipe = null;
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        Village village = game.getMap().getVillage();
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = Direction.directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell;
        if(player.isInFarm()){
            targetCell = farm.findCellFarm(targetCellX, targetCellY);
        }
        else if(player.isInVillage()){
            targetCell = village.findCellVillage(targetCellX, targetCellY);
        }
        else{
            return  Result.failure("You are not in village or farm");
        }


        Loot backpackloot = player.getInventory().findItemLoot(name);

        if (backpackloot == null) {
            return  Result.failure( "There is not any " + name + " in your inventory");
        }


        backpackloot.setCount(backpackloot.getCount() - 1);
        if (backpackloot.getCount() == 0) {

        }
        return saveGameState(game)
                .combine(Result.success( "You placed " + name + " in " + direction));
    }





}
