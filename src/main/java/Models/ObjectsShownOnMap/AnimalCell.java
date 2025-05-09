package Models.ObjectsShownOnMap;

import Models.Animal;

public class AnimalCell extends ObjectOnMap{
    public Animal animal;

    public AnimalCell(Animal animal) {
        super(false, "animal", "purple");
        this.animal = animal;
    }
}
