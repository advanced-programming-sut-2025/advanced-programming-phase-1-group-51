package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Others.Season;
import Models.Enums.Types.ItemTypes.FishType;

public class Fish extends Item{
    double R;
    double M;
    private FishType fishType;

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }

    public FishType getFishType() {
        return fishType;
    }


    public Fish(Quality quality, FishType fishType) {
        super(quality, Integer.MAX_VALUE, fishType.price, 0, fishType.name);
        this.fishType = fishType;
    }

    @Override
    public int getValue() {
        if (this.quality == Quality.SILVER)
            return (int) ((double) this.value * 1.25);
        else if (this.quality == Quality.GOLD)
            return (int) ((double) this.value * 1.5);
        else if (this.quality == Quality.IRIDIUM)
            return (int) ((double) this.value * 2);

        return value;
    }




}
