package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class NotificationAssets implements Disposable {
    private Texture backgroundTexture;
    private boolean loaded = false;

    public void load() {
        try {
            if (Gdx.files.internal("notifications/notification_bg.jpg").exists()) {
                backgroundTexture = new Texture(Gdx.files.internal("notifications/notification_bg.jpg"));
                loaded = true;
                Gdx.app.debug("NotificationAssets", "Successfully loaded notification background");
            } else {
                Gdx.app.error("NotificationAssets", "Notification background file not found");
                backgroundTexture = createFallbackTexture();
            }
        } catch (Exception e) {
            Gdx.app.error("NotificationAssets", "Error loading notification background", e);
            backgroundTexture = createFallbackTexture();
        }
    }

    private Texture createFallbackTexture() {
        Gdx.app.debug("NotificationAssets", "Creating fallback texture");
        Pixmap pixmap = new Pixmap(300, 80, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.9f, 0.8f, 0.7f, 0.8f); // Light brown parchment color
        pixmap.fill();
        pixmap.setColor(0.4f, 0.3f, 0.2f, 1f); // Dark brown border
        pixmap.drawRectangle(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture getBackgroundTexture() {
        if (!loaded) {
            Gdx.app.error("NotificationAssets", "Assets not loaded yet!");
            return createFallbackTexture();
        }
        return backgroundTexture;
    }

    @Override
    public void dispose() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
            Gdx.app.debug("NotificationAssets", "Disposed notification assets");
        }
    }
}
