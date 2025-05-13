package Models.ObjectsShownOnMap;

public class ForagingCrop {


    private ForagingCropType foragingCropsType;
    private boolean canBeHarvested;

    public ForagingCrop() {
        super();
    }

    public ForagingCrop(ForagingCropType type, boolean canBeHarvested) {
        super(true, "foragingCrop", "green");
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
}
