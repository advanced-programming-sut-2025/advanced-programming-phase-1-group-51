package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingCropType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;
import io.github.StardewValley.Models.Graphics.CollisionRect;
import io.github.StardewValley.Models.ObjectsOnMap.ForagingCrop;
import io.github.StardewValley.Models.ObjectsOnMap.ForagingMineralBlock;
import io.github.StardewValley.Models.ObjectsOnMap.ForagingTree;
import io.github.StardewValley.Models.ObjectsOnMap.Wall;
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
            if (checkWallCollisions()) {
                player.getPosition().x = oldPosition.x; // Revert X movement
                player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);
                player.move(0, movement.y); // Reset X velocity
            }

            // Try moving in Y direction
            player.getPosition().y += movement.y * player.getSpeed() * deltaTime;
            player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);

            // Check Y collision
            if (checkWallCollisions()) {
                player.getPosition().y = oldPosition.y; // Revert Y movement
                player.getCollisionRect().move(player.getPosition().x, player.getPosition().y);
                player.move(movement.x, 0); // Reset Y velocity
            }
        } else {
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
        ArrayList<Wall> walls = gameController.getWorldController().getHouseWalls();

        for (Wall wall : walls) {
            if (playerRect.collidesWith(wall.getCollisionRect())) {
                return true;
            }
        }
        return false;
    }

    public void handleInteraction() {
        Player player = getPlayer();
        Vector2 interactPosition = getInteractPosition(player);

        // Check for interactions with foraging items
        checkForagingInteractions(interactPosition);
    }

    private Vector2 getInteractPosition(Player player) {
        // Calculate position in front of player based on direction
        float interactDistance = 50f; // pixels in front of player
        Vector2 position = new Vector2(player.getPosition());

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

    private void checkForagingInteractions(Vector2 interactPosition) {
        ForagingController foraging = gameController.getWorldController().getForagingController();

        // Check trees
//        for (ForagingTree tree : foraging.getForagingTrees()) {
//            if (tree.getCollisionRect().contains(interactPosition.x, interactPosition.y)) {
//                handleTreeInteraction(tree);
//                return;
//            }
//        }
//
//        // Check crops
//        for (ForagingCrop crop : foraging.getForagingCrops()) {
//            if (crop.getCollisionRect().contains(interactPosition.x, interactPosition.y)) {
//                handleCropInteraction(crop);
//                return;
//            }
//        }
//
//        // Check minerals
//        for (ForagingMineralBlock mineral : foraging.getMineralBlocks()) {
//            if (mineral.getCollisionRect().contains(interactPosition.x, interactPosition.y)) {
//                handleMineralInteraction(mineral);
//                return;
//            }
//        }
    }

    private void handleTreeInteraction(ForagingTree tree) {
        // Check if player has axe equipped
        if (player.getItemInHand() != null && player.getItemInHand().getName().contains("Axe")) {
            // Add wood to inventory based on tree type
            int woodAmount = 5; // base amount
            if (tree.getForagingTreeType() == ForagingTreeType.MAHOGANY_TREE) {
                woodAmount = 10;
            }

            // Add wood to inventory
            // player.getInventory().addItem(MiscType.WOOD, woodAmount);

            // Remove tree (or you could leave it for regrowth)
            gameController.getWorldController().getForagingController().getForagingTrees().remove(tree);

            // Play sound
            // Main.playSound(Main.getChopSound());
        }
    }

    private void handleCropInteraction(ForagingCrop crop) {
        // Check if player has scythe equipped (for grass) or is empty handed (for spring onions)
        if ((crop.getForagingCropType() == ForagingCropType.GRASS &&
            player.getItemInHand() != null &&
            player.getItemInHand().getName().contains("Scythe")) ||
            (crop.getForagingCropType() != ForagingCropType.GRASS &&
                player.getItemInHand() == null)) {

            // Add item to inventory
            ItemType harvestedItem = crop.getForagingCropType().getHarvestedItemType();
           // player.getInventory().addItem(harvestedItem, 1);

            // Remove crop
            gameController.getWorldController().getForagingController().getForagingCrops().remove(crop);

            // Play sound
            // Main.playSound(Main.getHarvestSound());
        }
    }

    private void handleMineralInteraction(ForagingMineralBlock mineral) {
        // Check if player has pickaxe equipped
        if (player.getItemInHand() != null && player.getItemInHand().getName().contains("Pickaxe")) {
            // Add mineral to inventory
           // player.getInventory().addItem(mineral.getForagingMineralType(), 1);

            // Remove mineral block
            gameController.getWorldController().getForagingController().getMineralBlocks().remove(mineral);

            // Play sound
           // Main.playSound(Main.getRockCrackSound());
        }
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
