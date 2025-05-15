package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForagingCropType;

public class ForagingCrop extends ObjectOnMap{


    private ForagingCropType foragingCropsType;
    private boolean canBeHarvested;

    public ForagingCrop(ForagingCropType type, boolean canBeHarvested) {
        super(true, "plant", "green");
        this.foragingCropsType = type;
        this.canBeHarvested = canBeHarvested;
    }

    public boolean isCanBeHarvested() {
        return canBeHarvested;
    }

    public void setCanBeHarvested(boolean canBeHarvested) {
        this.canBeHarvested = canBeHarvested;
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
