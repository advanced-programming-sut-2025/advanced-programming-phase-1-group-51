package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ForagingMineralType;

public class Mineral extends Item{
    private ForagingMineralType type;

    public void setType(ForagingMineralType type) {
        this.type = type;
    }

    public ForagingMineralType getType() {
        return type;
    }

    public Mineral() {
        super();
        type = null;
    }

    public Mineral(Quality quality, int value, ForagingMineralType type) {
        super(quality, Integer.MAX_VALUE, value, 0, type.name);
        this.type = type;
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
