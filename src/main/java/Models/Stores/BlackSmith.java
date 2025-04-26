package Models.Stores;

import Models.Enums.Types.StoreType;
import Models.NPCs.NPC;
import Models.StoreProducts;

import java.util.ArrayList;

public class BlackSmith extends Store{

    public BlackSmith(StoreType storeName, NPC owner, ArrayList<StoreProducts> products) {
        super(storeName, owner, products);
    }
}
