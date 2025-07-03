package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.Player;

import java.time.LocalTime;

public class PlayerController {
    private Player player;
    private GameController gameController;
    private float footstepCooldown = 0;
    private final float FOOTSTEP_DELAY = 0.3f; // Adjust this value as needed


    public PlayerController(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;
    }

    public void update(float deltaTime) {
        handlePlayerInput(deltaTime); // Pass deltaTime
        player.update(deltaTime);
    }

    public void handlePlayerInput(float deltaTime) {
        float x = 0, y = 0;
        boolean isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 1;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 1;
            isMoving = true;
        }

        // Normalize diagonal movement
        if (x != 0 && y != 0) {
            float len = (float)Math.sqrt(x * x + y * y);
            x /= len;
            y /= len;
        }

        // Footstep sound logic
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

        player.move(x, y);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
