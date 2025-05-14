package Models.ObjectsShownOnMap;

public abstract class ObjectOnMap {

    protected String type;
    public boolean isWalkable;
    public String color;

    public ObjectOnMap(boolean isWalkable, String type, String color) {
        this.isWalkable = isWalkable;
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        return type.substring(0, 1).toUpperCase();
    }

    public String getType() {
        return type;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public String getColor() {
        return color;
    }

}
