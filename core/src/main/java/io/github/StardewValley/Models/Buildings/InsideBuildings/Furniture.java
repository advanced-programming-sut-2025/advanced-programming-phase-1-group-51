package io.github.StardewValley.Models.Buildings.InsideBuildings;

public class Furniture {
    private String name;
    private float x, y;
    private boolean interactable;
    private int type; // For texture atlas

    public Furniture(String name, float x, float y, boolean interactable) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.interactable = interactable;
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }
    public boolean isInteractable() { return interactable; }
    public int getType() { return type; }
}
