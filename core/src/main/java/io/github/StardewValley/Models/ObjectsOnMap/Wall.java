package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Graphics.CollisionRect;

public class Wall extends ObjectOnMap {
    private static Texture verticalWallTexture;
    private static Texture horizontalWallTexture;
    private static Texture backTexture;

    public Wall(float x, float y, float size, String type) {
        super(x, y, size, size, "wall");
        if (type.equals("vertical")) {
            if (verticalWallTexture == null) {
                verticalWallTexture = new Texture("initials/vertical_woll.jpg");
            }
            this.collisionRect = new CollisionRect(x, y, 20, size);
            this.sprite = new Sprite(verticalWallTexture);
            this.sprite.setSize(20, size);
            this.sprite.setPosition(x, y);

        } else if (type.equals("horizontal")) {
            if (horizontalWallTexture == null) {
                horizontalWallTexture = new Texture("initials/horizontal_woll.jpg");
            }
            this.collisionRect = new CollisionRect(x, y, size, 20);
            this.sprite = new Sprite(horizontalWallTexture);
            this.sprite.setSize(size, 20);
            this.sprite.setPosition(x, y);

        } else {
            if (backTexture == null) {
                backTexture = new Texture("initials/back_wall.png");
            }
            this.collisionRect = new CollisionRect(x, y, 480, 160);
            this.sprite = new Sprite(backTexture);
            this.sprite.setSize(480, 160);
            this.sprite.setPosition(x, y);
        }
    }
}
