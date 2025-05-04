package Models;

import java.util.ArrayList;

public class Store {

    private final String owner;
    private final int openHour;
    private final int closeHour;
    private final ArrayList<StoreProducts> products = new ArrayList<>();
    private final String name;


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

    public ArrayList<StoreProducts> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen(int hour) {
        return openHour <= hour && hour <= closeHour;
    }

    public ArrayList<StoreProducts> getProductsRemaining(){
        ArrayList<StoreProducts> remainingProducts = new ArrayList<>();
        for (StoreProducts product : products) {
            if(product.getRemainingCount()>0){
                remainingProducts.add(product);
            }
        }
        return remainingProducts;
    }

    public StoreProducts getProduct(String productName) {
        for (StoreProducts product : products) {
            if(product.getName().compareToIgnoreCase(productName) == 0){
                return product;
            }
        }
        return null;
    }
}
