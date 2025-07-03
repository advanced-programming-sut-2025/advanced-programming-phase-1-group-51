package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingCropSeedType;

public class CropSeed extends Item{
    private ForagingCropSeedType cropSeedsType;

    public CropSeed() {
    }

    public ForagingCropSeedType getCropType() {
        return cropSeedsType;
    }

    public void setCropType(ForagingCropSeedType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
    }

    public CropSeed(ForagingCropSeedType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
        this.name = cropSeedsType.source;
        this.quality = Quality.DEFAULT;
        this.maxSize = Integer.MAX_VALUE;
        this.value = cropSeedsType.baseSellPrice;
        this.energyCost = 0;
    }
}
