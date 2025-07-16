package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

public class GameAssetsManager {
    private static GameAssetsManager gameAssetsManager;
    private final ArrayList<PlayerAssets> playerAssets;
    private MapInitialAssets foragingAssetsManager;
    private NotificationAssets notificationAssets;
    private final Skin skin;

    private GameAssetsManager() {
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        playerAssets = new ArrayList<>();

        foragingAssetsManager = MapInitialAssets.getInstance();
        notificationAssets = new NotificationAssets(); // Add this
        notificationAssets.load(); // Load notification assets
    }

    // Add this method
    public NotificationAssets getNotificationAssets() {
        return notificationAssets;
    }

    public static GameAssetsManager getInstance() {
        if (gameAssetsManager == null) {
            gameAssetsManager = new GameAssetsManager();
        }
        return gameAssetsManager;
    }

    public MapInitialAssets getForagingAssetsManager() {
        return foragingAssetsManager;
    }

    public PlayerAssets getPlayerAssets() {
        PlayerAssets assets = new PlayerAssets();
        playerAssets.add(assets);
        return assets;
    }




    public Skin getSkin() {
        return skin;
    }

    public void dispose() {
        skin.dispose();
        for (PlayerAssets assets : playerAssets) {
            assets.dispose();
        }

        foragingAssetsManager.dispose();

    }
}
