package Models.ObjectsShownOnMap;

public class Lake extends ObjectOnMap{
    public Lake() {
        super(false, "Lake", "blue");
    }

    public Lake(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
