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

    public DroppedItemCell(boolean isWalkable, String type, String color) {
        super(isWalkable, type, color);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public boolean isWalkable() {
        return super.isWalkable();
    }

    @Override
    public void setWalkable(boolean walkable) {
        super.setWalkable(walkable);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }
}
