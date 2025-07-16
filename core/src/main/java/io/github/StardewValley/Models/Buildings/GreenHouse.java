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
        walls.add(new Wall(x- 25,y - 130,400,560,"ruined_greenhouse"));
    }

    public ArrayList<Wall> createWallsAndFloor() {
        ArrayList<Wall> newWalls = new ArrayList<>();

        //floor
        newWalls.add(new Wall(x,y,360,320,"floor_greenhouse"));

        // Back walls
        newWalls.add(new Wall(x, 2170, 360,160, "back_greenhouse"));

        // Bottom wall (with gap for door)
        newWalls.add(new Wall(x, y- 4,160 ,20, "horizontal"));
        newWalls.add(new Wall(x + 220, y - 4, 150,20, "horizontal"));

        // Top wall
        newWalls.add(new Wall(x - 20, y + height - 210, 385,20, "horizontal"));

        // Left wall
        newWalls.add(new Wall(x - 20, y -8, 20,415, "vertical"));

        // Right wall
        newWalls.add(new Wall(x + width - 125, y -8, 20,415, "vertical"));

        this.walls = newWalls; // Update the greenhouse's walls
        return newWalls;
    }





    public ArrayList<Wall> getWalls() {
        return walls;
    }

}
