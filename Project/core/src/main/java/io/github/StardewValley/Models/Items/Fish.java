package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FishType;

public class Fish extends Item{
    double R;
    double M;
    private FishType fishType;

    public FishType getFishType() {
        return fishType;
    }


    public Fish(Quality quality, FishType fishType) {
        super(quality, Integer.MAX_VALUE, fishType.price, 0, fishType.name);
        this.fishType = fishType;
    }
}
