package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ForagingMineralType;

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
    }

    public Mineral(Quality quality, ForagingMineralType type) {
        super(quality, Integer.MAX_VALUE, type.getSellPrice(), 0, type.name);
        this.type = type;
    }

    public Mineral(Quality quality, ForagingMineralType type, int sellPrice) {
        super(quality, Integer.MAX_VALUE, sellPrice, 0, type.name);
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
