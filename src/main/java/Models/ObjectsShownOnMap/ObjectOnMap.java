package Models.ObjectsShownOnMap;

public abstract class ObjectOnMap {

    public String type;
    public boolean isWalkable;
    public String color;

    public ObjectOnMap() {
    }

    public ObjectOnMap(boolean isWalkable, String type, String color) {
        this.isWalkable = isWalkable;
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        if (this instanceof AnimalCell) return "A";
        if (this instanceof ArtisanCell) return "B";
        if (this instanceof BuildingCell) return "B";
        if (this instanceof BurntCell) return "X";
        if (this instanceof Crop) return "C";
        if (this instanceof DroppedItemCell) return "D";
        if (this instanceof ForagingCrop) return "F";
        if (this instanceof ForagingTree) return "N";
        if (this instanceof Lake) return "L";
        if (this instanceof Tree) return "T";
        if (this instanceof HouseCell) return "H";
        if (this instanceof StoreCell) return "S";
        return "?"; // Unknown type
    }

}
