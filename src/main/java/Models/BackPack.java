package Models;

import Models.Enums.Types.BackPackType;

import java.util.ArrayList;

public class BackPack {
    private BackPackType type;
    private ArrayList<Loot> lots = new ArrayList<>();

    public BackPack(BackPackType type, ArrayList<Loot> lots) {
        this.type = type;
        this.lots = lots;
    }

    public BackPackType getType() {
        return type;
    }

    public void setType(BackPackType type) {
        this.type = type;
    }

    public ArrayList<Loot> getLots() {
        return lots;
    }

    public void setLots(ArrayList<Loot> lots) {
        this.lots = lots;
    }
}
