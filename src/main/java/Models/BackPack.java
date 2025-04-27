package Models;

import Models.Enums.Types.BackpackType;

import java.util.ArrayList;

public class BackPack {
    private BackpackType type;
    private ArrayList<Loot> loots = new ArrayList<>();
    int numberOfGold;
    int numberOfWood;

    public BackPack(BackpackType type) {
        this.type = type;
    }

    public BackpackType getType() {
        return type;
    }

    public void setType(BackpackType type) {
        this.type = type;
    }

    public ArrayList<Loot> getLoots() {
        return loots;
    }

    public void setLoots(ArrayList<Loot> lots) {
        this.loots = lots;
    }

    public int getNumberOfGold() {
        return numberOfGold;
    }

    public void setNumberOfGold(int numberOfGold) {
        this.numberOfGold = numberOfGold;
    }

    public int getNumberOfWood() {
        return numberOfWood;
    }

    public void setNumberOfWood(int numberOfWood) {
        this.numberOfWood = numberOfWood;
    }
}
