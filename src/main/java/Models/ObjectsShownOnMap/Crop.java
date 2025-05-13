package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ForgingCropType;

import java.time.LocalDateTime;

public class Crop extends ObjectOnMap{

    public ForgingCropType cropSeedsType;
    private int daysToNextStage;
    private int stageNumber;
    private boolean hasBeenWateredToday = false;
    private boolean hasBeenFertilized = false;
    private LocalDateTime harvestDeadLine = null;
    private LocalDateTime lastWateringDate = null;
    private boolean isGiant = false;


    public Crop(ForgingCropType plantType) {
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

    public ForgingCropType getCropSeedsType() {
        return cropSeedsType;
    }

    public void setCropSeedsType(ForgingCropType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
    }

    public LocalDateTime getHarvestDeadLine() {
        return harvestDeadLine;
    }

    public void setHarvestDeadLine(LocalDateTime harvestDeadLine) {
        this.harvestDeadLine = harvestDeadLine;
    }

    public LocalDateTime getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(LocalDateTime lastWateringDate) {
        this.lastWateringDate = lastWateringDate;
    }

    public boolean isGiant() {
        return isGiant;
    }

    public void setGiant(boolean giant) {
        isGiant = giant;
    }
}
