package Models;

import Models.Enums.Types.StoreType;
import Models.NPCs.NPC;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Store {
    private StoreType storeName;
    private NPC owner;
    private ArrayList<StoreProducts> products = new ArrayList<>();
    private LocalDateTime openHour;
    private LocalDateTime closeHour;
    private Cells cells;
    public Store(StoreType storeName,NPC owner, ArrayList<StoreProducts> products) {
        this.storeName = storeName;
        this.owner = owner;
        this.products = products;
    }

    public NPC getOwner() {
        return owner;
    }

    public ArrayList<StoreProducts> getProducts() {
        return products;
    }

    public void setOwner(NPC owner) {
        this.owner = owner;
    }

    public void setProducts(ArrayList<StoreProducts> products) {
        this.products = products;
    }

    public StoreType getStoreName() {
        return storeName;
    }

    public void setStoreName(StoreType storeName) {
        this.storeName = storeName;
    }

    public Cells getCells() {
        return cells;
    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }

    public LocalDateTime getOpenHour() {
        return openHour;
    }

    public void setOpenHour(LocalDateTime openHour) {
        this.openHour = openHour;
    }

    public LocalDateTime getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(LocalDateTime closeHour) {
        this.closeHour = closeHour;
    }
}
