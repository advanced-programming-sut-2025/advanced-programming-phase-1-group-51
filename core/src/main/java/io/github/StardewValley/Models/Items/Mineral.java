package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;

public class Mineral extends Item{
    private ForagingMineralType type;

    public void setType(ForagingMineralType type) {
        this.type = type;
    }

    public ForagingMineralType getType() {
        return type;
    }

    public Mineral(ForagingMineralType type) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, type.getSellPrice(), 0, type.name);
        this.type = type;
    }


}
