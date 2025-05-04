package Models;

import Models.Items.Item;

public class Loot {

    private Item item;
    private int count;


    public Loot(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Loot [header=" + item.getName() + ", count=" + count + "]";
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
