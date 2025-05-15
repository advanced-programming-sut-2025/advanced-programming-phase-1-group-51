package Models.ObjectsShownOnMap;

import Models.Enums.Types.ItemTypes.ForagingMineralType;

public class MineralCell extends ObjectOnMap{

    private ForagingMineralType mineralType;

    public MineralCell(ForagingMineralType mineralType) {
        super(false, "foragingMineral", "black");
        this.mineralType = mineralType;
    }

    public ForagingMineralType getMineralType() {
        return mineralType;
    }

    public void setMineralType(ForagingMineralType mineralType) {
        this.mineralType = mineralType;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
