package Models.Items;

import Models.Buff;
import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.FoodType;

public class Food extends Item{
    private FoodType foodTypes;
    private Buff buff;

    /// To be used in enums only!
    public Food(FoodType foodTypes) {
    }

    public Food(Quality quality, FoodType foodType) {
        super(quality, Integer.MAX_VALUE, foodType.price, -foodType.energy, foodType.name);
        this.foodTypes = foodType;
    }

    public Buff getBuff() {
        return  buff;
    }

    public void setFoodBuff(Buff buff) {
        this.buff = buff;
    }

    public FoodType getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(FoodType foodTypes) {
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
