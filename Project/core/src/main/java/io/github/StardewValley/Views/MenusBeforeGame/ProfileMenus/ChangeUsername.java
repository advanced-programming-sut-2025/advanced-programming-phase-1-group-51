package io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.ProfileControllers.ChangeUsernameController;
import io.github.StardewValley.Main;

public class ChangeUsername implements Screen {
    private Stage stage;
    private final Skin skin;
    private final TextButton submitButton;
    private final TextButton backButton;
    private final Label menuTitle;
    private final Label errorLabel;
    private final TextField newUsernameField;
    public Table table;
    private final ChangeUsernameController controller;

    public ChangeUsername(ChangeUsernameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.submitButton = new TextButton("Submit", skin);
        this.backButton = new TextButton("Back", skin);
        this.menuTitle = new Label("Change Username", skin);
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

        table.clear();
        table.setFillParent(true);
        table.top().padTop(100); // Centered with top padding

        // Title - large and centered
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(50);
        table.row();

        // Main form container
        Table formTable = new Table();
        formTable.defaults().pad(15); // Consistent padding

        // Email input row
        Table emailRow = new Table();
        emailRow.add(new Label("New Email:", skin)).width(250).right().padRight(20);
        emailRow.add(newUsernameField).width(500);
        formTable.add(emailRow).colspan(2).padBottom(30);
        formTable.row();

        // Buttons - centered with equal spacing
        Table buttonRow = new Table();
        buttonRow.defaults().width(200).pad(10);
        buttonRow.add(submitButton);
        buttonRow.add(backButton);
        formTable.add(buttonRow).colspan(2).padTop(20);
        formTable.row();

        // Error message - centered below buttons
        errorLabel.setFontScale(1.2f);
        formTable.add(errorLabel).colspan(2).padTop(30);

        // Add form to main table
        table.add(formTable).colspan(2);
        stage.addActor(table);

        // Focus on email field by default
        stage.setKeyboardFocus(newUsernameField);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleUsernameMenuButtons();
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
