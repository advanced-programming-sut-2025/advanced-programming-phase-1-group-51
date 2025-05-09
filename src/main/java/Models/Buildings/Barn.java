package Models.Buildings;

import Models.Animal;
import Models.Maps.Cells;

import java.util.ArrayList;

public class Barn extends Building {
    public ArrayList<Animal> Animals = new ArrayList<>();

    public Barn(ArrayList<Cells> buildingCells, ArrayList<Animal> animals) {
        super(buildingCells);
        Animals = animals;
    }
}
