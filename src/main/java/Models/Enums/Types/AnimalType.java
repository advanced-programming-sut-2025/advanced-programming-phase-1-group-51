package Models.Enums.Types;

import Models.Items.Item;

import java.util.ArrayList;

public enum AnimalType {
    HEN(1,800, 4),
    DUCK(2, 1200,8),
    RABBIT(4, 8000,12),
    DINOSAUR(7,14000, 8),
    COW(1, 1500,4),
    GOAT(2, 4000,8),
    SHEEP(3,8000, 12),
    PIG(1, 16000,12);

    public final int productPerDay;
    public final int price;
    public final int capacity;
    public final ArrayList<Item> products = new ArrayList<>();

    AnimalType(int productPerDay, int price, int capacity) {
        this.productPerDay = productPerDay;
        this.price = price;
        this.capacity = capacity;
    }
}
