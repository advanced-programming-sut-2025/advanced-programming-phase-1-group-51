package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.TreeSeedType;

public class TreeSeed extends Item{

    private TreeSeedType type;

    public TreeSeed() {
    }

    public TreeSeed(TreeSeedType treeSeedsType) {
        super(Quality.DEFAULT, Integer.MAX_VALUE, treeSeedsType.value, 0, treeSeedsType.name);
        this.type = treeSeedsType;
    }

    public TreeSeedType getType() {
        return type;
    }
}
