package io.github.StardewValley.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Assets.PlayerAssets;
import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Recipes.CookingRecipes;
import io.github.StardewValley.Models.Enums.Recipes.CraftingRecipes;
import io.github.StardewValley.Models.Enums.Types.BackpackType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ToolType;
import io.github.StardewValley.Models.Graphics.CollisionRect;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.Items.Tool;
import io.github.StardewValley.Models.Maps.Farm;
import io.github.StardewValley.Models.Skills.*;

import java.util.ArrayList;

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
    private float maxEnergy = 200;
    private float currentEnergy = maxEnergy;
    private float energyDrainRate = 0.2f; // Base drain rate per second
    private float money;
    private boolean isExhausted = false;
    private BackPack inventory;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<CraftingRecipes> craftingRecipes = new ArrayList<>();
    private Item itemInHand;
    private ArrayList<Notif> notifications = new ArrayList<>();
    private static final float NOTIFICATION_DISPLAY_TIME = 3f;

    public void addNotification(String message) {
        notifications.add(new Notif(message, NOTIFICATION_DISPLAY_TIME, message.length() * 20));

        // Limit number of notifications to prevent memory issues
        if (notifications.size() > 5) {
            notifications.remove(0);
        }
    }

    public void updateNotifications(float delta) {
        for (int i = notifications.size() - 1; i >= 0; i--) {
            Notif notif = notifications.get(i);
            notif.update(delta);
            if (notif.isExpired()) {
                notifications.remove(i);
            }
        }
    }

    // In Player.java
    public void renderNotifications(SpriteBatch batch) {
        if (batch == null || notifications.isEmpty()) return;

        float startY = 50; // Position from bottom of screen
        float spacing = 90; // Space between notifications
        float x = 20; // Position from left of screen

        // Render from bottom up
        for (int i = 0; i < notifications.size(); i++) {
            notifications.get(i).render(batch, x, startY + (i * spacing));
        }
    }

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
        this.speed = 500;

        // Initialize collision rect
        this.collisionRect = new CollisionRect(
            position.x,
            position.y,
            25,
            25
        );

        currentSprite.setPosition(position.x, position.y);
        this.inventory = new BackPack(BackpackType.DEFAULT);
        this.itemInHand = new Tool(ToolType.AXE);
        this.money = 2000;
        initializeInventory();
        initializeSkills();
        initializeRecipes();
    }

    public Player(User user) {
        this();
        this.user = user;
    }


    public void update(float deltaTime) {
        stateTime += deltaTime;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);

        // Update energy only when moving
        if (isMoving && !isExhausted) {
            currentEnergy -= energyDrainRate * deltaTime;
            if (currentEnergy <= 0) {
                currentEnergy = 0;
                isExhausted = true;
            }
        }

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
    }

    public void move(float x, float y) {
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


    public void restoreEnergy(float amount) {
        currentEnergy = Math.min(currentEnergy + amount, maxEnergy);
        if (currentEnergy > maxEnergy * 0.2f) { // 20% threshold
            isExhausted = false;
//            this.speed = 500; // Restore normal speed
        }
    }

    public void sleep() {
        currentEnergy = maxEnergy;
        isExhausted = false;
//        this.speed = 500;
    }


    private void initializeRecipes() {
        this.craftingRecipes.add(CraftingRecipes.FURNACE);
        this.craftingRecipes.add(CraftingRecipes.SCARE_CROW);
        this.craftingRecipes.add(CraftingRecipes.MAYONNAISE_MACHINE);
        this.cookingRecipes.add(CookingRecipes.FRIED_EGG);
        this.cookingRecipes.add(CookingRecipes.BAKED_FISH);
        this.cookingRecipes.add(CookingRecipes.SALAD);
    }


    public void initializeInventory() {
        if (inventory == null) {
            inventory = new BackPack(BackpackType.DEFAULT);
        }

        // Clear existing slots if any
        inventory.getSLots().clear();

        // Add default tools
        inventory.getSLots().add(
            new Slot(new Tool(Quality.DEFAULT, 0, 5, "Default Hoe", ToolType.HOE), 1));
        inventory.getSLots().add(
            new Slot(new Tool(Quality.DEFAULT, 0, 5, "Default Pickaxe", ToolType.PICKAXE), 1));
        inventory.getSLots().add(
            new Slot(new Tool(Quality.DEFAULT, 0, 5, "Default Axe", ToolType.AXE), 1));
        inventory.getSLots().add(
            new Slot(new Tool(Quality.DEFAULT, 0, 5, "Default Water Can", ToolType.WATERING_CAN), 1));
        inventory.getSLots().add(
            new Slot(new Tool(Quality.DEFAULT, 0, 5, "Default Scythe", ToolType.SCYTHE), 1));
    }

    private void initializeSkills() {
        this.skills.add(new Farming());
        this.skills.add(new Fishing());
        this.skills.add(new Foraging());
        this.skills.add(new Mining());
    }

    public void setEnergyDrainRate(float modifier) {
        this.energyDrainRate = 0.2f * modifier; // Base rate * weather modifier
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

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public float getSpeed() {
        return speed;
    }

    public float getCurrentEnergy() {
        return currentEnergy;
    }

    public float getMaxEnergy() {
        return maxEnergy;
    }

    public PlayerAssets getAssets() {
        return assets;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }



    public Item getItemInHand() {
        return itemInHand;
    }

    public BackPack getInventory() {
        return inventory;
    }

    public void setItemInHand(Item itemInHand) {
        this.itemInHand = itemInHand;
    }

    public void setCurrentEnergy(float currentEnergy) {
        this.currentEnergy = currentEnergy;
    }
}
