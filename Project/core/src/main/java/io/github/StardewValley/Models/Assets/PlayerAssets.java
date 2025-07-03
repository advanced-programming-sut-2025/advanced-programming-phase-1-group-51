package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAssets {
    // Animations for each direction and state
    public Animation<TextureRegion> idleDownAnimation;
    public Animation<TextureRegion> idleUpAnimation;
    public Animation<TextureRegion> idleLeftAnimation;
    public Animation<TextureRegion> idleRightAnimation;

    public Animation<TextureRegion> walkDownAnimation;
    public Animation<TextureRegion> walkUpAnimation;
    public Animation<TextureRegion> walkLeftAnimation;
    public Animation<TextureRegion> walkRightAnimation;

    private float animationTime = 0;
    private Texture spriteSheet;

    public PlayerAssets() {
        spriteSheet = new Texture(Gdx.files.internal("player/player_spriteSheet.png"));
        loadAnimations();
    }

    private void loadAnimations() {
        // Split the sprite sheet into a 4x3 grid (4 rows, 3 columns)
        TextureRegion[][] frames = TextureRegion.split(
            spriteSheet,
            spriteSheet.getWidth() / 3,  // 3 columns
            spriteSheet.getHeight() / 4   // 4 rows
        );

        // Create animations by selecting specific frames from the grid
        // Row 0: Down animations
        idleDownAnimation = new Animation<>(0.2f, frames[0][0]); // Idle Down (single frame)
        walkDownAnimation = new Animation<>(0.15f, frames[0][1], frames[0][2]); // Walk Down (2 frames)

        // Row 1: Left animations
        idleLeftAnimation = new Animation<>(0.2f, frames[1][0]); // Idle Left
        walkLeftAnimation = new Animation<>(0.15f, frames[1][1], frames[1][2]); // Walk Left

        // Row 2: Up animations
        idleUpAnimation = new Animation<>(0.2f, frames[2][0]); // Idle Up
        walkUpAnimation = new Animation<>(0.15f, frames[2][1], frames[2][2]); // Walk Up

        // Row 3: Right animations
        idleRightAnimation = new Animation<>(0.2f, frames[3][0]); // Idle Right
        walkRightAnimation = new Animation<>(0.15f, frames[3][1], frames[3][2]); // Walk Right
    }

    public void update(float delta) {
        animationTime += delta;
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
