package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForagingCropType;

public class ForagingCrop extends ObjectOnMap{


    private ForagingCropType foragingCropsType;
    private boolean canBeHarvested;

    public ForagingCrop(ForagingCropType type) {
        super(true, "plant", "green");
        this.foragingCropsType = type;
    }


    public ForagingCropType getForagingCropsType() {
        return foragingCropsType;
    }

    public void setForagingCropsType(ForagingCropType foragingCropsType) {
        this.foragingCropsType = foragingCropsType;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
