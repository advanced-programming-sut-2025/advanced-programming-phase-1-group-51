package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Graphics.CollisionRect;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.ObjectsOnMap.*;
import io.github.StardewValley.Models.Player;

import java.util.ArrayList;

public class PlayerController {
    private Player player;
    private GameController gameController;
    private float footstepCooldown = 0;
    private final float FOOTSTEP_DELAY = 0.3f;

    public PlayerController(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;
    }

    public void update(float deltaTime) {
        Vector2 movement = new Vector2(0, 0);
        boolean isMoving = handlePlayerInput(movement, deltaTime);

        if (isMoving) {
            // Store old position for collision recovery
            Vector2 oldPosition = new Vector2(player.getPosition());

            // Set the player's velocity using the move method
            player.move(movement.x, movement.y);

            // Try moving in X direction
            player.getPosition().x += movement.x * player.getSpeed() * deltaTime;
            player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);

            // Check X collision
            if (checkWallCollisions() || checkForagingCollisions()) {
                player.getPosition().x = oldPosition.x; // Revert X movement
                player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);
                player.move(0, movement.y); // Reset X velocity
            }

            // Try moving in Y direction
            player.getPosition().y += movement.y * player.getSpeed() * deltaTime;
            player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);

            // Check Y collision
            if (checkWallCollisions() || checkForagingCollisions()) {
                player.getPosition().y = oldPosition.y; // Revert Y movement
                player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);
                player.move(movement.x, 0); // Reset Y velocity
            }
        }
        else {
            // Stop movement when no input
            player.move(0, 0);
        }

        // Update player state (including stateTime for animations and energy)
        player.update(deltaTime);

        // Update sprite position
        player.getCurrentSprite().setPosition(player.getPosition().x, player.getPosition().y);
    }

    private boolean handlePlayerInput(Vector2 movement, float deltaTime) {
        boolean isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            movement.y = 1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            movement.y = -1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movement.x = -1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movement.x = 1;
            isMoving = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            handleInteraction();
        }

        // Normalize diagonal movement
        if (movement.x != 0 && movement.y != 0) {
            movement.nor();
        }

        if (isMoving) {
            if (footstepCooldown <= 0) {
                Main.playSound(Main.getFootstep());
                footstepCooldown = FOOTSTEP_DELAY;
            } else {
                footstepCooldown -= deltaTime;
            }
        } else {
            footstepCooldown = 0; // Reset when not moving
        }

        return isMoving;
    }

    private boolean checkWallCollisions() {
        CollisionRect playerRect = player.getCollisionRect();
        ArrayList<Wall> walls = gameController.getWorldController().getAllWalls();

        for (Wall wall : walls) {
            if (playerRect.collidesWith(wall.getCollisionRect()) && !wall.getTypeWall().equals("floor_greenhouse")) {
                return true;
            }
        }

        return false;
    }

    private boolean checkForagingCollisions() {
        CollisionRect playerRect = player.getCollisionRect();
        ArrayList<ForagingCropBlock> crops = gameController.getWorldController().getForagingController().getForagingCrops();
        ArrayList<ForagingTreeBlock> trees = gameController.getWorldController().getForagingController().getForagingTrees();
        ArrayList<ForagingMineralBlock> minerals = gameController.getWorldController().getForagingController().getMineralBlocks();

        for (ForagingCropBlock crop : crops) {
            if (playerRect.collidesWith(crop.getCollisionRect())) {
                return true;
            }
        }

        for (ForagingTreeBlock tree : trees) {
            if (playerRect.collidesWith(tree.getCollisionRect())) {
                return true;
            }
        }

        for (ForagingMineralBlock mineral : minerals) {
            if (playerRect.collidesWith(mineral.getCollisionRect())) {
                return true;
            }
        }
        return false;
    }



    private void handleInteraction() {
        Player player = getPlayer();
        Item itemInHand = player.getItemInHand();

        if (itemInHand == null) return;

        // Check for closest interactable object in range
        ObjectOnMap closestObject = findClosestInteractable(player);
        if (closestObject == null) return;

        // Handle interaction based on object type and tool
        if (closestObject instanceof ForagingTreeBlock && itemInHand.getName().contains("Axe")) {
            handleTreeInteraction((ForagingTreeBlock) closestObject);
        }
        else if (closestObject instanceof ForagingCropBlock && itemInHand.getName().contains("Scythe")) {
            handleCropInteraction((ForagingCropBlock) closestObject);
        }
        else if (closestObject instanceof ForagingMineralBlock && itemInHand.getName().contains("Pickaxe")) {
            handleMineralInteraction((ForagingMineralBlock) closestObject);
        }
    }

    private ObjectOnMap findClosestInteractable(Player player) {
        ForagingController foraging = gameController.getWorldController().getForagingController();
        Vector2 playerPos = new Vector2(
            player.getPosition().x + player.getCurrentSprite().getWidth()/2,
            player.getPosition().y + player.getCurrentSprite().getHeight()/2
        );

        ObjectOnMap closest = null;
        float closestDist = Float.MAX_VALUE;

        // Check trees
        for (ForagingTreeBlock tree : foraging.getForagingTrees()) {
            if (isInRange(player, tree)) {
                float dist = playerPos.dst2(tree.getPosition().x, tree.getPosition().y);
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = tree;
                }
            }
        }

        // Check crops
        for (ForagingCropBlock crop : foraging.getForagingCrops()) {
            if (isInRange(player, crop)) {
                float dist = playerPos.dst2(crop.getPosition().x, crop.getPosition().y);
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = crop;
                }
            }
        }

        // Check minerals
        for (ForagingMineralBlock mineral : foraging.getMineralBlocks()) {
            if (isInRange(player, mineral)) {
                float dist = playerPos.dst2(mineral.getPosition().x, mineral.getPosition().y);
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = mineral;
                }
            }
        }

        return closest;
    }

    private boolean isInRange(Player player, ObjectOnMap object) {
        // Get player's collision rect
        CollisionRect playerRect = player.getCollisionRect();

        // Expand the player's rect by 50 pixels in all directions for interaction
        float interactionRange = 50f;
        CollisionRect interactionRect = new CollisionRect(
            playerRect.getX() - interactionRange,
            playerRect.getY() - interactionRange,
            playerRect.getWidth() + 2 * interactionRange,
            playerRect.getHeight() + 2 * interactionRange
        );

        // Check if object collides with this expanded rect
        return interactionRect.collidesWith(object.getCollisionRect());
    }

    private void handleTreeInteraction(ForagingTreeBlock tree) {
        // Add wood to inventory
        BackPack inventory = player.getInventory();
        inventory.addItem(ForagingMineralType.WOOD, 5); // Each tree gives 5 wood

        // Remove tree
        gameController.getWorldController().getForagingController().getForagingTrees().remove(tree);
//        Main.playSound(Main.getChopSound());
    }

    private void handleCropInteraction(ForagingCropBlock crop) {
        // Add harvested item to inventory
        ItemType harvestedItem = crop.getForagingCropType().getHarvestedItemType();
        player.getInventory().addItem(harvestedItem, 1);

        // Remove crop
        gameController.getWorldController().getForagingController().getForagingCrops().remove(crop);
//        Main.playSound(Main.getHarvestSound());
    }

    private void handleMineralInteraction(ForagingMineralBlock mineral) {
        // Add mineral to inventory
        player.getInventory().addItem(mineral.getForagingMineralType(), 1);

        // Remove mineral block
        gameController.getWorldController().getForagingController().getMineralBlocks().remove(mineral);
//        Main.playSound(Main.getRockCrackSound());
    }

    private Vector2 getInteractPosition(Player player) {
        // Base position is player center
        Vector2 position = new Vector2(
            player.getPosition().x + player.getCurrentSprite().getWidth()/2,
            player.getPosition().y + player.getCurrentSprite().getHeight()/2
        );

        // Distance to project in front of player
        float interactDistance = 80f; // Increased from 50

        // Adjust based on direction
        switch (player.getCurrentDirection()) {
            case UP:
                position.y += interactDistance;
                break;
            case DOWN:
                position.y -= interactDistance;
                break;
            case LEFT:
                position.x -= interactDistance;
                break;
            case RIGHT:
                position.x += interactDistance;
                break;
        }

        return position;
    }




    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
