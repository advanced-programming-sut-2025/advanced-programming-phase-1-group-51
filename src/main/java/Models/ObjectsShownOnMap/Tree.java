package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForagingTreeType;

public class Tree extends ObjectOnMap{

    private ForagingTreeType treeType;
    private boolean hasBeenWateredToday;

    public Tree(ForagingTreeType treeType) {
        super(false, "plant", "green");
        this.treeType = treeType;
    }

    public ForagingTreeType getTreeType() {
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


}
