package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameAssetsManager {
    private static GameAssetsManager gameAssetsManager;
    private final ArrayList<PlayerAssets> playerAssets;
    private final Skin skin;

    private GameAssetsManager() {
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        playerAssets = new ArrayList<>();
    }

    public static GameAssetsManager getInstance() {
        if (gameAssetsManager == null) {
            gameAssetsManager = new GameAssetsManager();
        }
        return gameAssetsManager;
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
    }
}
