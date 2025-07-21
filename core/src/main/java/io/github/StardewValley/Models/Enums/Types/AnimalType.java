package io.github.StardewValley.Models.Enums.Types;

import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import java.util.ArrayList;
import java.util.Arrays;

public enum AnimalType {
    HEN(1, 800, 4, new ArrayList<>(Arrays.asList(MiscType.EGG, MiscType.LARGE_EGG))),
    DUCK(2, 1200, 8, new ArrayList<>(Arrays.asList(MiscType.DUCK_EGG, MiscType.DUCK_FEATHER))),
    RABBIT(4, 8000, 12, new ArrayList<>(Arrays.asList(MiscType.WOOL, MiscType.RABBITS_FOOT))),
    DINOSAUR(7, 14000, 8, new ArrayList<>(Arrays.asList(MiscType.DINOSAUR_EGG))),
    COW(1, 1500, 4, new ArrayList<>(Arrays.asList(MiscType.MILK, MiscType.LARGE_MILK))),
    GOAT(2, 4000, 8, new ArrayList<>(Arrays.asList(MiscType.GOAT_MILK, MiscType.LARGE_GOAT_MILK))),
    SHEEP(3, 8000, 12, new ArrayList<>(Arrays.asList(MiscType.WOOL))),
    PIG(1, 16000, 12, new ArrayList<>(Arrays.asList(MiscType.TRUFFLE)));

    public final int productPerDay;  // How often the animal produces (1 = daily, 2 = every other day, etc.)
    public final int price;          // Purchase price from Marnie's Ranch
    public final int capacity;       // How much space the animal needs in the barn/coop
    public final ArrayList<ItemType> products;  // What items the animal can produce

    AnimalType(int productPerDay, int price, int capacity, ArrayList<ItemType> products) {
        this.productPerDay = productPerDay;
        this.price = price;
        this.capacity = capacity;
        this.products = products;
    }

    // Helper method to check if this animal produces a specific item
    public boolean producesItem(ItemType item) {
        return products.contains(item);
    }

    // Get the primary product (first in the list)
    public ItemType getPrimaryProduct() {
        return products.get(0);
    }

    // Get all possible products as an array
    public ItemType[] getAllProducts() {
        return products.toArray(new ItemType[0]);
    }

    // Get the base price for the animal's products (average of all possible products)
    public int getAverageProductValue() {
        if (products.isEmpty()) return 0;
        int total = 0;
        for (ItemType product : products) {
            if (product instanceof MiscType) {
                total += ((MiscType) product).value;
            }
        }
        return total / products.size();
    }
}
