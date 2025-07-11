package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingTreeSeedType;

public class TreeSeed extends Item{

    private ForagingTreeSeedType treeSeedsType;

    public TreeSeed() {
    }

    public TreeSeed(ForagingTreeSeedType treeSeedsType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, treeSeedsType.value, 0, treeSeedsType.name);
        this.treeSeedsType = treeSeedsType;
    }

    public ForagingTreeSeedType getTreeSeedsType() {
        return treeSeedsType;
    }

    public void setTreeSeedsType(ForagingTreeSeedType treeSeedsType) {
        this.treeSeedsType = treeSeedsType;
    }
}
