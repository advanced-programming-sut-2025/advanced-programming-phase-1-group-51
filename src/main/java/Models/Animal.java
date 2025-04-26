package Models;

import Models.Enums.Types.AnimalType;
import Models.Items.Item;
import java.util.ArrayList;

public class Animal {

    private int price;
    private String name;
    private AnimalType type;
    private ArrayList<Item> animalProducts;

    public Animal(int price, String name, AnimalType type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }
}
