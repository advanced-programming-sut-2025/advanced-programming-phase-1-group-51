package Models.Maps;

import Models.Buildings.Building;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.ObjectsShownOnMap.*;

import java.util.ArrayList;

public class Farm {

    private ArrayList<Building> buildings;
    private ArrayList<Cells> cells;
    private int farmNumber;

    public Farm(ArrayList<Building> buildings, ArrayList<Cells> cells) {
        this.buildings = buildings;
        this.cells = cells;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Cells> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cells> cells) {
        this.cells = cells;
    }

    public void thor(int x, int y){
        Cells cell = findCell(x, y);
        if (cell != null) {
            if (cell.getObjectOnCell() instanceof Tree) {
                cell.setObjectOnCell(new Tree(TreeType.BURNT_TREE));
            }
            if (cell.getObjectOnCell() instanceof Crop) {
                cell.setObjectOnCell(new BurntCell());
            }
        }
        System.out.println("thor has hit to position: " + x + ", " + y);
    }


    public Cells findCell(int x, int y) {
        for (Cells cell : cells) {
            if (cell.getPosition().getX() == x && cell.getPosition().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public int getFarmNumber() {
        return farmNumber;
    }

    public void setFarmNumber(int farmNumber) {
        this.farmNumber = farmNumber;
    }
}
