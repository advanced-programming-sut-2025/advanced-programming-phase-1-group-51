package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.CropType;

public class Crop extends Item{
    private CropType type;

    public Crop() {
    }

    public Crop(CropType type) {
        this.type = type;
        this.quality = Quality.DEFAULT;
        this.maxSize = Integer.MAX_VALUE;
        this.energyCost = 0;
    }

    public CropType getType() {
        return type;
    }
}
