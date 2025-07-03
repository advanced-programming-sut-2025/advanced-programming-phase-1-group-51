package io.github.StardewValley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.StardewValley.Controllers.MenuControllers.FirstMenuController;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Views.MenusBeforeGame.FirstMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private static boolean isGamePaused = false;
    private static Music currentMusic;
    private static float musicVolume = 0.5f;
    private static float sfxVolume = 0.8f;
    private static boolean sfxEnabled = true;
    private static Pixmap cursorPixmap;
    private static Cursor customCursor;
    private static Sound buttonClickSound;
    private static Sound footstep;

    @Override
    public void create() {
        main = this;
        playMusic("Glacier.mp3");

        Pixmap originalPixmap = new Pixmap(Gdx.files.internal("ui/dadasham.png"));

        int newWidth = 64;
        int newHeight = 64;
        cursorPixmap = new Pixmap(newWidth, newHeight, originalPixmap.getFormat());

        // Draw the original pixmap scaled to the new size
        cursorPixmap.drawPixmap(originalPixmap,
            0, 0, originalPixmap.getWidth(), originalPixmap.getHeight(), // Source rectangle
            0, 0, newWidth, newHeight); // Destination rectangle

        // Dispose the original pixmap
        originalPixmap.dispose();

        // Create cursor (parameters are: pixmap, hotspot x, hotspot y)
        customCursor = Gdx.graphics.newCursor(cursorPixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        batch = new SpriteBatch();
        getMain().setScreen(new FirstMenu(new FirstMenuController(),
            GameAssetsManager.getInstance().getSkin()));

        try {
            buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("sfx/button_click.wav"));
            footstep = Gdx.audio.newSound(Gdx.files.internal("sfx/footstep.wav"));
        }
        catch (Exception e) {
            Gdx.app.error("SFX", "Error loading sound effects", e);
        }

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        // Clean up when game closes
        if (cursorPixmap != null) cursorPixmap.dispose();
        if (customCursor != null) customCursor.dispose();
        super.dispose();
    }

    public static void playMusic(String musicFile) {
        try {
            if (currentMusic != null) {
                currentMusic.stop();
                currentMusic.dispose();
            }

            FileHandle musicFileHandle = Gdx.files.internal("music/" + musicFile);
            if (!musicFileHandle.exists()) {
                Gdx.app.error("Music", "File not found: " + musicFile);
                return;
            }

            currentMusic = Gdx.audio.newMusic(musicFileHandle);
            currentMusic.setLooping(true);
            currentMusic.setVolume(musicVolume);
            if (!isGamePaused) {  // Only play if not paused
                currentMusic.play();
            }
        } catch (Exception e) {
            Gdx.app.error("Music", "Error loading music: " + musicFile, e);
        }
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
    }

    public static void playSound(Sound sound) {
        if (sfxEnabled && sound != null) {
            sound.play(sfxVolume);
        }
    }

    public static boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public static void setSfxEnabled(boolean enabled) {
        sfxEnabled = enabled;
    }

    public static float getSfxVolume() {
        return sfxVolume;
    }

    public static void setSfxVolume(float volume) {
        sfxVolume = volume;
    }

    public static Sound getButtonClickSound() {
        return buttonClickSound;
    }

    public static Sound getFootstep() {
        return footstep;
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static boolean isGamePaused() {
        return isGamePaused;
    }

    public static Music getCurrentMusic() {
        return currentMusic;
    }

    public static float getMusicVolume() {
        return musicVolume;
    }
}
