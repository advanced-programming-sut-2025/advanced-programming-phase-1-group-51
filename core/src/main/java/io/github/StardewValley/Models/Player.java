package io.github.StardewValley.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Assets.PlayerAssets;
import io.github.StardewValley.Models.Enums.Recipes.CookingRecipes;
import io.github.StardewValley.Models.Enums.Recipes.CraftingRecipes;
import io.github.StardewValley.Models.Enums.Types.TrashcanType;
import io.github.StardewValley.Models.FriendshipModels.Friendship;
import io.github.StardewValley.Models.FriendshipModels.Marriage;
import io.github.StardewValley.Models.Graphics.CollisionRect;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.Maps.Farm;
import io.github.StardewValley.Models.ObjectsOnMap.Animal;
import io.github.StardewValley.Models.Skills.Skill;

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
    private boolean isExhausted = false;
    private boolean isPlayerInHouse;
    private ArrayList<Notif> Notifications = new ArrayList<>();
    private ArrayList<Gift> gifts = new ArrayList<>();
    public ArrayList<Trade> tradingHistory = new ArrayList<>();
    private ArrayList<Friendship> friendships = new ArrayList<>();
    private TrashcanType trashcanType;
    private TrashCan trashcan;
    private ArrayList<Animal> animals = new ArrayList<>();
    private BackPack inventory;
    private int money;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<CraftingRecipes> craftingRecipes = new ArrayList<>();
    private boolean isPlayerFainted = false;
    private Item itemInHand;
    private ArrayList<Slot> refrigeratorLoots = new ArrayList<>();
    private double currentTurnUsedEnergy;
    private boolean isInVillage;
    private boolean isInFarm;
    private boolean isCloseToLake;
    private boolean isInHouse;
    private int currentFarmNumber;
    private int moneyTomorrow;
    private ArrayList<Buff> activeBuffs = new ArrayList<>();
    private String partnerUsername;
    private ArrayList<Notif> notifications = new ArrayList<>();
    private ArrayList<Marriage> marriageRequests = new ArrayList<>();

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
            currentSprite.getWidth() - 30,
            currentSprite.getHeight() - 70
        );

        currentSprite.setPosition(position.x, position.y);
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
            this.speed = 500; // Restore normal speed
        }
    }

    public void sleep() {
        currentEnergy = maxEnergy;
        isExhausted = false;
        this.speed = 500;
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


    // In Player.java
    public void setPosition(float x, float y) {
        this.position.set(x, y);
        this.collisionRect.move(x, y);
        this.currentSprite.setPosition(x, y);
    }

    public float getCurrentEnergy() {
        return currentEnergy;
    }

    public float getMaxEnergy() {
        return maxEnergy;
    }

    public boolean isExhausted() {
        return isExhausted;
    }

    public PlayerAssets getAssets() {
        return assets;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public float getStateTime() {
        return stateTime;
    }

    public float getEnergyDrainRate() {
        return energyDrainRate;
    }

    public boolean isPlayerInHouse() {
        return isPlayerInHouse;
    }

    public ArrayList<Notif> getNotifications() {
        return Notifications;
    }

    public ArrayList<Marriage> getMarriageRequests() {
        return marriageRequests;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public ArrayList<Trade> getTradingHistory() {
        return tradingHistory;
    }

    public ArrayList<Friendship> getFriendships() {
        return friendships;
    }

    public TrashcanType getTrashcanType() {
        return trashcanType;
    }

    public TrashCan getTrashcan() {
        return trashcan;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public BackPack getInventory() {
        return inventory;
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public ArrayList<CookingRecipes> getCookingRecipes() {
        return cookingRecipes;
    }

    public ArrayList<CraftingRecipes> getCraftingRecipes() {
        return craftingRecipes;
    }

    public boolean isPlayerFainted() {
        return isPlayerFainted;
    }

    public Item getItemInHand() {
        return itemInHand;
    }

    public ArrayList<Slot> getRefrigeratorLoots() {
        return refrigeratorLoots;
    }

    public double getCurrentTurnUsedEnergy() {
        return currentTurnUsedEnergy;
    }

    public boolean isInVillage() {
        return isInVillage;
    }

    public boolean isInFarm() {
        return isInFarm;
    }

    public boolean isCloseToLake() {
        return isCloseToLake;
    }

    public boolean isInHouse() {
        return isInHouse;
    }

    public int getCurrentFarmNumber() {
        return currentFarmNumber;
    }

    public int getMoneyTomorrow() {
        return moneyTomorrow;
    }

    public ArrayList<Buff> getActiveBuffs() {
        return activeBuffs;
    }

    public String getPartnerUsername() {
        return partnerUsername;
    }
}
