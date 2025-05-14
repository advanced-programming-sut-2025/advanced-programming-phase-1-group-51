package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.TreeType;

public class Tree extends ObjectOnMap{

    private final TreeType treeType;
    private boolean hasBeenWateredToday;

    public Tree(TreeType treeType) {
        super(false, "plant", "green");
        this.treeType = treeType;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public boolean isHasBeenWateredToday() {
        return hasBeenWateredToday;
    }

    public void setHasBeenWateredToday(boolean hasBeenWateredToday) {
        this.hasBeenWateredToday = hasBeenWateredToday;
    }

    public Tree(boolean isWalkable, String type, String color) {
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
    public void setWalkable(boolean walkable) {
        super.setWalkable(walkable);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }
}
