package Models.Stores;

import Models.Enums.Types.StoreType;
import Models.NPCs.NPC;
import Models.StoreProducts;

import java.util.ArrayList;

public class Marnie extends Store{

    public Marnie(StoreType storeName, NPC owner, ArrayList<StoreProducts> products) {
        super(storeName, owner, products);
    }
}
