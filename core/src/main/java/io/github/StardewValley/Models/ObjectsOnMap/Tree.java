package io.github.StardewValley.Models.ObjectsOnMap;

import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.TreeType;


public class Tree extends ObjectOnMap{
    private TreeType treeType;

    public Tree(float x, float y, TreeType treeType) {
        super(x,y, 50 , 50, 30,30,"tree");
        this.treeType = treeType;
    }

}
