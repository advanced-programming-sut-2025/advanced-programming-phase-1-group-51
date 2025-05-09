package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.FoodTypes;

public class Food extends Item{
    public FoodTypes foodTypes;

    public Food() {
        super();
    }

    /// To be used in enums only!
    public Food(FoodTypes foodTypes) {
    }

    public Food(Quality quality, FoodTypes foodTypes) {
        super(quality, Integer.MAX_VALUE, foodTypes.value, -foodTypes.energy, foodTypes.name);
        this.foodTypes = foodTypes;
    }

    public Food(Quality quality, FoodTypes foodTypes, int value) {
        super(quality, Integer.MAX_VALUE, value, -foodTypes.energy, foodTypes.name);
        this.foodTypes = foodTypes;
    }

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }
}
