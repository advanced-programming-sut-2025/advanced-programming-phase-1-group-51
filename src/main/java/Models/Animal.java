package Models;

import Models.Enums.Types.AnimalType;
import Models.Items.Item;
import java.util.ArrayList;

public class Animal {

    private int price;
    private String name;
    private AnimalType type;
    private Item product;
    private boolean hasBeenFedHayToday = false;
    private boolean hasBeenFedGrassToday = false;
    private boolean hasBeenPetToday = false;
    private boolean hasBeenCollected = false;
    private int friendshipLevel;


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

    public boolean isHasBeenFedHayToday() {
        return hasBeenFedHayToday;
    }

    public void setHasBeenFedHayToday(boolean hasBeenFedHayToday) {
        this.hasBeenFedHayToday = hasBeenFedHayToday;
    }

    public boolean isHasBeenFedGrassToday() {
        return hasBeenFedGrassToday;
    }

    public void setHasBeenFedGrassToday(boolean hasBeenFedGrassToday) {
        this.hasBeenFedGrassToday = hasBeenFedGrassToday;
    }

    public boolean isHasBeenPetToday() {
        return hasBeenPetToday;
    }

    public void setHasBeenPetToday(boolean hasBeenPetToday) {
        this.hasBeenPetToday = hasBeenPetToday;
    }

    public Item getProduct() {
        return product;
    }

    public void setProduct(Item product) {
        this.product = product;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }

    public boolean isHasBeenCollected() {
        return hasBeenCollected;
    }

    public void setHasBeenCollected(boolean hasBeenCollected) {
        this.hasBeenCollected = hasBeenCollected;
    }
}
