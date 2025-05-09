package Models.Items;

import Models.Enums.Others.Quality;

public abstract class Item {

    protected Quality quality;
    protected int maxSize;
    protected int value;
    protected double energyCost;
    protected String name;

    public Item() {
    }

    public Item(Quality quality, int maxSize, int value, double energyCost, String name) {
        this.quality = quality;
        this.maxSize = maxSize;
        this.value = value;
        this.energyCost = energyCost;
        this.name = name;
    }

    abstract public void useItem();

    abstract public void deleteItem();

    abstract public void dropItem();

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Quality getQuality() {
        return quality;
    }

    public double getEnergyCost() {
        return energyCost;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
