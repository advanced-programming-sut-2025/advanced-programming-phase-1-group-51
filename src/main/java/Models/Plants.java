package Models;

import Models.Enums.Types.TreeType;
import Models.Enums.Others.Season;

public class Plants {
    private Season GrowthSeason;
    private boolean CanBecomeGiant;
    private TreeType PlantType;
    private Cells PlantCell;

    public Plants(Season GrowthSeason, boolean CanBecomeGiant, TreeType PlantType) {
        this.GrowthSeason = GrowthSeason;
        this.CanBecomeGiant = CanBecomeGiant;
        this.PlantType = PlantType;
    }

    public Season getGrowthSeason() {
        return GrowthSeason;
    }

    public void setGrowthSeason(Season growthSeason) {
        GrowthSeason = growthSeason;
    }

    public boolean isCanBecomeGiant() {
        return CanBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        CanBecomeGiant = canBecomeGiant;
    }

    public TreeType getPlantType() {
        return PlantType;
    }

    public void setPlantType(TreeType plantType) {
        PlantType = plantType;
    }

    public Cells getPlantCell() {
        return PlantCell;
    }

    public void setPlantCell(Cells plantCell) {
        PlantCell = plantCell;
    }
}
