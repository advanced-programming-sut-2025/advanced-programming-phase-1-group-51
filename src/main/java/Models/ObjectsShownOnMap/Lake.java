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

    @Override
    public String getType() {
        return super.getType();
    }


    @Override
    public boolean isWalkable() {
        return super.isWalkable();
    }


    @Override
    public String getColor() {
        return super.getColor();
    }

}
