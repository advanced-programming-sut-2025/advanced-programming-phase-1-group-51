package Models.ObjectsShownOnMap;

public class BurntCell extends ObjectOnMap{

    public BurntCell() {
        super(true, "burnt", "brown");
    }

    public BurntCell(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
