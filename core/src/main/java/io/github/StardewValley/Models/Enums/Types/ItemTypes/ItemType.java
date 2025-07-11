package io.github.StardewValley.Models.Enums.Types.ItemTypes;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Slot;

public interface ItemType {
    Slot createAmountOfItem(int amount, Quality quality);
    String getName();
    String name();
}
