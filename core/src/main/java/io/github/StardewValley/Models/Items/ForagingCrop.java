package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingCropType;

public class ForagingCrop extends Item{
    private ForagingCropType type;

    public ForagingCrop() {
    }

    public ForagingCrop(ForagingCropType type) {
        this.type = type;
        this.quality = Quality.DEFAULT;
        this.maxSize = Integer.MAX_VALUE;
        this.energyCost = 0;
    }

    public ForagingCropType getType() {
        return type;
    }
}
