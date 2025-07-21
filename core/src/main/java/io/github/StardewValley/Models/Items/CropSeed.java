package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.CropSeedType;

public class CropSeed extends Item{
    private CropSeedType type;

    public CropSeed(CropSeedType cropSeedsType) {
        this.type = cropSeedsType;
        this.quality = Quality.DEFAULT;
        this.maxSize = Integer.MAX_VALUE;
        this.energyCost = 0;
    }

    public CropSeedType getType() {
        return type;
    }
}
