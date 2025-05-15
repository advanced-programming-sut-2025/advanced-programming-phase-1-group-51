package Models.Items;

import Models.Buff;
import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.FoodType;

public class Food extends Item{
    private FoodType foodType;
    private Buff buff;


    public Food(FoodType foodType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, foodType.price, -foodType.energy, foodType.name);
        this.foodType = foodType;
    }

    public Food(Quality quality, FoodType foodType) {
        super(quality, Integer.MAX_VALUE, foodType.price, -foodType.energy, foodType.name);
        this.foodType = foodType;
        this.buff = foodType.buff;
    }

    public Food(Quality quality, FoodType foodType, int value) {
        super(quality, Integer.MAX_VALUE, value, -foodType.energy, foodType.name);
        this.foodType = foodType;
        this.buff = foodType.buff;
    }
    public Food(Quality quality, FoodType foodTypes, int value, int energy) {
        super(quality, Integer.MAX_VALUE, value, energy, foodTypes.name);
        this.foodType = foodTypes;
        this.buff = foodTypes.buff;
    }

    public Buff getBuff() {
        return  buff;
    }

    public void setFoodBuff(Buff buff) {
        this.buff = buff;
    }

    public FoodType getFoodTypes() {
        return foodType;
    }

    public void setFoodTypes(FoodType foodTypes) {
        this.foodType = foodTypes;
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
