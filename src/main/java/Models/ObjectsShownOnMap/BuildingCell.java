package Models.ObjectsShownOnMap;

public class BuildingCell extends ObjectOnMap{

    public String buildingType;

    public BuildingCell(boolean isWalkable, String buildingType) {
        super(isWalkable, "buildingBlock", "red");
        this.buildingType = buildingType;
    }

    public BuildingCell(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
