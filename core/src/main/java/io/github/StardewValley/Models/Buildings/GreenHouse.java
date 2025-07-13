package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class GreenHouse {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public GreenHouse(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWallsAndFloor();
    }

    private void createWallsAndFloor() {

        //floor
        walls.add(new Wall(x,y,360,420,"floor_greenhouse"));

        // Back walls
        walls.add(new Wall(x, 2270, 360,160, "back_greenhouse"));

        // Bottom wall (with gap for door)
            walls.add(new Wall(x, y- 4,160 ,20, "horizontal"));

            walls.add(new Wall(x + 220, y - 4, 150,20, "horizontal"));

        // Top wall
            walls.add(new Wall(x - 20, y + height - 110, 385,20, "horizontal"));

        // Left wall
            walls.add(new Wall(x - 20, y - 8, 20,515, "vertical"));

        // Right wall
            walls.add(new Wall(x + width - 125, y -8, 20,515, "vertical"));
    }





    public ArrayList<Wall> getWalls() {
        return walls;
    }

}
