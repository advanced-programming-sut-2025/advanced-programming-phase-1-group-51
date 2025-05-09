package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ElseType;

public class Else extends Item{
    private ElseType ElseType;

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }


    public Else() {
        super();
        ElseType = null;
    }

    public Else(ElseType ElseType, Quality quality) {
        super(quality, Integer.MAX_VALUE, ElseType.value, 0, ElseType.name);
        this.ElseType = ElseType;
    }

    public Else(Quality quality, ElseType ElseType, int price) {
        super(quality, Integer.MAX_VALUE, price, 0, ElseType.name);
        this.ElseType = ElseType;
    }

    public Else(ElseType ElseType) {
        this(ElseType, Quality.DEFAULT);
    }

    public ElseType getElseType() {
        return ElseType;
    }

    public void setElseType(ElseType ElseType) {
        this.ElseType = ElseType;
    }

}
