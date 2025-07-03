package io.github.StardewValley.Views.MenusBeforeGame.LoginMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.LoginControllers.LoginMenuController;
import io.github.StardewValley.Main;
public class LoginMenu implements Screen {
    private Stage stage;
    private final Skin skin;
    private final TextButton goToMainButton;
    private final TextButton forgotPasswordButton;
    private final TextButton stayLoggedInButton;
    private final TextButton backButton;
    private final Label menuTitle;
    private final Label errorLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    public Table table;
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.goToMainButton = new TextButton("Main Menu", skin);
        this.forgotPasswordButton = new TextButton("Forgot password", skin);
        this.stayLoggedInButton = new TextButton("Stay Logged In", skin);
        this.backButton = new TextButton("Back", skin);
        this.menuTitle = new Label("Login Menu", skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.usernameField = new TextField("", skin);
        this.passwordField = new TextField("", skin);
        this.passwordField.setPasswordMode(true);
        this.passwordField.setPasswordCharacter('•');
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Clear the table first (good practice)
        table.clear();

        // Set table to fill stage and center everything
        table.setFillParent(true);
        table.center();

        // Add title with padding
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(30);
        table.row();

        // Username row
        table.add(new Label("Username:", skin)).padRight(10).right();
        table.add(usernameField).width(400).padBottom(15);
        table.row();

        // Password row
        table.add(new Label("Password:", skin)).padRight(10).right();
        table.add(passwordField).width(400).padBottom(30);
        table.row();

        // Error label (centered across both columns)
        table.add(errorLabel).colspan(2).padBottom(20);
        table.row();

        // First button row (Main and Forgot Password)
        Table buttonTable = new Table();
        buttonTable.add(goToMainButton).padRight(20);
        buttonTable.add(forgotPasswordButton).padRight(20);
        table.add(buttonTable).colspan(2).padBottom(15);
        table.row();

        // Second button row (Stay Logged In and Back)
        Table bottomButtonTable = new Table();
        bottomButtonTable.add(stayLoggedInButton).padRight(20);
        bottomButtonTable.add(backButton);
        table.add(bottomButtonTable).colspan(2);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleLoginMenuButtons();
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

    public TextButton getGoToMainButton() {
        return goToMainButton;
    }

    public TextButton getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getStayLoggedInButton() {
        return stayLoggedInButton;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }
}
