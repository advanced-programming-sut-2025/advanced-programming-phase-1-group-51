package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.StardewValley.Models.Game;

import java.time.LocalTime;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture, clockTexture, arrow;
    private OrthographicCamera camera, hudCamera;
    private float backgroundWidth, backgroundHeight;
    private int tilesX, tilesY;
    private BitmapFont font;

    public WorldController(PlayerController playerController) {
        this.playerController = playerController;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        clockTexture = new Texture("Clock/clock-0.png");
        arrow = new Texture("Clock/arrow.png");

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.update();

        backgroundTexture = new Texture("background.png");
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        backgroundWidth = backgroundTexture.getWidth();
        backgroundHeight = backgroundTexture.getHeight();

        tilesX = (int)(camera.viewportWidth / backgroundWidth) + 2;
        tilesY = (int)(camera.viewportHeight / backgroundHeight) + 2;

        font = new BitmapFont();
        font.getData().setScale(2.5f);
        font.setColor(Color.BLACK);
    }

    public void update() {
        camera.position.set(
            playerController.getPlayer().getPosition().x,
            playerController.getPlayer().getPosition().y,
            0
        );
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        float cameraLeft = camera.position.x - camera.viewportWidth / 2;
        float cameraBottom = camera.position.y - camera.viewportHeight / 2;

        int startX = (int)(cameraLeft / backgroundWidth);
        int startY = (int)(cameraBottom / backgroundHeight);

        float offsetX = -cameraLeft % backgroundWidth;
        float offsetY = -cameraBottom % backgroundHeight;

        for (int x = startX - 1; x <= startX + tilesX; x++) {
            for (int y = startY - 1; y <= startY + tilesY; y++) {
                batch.draw(backgroundTexture,
                    x * backgroundWidth + offsetX,
                    y * backgroundHeight + offsetY,
                    backgroundWidth,
                    backgroundHeight);
            }
        }

        playerController.getPlayer().render(batch);
        batch.end();

        // HUD rendering
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        float clockScale = 3.5f;
        float clockX = hudCamera.viewportWidth - clockTexture.getWidth() * clockScale - 20;
        float clockY = hudCamera.viewportHeight - clockTexture.getHeight() * clockScale - 20;

        batch.draw(clockTexture, clockX, clockY,
            clockTexture.getWidth() * clockScale,
            clockTexture.getHeight() * clockScale);

        LocalTime time = Game.getTime();
        int hour = time.getHour();
        int minute = time.getMinute();

        float totalMinutes = (hour - 9) * 60 + minute;
        float normalizedTime = totalMinutes / (13f * 60f);
        float rotation = -normalizedTime * 180f + 180f;

        float arrowWidth = arrow.getWidth() * clockScale;
        float arrowHeight = arrow.getHeight() * clockScale;
        float arrowX = clockX + (clockTexture.getWidth() * clockScale / 2f);
        float arrowY = clockY + (clockTexture.getHeight() * clockScale / 2f);

        batch.draw(
            arrow,
            arrowX - 48, arrowY + 30,
            0f, 0f,
            arrowWidth, arrowHeight,
            0.8f, 0.8f,
            rotation,
            0, 0,
            arrow.getWidth(), arrow.getHeight(),
            false, false
        );

        // Draw digital clock
        String hourStr = String.format("%02d:%02d", hour, minute);
        float textX = clockX + 110;
        float textY = clockY + 110;
        font.draw(batch, hourStr, textX, textY);

        // Draw weekday and day number
        int day = Game.getCurrentDay();
        String dayOfWeek = getDayOfWeek(day);
        String dayStr = String.format("%s.%02d", dayOfWeek, day);
        font.getData().setScale(1.5f);
        font.draw(batch, dayStr, textX - 12, textY + 75);
        font.getData().setScale(2.5f);

        batch.end();
    }

    public void dispose() {
        backgroundTexture.dispose();
        clockTexture.dispose();
        arrow.dispose();
        font.dispose();
    }

    private String getDayOfWeek(int day) {
        switch (day % 7) {
            case 1:
                return "Monday";
                case 2:
                    return "Tuesday";
                    case 3:
                        return "Wednesday";
                        case 4:
                            return "Thursday";
                            case 5:
                                return "Friday";
                                case 6:
                                    return "Saturday";
                                    case 0:
                                        return "Sunday";
                                        default:
                                            return "???";
        }
    }
}
