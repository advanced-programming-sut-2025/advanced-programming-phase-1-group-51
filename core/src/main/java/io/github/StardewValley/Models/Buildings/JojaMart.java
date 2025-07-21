package io.github.StardewValley.Models.Buildings;

import io.github.StardewValley.Models.ObjectsOnMap.Wall;

import java.util.ArrayList;

public class JojaMart {
    private ArrayList<Wall> walls;
    private float x, y;
    private float width, height;

    public JojaMart(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.walls = new ArrayList<>();
        createWalls();
    }

    private void createWalls() {


        // Back wall
        walls.add(new Wall(x + 20, y + 720, width - 20,250, "jojaMart_back"));

        // Bottom wall (with gap for door)
        walls.add(new Wall(x, y,400 ,20, "horizontal"));

        walls.add(new Wall(x + 500 , y,width  - 500,20, "horizontal"));

        // Top wall
        walls.add(new Wall(x, y + height, width + 20,20, "horizontal"));

        // Left wall
        walls.add(new Wall(x , y, 20,height, "vertical"));

        // Right wall
        walls.add(new Wall(x + width, y, 20,height, "vertical"));

        walls.add(new Wall(x + width - 380, y + 60, 270,140, "jojaMart_desk"));

        walls.add(new Wall(x + 200, y + height - 300, 205,105 , "jojaMart_food"));

        walls.add(new Wall(x + 60, y + 50, 110,175 , "jojaMart_cash"));

        walls.add(new Wall(x + 190, y + 50, 110,175 , "jojaMart_cash"));

        walls.add(new Wall(x + 100, y + 270, 75,360 , "jojaMart_bar1"));

        walls.add(new Wall(x + 250, y + 270, 75,360 , "jojaMart_bar2"));

        walls.add(new Wall(x + 400, y + 270, 75,360 , "jojaMart_bar1"));

        walls.add(new Wall(x + 550, y + 270, 75,360 , "jojaMart_bar2"));

        walls.add(new Wall(x + 700, y + 270, 75,360 , "jojaMart_bar1"));

        walls.add(new Wall(x + 850, y + 270, 75,360 , "jojaMart_bar2"));



    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
