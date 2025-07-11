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
        float wallSize = 50f; // Size of each wall segment

        // Back walls
        walls.add(new Wall(960, 2345, wallSize, "back"));
        walls.add(new Wall(960 + 480, 2345, wallSize, "back"));

        // Bottom wall (with gap for door)
        for (float i = x; i < x + 224; i += wallSize) {
            walls.add(new Wall(i, y, wallSize, "horizontal"));
        }
        for (float i = x + 240 + 120; i < x + width; i += wallSize) {
            walls.add(new Wall(i, y, wallSize, "horizontal"));
        }

        // Top wall
        for (float i = x; i < x + width - 35; i += wallSize) {
            walls.add(new Wall(i, y + height - 20, wallSize, "horizontal"));
        }

        // Left wall
        for (float j = y; j < y + height; j += wallSize) {
            walls.add(new Wall(x, j, wallSize, "vertical"));
        }

        // Right wall
        for (float j = y; j < y + height; j += wallSize) {
            walls.add(new Wall(x + width - 20, j, wallSize, "vertical"));
        }
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
