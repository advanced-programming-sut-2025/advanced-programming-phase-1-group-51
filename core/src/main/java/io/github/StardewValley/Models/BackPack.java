package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.BackpackType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.Items.Mineral;
import io.github.StardewValley.Models.Items.Misc;

import java.util.ArrayList;

public class BackPack {
    private BackpackType backpackType;
    private ArrayList<Slot> slots = new ArrayList<>();

    public BackPack(BackpackType type) {
        this.backpackType = type;
    }

    public void addItem(ItemType type, int count) {
        // Check if item already exists in inventory
        for (Slot slot : slots) {
            if (slot != null && slot.getItem() != null &&
                slot.getItem().getName().equals(type.getName())) {
                slot.setCount(slot.getCount() + count);
                return;
            }
        }

        // If not found, add new slot
        if (slots.size() < backpackType.getCapacity()) {  // Fixed typo here (was getcCapacity)
            slots.add(new Slot(type, count));
        } else {
            // Inventory full - handle accordingly
            System.out.println("Inventory full!");
        }
    }

    public void addItem(MiscType type, int count) {
        addItem(new Misc(type), count);
    }

    public void addItem(ForagingMineralType type, int count) {
        addItem(new Mineral(Quality.DEFAULT, type), count);
    }

    // Helper method
    private void addItem(Item item, int count) {
        // Similar logic as above but with Item object
        for (Slot slot : slots) {
            if (slot != null && slot.getItem() != null &&
                slot.getItem().getName().equals(item.getName())) {
                slot.setCount(slot.getCount() + count);
                return;
            }
        }

        if (slots.size() < backpackType.getCapacity()) {  // Fixed typo here (was getcCapacity)
            slots.add(new Slot(item, count));
        } else {
            System.out.println("Inventory full!");
        }
    }

    public BackpackType getType() {
        return backpackType;
    }

    public void setType(BackpackType type) {
        this.backpackType = type;
    }

    public ArrayList<Slot> getSLots() {
        if (slots == null) {
            slots = new ArrayList<>();
        }
        return slots;
    }

    public void setSLots(ArrayList<Slot> slots) {
        this.slots = slots;
    }
}
