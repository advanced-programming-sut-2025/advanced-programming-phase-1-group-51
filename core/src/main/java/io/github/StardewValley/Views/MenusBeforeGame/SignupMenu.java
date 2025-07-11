package io.github.StardewValley.Views.MenusBeforeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.SignupMenuController;
import io.github.StardewValley.Main;

public class SignupMenu implements Screen {
    private final Skin skin;
    private Stage stage;
    private final TextButton goToLoginButton;
    private final TextButton backButton;
    private final TextButton exitButton;
    private final Label menuTitle;
    private Label errorLabel;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField confirmPasswordField;
    private final TextField nicknameField;
    private final TextField emailField;
    private final TextField securityQuestionField;
    private final TextField securityAnswerField;
    private final TextButton generatePasswordButton;
    private final Label generatedPasswordLabel;
    public Table table;
    private final SignupMenuController controller;

    public SignupMenu(SignupMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.goToLoginButton = new TextButton("Login Menu", skin);
        this.backButton = new TextButton("Back", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.menuTitle = new Label("Signup Menu", skin);
        this.generatePasswordButton = new TextButton("Generate Password", skin);
        this.generatedPasswordLabel = new Label("", skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.usernameField = new TextField("", skin);
        this.passwordField = new TextField("", skin);
        this.passwordField.setPasswordMode(true);
        this.passwordField.setPasswordCharacter('•');
        this.confirmPasswordField = new TextField("", skin);
        this.confirmPasswordField.setPasswordMode(true);
        this.confirmPasswordField.setPasswordCharacter('•');
        this.nicknameField = new TextField("", skin);
        this.emailField = new TextField("", skin);
        this.securityQuestionField = new TextField("", skin);
        this.securityAnswerField = new TextField("", skin);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.clear();
        table.setFillParent(true);
        table.top().padTop(25); // Generous top padding

        // Title - large and centered
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(10);
        table.row();

        // Main form container with generous spacing
        Table formTable = new Table();
        formTable.pad(20); // Internal padding

        // Column configuration
        float labelWidth = 250; // Wider labels for readability
        float fieldWidth = 400; // Wider input fields
        float rowHeight = 70; // Taller rows for better spacing

        // Left Column
        Table leftColumn = new Table();

        // Username
        leftColumn.add(new Label("Username:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        leftColumn.add(usernameField).width(fieldWidth).height(100).padBottom(15);
        leftColumn.row();

        // Password
        leftColumn.add(new Label("Password:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        leftColumn.add(passwordField).width(fieldWidth).height(100).padBottom(15);
        leftColumn.row();

        // Generated Password
        leftColumn.add(new Label("Generated Password:", skin)).width(labelWidth).right().padRight(50).height(rowHeight);
        leftColumn.add(generatedPasswordLabel).width(fieldWidth).left().height(100).padBottom(15);
        leftColumn.row();

        // Generate Password Button (larger)
        leftColumn.add(generatePasswordButton).colspan(2).width(fieldWidth+labelWidth).height(100).padBottom(25);
        leftColumn.row();

        // Confirm Password
        leftColumn.add(new Label("Confirm Password:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        leftColumn.add(confirmPasswordField).width(fieldWidth).height(100).padBottom(15);
        leftColumn.row();

        // Right Column
        Table rightColumn = new Table();

        // Email
        rightColumn.add(new Label("Email:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        rightColumn.add(emailField).width(fieldWidth).height(100).padBottom(15);
        rightColumn.row();

        // Nickname
        rightColumn.add(new Label("Nickname:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        rightColumn.add(nicknameField).width(fieldWidth).height(100).padBottom(15);
        rightColumn.row();

        // Gender (using SelectBox with larger font)
        SelectBox<String> genderSelect = new SelectBox<>(skin);
        genderSelect.getStyle().font.getData().setScale(1.2f); // Bigger font
        genderSelect.setItems("Male", "Female", "Other");
        rightColumn.add(new Label("Gender:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        rightColumn.add(genderSelect).width(fieldWidth).height(100).padBottom(15);
        rightColumn.row();

        // Security Question
        rightColumn.add(new Label("Security Question:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        rightColumn.add(securityQuestionField).width(fieldWidth).height(100).padBottom(15);
        rightColumn.row();

        // Security Answer
        rightColumn.add(new Label("Security Answer:", skin)).width(labelWidth).right().padRight(20).height(rowHeight);
        rightColumn.add(securityAnswerField).width(fieldWidth).height(100).padBottom(15);
        rightColumn.row();

        // Add columns to form with spacing
        formTable.add(leftColumn).padRight(50);
        formTable.add(rightColumn);

        // Add form to main table
        table.add(formTable).colspan(2).padBottom(2);
        table.row();

        // Error message (larger and more prominent)
        errorLabel.setFontScale(1.2f);
        table.add(errorLabel).colspan(2).padBottom(2).height(60);
        table.row();

        // Navigation buttons (larger and evenly spaced)
        Table buttonTable = new Table();
        buttonTable.defaults().height(120).pad(10);
        buttonTable.add(goToLoginButton);
        buttonTable.add(backButton);
        buttonTable.add(exitButton);
        table.add(buttonTable).colspan(2).padTop(10);

        stage.addActor(table);

        // Connect gender SelectBox to genderField
        //genderField.setText(genderSelect.getSelected());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleSignUpMenuButtons();
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

    public void setGeneratedPassword(String password) {
        generatedPasswordLabel.setText(password);
        passwordField.setText(password);
        confirmPasswordField.setText(password);
    }

    // Add this getter
    public TextButton getGeneratePasswordButton() {
        return generatePasswordButton;
    }

    public TextButton getGoToLoginButton() {
        return goToLoginButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }

    public TextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public TextField getNicknameField() {
        return nicknameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextField getSecurityAnswerField() {
        return securityAnswerField;
    }
}
