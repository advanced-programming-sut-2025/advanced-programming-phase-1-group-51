package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;
import java.util.ArrayList;

public class PlayerHouse {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public PlayerHouse(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {

        // Back walls
        walls.add(new Wall(960, 2345, 480,160, "back_house"));
        walls.add(new Wall(960 + 480, 2345, 480,160, "back_house"));

        // Bottom wall (with gap for door)
            walls.add(new Wall(x, y, 240,20, "horizontal"));
            walls.add(new Wall(x + 360, y, 600,20, "horizontal"));

        // Top wall
            walls.add(new Wall(x, y + height - 20, 960,20, "horizontal"));

        // Left wall
            walls.add(new Wall(x, y, 20,600, "vertical"));

        // Right wall
            walls.add(new Wall(x + width - 20, y, 20,600, "vertical"));
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
