package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Types.BackpackType;

import java.util.ArrayList;

public class BackPack {
    private BackpackType type;
    private ArrayList<Slot> slots = new ArrayList<>();

    public BackPack(BackpackType type) {
        this.type = type;
    }

    public BackpackType getType() {
        return type;
    }

    public void setType(BackpackType type) {
        this.type = type;
    }

    public ArrayList<Slot> getSLots() {
        return slots;
    }

    public void setSLots(ArrayList<Slot> slots) {
        this.slots = slots;
    }
}
