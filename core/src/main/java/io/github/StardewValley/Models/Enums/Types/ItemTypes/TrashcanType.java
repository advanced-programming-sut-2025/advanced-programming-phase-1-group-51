package io.github.StardewValley.Models.Enums.Types.ItemTypes;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Slot;

public enum TrashcanType implements ItemType {
    DEFAULT(0),
    COPPER(15),
    STEEL(30),
    GOLD(45),
    IRIDIUM(60),
    ;
    final public int refundPercentage;

    TrashcanType(int refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    @Override
    public Slot createAmountOfItem(int amount, Quality quality) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
