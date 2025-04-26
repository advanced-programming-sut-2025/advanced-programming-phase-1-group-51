package Models.Enums.Types;

import Models.Enums.Others.Season;

public enum ForagingTreeType {

    ACORNS(Season.values()),
    MAPLE_SEEDS(Season.values()),
    PINE_CONES(Season.values()),
    MAHOGANY_SEEDS(Season.values()),
    MUSHROOM_TREE_SEEDS(Season.values()),;

    private final Season[] growthSeasons;

    ForagingTreeType(Season[] season) {
        this.growthSeasons = season;
    }
}
