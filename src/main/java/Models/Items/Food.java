package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.FoodType;

public class Food extends Item{
    public FoodType foodTypes;

    public Food() {
        super();
    }

    /// To be used in enums only!
    public Food(FoodType foodTypes) {
    }

    public Food(Quality quality, FoodType foodType) {
        super(quality, Integer.MAX_VALUE, foodType.price, -foodType.energy, foodType.name);
        this.foodTypes = foodType;
    }

    public Food(Quality quality, FoodType foodType, int value) {
        super(quality, Integer.MAX_VALUE, value, -foodType.energy, foodType.name);
        this.foodTypes = foodType;
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
