package Models.Buildings;

import Models.Animal;

import java.util.ArrayList;

public class Coop extends Building {
    private ArrayList<Animal> Animals = new ArrayList<>();
    private final int capacity = 4;

    public Coop(ArrayList<Animal> animals) {
        Animals = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return Animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        Animals = animals;
    }
}
