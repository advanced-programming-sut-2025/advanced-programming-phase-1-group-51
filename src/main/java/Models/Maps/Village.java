package Models.Maps;

import Models.Enums.Types.StoresProductsTypes.*;
import Models.NPCs.NPC;
import Models.Store;
import Models.StoreProduct;

import java.util.ArrayList;

public class Village {

    private final ArrayList<Store> stores = new ArrayList<>();
    private final ArrayList<NPC> NPCs = new ArrayList<>();
    private final ArrayList<Cells> cells = new ArrayList<>();

    public Village() {
        initializeStores();
    }

    public void initializeStores() {
        Store s1 = new Store("Clint", 9, 16, "Blacksmith");
        Store s2 = new Store("Morris", 9, 23, "JojaMart");
        Store s3 = new Store("Pierre", 9, 17, "Pierre's General Store");
        Store s4 = new Store("Robin", 9, 20, "Carpenter's Shop");
        Store s5 = new Store("Willy", 9, 17, "Fish Shop");
        Store s6 = new Store("Marnie", 9, 16, "Marnie's Ranch");
        Store s7 = new Store("Gus", 12, 24, "The Stardrop Saloon");

        for (BlackSmithProducts p : BlackSmithProducts.values()) {
            StoreProduct sp = new StoreProduct(AllProducts.valueOf(p.name()) , s1.getName());
            s1.getProducts().add(sp);
        }
        for (JojaMartProducts p : JojaMartProducts.values()) {
            StoreProduct sp = new StoreProduct(AllProducts.valueOf(p.name()), s2.getName());
            s2.getProducts().add(sp);
        }
        for (PierreGeneralStoreProducts p : PierreGeneralStoreProducts.values()) {
            StoreProduct sp = new StoreProduct(AllProducts.valueOf(p.name()), s3.getName());
            s3.getProducts().add(sp);
        }
        for (FishShopProducts p : FishShopProducts.values()) {
            StoreProduct sp = new StoreProduct(AllProducts.valueOf(p.name()),s5.getName());
            s5.getProducts().add(sp);
        }
        for (StarDropSaloonProducts p : StarDropSaloonProducts.values()) {
            StoreProduct sp = new StoreProduct(AllProducts.valueOf(p.name()),s7.getName());
            s7.getProducts().add(sp);
        }

        addStore(s1);
        addStore(s2);
        addStore(s3);
        addStore(s4);
        addStore(s5);
        addStore(s6);
        addStore(s7);
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public Store getStore(String storeName) {
        for (Store store : stores) {
            if (store.getName().equalsIgnoreCase(storeName)) {
                return store;
            }
        }
        return null;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public ArrayList<Cells> getCells() {
        return cells;
    }



    public Cells findCellVillage(int x, int y) {
        for (Cells cell : cells) {
            if (cell.getPosition().getX() == x && cell.getPosition().getY() == y) {
                return cell;
            }
        }
        return null;
    }
}
