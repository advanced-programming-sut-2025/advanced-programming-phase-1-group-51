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


}
