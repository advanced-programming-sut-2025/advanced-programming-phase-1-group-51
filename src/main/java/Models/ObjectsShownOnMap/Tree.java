package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForagingTreeType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;

public class Tree extends ObjectOnMap{

    private TreeType treeType;
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




}
