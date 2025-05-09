package Models.Maps;

import Models.NPCs.NPC;
import Models.Store;

import java.util.ArrayList;

public class Village {

    private ArrayList<Store>  stores;
    private ArrayList<NPC>  NPCs;

    public Village(ArrayList<Store> stores, ArrayList<NPC> NPCs) {
        this.stores = stores;
        this.NPCs = NPCs;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public void setNPCs(ArrayList<NPC> NPCs) {
        this.NPCs = NPCs;
    }
}
