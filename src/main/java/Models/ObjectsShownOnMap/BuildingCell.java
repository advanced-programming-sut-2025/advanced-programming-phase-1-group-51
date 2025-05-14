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

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public boolean isWalkable() {
        return super.isWalkable();
    }

    @Override
    public void setWalkable(boolean walkable) {
        super.setWalkable(walkable);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }
}
