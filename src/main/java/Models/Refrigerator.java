package Models;

import Models.Items.Food;

import java.util.ArrayList;

public class Refrigerator {

    private ArrayList<Food> foods;

    public Refrigerator(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
