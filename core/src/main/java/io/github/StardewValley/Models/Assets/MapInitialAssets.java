package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MapInitialAssets {
    public TextureRegion stillTexture;

    public MapInitialAssets(String initialName) {
        try {
            Texture texture = new Texture(Gdx.files.internal(String.format("Initials/%s.jpg", initialName)));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            this.stillTexture = new TextureRegion(texture);
        } catch (Exception e) {
            System.err.println("Failed to load texture for: " + initialName);
            throw new RuntimeException("Could not load initial asset: " + initialName, e);
        }
    }

    public void dispose() {
        if (stillTexture != null && stillTexture.getTexture() != null) {
            stillTexture.getTexture().dispose();
        }
    }
}
