package Models;

import Models.Items.Item;

public class StoreProducts {
    private int price;
    private Item item;

    public StoreProducts(int price, Item item) {
        this.price = price;
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
