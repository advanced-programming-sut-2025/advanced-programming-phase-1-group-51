package Controllers.Others;

import Controllers.Controller;
import Models.*;
import Models.Enums.Types.BackpackType;

import java.util.ArrayList;

public class InventoryController extends Controller {

    Player currentPlayer = Game.getCurrentPlayer();

    public Result showInventory() {
        StringBuilder output = new StringBuilder();
        BackPack inventory = currentPlayer.getInventory();

        // Show backpack capacity
        output.append("Backpack Type: ").append(inventory.getType()).append("\n");
        output.append("Capacity: ");
        if (inventory.getType() == BackpackType.DELUXE) {
            output.append("Unlimited\n");
        } else {
            output.append(inventory.getLots().size()).append("/")
                    .append(inventory.getType().getCapacity()).append(" slots used\n");
        }

        // Show items in inventory
        output.append("\nInventory Items:\n");
        ArrayList<Loot> items = inventory.getLots();
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
        BackPack inventory = currentPlayer.getInventory();
        ArrayList<Loot> items = inventory.getLots();
        TrashCan trashCan = currentPlayer.getTrashCan();

        // Check if inventory is full (for non-deluxe backpacks)
        if (inventory.getType() != BackpackType.DELUXE &&
                items.size() >= inventory.getType().getCapacity()) {
            return new Result(false, "Inventory is full!");
        }

        // Find the item in inventory
        for (int i = 0; i < items.size(); i++) {
            Loot loot = items.get(i);
            if (loot.getItem().getName().equalsIgnoreCase(itemName)) {
                // Calculate cash back based on trash can type
                int cashBack = 0;
                if (trashCan.getPercentOfCashBack() > 0) {
                    cashBack = (int)(loot.getItem().getValue() * loot.getCount() *
                            trashCan.getPercentOfCashBack() / 100.0);
                    currentPlayer.setMoney(currentPlayer.getMoney() + cashBack);
                }

                // Remove the item completely
                items.remove(i);

                String message = "All " + itemName + " has been removed from inventory.";
                if (cashBack > 0) {
                    message += " You received " + cashBack + " gold.";
                }
                return new Result(true, message);
            }
        }

        return new Result(false, "Item not found in inventory: " + itemName);
    }

    public Result inventoryTrashNotFull(String itemName, int itemCount) {
        if (itemCount <= 0) {
            return new Result(false, "Item count must be positive.");
        }

        BackPack inventory = currentPlayer.getInventory();
        ArrayList<Loot> items = inventory.getLots();
        TrashCan trashCan = currentPlayer.getTrashCan();

        // Find the item in inventory
        for (Loot loot : items) {
            if (loot.getItem().getName().equalsIgnoreCase(itemName)) {
                int currentCount = loot.getCount();
                if (currentCount < itemCount) {
                    return new Result(false, "Not enough " + itemName +
                            " in inventory. You only have " + currentCount);
                }

                // Calculate cash back based on trash can type
                int cashBack = 0;
                if (trashCan.getPercentOfCashBack() > 0) {
                    cashBack = (int)(loot.getItem().getValue() * itemCount *
                            trashCan.getPercentOfCashBack() / 100.0);
                    currentPlayer.setMoney(currentPlayer.getMoney() + cashBack);
                }

                // Update the count
                loot.setCount(currentCount - itemCount);

                // If count reaches zero, remove the item
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


    public Result toolEquip(String item){


        return null;
    }

    public Result showTools(){


        return null;
    }

    public Result showAvailableTools(){


        return null;
    }

    public  Result toolUpgrade(String item){


        return null;
    }

    public Result toolUse(String direction){


        return null;
    }


}
