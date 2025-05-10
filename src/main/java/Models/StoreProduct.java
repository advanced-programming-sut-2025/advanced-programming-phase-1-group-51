package Models;

import Models.Enums.Types.StoresProductsTypes.AllProducts;
import Models.Items.Item;

public class StoreProduct {
    private AllProducts type;
    private int price;
    private Item item;
    private double remainingCount;
    private String storeName;

    public StoreProduct() {
    }

    public StoreProduct(AllProducts type , String storeName) {
        this.type = type;
        this.remainingCount = type.getDailyLimit();
        this.storeName = storeName;
    }

    public StoreProduct(Item item, int price) {
        this.item = item;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Item getItem() {
        return item;
    }

    public double getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(double remainingCount) {
        this.remainingCount = remainingCount;
    }

    public AllProducts getType() {
        return type;
    }

    public void setType(AllProducts type) {
        this.type = type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
