package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class StarDropSaloon {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public StarDropSaloon(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {



        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,600 ,20, "horizontal"));

        walls.add(new Wall(x + 800 , y,width  - 800,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width + 20,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,height, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,height, "vertical"));


        walls.add(new Wall(x + width - 1200 + 10, y + 460, 1190,height / 2 - 50, "starDrop_back"));

        walls.add(new Wall(x + width - 1080, y +405, 650,height / 3 - 60, "starDrop_desk"));
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
