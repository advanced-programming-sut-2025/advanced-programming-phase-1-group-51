package Models.Buildings;

import Models.Animal;
import Models.Maps.Cells;

import java.util.ArrayList;

public class Barn extends Building {
    public String barnType;
    public ArrayList<Animal> animals = new ArrayList<>();
    public int capacity;

    public Barn() {
    }

    public Barn(ArrayList<Cells> buildingCells, String barnType, int capacity) {
        super(buildingCells);
        this.barnType = barnType;
        this.capacity = capacity;
    }
}
