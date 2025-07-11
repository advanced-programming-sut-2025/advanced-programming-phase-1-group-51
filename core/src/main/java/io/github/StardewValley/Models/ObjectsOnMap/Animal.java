package io.github.StardewValley.Models.ObjectsOnMap;

import io.github.StardewValley.Models.Enums.Types.AnimalType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;

public class Animal extends ObjectOnMap{

    private AnimalType animalType;

    public Animal(float x, float y, AnimalType animalType) {
        super(x, y, 50, 50, "animal");
        this.animalType = animalType;
    }
}
