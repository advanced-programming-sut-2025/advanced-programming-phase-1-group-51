package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.CropType;

import java.time.LocalDateTime;

public class Crop extends ObjectOnMap{

    public CropType cropSeedsType;
    private int daysToNextStage;
    private int stageNumber;
    private boolean hasBeenWateredToday = false;
    private boolean hasBeenFertilized = false;
    private LocalDateTime harvestDeadLine = null;
    private LocalDateTime lastWateringDate = null;
    private boolean isGiant = false;


    public Crop(CropType plantType) {
        super(true, "plant", "green");
        this.cropSeedsType = plantType;
        stageNumber = 0;
        daysToNextStage = cropSeedsType.stageZero;
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

    public CropType getCropSeedsType() {
        return cropSeedsType;
    }

    public void setCropSeedsType(CropType cropSeedsType) {
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

    public String plantInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("name : ").append(cropSeedsType.name).append("\n");
        builder.append("time to next stage : ").append(getDaysToNextStage()).append("\n");
        builder.append("stage number : ").append(stageNumber).append("\n");
        builder.append("has been watered today : ").append(hasBeenWateredToday).append("\n");
        return builder.toString();
    }
}
