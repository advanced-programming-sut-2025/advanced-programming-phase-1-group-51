package Models.Buildings;

import Models.Animals;

import java.util.ArrayList;

public class Coop implements Building {
    public ArrayList<Animals> Animals = new ArrayList<>();

    public Coop(ArrayList<Models.Animals> animals) {
        Animals = animals;
    }
}
