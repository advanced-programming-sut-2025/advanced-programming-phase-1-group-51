package Models.ObjectsShownOnMap;

public abstract class ObjectOnMap {

    public String type;
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
}
