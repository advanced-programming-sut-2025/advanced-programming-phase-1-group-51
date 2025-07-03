package io.github.StardewValley.Views.GameMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Models.Game;

public class GameMenu implements Screen, InputProcessor {
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private GameController controller;

    public GameMenu(GameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.batch = new SpriteBatch();
        controller.setView(this);
    }



    @Override
    public void render(float delta) {

        controller.updateGame(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        controller.getWorldController().render(batch);

        batch.begin();
          controller.getPlayerController().getPlayer().render(batch);
//        controller.getWeaponController().render(batch);
//        controller.getEnemyController().render(batch);
        batch.end();

        // Render UI on top
        batch.begin();
//        controller.renderUi(batch);
        batch.end();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            controller.goToCheatMenu();
            return true;
        }
        if (keycode == Input.Keys.H) {
            controller.ChangeTime('H');
        }
        if (keycode == Input.Keys.M) {
            controller.ChangeTime('M');
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


}
