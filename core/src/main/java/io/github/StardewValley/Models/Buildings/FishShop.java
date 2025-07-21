package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class FishShop {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public FishShop(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back walls
        walls.add(new Wall(x, y+ height - 310, width,320, "fishShop_back"));

        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,width ,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width + 20,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,height, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,150, "vertical"));

        walls.add(new Wall(x + width, y + 300, 20,height - 300, "vertical"));

        walls.add(new Wall(x + 120, y+ height - 340 , width /2 - 50,140,"horizontal_desk_fishShop"));
        walls.add(new Wall(x +60, y+ height - 310 -  320 + 80 + 100 - 10, width / 8 - 5,280,"vertical_desk_fishShop"));
        walls.add(new Wall(x +360, y+ height - 310 -  320 + 80 + 100 - 10, width / 8 - 5,280,"vertical_desk_fishShop"));


    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
