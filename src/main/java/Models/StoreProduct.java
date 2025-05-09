package Models;

import Models.Items.Item;

public class StoreProduct {
    private final int price;
    private final Item item;
    private final String name;
    private int remainingCount;

    public StoreProduct(Item item, int price, String name) {
        this.item = item;
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public int getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }

    public String getName() {
        return name;
    }
}
