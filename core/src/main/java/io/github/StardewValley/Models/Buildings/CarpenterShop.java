package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class CarpenterShop {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public CarpenterShop(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back wall
        walls.add(new Wall(x, y + height - 250 ,width ,250, "Carpenter_back"));

        walls.add(new Wall(x + 100, y + height - 800 ,350 ,150, "Carpenter_desk"));

        walls.add(new Wall(x + 700, y + height - 800 ,350 ,150, "Carpenter_desk"));

        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,width ,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width + 20,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,600, "vertical"));

        walls.add(new Wall(x , y + 800, 20,height - 800, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,height, "vertical"));

    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
