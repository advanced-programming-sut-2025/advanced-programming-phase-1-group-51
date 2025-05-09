package Models.Buildings;

import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Maps.Cells;

import java.util.ArrayList;

public class Mine extends Building{

    public ArrayList<ForagingMineralType> minerals = new ArrayList<>();

    public Mine(ArrayList<Cells> buildingCells, ArrayList<ForagingMineralType> minerals) {
        super(buildingCells);
        this.minerals = minerals;
    }
}
