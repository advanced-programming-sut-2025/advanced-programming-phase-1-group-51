package Models.ObjectsShownOnMap;

import Models.Enums.Types.ItemTypes.ForagingCropSeedType;

public class ForagingCropSeed extends ObjectOnMap{

    private ForagingCropSeedType type;


    public ForagingCropSeed(ForagingCropSeedType type) {
        super(false,"Seed","green");
        this.type = type;
    }
}
