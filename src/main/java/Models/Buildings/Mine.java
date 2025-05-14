package Models.Buildings;

import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Maps.Cells;

import java.util.ArrayList;

public class Mine extends Building{

    public Mine() {
    }

    public Mine(ArrayList<Cells> buildingCells) {
        super(buildingCells);
    }
}
