package io.github.StardewValley.Views.MenusBeforeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.FirstMenuController;
import io.github.StardewValley.Main;

public class FirstMenu implements Screen {

    private final Skin skin;
    private Stage stage;
    private final TextButton goToSignupButton;
    private final TextButton goToLoginButton;
    private final TextButton goToMainButton;
    private final Label menuTitle;
    public Table table;
    private final FirstMenuController controller;

    public FirstMenu(FirstMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.menuTitle = new Label("Starting Menu", skin);
        this.goToSignupButton = new TextButton("Signup Menu", skin);
        this.goToLoginButton = new TextButton("Login Menu", skin);
        this.goToMainButton = new TextButton("Main Menu", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(30);
        table.row();
        table.row().pad(10, 0, 10, 0);
        table.add(goToSignupButton);
        table.row().pad(10, 0, 10, 0);
        table.add(goToLoginButton);
        table.row().pad(10, 0, 10, 0);
        table.add(goToMainButton);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleFirstMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {

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

    public TextButton getGoToLoginButton() {
        return goToLoginButton;
    }

    public TextButton getGoToSignupButton() {
        return goToSignupButton;
    }

    public TextButton getGoToMainButton() {
        return goToMainButton;
    }
}
