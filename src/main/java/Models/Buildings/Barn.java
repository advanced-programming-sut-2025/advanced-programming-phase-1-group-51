package Models.Buildings;

import Models.Animals;

import java.util.ArrayList;

public class Barn implements Building {
    public ArrayList<Models.Animals> Animals = new ArrayList<>();

    public Barn(ArrayList<Models.Animals> animals) {
        Animals = animals;
    }
}
