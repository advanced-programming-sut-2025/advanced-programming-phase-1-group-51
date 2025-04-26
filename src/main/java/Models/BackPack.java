package Models;

import Models.Enums.Types.BackpackType;

import java.util.ArrayList;

public class BackPack {
    private BackpackType type;
    private ArrayList<Loot> lots = new ArrayList<>();
    int numberOfGold;
    int numberOfWood;

    public BackPack(BackpackType type, ArrayList<Loot> lots) {
        this.type = type;
        this.lots = lots;
    }

    public BackpackType getType() {
        return type;
    }

    public void setType(BackpackType type) {
        this.type = type;
    }

    public ArrayList<Loot> getLots() {
        return lots;
    }

    public void setLots(ArrayList<Loot> lots) {
        this.lots = lots;
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
