package Controllers.Others;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Others.SkillLevel;
import Models.Enums.Others.Weather;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Types.AnimalType;
import Models.Enums.Types.BackpackType;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Enums.Types.TrashcanType;
import Models.Items.Else;
import Models.Items.Item;
import Models.Items.Tool;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.AnimalCell;
import Models.ObjectsShownOnMap.BurntCell;
import Models.ObjectsShownOnMap.Crop;
import Models.ObjectsShownOnMap.Tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryController extends Controller {


    public Result showInventory() {
        Player currentPlayer = Game.getCurrentPlayer();
        StringBuilder output = new StringBuilder();
        BackPack inventory = currentPlayer.getInventory();


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

        return new Result(true, output.toString());
    }

    public Result inventoryTrashFull(String itemName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        BackPack backpack = Game.getCurrentPlayer().getInventory();
        Loot loot = backpack.findItemLoot(itemName);

        return new Result(false, "Item not found in inventory: " + itemName);
    }

    public Result inventoryTrashNotFull(String itemName, int itemCount) {
        Player currentPlayer = Game.getCurrentPlayer();
        if (itemCount <= 0) {
            return new Result(false, "Item count must be positive.");
        }

        BackPack inventory = currentPlayer.getInventory();
        ArrayList<Loot> items = inventory.getLoots();
        TrashCan trashCan = currentPlayer.getTrashcan();


        for (Loot loot : items) {
            if (loot.getItem().getName().equalsIgnoreCase(itemName)) {
                int currentCount = loot.getCount();
                if (currentCount < itemCount) {
                    return new Result(false, "Not enough " + itemName +
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
                return new Result(true, message);
            }
        }

        return new Result(false, "Item not found in inventory: " + itemName);
    }


    public Result toolEquip(String toolName) {
        Player player = Game.getCurrentPlayer();
        ToolType toolType = ToolType.findToolTypeByName(toolName);
        if (toolType == null) {
            return new Result(false, "Invalid tool name: " + toolName);
        }

        // Search player's inventory for this tool
        for (Loot loot : player.getInventory().getLoots()) {
            Item item = loot.getItem();
            if (item instanceof Tool) {
                Tool toolItem = (Tool) item;
                if (toolItem.getType() == toolType) {
                    // Found the tool - set it in hand
                    player.setItemInHand(toolItem);
                    return new Result(true, toolName + " equipped successfully");
                }
            }
        }

        return new Result(false, "You don't have a " + toolName + " in your inventory");
    }

    public Result showCurrentTool() {
        Player player = Game.getCurrentPlayer();

        Item itemInHand = player.getItemInHand();

        if (itemInHand instanceof Tool) {
            Tool tool = (Tool) itemInHand;
            return new Result(true, "You have a " + tool.getName() + " in your hand");
        }
        else {
            return new Result(false, "You don't have a tool in your hands");
        }
    }

    public Result showAvailableTools() {
        Player player = Game.getCurrentPlayer();

        BackPack backpack = player.getInventory();
        if (backpack == null || backpack.getLoots().isEmpty()) {
            return new Result(false, "Your backpack is empty");
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
            return new Result(false, "You don't have any tools in your backpack");
        }

        // Format the output
        for (Map.Entry<String, Integer> entry : toolCounts.entrySet()) {
            output.append("- ").append(entry.getKey())
                    .append(" (x").append(entry.getValue()).append(")\n");
        }

        // Add water info for watering cans
        output.append("\nNote: Watering cans show their current water level\n");

        return new Result(true, output.toString());
    }

    private static Direction directionManaging(String direction, int playerX, int playerY) {
        int directionX = playerX;
        int directionY = playerY;

        switch (direction.toLowerCase()) {
            case "up":
                directionY--;
                break;
            case "down":
                directionY++;
                break;
            case "left":
                directionX--;
                break;
            case "right":
                directionX++;
                break;
            case "up left":
                directionX--;
                directionY--;
                break;
            case "up right":
                directionX++;
                directionY--;
                break;
            case "down left":
                directionX--;
                directionY++;
                break;
            case "down right":
                directionX++;
                directionY++;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        return new Direction(directionX, directionY);
    }

    public static Result toolUse(String direction) {
        Player player = Game.getCurrentPlayer();

        if (player.getItemInHand() == null) {
            return new Result(false, "You don't have any item in your hand");
        }
        if (!(player.getItemInHand() instanceof Tool)) {
            return new Result(false, "You don't have any tool in your hand");
        }
        if (!(direction.equals("up") || direction.equals("down") || direction.equals("left") || direction.equals("right") || 
                direction.equals("up left") || direction.equals("up right") || direction.equals("down left") || direction.equals("down right") )) {
            return new Result(false, "wrong direction.");
        }

        Tool toolInHand = (Tool) player.getItemInHand();
        ToolType toolType = toolInHand.getType();

        if (toolType == ToolType.HOE) {
            return hoeUse( player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality(),direction);
        } 
        else if (toolType == ToolType.PICKAXE) {
            return pickaxeUse(direction
                    , player.getMiningSkill().getLevel()
                    , toolInHand.getQuality()
                    , player.getMiningSkill().getLevel().energyCostDiscount);
        }
        else if (toolType == ToolType.AXE) {
            return axeUse(direction, toolInHand.getQuality()
                    , player.getForagingSkill().getLevel().energyCostDiscount);
        }
        else if (toolType == ToolType.WATERING_CAN_DEFAULT) {
            return wateringCanUse(direction, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand);
        } 
        else if (toolType == ToolType.WATERING_CAN_COPPER) {
            return wateringCanUse(direction, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand);
        } 
        else if (toolType == ToolType.WATERING_CAN_IRON) {
            return wateringCanUse(direction, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand);
        }
        else if (toolType == ToolType.WATERING_CAN_GOLD) {
            return wateringCanUse(direction, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand);
        } 
        else if (toolType == ToolType.WATERING_CAN_IRIDIUM) {
            return wateringCanUse(direction, toolType
                    , player.getFarmingSkill().getLevel().energyCostDiscount, toolInHand.getQuality()
                    , toolInHand);
        } 
        else if (toolType == ToolType.FISHING_ROD) {
            return fishingRodUse(direction, toolInHand.getQuality()
                    , player.getFishingSkill().getLevel().energyCostDiscount);
        } 
        else if (toolType == ToolType.SCYTHE) {
            return scytheUse(direction);
        }
        else if (toolType == ToolType.MILK_PAIL) {
            return milkPailUse(direction);
        } 
        else if (toolType == ToolType.SHEAR) {
            return shearUse(direction);
        } 
        else {
            return new Result(false, "Invalid tool");
        }
    }




    private static Result hoeUse(int energyDiscount, Quality quality, String direction){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);

        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int takenEnergy = 0;
        int playerEnergy = player.getEnergy();

        if (takenEnergy + turnUsedEnergy > 50) {
            return new Result(false, "You don't have enough energy in this turn");
        }

        else if (playerEnergy - takenEnergy < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        else if (targetCell == null) {
            return new Result(false, "Target cell does not exist");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (!(targetCell.getObjectOnCell() instanceof BurntCell)) {
            return new Result(false, "cell in this direction is not empty");
        }
        targetCell.setHasBeenPlowed(true);
        return new Result(true, "Target cell is now plowed.");
    }

    private static Result pickaxeUse(String direction, SkillLevel skillLevel
            , Quality quality, int energyDiscount){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);


        return null;
    }

    private static Result axeUse(String direction, Quality quality, int energyDiscount) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);

        int turnUsedEnergy = player.getCurrentTurnUsedEnergy();
        int takenEnergy = 0;
        int playerEnergy = player.getEnergy();


        if (targetCellX + turnUsedEnergy > 50) {
            return new Result(false, "You don't have enough energy in this turn.");
        } 
        else if (playerEnergy - takenEnergy < 0) {
            return new Result(false, "You don't have enough energy.");
        } 
        else if (targetCell == null) {
            return new Result(false, "Target cell does not exist.");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (!(targetCell.getObjectOnCell() instanceof Tree tree)) {
            return new Result(false, "Target cell is not a tree or wood");
        }

        if (tree.getTreeType() == TreeType.NORMAL_TREE) {
            int woodCount = (int) (Math.random() * 4 + 2);
            targetCell.setObjectOnCell(new Tree(TreeType.TREE_BARK));

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot("Wood");

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (loot == null) {
                    return new Result(false,
                            "yous backpack is full");
                }
                
                loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                return new Result(true, "you got " + woodCount + " woods");
            }

            else {
                if (loot == null) {
                    backpack.getLoots().add(new Loot(new Else(ElseType.WOOD, Quality.DEFAULT), woodCount));
                }
                else {
                    loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                }
                return new Result(true, "You got " + woodCount + " woods.");
            }
        }

        else if (tree.getTreeType() == TreeType.TREE_BARK) {
            int woodCount = (int) (Math.random() * 2 + 1);
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot("Wood");

            if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                if (loot == null) {
                    return new Result(false,
                            "Your backpack is full");
                }
                loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                return new Result(true, "You got " + woodCount + " woods.");
            }
            else {
                if (loot == null) {
                    backpack.getLoots().add(new Loot(new Else(ElseType.WOOD, Quality.DEFAULT), woodCount));
                } else {
                    loot.setCount(Math.min(loot.getCount() + woodCount, loot.getItem().getMaxSize()));
                }
                return new Result(true, "You received " + woodCount + " wood.");
            }
        }

        else if (tree.getTreeType() == TreeType.BURNT_TREE) {
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(ForagingMineralType.COAL.name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                    return new Result(false,
                            "Your backpack is full");
                }

                Loot lootToAdd = tree.getTreeType().fruitItem.createAmountOfItem(1, Quality.DEFAULT);
                backpack.getLoots().add(lootToAdd);
                return new Result(true, "You got " + 1 + " coal.");
            }
            else {
                loot.setCount(Math.min(loot.getCount() + 1, loot.getItem().getMaxSize()));
                return new Result(true, "You got " + 1 + " coal.");
            }
        }

        else {
            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(TreeSeedsType.findTreeType(tree.getTreeType().source).name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                    return new Result(false
                            , "Tree was chopped; however, your backpack was full. Wood wasn't added to your backpack.");
                }

                Loot lootToAdd = TreeSeedsType.findTreeType(tree.getTreeType().source).createAmountOfItem(2, Quality.DEFAULT);
                backpack.getLoots().add(lootToAdd);

                return new Result(true, "You received " + 2 + " tree seeds.");
            } else {
                loot.setCount(Math.min(loot.getCount() + 2, loot.getItem().getMaxSize()));
                return new Result(true, "You received " + 2 + " tree seeds.");
            }
            return null;
        }

    }

    private static Result wateringCanUse(String direction, ToolType wateringCanType
            , int energyDiscount, Quality quality, Tool toolInHand){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);


        return null;
    }

    private static Result fishingRodUse(String direction, Quality quality, int skillEnergyDiscount) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);

        return null;
    }

    private static Result scytheUse(String direction){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);
        int energyCost = getScytheEnergyCost();
        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (energyCost + currentEnergyUsed > 50) {
            return new Result(false, "You can't perform this activity. " +
                    "You will exceed your energy usage limit.");
        }

        if (playerEnergy - energyCost < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        if (targetCell == null) {
            return new Result(false, "Target cell not found.");
        }

        player.setEnergy(player.getEnergy() - energyCost);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + energyCost);

        if (targetCell.getObjectOnCell() instanceof ForagingCrop crop) {

            targetCell.setObjectOnCell(new BurntCell());

            BackPack backpack = player.getInventory();
            ItemType itemType = crop.getForagingCropsType().getHarvestedItemType();
            Loot loot = null;
            String name = null;

            if (itemType instanceof ElseType) {
                loot = backpack.findItemLoot(((ElseType) itemType).name);
                name = ((ElseType) itemType).name;
            } else if (itemType instanceof FoodType) {
                loot = backpack.findItemLoot(((FoodType) itemType).name);
                name = ((FoodType) itemType).name;
            }

            int randomInt = (int) (Math.random() * 3) + 1;

            if (player.getInventory().getType().getCapacity() == player.getInventory().getLoots().size()) {
                if (loot == null) {
                    System.out.println("You had no inventory space to collect the materials.");
                } else {
                    loot.setCount(Math.min(loot.getCount() + randomInt, loot.getItem().getMaxSize()));
                    System.out.println("Added x(" + randomInt + ") " + name + " to your backpack.");
                }
            } else {
                if (loot == null) {
                    backpack.getLoots().add(itemType.createAmountOfItem(randomInt, Quality.DEFAULT));
                } else {
                    loot.setCount(Math.min(loot.getCount() + randomInt, loot.getItem().getMaxSize()));
                }
                System.out.println("Added x(" + randomInt + ") " + name + " to your backpack.");
            }

            player.getForagingSkill().setXp(player.getForagingSkill().getXp() + 10);
            
            return new Result(true, "Removed " + name + "from tile.");
        } else if (targetCell.getObjectOnCell() instanceof Crop crop) {

            if (crop.getHarvestDeadLine() == null || crop.getHarvestDeadLine().isAfter(game.getDate())) {
                return new Result(false, "Crop isn't ready for harvest.");
            }

            int amountToHarvest = crop.isGiant() ? 10 : 1;

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(crop.getCropSeedsType().name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == player.getInventory().getLoots().size()) {
                    
                    return new Result(false, "Not enough inventory space.");
                }

                Loot newloot = new Loot(FoodType.findFoodType(crop.cropSeedsType.name), amountToHarvest);
                backpack.getLoots().add(newloot);

                if (crop.cropSeedsType.oneTime) {
                    targetCell.setObjectOnCell(new BurntCell());
                } else {
                    crop.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), crop.cropSeedsType.regrowthTime));
                }

                player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);

                
                return new Result(true, "Added x(" + amountToHarvest + ") of " + crop.cropSeedsType.name + " to your backpack.");
            }

            if (crop.cropSeedsType.oneTime) {
                targetCell.setObjectOnCell(new BurntCell());
            } else {
                crop.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), crop.cropSeedsType.regrowthTime));
            }

            player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);

            loot.setCount(Math.min(loot.getCount() + amountToHarvest, loot.getItem().getMaxSize()));
            

            return new Result(true, "Added x(" + amountToHarvest + ") of " + crop.cropSeedsType.name + " to your backpack.");
        } else if (targetCell.getObjectOnCell() instanceof Tree tree) {

            if (tree.getHarvestDeadLine() == null || tree.getHarvestDeadLine().isAfter(game.getDate())) {
                return new Result(false, "Tree isn't ready for harvest.");
            }

            int amountToHarvest = 1;

            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(tree.getTreeType().fruitItem.getName());

            if (loot == null) {
                if (backpack.getType().getCapacity() == player.getInventory().getLoots().size()) {
                    
                    return new Result(false, "Not enough inventory space.");
                }

                if (tree.getTreeType() == TreeType.NORMAL_TREE
                        || tree.getTreeType() == TreeType.TREE_BARK
                        || tree.getTreeType() == TreeType.BURNT_TREE) {
                    return new Result(false, "Tree isn't harvestable.");
                } else {
                    tree.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), tree.getTreeType().harvestCycleTime));
                }

                Loot newloot = tree.getTreeType().fruitItem.createAmountOfItem(amountToHarvest, Quality.DEFAULT);
                backpack.getLoots().add(newloot);

                player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);

                
                return new Result(true, "Added x(" + amountToHarvest + ") of " + tree.getTreeType().fruitItem.getName() + " to your backpack.");
            }

            if (tree.getTreeType() == TreeType.NORMAL_TREE
                    || tree.getTreeType() == TreeType.TREE_BARK
                    || tree.getTreeType() == TreeType.BURNT_TREE) {
                return new Result(false, "Can't harvest a normal, burnt tree or bark.");
            } else {
                tree.setHarvestDeadLine(DateUtility.getLocalDateTime(game.getDate(), tree.getTreeType().harvestCycleTime));
            }

            loot.setCount(Math.min(loot.getCount() + amountToHarvest, loot.getItem().getMaxSize()));

            player.getUnbuffedFarmingSkill().setXp(player.getUnbuffedFarmingSkill().getXp() + 5);

            return new Result(true, "Added x(" + amountToHarvest + ") of " + tree.getTreeType().fruitItem.getName() + " to your backpack.");

        } else {
            return new Result(false, "Target cell isn't a valid use case of the scythe.");
        }
    }

    private static int getScytheEnergyCost() {
        Game game = Game.getCurrentUser().getCurrentGame();

        int energyCost = 2;
        if (game.getWeatherToday() == Weather.SNOW) {
            energyCost *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            energyCost *= 1.5;
        }
        return energyCost;
    }

    public static  Result collectProducts(Item product, BackPack backpack, Loot productloot, Animal animal, Player player, Game game) {
        Item item = new Else(((Else) product).getElseType(), ((Else) product).getQuality());
        for (Loot loot : backpack.getLoots()) {
            if (loot.getItem().getName().equals(product.getName())) {
                productloot = loot;
            }
        }
        if (productloot == null) {
            if (backpack.getLoots().size() == backpack.getType().getCapacity()) {
                return new Result(false, "your backpack is full");
            }
            Loot newLoot = new Loot(item, animal.getType().productPerDay);
            backpack.addLoot(newLoot);
            if (animal.getType().equals(AnimalType.SHEEP) || animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT)) {
                animal.setHasBeenCollected(true);
                animal.setFriendshipLevel(animal.getFriendshipLevel() + 5);
            }
            animal.setProduct(null);
            return new Result(true, "you have collected " + animal.getType().productPerDay + " of " + product.getName());
        }
        productloot.setCount(productloot.getCount() + animal.getType().productPerDay);
        if (animal.getType().equals(AnimalType.SHEEP) || animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT)) {
            animal.setHasBeenCollected(true);
            animal.setFriendshipLevel(animal.getFriendshipLevel() + 15);
        }
        animal.setProduct(null);
        return new Result(true, "you have collected " + animal.getType().productPerDay + " of " + product.getName());
    }



    private static Result milkPailUse(String direction){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);
        Item equippedItem = player.getItemInHand();
        BackPack backpack = player.getInventory();
        Loot productloot = null;
        double energyCost = 4;
        double currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        double playerEnergy = player.getEnergy();

        if (energyCost + currentEnergyUsed > 50) {
            return new Result(false, "You can't perform this activity. " +
                    "You will exceed your energy usage limit.");
        }

        if (playerEnergy - energyCost < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        if (targetCell == null || !(targetCell.getObjectOnCell() instanceof AnimalCell)) {
            return new Result(false, "Target cell not found.");
        }
        Animal animal = ((AnimalCell) targetCell.getObjectOnCell()).animal;
        if (animal == null) {
            return new Result(false, "no animal found");
        }
        if (!animal.getType().equals(AnimalType.COW) && !animal.getType().equals(AnimalType.GOAT)) {
            return new Result(false, "wrong cell selected");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return new Result(false,"no product found");
        }
        return collectProducts(product, backpack, productloot, animal, player, game);
    }

    private static Result shearUse(String direction){
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCell(targetCellX, targetCellY);
        Item equippedItem = player.getItemInHand();
        BackPack backpack = player.getInventory();
        double takenEnergy = 4;
        double currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        double playerEnergy = player.getEnergy();
        Loot productloot = null;
        if (takenEnergy + currentEnergyUsed > 50) {
            return new Result(false, "You can't perform this activity." +
                    "You will exceed your energy usage limit.");
        }

        if (playerEnergy - takenEnergy < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        if (targetCell == null || !(targetCell.getObjectOnCell() instanceof AnimalCell)) {
            return new Result(false, "Target cell not found.");
        }
        Animal animal = ((AnimalCell) targetCell.getObjectOnCell()).animal;
        if (animal == null) {
            return new Result(false, "no animal found");
        }
        if (!animal.getType().equals(AnimalType.SHEEP)) {
            return new Result(false, "wrong cell selected");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return new Result(false,"no product found");
        }
        return collectProducts(product, backpack, productloot, animal, player, game);
    }


}
