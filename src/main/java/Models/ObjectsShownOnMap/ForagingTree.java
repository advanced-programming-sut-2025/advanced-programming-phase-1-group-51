package Models.ObjectsShownOnMap;

public class ForagingTree extends ObjectOnMap{

    public ForagingTree() {
        super(false, "plant", "yellow");
    }

    public ForagingTree(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
