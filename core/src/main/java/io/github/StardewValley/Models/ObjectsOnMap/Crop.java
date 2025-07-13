package io.github.StardewValley.Models.ObjectsOnMap;

import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.CropType;

public class Crop extends ObjectOnMap{
    private CropType cropType;

    public Crop(float x, float y,  CropType cropType) {
        super(x, y,50, 50,30,30, "crop");
        this.cropType = cropType;
    }
}
