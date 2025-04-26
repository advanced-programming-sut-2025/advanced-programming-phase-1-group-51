package Models.ObjectsShownOnMap;

import Models.Enums.Types.TreeType;

public class Tree extends ObjectOnMap{

    private final TreeType treeType;
    private boolean hasBeenWateredToday;

    public Tree(TreeType treeType) {
        super(false, "tree", "green");
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
}
