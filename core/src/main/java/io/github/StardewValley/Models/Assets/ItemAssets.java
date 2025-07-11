package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ItemAssets {
    private static ItemAssets itemAssets;
    private final String Clock = "Clock";
    private final Texture ClockTexture = new Texture(Gdx.files.internal("clock.png"));

    private ItemAssets() {

    }
    public static ItemAssets getItemAssets() {
        if (itemAssets == null) {
            itemAssets = new ItemAssets();
        }
        return itemAssets;
    }
    public Texture getClockTexture() {
        return ClockTexture;
    }
}
