package Models.ObjectsShownOnMap;

import Models.Enums.Types.ItemTypes.ForagingMineralType;

public class MineralCell extends ObjectOnMap{

    private ForagingMineralType Type;

    public MineralCell(ForagingMineralType type, String color, String name) {
        super(false, "foragingMineral", "black");
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
    public boolean isWalkable() {
        return super.isWalkable();
    }


    @Override
    public String getColor() {
        return super.getColor();
    }

}
