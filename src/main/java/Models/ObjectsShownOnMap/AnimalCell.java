package Models.ObjectsShownOnMap;

import Models.Animal;

public class AnimalCell extends ObjectOnMap{
    public Animal animal;

    public AnimalCell(Animal animal) {
        super(false, "animal", "gold");
        this.animal = animal;
    }

    public AnimalCell(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public boolean isWalkable() {
        return super.isWalkable();
    }


    @Override
    public String getColor() {
        return super.getColor();
    }
}
