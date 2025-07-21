package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Graphics.CollisionRect;

public class Wall extends ObjectOnMap {
    private static Texture verticalWallTexture;
    private static Texture horizontalWallTexture;
    private static Texture houseBackTexture;
    private static Texture greenhouseBackTexture;
    private static Texture greenhouseFloorTexture;
    private static Texture ruinedGreenhouseTexture;
    private static Texture blackSmithBackTexture;
    private static Texture fishShopBackTexture;
    private static Texture horDeskFish;
    private static Texture verDeskFish;
    private static Texture blackSmithFurniture;
    private static Texture starDropBack;
    private static Texture starDropDesk;

    private String typeWall;


    public Wall(float x, float y, float width, float height, String typeWall) {
        super(x, y, width, height,width,height, "wall");
        this.typeWall = typeWall;
        if (typeWall.equals("vertical")) {
            if (verticalWallTexture == null) {
                verticalWallTexture = new Texture("initials/Vertical_Wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, width, height);
            this.sprite = new Sprite(verticalWallTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);

        }
        else if (typeWall.equals("horizontal")) {
            if (horizontalWallTexture == null) {
                horizontalWallTexture = new Texture("initials/Horizontal_Wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, width, height);
            this.sprite = new Sprite(horizontalWallTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);

        }
        // house
        else if(typeWall.equals("back_house")) {
            if (houseBackTexture == null) {
                houseBackTexture = new Texture("initials/back_wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, width, height);
            this.sprite = new Sprite(houseBackTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        // greenhouse
        else if(typeWall.equals("back_greenhouse")) {
            if (greenhouseBackTexture == null) {
                greenhouseBackTexture = new Texture("initials/greenhouse_back_wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, width, height);
            this.sprite = new Sprite(greenhouseBackTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        else if(typeWall.equals("floor_greenhouse")) {
            if (greenhouseFloorTexture == null) {
                greenhouseFloorTexture = new Texture("initials/greenhouse_floor.png");
            }

            this.sprite = new Sprite(greenhouseFloorTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        else if(typeWall.equals("ruined_greenhouse")) {
            if (ruinedGreenhouseTexture == null) {
                ruinedGreenhouseTexture = new Texture("initials/ruinedGreenhouse.png");
            }

            this.sprite = new Sprite(ruinedGreenhouseTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // blacksmith
        else if(typeWall.equals("blackSmith_back")) {
            if (blackSmithBackTexture == null) {
                blackSmithBackTexture = new Texture("initials/BlackSmith_Back.png");
            }

            this.sprite = new Sprite(blackSmithBackTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("blackSmith_furniture")) {
            if (blackSmithFurniture == null) {
                blackSmithFurniture = new Texture("initials/blackSmith_furniture.png");
            }

            this.sprite = new Sprite(blackSmithFurniture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // fishShop
        else if(typeWall.equals("fishShop_back")) {
            if (fishShopBackTexture == null) {
                fishShopBackTexture = new Texture("initials/FishShop_Back.png");
            }

            this.sprite = new Sprite(fishShopBackTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        else if(typeWall.equals("horizontal_desk_fishShop")) {
            if (horDeskFish == null) {
                horDeskFish = new Texture("initials/horizontal_desk_fishShop.png");
            }

            this.sprite = new Sprite(horDeskFish);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        else if(typeWall.equals("vertical_desk_fishShop")) {
            if (verDeskFish == null) {
                verDeskFish = new Texture("initials/vertical_desk_fishShop.png");
            }

            this.sprite = new Sprite(verDeskFish);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        //StarDrop
        else if(typeWall.equals("starDrop_back")) {
            if (starDropBack == null) {
                starDropBack = new Texture("initials/starDrop_back.png");
            }

            this.sprite = new Sprite(starDropBack);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
        else if(typeWall.equals("starDrop_desk")) {
            if (starDropDesk == null) {
                starDropDesk = new Texture("initials/starDrop_desk.png");
            }

            this.sprite = new Sprite(starDropDesk);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // JojaMart

        else if(typeWall.equals("jojaMart_back")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("jojaMart_desk")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_desk.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("jojaMart_food")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_food.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("jojaMart_cash")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_cash.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("jojaMart_bar1")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_bar1.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("jojaMart_bar2")) {

            this.sprite = new Sprite( new Texture("initials/jojaMart_bar2.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // Pierre General Store

        else if(typeWall.equals("pierre_back")) {

            this.sprite = new Sprite( new Texture("initials/pierre_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("pierre_desk")) {

            this.sprite = new Sprite( new Texture("initials/pierre_desk.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("pierre_help_back")) {

            this.sprite = new Sprite( new Texture("initials/pierre_help_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("pierre_side")) {

            this.sprite = new Sprite( new Texture("initials/pierre_side.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("pierre_food")) {

            this.sprite = new Sprite( new Texture("initials/pierre_food.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // Marnie's Ranch

        else if(typeWall.equals("Marnie_back")) {

            this.sprite = new Sprite( new Texture("initials/Marnie_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("Marnie_help_back")) {

            this.sprite = new Sprite( new Texture("initials/Marnie_help_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("Marnie_desk")) {

            this.sprite = new Sprite( new Texture("initials/Marnie_desk.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        // Carpenter Shop

        else if(typeWall.equals("Carpenter_back")) {

            this.sprite = new Sprite( new Texture("initials/Carpenter_back.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }

        else if(typeWall.equals("Carpenter_desk")) {

            this.sprite = new Sprite( new Texture("initials/Carpenter_desk.png"));
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }


    }

    public String getTypeWall() {
        return typeWall;
    }
}
