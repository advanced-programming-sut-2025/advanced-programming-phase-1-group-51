package Models;

import Models.Items.Item;

public class Loot {

    private Item nameOfThing;
    int numberOfItems;

    public Loot(Item nameOfThing, int numberOfItems) {
        this.nameOfThing = nameOfThing;
        this.numberOfItems = numberOfItems;
    }


    public Item getNameOfThing() {
        return nameOfThing;
    }

    public void setNameOfThing(Item nameOfThing) {
        this.nameOfThing = nameOfThing;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
