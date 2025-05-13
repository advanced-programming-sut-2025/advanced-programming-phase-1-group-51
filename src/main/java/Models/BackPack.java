package Models;

import Models.Enums.Types.BackpackType;

import java.util.ArrayList;

public class BackPack {
    private BackpackType type;
    private ArrayList<Loot> loots = new ArrayList<>();

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

    public Loot findItemLoot(String itemName) {
        for (Loot loot : loots) {
            if (loot.getItem().getName().compareToIgnoreCase(itemName) == 0) {
                return loot;
            }
        }
        return null;
    }

    public void addLoot(Loot Loot) {
        this.loots.add(Loot);
    }

    public void removeLoot(Loot Loot) {
        loots.remove(Loot);
    }
}
