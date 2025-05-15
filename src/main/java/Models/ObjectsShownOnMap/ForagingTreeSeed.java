package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForagingTreeType;

public class ForagingTreeSeed extends ObjectOnMap{

    private ForagingTreeType type;

    public ForagingTreeSeed(ForagingTreeType type) {
        super(false,"seed","green");
        this.type = type;
    }
}
