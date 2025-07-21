package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class PierreGeneralStore {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public PierreGeneralStore(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back wall
        walls.add(new Wall(x + 20, y + height - 340, 880,360, "pierre_back"));

        walls.add(new Wall(x + 1000 , y + height - 205, width- 1000,210, "pierre_help_back"));

        walls.add(new Wall(x + 640, y + height - 550, 360,160, "pierre_desk"));

        walls.add(new Wall(x + 640, y + height - 720, 360,160, "pierre_desk"));

        walls.add(new Wall(x + 20, y + height - 880, 60,500, "pierre_side"));

        walls.add(new Wall(x + 120, y + height - 690, 120,120, "pierre_food"));

        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,width ,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, 900,20, "horizontal"));

        walls.add(new Wall(x + 1000 , y + height, width -980,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,height, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,height, "vertical"));

    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
