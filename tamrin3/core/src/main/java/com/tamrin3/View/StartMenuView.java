package com.tamrin3.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tamrin3.Controller.MainMenuController;
import com.tamrin3.Main;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton LoginButton;
    private final TextButton RegisterButton;
    private final TextButton PlayAsGuestButton;
    private final Label LoginTitle;
    private final Label RegisterTitle;
    public Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.RegisterButton = new TextButton("Register", skin);
        this.LoginButton = new TextButton("Login", skin);
        this.PlayAsGuestButton = new TextButton("Play as Guest", skin);
        this.LoginTitle = new Label("If you have an account:", skin);
        this.RegisterTitle = new Label("If its first that you entered:", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(LoginTitle);
        table.row();
        table.add(LoginButton);
        table.row();

        table.add(RegisterTitle);
        table.row();
        table.add(RegisterButton);
        table.row();
        table.add(PlayAsGuestButton);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
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

    public TextButton getLoginButton() {
        return LoginButton;
    }
    public TextButton getRegisterButton() {return RegisterButton;}
    public TextButton getPlayAsGuestButton() {return PlayAsGuestButton;}

}
