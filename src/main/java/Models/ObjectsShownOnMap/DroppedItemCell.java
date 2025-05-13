package Models.ObjectsShownOnMap;

import Models.Items.Item;

public class DroppedItemCell extends ObjectOnMap{

    private int quantity;
    private Item item;

    public DroppedItemCell(String color, int quantity, Item item) {
        super(true, "droppedItem", color);
        this.quantity = quantity;
        this.item = item;
    }



    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }
}
