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
        else if(typeWall.equals("back_house")) {
            if (houseBackTexture == null) {
                houseBackTexture = new Texture("initials/back_wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, width, height);
            this.sprite = new Sprite(houseBackTexture);
            this.sprite.setSize(width, height);
            this.sprite.setPosition(x, y);
        }
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
    }

    public String getTypeWall() {
        return typeWall;
    }
}
