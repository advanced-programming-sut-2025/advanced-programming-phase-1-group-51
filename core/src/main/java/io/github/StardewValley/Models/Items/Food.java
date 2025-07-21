package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Buff;
import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FoodType;

public class Food extends Item{
    private FoodType type;
    private Buff buff;


    public Food(FoodType foodType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, foodType.price, -foodType.energy, foodType.name);
        this.type = foodType;
    }

    public FoodType getType() {
        return type;
    }
}
