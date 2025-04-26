package Models.Buildings;

import Models.Animal;

import java.util.ArrayList;

public class Barn extends Building {
    public ArrayList<Animal> Animals = new ArrayList<>();

    public Barn(ArrayList<Animal> animals) {
        Animals = animals;
    }
}
