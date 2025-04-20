package Models;

import Models.Enums.Types.PlantType;
import Models.Enums.Types.SeasonType;

public class Plants {
    private SeasonType GrowthSeason;
    private boolean CanBecomeGiant;
    private PlantType PlantType;
    private Cells PlantCell;

    public Plants(SeasonType GrowthSeason, boolean CanBecomeGiant, Models.Enums.Types.PlantType PlantType) {
        this.GrowthSeason = GrowthSeason;
        this.CanBecomeGiant = CanBecomeGiant;
        this.PlantType = PlantType;
    }

    public SeasonType getGrowthSeason() {
        return GrowthSeason;
    }

    public void setGrowthSeason(SeasonType growthSeason) {
        GrowthSeason = growthSeason;
    }

    public boolean isCanBecomeGiant() {
        return CanBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        CanBecomeGiant = canBecomeGiant;
    }

    public PlantType getPlantType() {
        return PlantType;
    }

    public void setPlantType(PlantType plantType) {
        PlantType = plantType;
    }

    public Cells getPlantCell() {
        return PlantCell;
    }

    public void setPlantCell(Cells plantCell) {
        PlantCell = plantCell;
    }
}
