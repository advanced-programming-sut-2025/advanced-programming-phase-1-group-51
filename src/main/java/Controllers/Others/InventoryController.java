package Controllers.Others;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Others.Season;
import Models.Enums.Others.SkillLevel;
import Models.Enums.Others.Weather;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Types.AnimalType;
import Models.Enums.Types.BackpackType;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Enums.Types.TrashcanType;
import Models.Items.Else;
import Models.Items.Fish;
import Models.Items.Item;
import Models.Items.Tool;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.*;

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
            Loot loot = backpack.findItemLoot(TreeSeedsType.findTreeType(tree.getTreeType().seed).name);

            if (loot == null) {
                if (backpack.getType().getCapacity() == backpack.getLoots().size()) {
                    return new Result(false
                            , "Your backpack is full");
                }

                Loot lootToAdd = TreeSeedsType.findTreeType(tree.getTreeType().seed).createAmountOfItem(2, Quality.DEFAULT);
                backpack.getLoots().add(lootToAdd);

                return new Result(true, "You got " + 2 + " tree seeds.");
            }
            else {
                loot.setCount(Math.min(loot.getCount() + 2, loot.getItem().getMaxSize()));
                return new Result(true, "You got " + 2 + " tree seeds.");
            }
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

        int takenEnergy = 0;
        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (takenEnergy + currentEnergyUsed > 50) {
            return new Result(false, "You can't perform this activity. " +
                    "You will exceed your energy usage limit.");
        }

        if (playerEnergy - takenEnergy < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        if (targetCell == null) {
            return new Result(false, "Target cell not found.");
        }

        player.setEnergy(player.getEnergy() - takenEnergy);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + takenEnergy);

        if (targetCell.getObjectOnCell() instanceof Lake) {
            toolInHand.setWaterReserve(wateringCanType.waterCapacity);
         
            return new Result(true, "Water filled successfully.");
        }

        if (targetCell.getObjectOnCell() instanceof Tree tree) {
            if (toolInHand.getWaterReserve() == 0) {
             
                return new Result(false, "Watering can is empty.");
            }
            tree.setHasBeenWateredToday(true);
         
            return new Result(true, "Tree watered successfully.");
        }

        if (targetCell.getObjectOnCell() instanceof Crop crop) {
            if (toolInHand.getWaterReserve() == 0) {
             
                return new Result(false, "Watering can is empty.");
            }
            crop.setHasBeenWateredToday(true);
            crop.setLastWateringDate(game.getDate());
         
            return new Result(true, "Crop watered successfully.");
        }

     
        return new Result(false, "No operation was performed.");
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

        int energyCost = calculateFishingEnergyCost(skillEnergyDiscount, quality);
        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (energyCost + currentEnergyUsed > 50) {
            return new Result(false, "You don't have enough energy in this turn");
        }

        if (playerEnergy - energyCost < 0) {
            return new Result(false, "You don't have enough energy.");
        }

        if (targetCell == null) {
            return new Result(false, "Target cell not found.");
        }

        player.setEnergy(player.getEnergy() - energyCost);
        player.setCurrentTurnUsedEnergy(player.getCurrentTurnUsedEnergy() + energyCost);

        if (targetCell.getObjectOnCell() instanceof Lake) {
            int randomNumber = (int) (Math.random() * 2);
            double weatherModifier = setWeatherModifierFishing(game);
            int playerLevel = player.getFishingSkill().getLevel().levelNumber;
            int numberOfFishes = (int) (((double) randomNumber)
                    * weatherModifier * (double) (playerLevel + 2));
            if (numberOfFishes == 0) {
                return new Result(false, "You could not catch fish");
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
              
                return new Result(false, "You didn't have enough space. But caught a fish anyways.");
            }

            addFishes(fish, backpack, numberOfFishes);

          
            return new Result(true, "Fishing done! You caught " + numberOfFishes + " of " + fishType.name);
        }

      
        return new Result(false, "You only can fish in Lake");
    }

    private static int calculateFishingEnergyCost(int discount, Quality quality) {
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

        Game game = Game.getCurrentUser().getCurrentGame();
        if (game.getWeatherToday() == Weather.SNOW) {
            answer *= 2;
        }
        if (game.getWeatherToday() == Weather.RAIN) {
            answer *= 1.5;
        }

        return answer;
    }

    private static void addFishes(Fish fish, BackPack backpack, int numberOfFishes) {
        for (Loot  loot  : backpack.getLoots()) {
            if (loot.getItem().getName().compareToIgnoreCase(fish.getName()) == 0) {
                loot.setCount(loot.getCount() + numberOfFishes);
                return;
            }
        }
        Loot newLoot  = new Loot (fish, numberOfFishes);
        backpack.addLoot(newLoot);
    }

    private static ArrayList<FishType> getValidFishTypes(Season season, int playerLevel) {
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

    private static Quality setFishQuality(double qualityNumber) {
        if (qualityNumber >= 0.5 && qualityNumber < 0.7)
            return Quality.SILVER;
        else if (qualityNumber >= 0.7 && qualityNumber < 0.9)
            return Quality.GOLD;
        else if (qualityNumber >= 0.9)
            return Quality.IRIDIUM;
        return Quality.COPPER;
    }

    private static double setPoleModifier(Quality quality) {
        if (quality == Quality.COPPER)
            return 0.1;
        else if (quality == Quality.SILVER)
            return 0.5;
        else if (quality == Quality.GOLD)
            return 0.9;
        return 1.2;
    }

    private static double setWeatherModifierFishing(Game game) {
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




    private static Result scytheUse(String direction){
        return null;
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
        int takenEnergy = 4;
        int currentEnergyUsed = player.getCurrentTurnUsedEnergy();
        int playerEnergy = player.getEnergy();

        if (takenEnergy + currentEnergyUsed > 50) {
            return new Result(false, "You don't have enough energy in this turn");
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
