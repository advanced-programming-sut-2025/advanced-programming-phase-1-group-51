package Models.ObjectsShownOnMap;

import Models.Enums.Types.AllCropsType;

public class Crop extends ObjectOnMap{

    public AllCropsType cropSeedsType;
    private int daysToNextStage;
    private int stageNumber;
    private boolean hasBeenWateredToday = false;
    private boolean hasBeenFertilized = false;


    public Crop(AllCropsType plantType) {
        super(true, "plant", "green");
        this.cropSeedsType = plantType;
        stageNumber = 0;
        daysToNextStage = cropSeedsType.StageZero;
        this.hasBeenWateredToday = false;
    }

    public boolean isHasBeenWateredToday() {
        return hasBeenWateredToday;
    }

    public void setHasBeenWateredToday(boolean hasBeenWateredToday) {
        this.hasBeenWateredToday = hasBeenWateredToday;
    }

    public int getDaysToNextStage() {
        return daysToNextStage;
    }

    public void setDaysToNextStage(int daysToNextStage) {
        this.daysToNextStage = daysToNextStage;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public boolean isHasBeenFertilized() {
        return hasBeenFertilized;
    }

    public void setHasBeenFertilized(boolean hasBeenFertilized) {
        this.hasBeenFertilized = hasBeenFertilized;
    }
}
