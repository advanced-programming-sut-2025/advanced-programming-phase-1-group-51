package io.github.StardewValley.Models.ObjectsOnMap;

import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ArtisanMachineType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;

public class ArtisanMachine extends ObjectOnMap{

    private ArtisanMachineType artisanMachineType;

    public ArtisanMachine(float x, float y,  ArtisanMachineType artisanMachineType) {
        super(x, y, 50, 50,30,30, "foragingTree");
        this.artisanMachineType = artisanMachineType;
    }
}
