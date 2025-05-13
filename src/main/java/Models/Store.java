package Models;

import Models.Enums.Others.Season;

import java.util.ArrayList;

public class Store {

    private String owner;
    private int openHour;
    private int closeHour;
    private ArrayList<StoreProduct> products = new ArrayList<>();
    private String name;


    public Store(String owner, int openHour, int closeHour, String name) {
        this.owner = owner;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public int getOpenHour() {
        return openHour;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public ArrayList<StoreProduct> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen(int hour) {
        return openHour <= hour && hour <= closeHour;
    }


    public StoreProduct getProduct(String productName) {
        for (StoreProduct product : products) {
            if (product.getType().getName().compareToIgnoreCase(productName) == 0) {
                return product;
            }
        }
        return null;
    }
}
