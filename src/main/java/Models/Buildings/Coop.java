package Models.Buildings;

import Models.Animal;
import Models.Maps.Cells;

import java.util.ArrayList;

public class Coop extends Building {
    public String coopType;
    public ArrayList<Animal> animals = new ArrayList<>();
    public int capacity;

    public Coop() {
    }

    public Coop(ArrayList<Cells> buildingCells, String coopType, int capacity) {
        super(buildingCells);
        this.coopType = "Coop";
        this.capacity = capacity;
    }
}
