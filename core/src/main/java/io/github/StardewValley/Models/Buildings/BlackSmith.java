package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class BlackSmith {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public BlackSmith(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back walls
//        walls.add(new Wall(x, 2270, 360,160, "back_greenhouse"));

        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,width ,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,height, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,515, "vertical"));

        walls.add(new Wall(x + width, y + 650, 20,810, "vertical"));
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
