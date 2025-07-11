package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.Graphics.CollisionRect;

public abstract class ObjectOnMap {
    protected String type;
    protected Sprite sprite;
    protected CollisionRect collisionRect;
    protected Vector2 position;
    protected float width;
    protected float height;

    public ObjectOnMap(float x, float y, float width, float height, String type) {
        this.type = type;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.collisionRect = new CollisionRect(x, y, width, height);
    }

    public void render(SpriteBatch batch) {
        if (sprite != null) {
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
}
