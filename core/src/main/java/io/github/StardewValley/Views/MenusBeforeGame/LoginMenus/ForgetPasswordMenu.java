package io.github.StardewValley.Views.MenusBeforeGame.LoginMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.LoginControllers.ForgetPasswordController;
import io.github.StardewValley.Main;

public class ForgetPasswordMenu implements Screen {

    private Stage stage;
    private final Skin skin;
    private final Label menuTitle;
    private final Label answerMessage;
    private final TextField username;
    private final TextField answer;
    private final Label newPasswordMessage;
    private final TextField newPassword;
    private final TextButton backButton;
    private final TextButton submitButton;
    private Label errorLabel;
    public Table table;
    private final ForgetPasswordController controller;

    public ForgetPasswordMenu(ForgetPasswordController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        this.menuTitle = new Label("Forget Password", skin);
        this.answerMessage = new Label("Answer:", skin);
        this.answer = new TextField("", skin);
        this.username = new TextField("", skin);
        this.newPasswordMessage = new Label("New Password:", skin);
        this.newPassword = new TextField("", skin);
        this.newPassword.setPasswordMode(true);
        this.newPassword.setPasswordCharacter('•');
        this.backButton = new TextButton("Back", skin);
        this.submitButton = new TextButton("Submit", skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
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
        table.add(answerMessage).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(answer).width(600).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(newPasswordMessage).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(newPassword).width(600).colspan(2);
        table.row().pad(10, 0, 10, 0);

        Table buttonTable = new Table();
        buttonTable.add(backButton).padRight(10);
        buttonTable.add(submitButton);
        table.add(buttonTable);
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).colspan(2);

        stage.addActor(table);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleForgotPasswordButtons();
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

    public TextField getAnswer() {
        return answer;
    }

    public TextField getNewPassword() {
        return newPassword;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getSubmitButton() {
        return submitButton;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public TextField getUsername() {
        return username;
    }
}

