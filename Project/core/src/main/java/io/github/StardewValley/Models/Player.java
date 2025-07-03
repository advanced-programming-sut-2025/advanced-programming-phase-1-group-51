package io.github.StardewValley.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Assets.PlayerAssets;
import io.github.StardewValley.Models.Graphics.CollisionRect;
import io.github.StardewValley.Models.Maps.Farm;

public class Player {
    private User user;
    private Vector2 position;
    private Vector2 velocity;
    private Farm farm;
    private CollisionRect collisionRect;
    private Sprite currentSprite;
    private float speed;
    private PlayerAssets assets;
    private Direction currentDirection = Direction.DOWN;
    private boolean isMoving = false;
    private float stateTime = 0f;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Player() {
        this.position = new Vector2(
            Gdx.graphics.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f
        );
        this.assets = GameAssetsManager.getInstance().getPlayerAssets();
        this.currentSprite = new Sprite(assets.idleDownAnimation.getKeyFrame(0));
        this.velocity = new Vector2();
        this.speed = 300;

        // Initialize collision rect
        this.collisionRect = new CollisionRect(
            position.x,
            position.y,
            currentSprite.getWidth(),
            currentSprite.getHeight()
        );

        currentSprite.setPosition(position.x, position.y);
    }

    public Player(User user) {
        this();
        this.user = user;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime); // Movement applied here
        collisionRect.move(position.x, position.y);
        updateSprite();
        currentSprite.setPosition(position.x, position.y);
    }

    private void updateSprite() {
        TextureRegion frame;

        if (isMoving) {
            switch (currentDirection) {
                case UP:
                    frame = assets.walkUpAnimation.getKeyFrame(stateTime, true);
                    break;
                case DOWN:
                    frame = assets.walkDownAnimation.getKeyFrame(stateTime, true);
                    break;
                case LEFT:
                    frame = assets.walkLeftAnimation.getKeyFrame(stateTime, true);
                    break;
                case RIGHT:
                    frame = assets.walkRightAnimation.getKeyFrame(stateTime, true);
                    break;
                default:
                    frame = assets.idleDownAnimation.getKeyFrame(stateTime, true);
            }
        } else {
            switch (currentDirection) {
                case UP:
                    frame = assets.idleUpAnimation.getKeyFrame(stateTime, false);
                    break;
                case DOWN:
                    frame = assets.idleDownAnimation.getKeyFrame(stateTime, false);
                    break;
                case LEFT:
                    frame = assets.idleLeftAnimation.getKeyFrame(stateTime, false);
                    break;
                case RIGHT:
                    frame = assets.idleRightAnimation.getKeyFrame(stateTime, false);
                    break;
                default:
                    frame = assets.idleDownAnimation.getKeyFrame(stateTime, false);
            }
        }

        // Flip sprite if needed (for left/right directions)
        if (currentDirection == Direction.LEFT) {
            currentSprite.setFlip(true, false);
        } else {
            currentSprite.setFlip(false, false);
        }

        currentSprite.setRegion(frame);
    }public void move(float x, float y) {
        velocity.set(x * speed, y * speed);
        isMoving = (x != 0 || y != 0);

        // Update direction based on movement (prioritize vertical movement)
        if (y > 0) {
            currentDirection = Direction.UP;
        } else if (y < 0) {
            currentDirection = Direction.DOWN;
        } else if (x > 0) {
            currentDirection = Direction.RIGHT;
        } else if (x < 0) {
            currentDirection = Direction.LEFT;
        }
    }

    public void render(SpriteBatch batch) {
        currentSprite.draw(batch);
    }

    // Getters and setters

    public User getUser() {
        return user;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
