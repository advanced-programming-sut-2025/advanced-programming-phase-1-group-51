package io.github.StardewValley.Views.MenusBeforeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.MainMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;

public class MainMenu implements Screen {
    private final Skin skin;
    private Stage stage;
    private final TextButton profileButton;
    private final TextButton pregameButton;
    private final TextButton loginButton;
    private final TextButton logoutButton;
    private final Label menuTitle;
    public Table table;
    private final MainMenuController controller;

    public MainMenu(MainMenuController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        this.profileButton = new TextButton("Profile Menu", skin);
        this.pregameButton = new TextButton("Pregame Menu", skin);
        this.loginButton = new TextButton("Login Menu", skin);
        this.logoutButton = new TextButton("Logout", skin);
        this.menuTitle = new Label("Main Menu", skin);
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

        if(App.getCurrentUser() != null) {
            table.row().pad(2, 0, 2, 0);
            table.add(new Label("User : " + App.getCurrentUser().getUsername(), skin));
        }
        else{
            table.row().pad(2, 0 , 2 , 0);
            table.add(new Label("no user logged in", skin));
        }

        table.row().pad(6, 0 , 6 , 0);
        table.add(profileButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(pregameButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(loginButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(logoutButton);


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


    public TextButton getProfileButton() {
        return profileButton;
    }

    public TextButton getPregameButton() {
        return pregameButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }
}
