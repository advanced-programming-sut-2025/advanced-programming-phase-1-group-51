package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Buildings.*;
import io.github.StardewValley.Models.Enums.Others.Weather;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.ObjectsOnMap.ForagingTreeBlock;
import io.github.StardewValley.Models.ObjectsOnMap.Wall;
import io.github.StardewValley.Views.GameMenus.EnergyHUD;

import java.time.LocalTime;
import java.util.ArrayList;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture, clockTexture, arrow;
    private float backgroundWidth, backgroundHeight;
    private BitmapFont font;
    private EnergyHUD energyHUD;
    private Weather weatherToday = App.getCurrentGame().getWeatherToday();
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    private Viewport hudViewport;
    private PlayerHouse playerHouse;
    private GreenHouse greenhouse;
    private FishShop fishShop;
    private PierreGeneralStore pierreGeneralStore;
    private CarpenterShop carpenterShop;
    private JojaMart jojaMart;
    private StarDropSaloon starDropSaloon;
    private MarnieRanch marnieRanch;
    private BlackSmith blackSmith;
    private ArrayList<Wall> allWalls;
    private ForagingController foragingController;
    private Game game;

    public WorldController(PlayerController playerController) {
        this.playerController = playerController;
        this.game = App.getCurrentGame();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundTexture = new Texture("Flooring/map (11).png");

        backgroundWidth = backgroundTexture.getWidth();
        backgroundHeight = backgroundTexture.getHeight();

        // HUD Camera and Viewport setup
        hudCamera = new OrthographicCamera();
        hudViewport = new ScreenViewport(hudCamera);  // Create the viewport
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.update();

        // Initialize EnergyHUD with the viewport
        this.energyHUD = new EnergyHUD(playerController.getPlayer(),
            GameAssetsManager.getInstance().getSkin(),
            hudViewport);
        updateWeatherEffects();

        clockTexture = new Texture("Clock/clock-0.png");
        arrow = new Texture("Clock/arrow.png");
        this.foragingController = new ForagingController(playerController.getGameController());

        font = new BitmapFont();
        font.getData().setScale(2.5f);
        font.setColor(Color.BLACK);

        playerHouse = new PlayerHouse(960, 1920, 960, 600);
        greenhouse = new GreenHouse(240,1920,480, 600);
        fishShop = new FishShop(3120,3720,720, 720);
        pierreGeneralStore = new PierreGeneralStore(4560,2520,1080, 960);
        carpenterShop = new CarpenterShop(6600,3600,1080, 1080);
        jojaMart = new JojaMart(3600,7305,1080, 960);
        starDropSaloon = new StarDropSaloon(5280,7305,1200, 720);
        marnieRanch = new MarnieRanch(6600,5760,1080, 1080);
        blackSmith = new BlackSmith(3120,5265,720, 720);

        allWalls = new ArrayList<>();
        allWalls.addAll(fishShop.getWalls());
        allWalls.addAll(pierreGeneralStore.getWalls());
        allWalls.addAll(carpenterShop.getWalls());
        allWalls.addAll(greenhouse.getWalls());
        allWalls.addAll(jojaMart.getWalls());
        allWalls.addAll(starDropSaloon.getWalls());
        allWalls.addAll(marnieRanch.getWalls());
        allWalls.addAll(playerHouse.getWalls());
        allWalls.addAll(blackSmith.getWalls());

    }

    public void update() {
        float camX = playerController.getPlayer().getPosition().x;
        float camY = playerController.getPlayer().getPosition().y;

        float halfViewportWidth = camera.viewportWidth / 2f;
        float halfViewportHeight = camera.viewportHeight / 2f;

        camX = Math.max(halfViewportWidth, Math.min(camX, backgroundWidth - halfViewportWidth));
        camY = Math.max(halfViewportHeight, Math.min(camY, backgroundHeight - halfViewportHeight));

        camera.position.set(camX, camY, 0);
        camera.update();


    }


    private void updateWeatherEffects() {
        float energyModifier = 1.0f; // Default

        switch(weatherToday) {
            case SUNNY:
                energyModifier = 20f; // Normal drain
                break;
            case RAIN:
                energyModifier = 1.2f; // 20% faster drain
                break;
            case STORM:
                energyModifier = 1.5f; // 50% faster drain
                break;
            case SNOW:
                energyModifier = 1.3f; // 30% faster drain
                break;
        }

        playerController.getPlayer().setEnergyDrainRate(energyModifier);
    }

    public void setWeather(Weather weather) {
        this.weatherToday = weather;
        updateWeatherEffects();
    }

    public void render(SpriteBatch batch) {
        // Render world
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(backgroundTexture, 0, 0);

        // Render house walls
        for (Wall wall : allWalls) {
            wall.render(batch);
        }
        foragingController.render(batch);

        // 3. Draw trees (which might be taller than player)
        for (ForagingTreeBlock tree : foragingController.getForagingTrees()) {
            tree.render(batch);
        }

        playerController.getPlayer().render(batch);
        batch.end();


        // Render HUD (clock)
        renderHUD(batch);

        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        playerController.getPlayer().renderNotifications(batch);
        batch.end();
    }



    private void renderHUD(SpriteBatch batch) {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        float clockScale = 3.5f;
        float clockX = hudCamera.viewportWidth - clockTexture.getWidth() * clockScale - 20;
        float clockY = hudCamera.viewportHeight - clockTexture.getHeight() * clockScale - 20;

        batch.draw(clockTexture, clockX, clockY,
            clockTexture.getWidth() * clockScale,
            clockTexture.getHeight() * clockScale);

        LocalTime time = game.getTime();
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
        int day = game.getCurrentDay();
        String dayOfWeek = getDayOfWeek(day);
        String dayStr = String.format("%s.%02d", dayOfWeek, day);
        font.getData().setScale(1.5f);
        font.draw(batch, dayStr, textX - 12, textY + 75);
        font.getData().setScale(2.5f);


        batch.end();
        energyHUD.update(Gdx.graphics.getDeltaTime());
        energyHUD.render();
    }

    public void dispose() {
        backgroundTexture.dispose();
        clockTexture.dispose();
        arrow.dispose();
        font.dispose();
        if (energyHUD != null) {
            energyHUD.dispose();
        }
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

    public ArrayList<Wall> getAllWalls() {
        return allWalls;
    }

    public ForagingController getForagingController() {
        return foragingController;
    }


    public GreenHouse getGreenhouse() {
        return greenhouse;
    }
}
