package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class MarnieRanch {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public MarnieRanch(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back wall


        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,width ,20 - 7, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width + 20 - 7,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,200, "vertical"));

        walls.add(new Wall(x , y + 300, 20,height - 300, "vertical"));

        // Right wall
        walls.add(new Wall(x + width - 7, y, 20,height, "vertical"));

        walls.add(new Wall(x + 20, y + height - 360 + 35, 360 ,360, "Marnie_back"));

        walls.add(new Wall(x + 20, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 60, y + height - 360 + 234, 50,130, "Marnie_help_back"));;

        walls.add(new Wall(x + 373, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 423, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 473, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 523, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 573, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 623, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 673, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 723, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 773, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 823, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 873, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 923, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 973, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 1023, y + height - 360 + 234, 50,130, "Marnie_help_back"));

        walls.add(new Wall(x + 500, y + height - 360 + 110, 300,150, "Marnie_desk"));
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
