package io.github.StardewValley.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import io.github.StardewValley.Models.Assets.GameAssetsManager;

public class Notif {
    private String message;
    private float displayTime;
    private float elapsedTime;
    private boolean expired;
    private BitmapFont font;
    private int width;

    public Notif(String message, float displayTime, int width) {
        this.width = width;
        this.message = message;
        this.displayTime = displayTime;

        try {
            this.font = GameAssetsManager.getInstance().getSkin().getFont("font");
            if (font == null) {
                Gdx.app.error("Notif", "Could not get default font from skin");
                font = new BitmapFont();
            }
            font.getData().setScale(1.2f);
            font.setColor(Color.BLACK);
        } catch (Exception e) {
            Gdx.app.error("Notif", "Error initializing notification font", e);
            font = new BitmapFont();
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                expired = true;
            }
        }, displayTime);
    }

    public void render(SpriteBatch batch, float x, float y) {
        if (expired || batch == null) return;

        try {
            Texture bg = GameAssetsManager.getInstance().getNotificationAssets().getBackgroundTexture();
            if (bg != null) {
                batch.draw(bg, x, y, width, 80);

                if (font != null) {
                    font.draw(batch, message, x + 20, y + 50);
                } else {
                    Gdx.app.error("Notif", "Font is null during render");
                }
            } else {
                Gdx.app.error("Notif", "Background texture is null");
            }
        } catch (Exception e) {
            Gdx.app.error("Notif", "Error rendering notification", e);
        }
    }

    public void update(float delta) {
        elapsedTime += delta;
        if (elapsedTime >= displayTime) {
            expired = true;
        }
    }


    public boolean isExpired() {
        return expired;
    }
}
