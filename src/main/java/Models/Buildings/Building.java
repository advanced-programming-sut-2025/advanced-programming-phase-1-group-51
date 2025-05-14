package Models.Buildings;

import Models.Maps.Cells;

import java.util.ArrayList;

public abstract class Building {

    public ArrayList<Cells> buildingCells;

    public Building() {
    }

    public Building(ArrayList<Cells> buildingCells) {
        this.buildingCells = buildingCells;
    }

}
