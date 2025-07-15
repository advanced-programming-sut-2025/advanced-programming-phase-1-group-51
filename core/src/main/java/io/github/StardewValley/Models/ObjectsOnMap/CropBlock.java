package io.github.StardewValley.Models.ObjectsOnMap;

import io.github.StardewValley.Models.Enums.Types.ItemTypes.CropType;

public class CropBlock extends ObjectOnMap{
    private CropType cropType;

    public CropBlock(float x, float y, CropType cropType) {
        super(x, y,50, 50,30,30, "crop");
        this.cropType = cropType;
    }
}
