package io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.ProfileControllers.ChangeNicknameController;
import io.github.StardewValley.Main;

public class ChangeNickname implements Screen {
    private Stage stage;
    private final Skin skin;
    private final TextButton submitButton;
    private final TextButton backButton;
    private final Label menuTitle;
    private final Label errorLabel;
    private final TextField newUsernameField;
    public Table table;
    private final ChangeNicknameController controller;

    public ChangeNickname(ChangeNicknameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.submitButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);
        this.menuTitle = new Label("Change NickName", skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.newUsernameField = new TextField("", skin);
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
        table.add(new Label("New Nickname :",skin)).padRight(5);
        table.add(newUsernameField).width(600).colspan(2); // Span across 2 columns
        table.row().pad(10, 0, 10, 0);

        table.add(submitButton).padRight(10);
        table.add(backButton);
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).colspan(2);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleNicknameMenuButtons();
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

    public TextButton getSubmitButton() {
        return submitButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Label getMenuTitle() {
        return menuTitle;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public TextField getNewUsernameField() {
        return newUsernameField;
    }


    public void showError(String message) {
        errorLabel.setText(message);
    }
}
