package io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.ProfileControllers.ProfileMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;

public class ProfileMenu implements Screen {
    private final Skin skin;
    private Stage stage;
    private final TextButton changeUsernameButton;
    private final TextButton changePasswordButton;
    private final TextButton changeNicknameButton;
    private final TextButton changeEmailButton;
    private final TextButton userInfoButton;
    private final TextButton backButton;
    private final Label menuTitle;
    public Table table;
    private final ProfileMenuController controller;

    public ProfileMenu(ProfileMenuController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        this.changeUsernameButton = new TextButton("Change Username", skin);
        this.changePasswordButton = new TextButton("Change Password", skin);
        this.changeNicknameButton = new TextButton("Change Nickname", skin);
        this.changeEmailButton = new TextButton("Change Email", skin);
        this.userInfoButton = new TextButton("User Info", skin);
        this.backButton = new TextButton("Back", skin);
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
        table.row().pad(2, 0, 2, 0);
        table.add(new Label("User : " + App.getCurrentUser().getUsername(), skin));
        table.row().pad(6, 0, 6, 0);
        table.add(new Label("Gender : " + App.getCurrentUser().getGender(), skin));
        table.row().pad(2, 0 , 2 , 0);
        table.add(changeUsernameButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(changePasswordButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(changeNicknameButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(changeEmailButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(userInfoButton);
        table.row().pad(6, 0 , 6 , 0);
        table.add(backButton);


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
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

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextButton getChangeNicknameButton() {
        return changeNicknameButton;
    }

    public TextButton getChangeEmailButton() {
        return changeEmailButton;
    }

    public TextButton getUserInfoButton() {
        return userInfoButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
