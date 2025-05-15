package Controllers.Others;

import Controllers.BaseController;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Others.Season;
import Models.Enums.Others.SkillLevel;
import Models.Enums.Others.Weather;
import Models.Enums.Types.AnimalType;
import Models.Enums.Types.BackpackType;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.ForagingTreeType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Items.*;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryController  extends BaseController {


    public Result showInventory() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        BackPack inventory = currentPlayer.getInventory();
        StringBuilder output = new StringBuilder();

        output.append("Backpack Type: ").append(inventory.getType()).append("\n");
        output.append("Capacity: ");
        if (inventory.getType() == BackpackType.DELUXE) {
            output.append("Unlimited\n");
        } else {
            output.append(inventory.getLoots().size()).append("/")
                    .append(inventory.getType().getCapacity()).append(" loots used\n");
        }


        output.append("\nInventory Items:\n");
        ArrayList<Loot> items = inventory.getLoots();
        if (items.isEmpty()) {
            output.append("Your inventory is empty.\n");
        } else {
            for (Loot loot : items) {
                output.append("- ").append(loot.getItem().getName())
                        .append(": ").append(loot.getCount()).append("\n");
            }
        }

        return saveGameState(game)
                .combine(Result.success( output.toString()));
    }

    public Result inventoryTrashFull(String itemName) {
        // TODO
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();
        Loot itemLoot = backpack.findItemLoot(itemName);
        backpack.getLoots().remove(itemLoot);
        return  Result.failure( itemName + "totally removed from backpack");
    }

    public Result inventoryTrashNotFull(String itemName, int itemCount) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        if (itemCount <= 0) {
            return  Result.failure( "Item count must be positive.");
        }

        BackPack inventory = currentPlayer.getInventory();
        ArrayList<Loot> items = inventory.getLoots();
        TrashCan trashCan = currentPlayer.getTrashcan();


        for (Loot loot : items) {
            if (loot.getItem().getName().equalsIgnoreCase(itemName)) {
                int currentCount = loot.getCount();
                if (currentCount < itemCount) {
                    return  Result.failure( "Not enough " + itemName +
                            " in inventory. You only have " + currentCount);
                }


                int cashBack = 0;
                if (trashCan.getPercentOfCashBack() > 0) {
                    cashBack = (int)(loot.getItem().getValue() * itemCount *
                            trashCan.getPercentOfCashBack() / 100.0);
                    currentPlayer.setMoney(currentPlayer.getMoney() + cashBack);
                }

                loot.setCount(currentCount - itemCount);

                if (loot.getCount() == 0) {
                    items.remove(loot);
                }

                String message = "Removed " + itemCount + " of " + itemName + " from inventory.";
                if (cashBack > 0) {
                    message += " You received " + cashBack + " gold.";
                }
                return saveGameState(game)
                .combine(Result.success( message));
            }
        }

        return  Result.failure( "Item not found in inventory: " + itemName);
    }


    public Result toolEquip(String toolName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        ToolType toolType = ToolType.findToolTypeByName(toolName);
        if (toolType == null) {
            return  Result.failure( "Invalid tool name: " + toolName);
        }

        // Search player's inventory for this tool
        for (Loot loot : player.getInventory().getLoots()) {
            Item item = loot.getItem();
            if (item instanceof Tool) {
                Tool toolItem = (Tool) item;
                if (toolItem.getType() == toolType) {
                    // Found the tool - set it in hand
                    player.setItemInHand(toolItem);
                    return saveGameState(game)
                .combine(Result.success( toolName + " equipped successfully"));
                }
            }
        }

        return  Result.failure( "You don't have a " + toolName + " in your inventory");
    }

    public Result showCurrentTool() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Item itemInHand = player.getItemInHand();

        if (itemInHand instanceof Tool) {
            Tool tool = (Tool) itemInHand;
            return saveGameState(game)
                .combine(Result.success( "You have a " + tool.getName() + " in your hand"));
        }
        else {
            return  Result.failure( "You don't have a tool in your hands");
        }
    }

    public Result showAvailableTools() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        BackPack backpack = player.getInventory();
        if (backpack == null || backpack.getLoots().isEmpty()) {
            return  Result.failure( "Your backpack is empty");
        }

        StringBuilder output = new StringBuilder();
        output.append("Tools in your backpack:\n");
        output.append("------------------------\n");

        boolean hasTools = false;

        // Count tools by type and quality
        Map<String, Integer> toolCounts = new LinkedHashMap<>();

        for (Loot loot : backpack.getLoots()) {
            Item item = loot.getItem();
            if (item instanceof Tool) {
                Tool tool = (Tool) item;
                String toolKey = tool.getQuality() + " " + tool.getType().name();
                toolCounts.put(toolKey, toolCounts.getOrDefault(toolKey, 0) + loot.getCount());
                hasTools = true;
            }
        }

        if (!hasTools) {
            return  Result.failure( "You don't have any tools in your backpack");
        }

        // Format the output
        for (Map.Entry<String, Integer> entry : toolCounts.entrySet()) {
            output.append("- ").append(entry.getKey())
                    .append(" (x").append(entry.getValue()).append(")\n");
        }

        // Add water info for watering cans
        output.append("\nNote: Watering cans show their current water level\n");

        return saveGameState(game)
                .combine(Result.success( output.toString()));
    }


    public Result toolUse(String direction) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (player.getItemInHand() == null) {
            return  Result.failure( "You don't have any item in your hand");
        }
        if (!(player.getItemInHand() instanceof Tool)) {
            return  Result.failure( "You don't have any tool in your hand");
        }
        if (!(direction.equals("up") || direction.equals("down") || direction.equals("left") || direction.equals("right") || 
                direction.equals("up left") || direction.equals("up right") || direction.equals("down left") || direction.equals("down right") )) {
            return  Result.failure( "wrong direction.");
        }

        Tool toolInHand = (Tool) player.getItemInHand();
        ToolType toolType = toolInHand.getType();

        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = Direction.directionManaging(direction,playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCellFarm(targetCellX, targetCellY);

        if (toolType == ToolType.HOE) {
            return hoeUse(player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality(),targetCell, player, game);
        }
        else if (toolType == ToolType.PICKAXE) {
            return pickaxeUse(targetCell, player
                    , player.getMiningSkill().getLevel()
                    , toolInHand.getQuality()
                    , player.getMiningSkill().getLevel().energyCostDiscount, game);
        }
        else if (toolType == ToolType.AXE) {
            return axeUse(targetCell, player, toolInHand.getQuality()
                    , player.getForagingSkill().getLevel().energyCostDiscount, game);
        }
        else if (toolType == ToolType.WATERING_CAN_DEFAULT) {
            return wateringCanUse(targetCell, player, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand, game);
        }
        else if (toolType == ToolType.WATERING_CAN_COPPER) {
            return wateringCanUse(targetCell, player, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand, game);
        }
        else if (toolType == ToolType.WATERING_CAN_IRON) {
            return wateringCanUse(targetCell,player, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand, game);
        }
        else if (toolType == ToolType.WATERING_CAN_GOLD) {
            return wateringCanUse(targetCell, player, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand, game);
        }
        else if (toolType == ToolType.WATERING_CAN_IRIDIUM) {
            return wateringCanUse(targetCell, player, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand, game);
        }
        else if (toolType == ToolType.FISHING_ROD) {
            return fishingRodUse(targetCell, player, toolInHand.getQuality()
                    , player.getFishingSkill().getLevel().energyCostDiscount, game);
        }
        else if (toolType == ToolType.SCYTHE) {
            return scytheUse(targetCell, player, game);
        }
        else if (toolType == ToolType.MILK_PAIL) {
            return milkPailUse(targetCell, player, game);
        }
        else if (toolType == ToolType.SHEAR) {
            return shearUse(targetCell, player, game);
        }
        else {
            return  Result.failure( "Invalid tool");
        }
    }




    private Result hoeUse(int energyDiscount, Quality quality, Cells targetCell, Player player, Game game){

        int takenEnergy;
        takenEnergy = 5 - quality.getQualityLevel() - energyDiscount;
        if (takenEnergy < 0) {
            takenEnergy = 0;
        }

        if (game.getWeatherToday() == Weather.SNOW) {
            takenEnergy *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            takenEnergy *= 1.5;
        }
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (targetCell == null) {
            return  Result.failure( "Target cell does not exist");
        }

        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }


        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (!(targetCell.getObjectOnCell() instanceof BurntCell)) {
            return  Result.failure( "cell in this direction is not empty");
        }
        targetCell.setHasBeenPlowed(true);
        return saveGameState(game)
                .combine(Result.success( "Target cell is now plowed."));
    }

    private boolean canPickaxeMineThis( ForagingMineralType target, Quality pickaxeQuality) {
        if (pickaxeQuality == Quality.DEFAULT) {
            if (target ==  ForagingMineralType.STONE) {
                return true;
            } else if (target ==  ForagingMineralType.COPPER_ORE) {
                return true;
            } else if (target ==  ForagingMineralType.COAL) {
                return true;
            }
            return false;
        }
        if (pickaxeQuality == Quality.COPPER) {
            if (target ==  ForagingMineralType.STONE) {
                return true;
            } else if (target ==  ForagingMineralType.COPPER_ORE) {
                return true;
            } else if (target ==  ForagingMineralType.COAL) {
                return true;
            } else if (target ==  ForagingMineralType.IRON_ORE) {
                return true;
            }
            return false;
        }
        if (pickaxeQuality == Quality.SILVER) {
            if (target ==  ForagingMineralType.GOLD_ORE) {
                return false;
            } else if (target ==  ForagingMineralType.IRIDIUM_ORE) {
                return false;
            }
            return true;
        }
        return true;
    }

    private Result pickaxeUse(Cells targetCell, Player player, SkillLevel skillLevel
            , Quality quality, int energyDiscount, Game game) {
        int energyCost;
         energyCost = 5 - quality.getQualityLevel() - energyDiscount;
        if (energyCost < 0) {
            energyCost = 0;
        }

        if (game.getWeatherToday() == Weather.SNOW) {
            energyCost *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            energyCost *= 1.5;
        }


        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (energyCost + currentEnergyUsed > 50) {
            return  Result.failure( "You can't perform this activity. " +
                    "You will exceed your energy usage limit.");
        }

        if (playerEnergy - energyCost < 0) {
            return  Result.failure( "You don't have enough energy.");
        }

        if (targetCell == null) {
            return  Result.failure( "Target cell not found.");
        }

        player.setEnergy(player.getEnergy() - energyCost);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + energyCost);


        if (targetCell.getObjectOnCell() instanceof Mineral) {

            ForagingMineralType type = ((Mineral) targetCell.getObjectOnCell()).getType();

            BackPack backpack = player.getInventory();
            Loot slot = backpack.findItemLoot(type.name);

            if (!canPickaxeMineThis(type, quality)) {

                return  Result.failure( "Pickaxe is too weak to mine this block.");
            }

//            player.getUnbuffedMiningSkill().setXp(player.getUnbuffedMiningSkill().getXp() + 10);
//
//            int cellX = targetCell.getCoordinate().getX();
//            int cellY = targetCell.getCoordinate().getY();

//            boolean check = cellX <= 9 && cellY <= 11;

//            targetCell.setObjectOnCell(check ? new BuildingCell(true, "Mine") : new BurntCell());

            int count = 1;
            if (skillLevel.ordinal() <= 2) {
                count++;
            }

            if (backpack.getLoots().size() == backpack.getType().getCapacity()) {
                if (slot == null) {
                    System.out.println("No space in backpack.");
                } else {
                    slot.setCount(Math.min(slot.getCount() + count, slot.getItem().getMaxSize()));
                    System.out.println("Added " + count + " to backpack.");
                }
            }
//            else {
//                if (slot == null) {
//                    backpack.getLoots().add(new
//                            Loot(new ForagingMineralItem(Quality.DEFAULT, type), count));
//                } else {
//                    slot.setCount(Math.min(slot.getItem().getMaxSize(), slot.getCount() + count));
//                }
//                System.out.println("Added " + count + " to backpack.");
//            }


            return saveGameState(game)
                .combine(Result.success( "Mined stone/mineral at target cell."));
        }

        if (targetCell.getObjectOnCell() instanceof DroppedItemCell) {

            DroppedItemCell droppedItem = (DroppedItemCell) targetCell.getObjectOnCell();
            Loot droppedSlot = new Loot(droppedItem.getItem(), droppedItem.getQuantity());
            BackPack backpack = player.getInventory();
            Loot backpackSlot = backpack.findItemLoot(droppedSlot.getItem().getName());

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (backpackSlot == null) {

                    return  Result.failure( "Backpack was full! Couldn't retrieve item from the ground.");
                } else {
                    backpackSlot.setCount(Math.min(backpackSlot.getCount() + droppedSlot.getCount(), backpackSlot.getItem().getMaxSize()));
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + droppedItem.getQuantity() + ")."));
                }
            } else {
                if (backpackSlot == null) {
                    backpack.getLoots().add(droppedSlot);
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + droppedItem.getQuantity() + ")."));
                } else {
                    backpackSlot.setCount(Math.min(backpackSlot.getCount() + droppedSlot.getCount(), backpackSlot.getItem().getMaxSize()));
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + droppedItem.getQuantity() + ")."));
                }
            }
        }

        if (targetCell.getObjectOnCell() instanceof ArtisanCell) {
            ArtisanCell block = (ArtisanCell) targetCell.getObjectOnCell();
            Loot droppedSlot = new Loot(new Else(ElseType.getElseTypeByName(block.getArtisanType().name), Quality.DEFAULT), 1);

            BackPack backpack = player.getInventory();
            Loot backpackSlot = backpack.findItemLoot(droppedSlot.getItem().getName());

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (backpackSlot == null) {

                    return  Result.failure( "Backpack was full! Couldn't retrieve item from the ground.");
                } else {
                    backpackSlot.setCount(Math.min(backpackSlot.getCount() + droppedSlot.getCount(), backpackSlot.getItem().getMaxSize()));
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + 1 + ")."));
                }
            } else {
                if (backpackSlot == null) {
                    backpack.getLoots().add(droppedSlot);
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + 1 + ")."));
                } else {
                    backpackSlot.setCount(Math.min(backpackSlot.getCount() + droppedSlot.getCount(), backpackSlot.getItem().getMaxSize()));
                    targetCell.setObjectOnCell(new BurntCell());

                    return saveGameState(game)
                .combine(Result.success( "Item has been added to the backpack: " + droppedSlot.getItem().getName() + " x(" + 1 + ")."));
                }
            }
        }


        return  Result.failure( "No operation was performed.");
    }

    private Result axeUse(Cells targetCell, Player player, Quality quality, int energyDiscount, Game game) {

        int takenEnergy;
        takenEnergy = 5 - quality.getQualityLevel() - energyDiscount;
        if (takenEnergy < 0) {
            takenEnergy = 0;
        }

        if (game.getWeatherToday() == Weather.SNOW) {
            takenEnergy *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            takenEnergy *= 1.5;
        }
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (targetCell == null) {
            return  Result.failure( "Target cell does not exist");
        }

        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (!(targetCell.getObjectOnCell() instanceof Tree tree)) {
            return  Result.failure( "Target cell is not a tree or wood");
        }

        if (tree.getTreeType() == ForagingTreeType.NORMAL_TREE) {
            int woodCount = (int) (Math.random() * 4 + 2);
            targetCell.setObjectOnCell(new Tree(ForagingTreeType.TREE_BARK));

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot("Wood");

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (loot == null) {
                    return  Result.failure(
                            "yous backpack is full");
                }

                loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                return saveGameState(game)
                .combine(Result.success( "you got " + woodCount + " woods"));
            }

            else {
                if (loot == null) {
                    backpack.getLoots().add(new Loot(new Else(ElseType.WOOD, Quality.DEFAULT), woodCount));
                }
                else {
                    loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                }
                return saveGameState(game)
                .combine(Result.success( "You got " + woodCount + " woods."));
            }
        }

        else if (tree.getTreeType() == ForagingTreeType.TREE_BARK) {
            int woodCount = (int) (Math.random() * 2 + 1);
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot("Wood");

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (loot == null) {
                    return  Result.failure(
                            "Your backpack is full");
                }
                loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                return saveGameState(game)
                .combine(Result.success( "You got " + woodCount + " woods."));
            }
            else {
                if (loot == null) {
                    backpack.getLoots().add(new Loot(new Else(ElseType.WOOD, Quality.DEFAULT), woodCount));
                } else {
                    loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                }
                return saveGameState(game)
                .combine(Result.success( "You received " + woodCount + " wood."));
            }
        }

        else if (tree.getTreeType() == ForagingTreeType.BURNT_TREE) {
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(ForagingMineralType.COAL.name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                    return  Result.failure(
                            "Your backpack is full");
                }

                Loot lootToAdd = tree.getTreeType().fruitItem.createAmountOfItem(1, Quality.DEFAULT);
                backpack.getLoots().add(lootToAdd);
                return saveGameState(game)
                .combine(Result.success( "You got " + 1 + " coal."));
            }
            else {
                loot.setCount(Math.min(loot.getCount() + 1, loot.getItem().getMaxSize()));
                return saveGameState(game)
                .combine(Result.success( "You got " + 1 + " coal."));
            }
        }

        else {
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(TreeType.findTreeBySeed(tree.getTreeType().seed).name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                    return new Result(false
                            , "Your backpack is full");
                }

             //   Loot lootToAdd = TreeType.findTreeBySeed(tree.getTreeType().seed).createAmountOfItem(2, Quality.DEFAULT);
              //  backpack.getLoots().add(lootToAdd);

                return saveGameState(game)
                .combine(Result.success( "You got " + 2 + " tree seeds."));
            }
            else {
                loot.setCount(Math.min(loot.getCount() + 2, loot.getItem().getMaxSize()));
                return saveGameState(game)
                .combine(Result.success( "You got " + 2 + " tree seeds."));
            }
        }
    }

    private Result wateringCanUse(Cells targetCell, Player player, ToolType wateringCanType
            , int energyDiscount, Quality quality, Tool toolInHand, Game game){

        int takenEnergy;
        takenEnergy = 5 - quality.getQualityLevel() - energyDiscount;
        if (takenEnergy < 0) {
            takenEnergy = 0;
        }

        if (game.getWeatherToday() == Weather.SNOW) {
            takenEnergy *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            takenEnergy *= 1.5;
        }
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (targetCell == null) {
            return  Result.failure( "Target cell does not exist");
        }

        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (targetCell.getObjectOnCell() instanceof Lake) {
            toolInHand.setWaterReserve(wateringCanType.waterCapacity);

            return saveGameState(game)
                .combine(Result.success( "Water filled successfully."));
        }

        if (targetCell.getObjectOnCell() instanceof Tree tree) {
            if (toolInHand.getWaterReserve() == 0) {

                return  Result.failure( "Watering can is empty.");
            }
            tree.setHasBeenWateredToday(true);

            return saveGameState(game)
                .combine(Result.success( "Tree watered successfully."));
        }

        if (targetCell.getObjectOnCell() instanceof Crop crop) {
            if (toolInHand.getWaterReserve() == 0) {

                return  Result.failure( "Watering can is empty.");
            }
            crop.setHasBeenWateredToday(true);
            crop.setLastWateringDate(game.getDate());

            return saveGameState(game)
                .combine(Result.success( "Crop watered successfully."));
        }


        return  Result.failure( "No operation was performed.");
    }

    private Result fishingRodUse(Cells targetCell, Player player, Quality quality, int skillEnergyDiscount, Game game) {

        int takenEnergy = calculateFishingEnergyCost(skillEnergyDiscount, quality);
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (targetCell == null) {
            return  Result.failure( "Target cell does not exist");
        }

        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (targetCell.getObjectOnCell() instanceof Lake) {
            int randomNumber = (int) (Math.random() * 2);
            double weatherModifier = setWeatherModifierFishing(game);
            int playerLevel = player.getFishingSkill().getLevel().levelNumber;
            int numberOfFishes = (int) (((double) randomNumber)
                    * weatherModifier * (double) (playerLevel + 2));
            if (numberOfFishes == 0) {
                return  Result.failure( "You could not catch fish");
            }
            ArrayList<FishType> values = getValidFishTypes(game.getSeason(), playerLevel);
            int randomFishNumber = (int) (Math.random() * values.size());
            FishType fishType = values.get(randomFishNumber);

            double qualityNumber = 0;
            double pole = setPoleModifier(quality);
            qualityNumber = (randomNumber * (double) (playerLevel + 2) * pole) / (7.0 - weatherModifier);
            Quality fishQuality = setFishQuality(qualityNumber);
            int price = fishType.price;

            Fish fish = new Fish(fishQuality, fishType);
            BackPack backpack = player.getInventory();
            player.getFishingSkill().setXp(player.getFishingSkill().getXp() + 5);

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {

                return  Result.failure( "You didn't have enough space. But caught a fish anyways.");
            }

            addFishes(fish, backpack, numberOfFishes);


            return saveGameState(game)
                .combine(Result.success( "Fishing done! You caught " + numberOfFishes + " of " + fishType.name));
        }


        return  Result.failure( "You only can fish in Lake");
    }

    private int calculateFishingEnergyCost(int discount, Quality quality) {
        int answer = 0;
        if (quality == Quality.COPPER) {
            answer = 8;
        } else if (quality == Quality.SILVER) {
            answer = 8;
        } else if (quality == Quality.GOLD) {
            answer = 6;
        } else if (quality == Quality.IRIDIUM) {
            answer = 4;
        } else {
            answer = 1;
        }
        answer -= discount;

        if (answer < 0) {
            answer = 0;
        }
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        if (game.getWeatherToday() == Weather.SNOW) {
            answer *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            answer *= 1.5;
        }

        return answer;
    }

    private void addFishes(Fish fish, BackPack backpack, int numberOfFishes) {
        for (Loot  loot  : backpack.getLoots()) {
            if (loot.getItem().getName().compareToIgnoreCase(fish.getName()) == 0) {
                loot.setCount(loot.getCount() + numberOfFishes);
                return;
            }
        }
        Loot newLoot  = new Loot (fish, numberOfFishes);
        backpack.addLoot(newLoot);
    }

    private ArrayList<FishType> getValidFishTypes(Season season, int playerLevel) {
        if (playerLevel == 4) {
            FishType[] values = FishType.values();
            ArrayList<FishType> finalValues = new ArrayList<>();
            for (int i = 0; i < values.length; i++) {
                if (values[i].season == season) {
                    finalValues.add(values[i]);
                }
            }
            return finalValues;
        }
        FishType[] values = FishType.values();
        ArrayList<FishType> finalValues = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i].season == season && !values[i].isLegendary) {
                finalValues.add(values[i]);
            }
        }
        return finalValues;
    }

    private Quality setFishQuality(double qualityNumber) {
        if (qualityNumber >= 0.5 && qualityNumber < 0.7)
            return Quality.SILVER;
        else if (qualityNumber >= 0.7 && qualityNumber < 0.9)
            return Quality.GOLD;
        else if (qualityNumber >= 0.9)
            return Quality.IRIDIUM;
        return Quality.COPPER;
    }

    private double setPoleModifier(Quality quality) {
        if (quality == Quality.COPPER)
            return 0.1;
        else if (quality == Quality.SILVER)
            return 0.5;
        else if (quality == Quality.GOLD)
            return 0.9;
        return 1.2;
    }

    private double setWeatherModifierFishing(Game game) {
        double weatherModifier;
        if (game.getWeatherToday().equals(Weather.SUNNY))
            weatherModifier = 1.5;
        else if (game.getWeatherToday().equals(Weather.RAIN))
            weatherModifier = 1.2;
        else if (game.getWeatherToday().equals(Weather.STORM))
            weatherModifier = 0.5;
        else
            weatherModifier = 1.0;
        return weatherModifier;
    }




  private Result scytheUse(Cells targetCell, Player player, Game game){
//
//        int energyCost = getScytheEnergyCost();
//        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
//        int playerEnergy = player.getEnergy();
//
//        if (energyCost + currentEnergyUsed > 50) {
//            return  Result.failure( "You can't perform this activity. " +
//                    "You will exceed your energy usage limit.");
//        }
//
//        if (playerEnergy - energyCost < 0) {
//            return  Result.failure( "You don't have enough energy.");
//        }
//
//        if (targetCell == null) {
//            return  Result.failure( "Target cell not found.");
//        }
//
//        player.setEnergy(player.getEnergy() - energyCost);
//        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + energyCost);
//
//        if (targetCell.getObjectOnCell() instanceof ForagingCrop crop) {
//
//            targetCell.setObjectOnCell(new BurntCell());
//
//            BackPack backpack = player.getInventory();
//            ItemType itemType = crop.getForagingCropsType().getHarvestedItemType();
//            Loot loot = null;
//            String name = null;
//
//            if (itemType instanceof ElseType) {
//                loot = backpack.findItemLoot(((ElseType) itemType).name);
//                name = ((ElseType) itemType).name;
//            } else if (itemType instanceof FoodType) {
//                loot = backpack.findItemLoot(((FoodType) itemType).name);
//                name = ((FoodType) itemType).name;
//            }
//
//            int randomInt = (int) (Math.random() * 3) + 1;
//
//            if (player.getInventory().getType().getCapacity() == player.getInventory().getLoots().size()) {
//                if (loot == null) {
//                    System.out.println("You had no inventory space to collect the materials.");
//                } else {
//                    loot.setCount(Math.min(loot.getCount() + randomInt, loot.getItem().getMaxSize()));
//                    System.out.println("Added x(" + randomInt + ") " + name + " to your backpack.");
//                }
//            }
//            else {
//                if (loot == null) {
//                    backpack.getLoots().add(itemType.createAmountOfItem(randomInt, Quality.DEFAULT));
//                } else {
//                    loot.setCount(Math.min(loot.getCount() + randomInt, loot.getItem().getMaxStackSize()));
//                }
//                System.out.println("Added x(" + randomInt + ") " + name + " to your backpack.");
//            }
//
//            player.getUnbuffedForagingSkill().setXp(player.getUnbuffedForagingSkill().getXp() + 10);
//
//            return saveGameState(game)
              //  .combine(Result.success( "Removed " + name + "from tile.");
//        } else if (targetCell.getObjectOnCell() instanceof Crop crop) {
//
//            if (crop.getHarvestDeadLine() == null || crop.getHarvestDeadLine().isAfter(game.getDate())) {
//                return  Result.failure( "Crop isn't ready for harvest.");
//            }
//
//            int amountToHarvest = crop.isGiant() ? 10 : 1;
//
//            BackPack backpack = player.getInventory();
//            Loot slot = backpack.findItemLoot(crop.cropSeedsType.name);
//
//            if (slot == null) {
//                if (backpack.getType().getCapacity() == player.getInventory().getLoots().size()) {
//
//                    return  Result.failure( "Not enough inventory space.");
//                }
//
//                Loot newSlot = new Loot(FoodType.findFoodByName(crop.cropSeedsType.name), amountToHarvest);
//                backpack.getLoots().add(newSlot);
//
//                if (crop.cropSeedsType.oneTime) {
//                    targetCell.setObjectOnCell(new BurntCell());
//                } else {
//                    crop.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), crop.cropSeedsType.regrowthTime));
//                }
//
//                player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);
//
//
//                return saveGameState(game)
    //            .combine(Result.success( "Added x(" + amountToHarvest + ") of " + crop.cropSeedsType.name + " to your backpack.");
//            }
//
//            if (crop.cropSeedsType.oneTime) {
//                targetCell.setObjectOnCell(new BurntCell());
//            } else {
//                crop.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), crop.cropSeedsType.regrowthTime));
//            }
//
//            player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);
//
//            slot.setCount(Math.min(slot.getCount() + amountToHarvest, slot.getItem().getMaxSize()));
//
//
//            return saveGameState(game)
         //       .combine(Result.success( "Added x(" + amountToHarvest + ") of " + crop.cropSeedsType.name + " to your backpack.");
//        } else if (targetCell.getObjectOnCell() instanceof Tree tree) {
//
//            if (tree.getHarvestDeadLine() == null || tree.getHarvestDeadLine().isAfter(game.getDate())) {
//                return  Result.failure( "Tree isn't ready for harvest.");
//            }
//
//            int amountToHarvest = 1;
//
//            BackPack backpack = player.getInventory();
//            Loot loot = backpack.getLoots(tree.getTreeType().fruitItem.getName());
//
//            if (loot == null) {
//                if (backpack.getType().getCapacity() == player.getInventory().getLoots().size()) {
//
//                    return  Result.failure( "Not enough inventory space.");
//                }
//
//                if (tree.getTreeType() == TreeType.NORMAL_TREE
//                        || tree.getTreeType() == TreeType.TREE_BARK
//                        || tree.getTreeType() == TreeType.BURNT_TREE) {
//                    return  Result.failure( "Tree isn't harvestable.");
//                }
//                else {
//                    tree.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), tree.getTreeType().harvestCycleTime));
//                }
//
//                Loot newSlot = tree.getTreeType().fruitItem.createAmountOfItem(amountToHarvest, Quality.DEFAULT);
//                backpack.getLoots().add(newSlot);
//
//                player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);
//
//
//                return saveGameState(game)
     //           .combine(Result.success( "Added x(" + amountToHarvest + ") of " + tree.getTreeType().fruitItem.getName() + " to your backpack.");
//            }
//
//            if (tree.getTreeType() == TreeType.NORMAL_TREE
//                    || tree.getTreeType() == TreeType.TREE_BARK
//                    || tree.getTreeType() == TreeType.BURNT_TREE) {
//                return  Result.failure( "Can't harvest a normal, burnt tree or bark.");
//            }
//            else {
//                tree.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), tree.getTreeType().harvestCycleTime));
//            }
//
//            loot.setCount(Math.min(loot.getCount() + amountToHarvest, loot.getItem().getMaxSize()));
//
//            player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);
//
//
//            return saveGameState(game)
     //           .combine(Result.success( "Added x(" + amountToHarvest + ") of " + tree.getTreeType().fruitItem.getName() + " to your backpack.");
//
//        } else {
//
       return  Result.failure( "Target cell isn't a valid use case of the scythe.");

   }

    private int getScytheEnergyCost() {
        Game game = App.getCurrentUser().getCurrentGame();

        int energyCost = 2;
        if (game.getWeatherToday() == Weather.SNOW) {
            energyCost *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            energyCost *= 1.5;
        }
        return energyCost;
    }



    public  Result collectProducts(Item product, BackPack backpack, Loot productloot, Animal animal, Player player, Game game) {
        Item item = new Else(((Else) product).getElseType(), ((Else) product).getQuality());
        for (Loot loot : backpack.getLoots()) {
            if (loot.getItem().getName().equals(product.getName())) {
                productloot = loot;
            }
        }
        if (productloot == null) {
            if (backpack.getLoots().size() == backpack.getType().getCapacity()) {
                return  Result.failure( "your backpack is full");
            }
            Loot newLoot = new Loot(item, animal.getType().productPerDay);
            backpack.addLoot(newLoot);
            if (animal.getType().equals(AnimalType.SHEEP) || animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT)) {
                animal.setHasBeenCollected(true);
                animal.setFriendshipLevel(animal.getFriendshipLevel() + 5);
            }
            animal.setProduct(null);
            return saveGameState(game)
                .combine(Result.success( "you have collected " + animal.getType().productPerDay + " of " + product.getName()));
        }
        productloot.setCount(productloot.getCount() + animal.getType().productPerDay);
        if (animal.getType().equals(AnimalType.SHEEP) || animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT)) {
            animal.setHasBeenCollected(true);
            animal.setFriendshipLevel(animal.getFriendshipLevel() + 15);
        }
        animal.setProduct(null);
        return saveGameState(game)
                .combine(Result.success( "you have collected " + animal.getType().productPerDay + " of " + product.getName()));
    }





    private Result milkPailUse(Cells targetCell, Player player, Game game){
        BackPack backpack = player.getInventory();
        Loot productloot = null;
        int takenEnergy = 4;
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (targetCell == null || !(targetCell.getObjectOnCell() instanceof AnimalCell)) {
            return  Result.failure( "Target cell not found.");
        }

        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }

        Animal animal = ((AnimalCell) targetCell.getObjectOnCell()).animal;
        if (animal == null) {
            return  Result.failure( "no animal found");
        }
        if (!animal.getType().equals(AnimalType.COW) && !animal.getType().equals(AnimalType.GOAT)) {
            return  Result.failure( "wrong cell selected");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return  Result.failure("no product found");
        }
        return collectProducts(product, backpack, productloot, animal, player, game);
    }

    private Result shearUse(Cells targetCell, Player player, Game game){
        BackPack backpack = player.getInventory();
        int takenEnergy = 4;
        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();
        Loot productloot = null;


        if (targetCell == null || !(targetCell.getObjectOnCell() instanceof AnimalCell)) {
            return  Result.failure( "Target cell not found.");
        }
        else if (takenEnergy + turnUsedEnergy > 50) {
            return  Result.failure( "You don't have enough energy in this turn");
        }
        else if (playerEnergy - takenEnergy < 0) {
            return  Result.failure( "You don't have enough energy.");
        }
        Animal animal = ((AnimalCell) targetCell.getObjectOnCell()).animal;
        if (animal == null) {
            return  Result.failure( "no animal found");
        }
        if (!animal.getType().equals(AnimalType.SHEEP)) {
            return  Result.failure( "wrong cell selected");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return  Result.failure("no product found");
        }
        return collectProducts(product, backpack, productloot, animal, player, game);
    }

    public Result addItem(String name, int count) {
        // Validate inputs
        if (count <= 0) {
            return  Result.failure( "Count must be positive");
        }

        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        if (game == null) {
            return  Result.failure( "No active game found");
        }

        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();

        // Check if item exists in the game's item types
        HashMap<String, ItemType> allItemTypes = App.getAllItemTypes();
        ItemType itemType = allItemTypes.get(name);
        if (itemType == null) {
            return  Result.failure( "Item '" + name + "' does not exist in the game");
        }

        // Check backpack capacity (unless it's the deluxe backpack)
        if (backpack.getType() != BackpackType.DELUXE &&
                backpack.getLoots().size() >= backpack.getType().getCapacity()) {
            // Check if we already have this item
            Loot existingLoot = backpack.findItemLoot(name);
            if (existingLoot == null) {
                return  Result.failure( "Backpack is full");
            }
        }

        try {
            // Create the item with default quality
            Loot newLoot = itemType.createAmountOfItem(count, Quality.DEFAULT);

            // Check if item already exists in inventory
            Loot existingLoot = backpack.findItemLoot(name);
            if (existingLoot != null) {
                // Add to existing count, respecting max stack size
                int newCount = existingLoot.getCount() + count;
                if (newCount > existingLoot.getItem().getMaxSize()) {
                    // If stack would overflow, create new stacks as needed
                    int remaining = newCount;
                    existingLoot.setCount(existingLoot.getItem().getMaxSize());
                    remaining -= existingLoot.getItem().getMaxSize();

                    while (remaining > 0) {
                        int addAmount = Math.min(remaining, existingLoot.getItem().getMaxSize());
                        if (backpack.getType() != BackpackType.DELUXE &&
                                backpack.getLoots().size() >= backpack.getType().getCapacity()) {
                            return saveGameState(game)
                .combine(Result.success( "Added " + (count - remaining) + " of " + name +
                                    " (backpack full, couldn't add remaining " + remaining + ")"));
                        }
                        backpack.addLoot(itemType.createAmountOfItem(addAmount, Quality.DEFAULT));
                        remaining -= addAmount;
                    }
                } else {
                    existingLoot.setCount(newCount);
                }
            } else {
                // Add new item to inventory
                backpack.addLoot(newLoot);
            }

            return saveGameState(game)
                .combine(Result.success( "Added " + count + " of " + name + " to your backpack"));
        } catch (Exception e) {
            return  Result.failure( "Failed to add item: " + e.getMessage());
        }
    }


}
