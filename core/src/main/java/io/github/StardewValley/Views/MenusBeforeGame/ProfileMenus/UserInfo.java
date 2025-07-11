package io.github.StardewValley.Views.MenusBeforeGame.ProfileMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.ProfileControllers.ProfileMenuController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;

public class UserInfo implements Screen {
    private Stage stage;
    private final Skin skin;
    private final Label menuTitle;
    private final TextButton backButton;
    public Table table;

    public UserInfo(Skin skin) {
        this.skin = skin;
        this.menuTitle = new Label("User Information", skin);
        this.backButton = new TextButton("Back",skin);
        this.table = new Table();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Clear and setup table
        table.clear();
        table.setFillParent(true);
        table.center();

        // Title with styling
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(40);
        table.row();

        // User info fields in a clean two-column layout
        float labelWidth = 250;  // Fixed width for labels
        float valueWidth = 300;  // Fixed width for values

        // Username
        table.add(new Label("Username:", skin)).width(labelWidth).right().padRight(20);
        table.add(new Label(App.getCurrentUser().getUsername(), skin))
            .width(valueWidth).left().padBottom(20);
        table.row();

        // Nickname
        table.add(new Label("Nickname:", skin)).width(labelWidth).right().padRight(20);
        table.add(new Label(App.getCurrentUser().getNickName(), skin))
            .width(valueWidth).left().padBottom(20);
        table.row();

        // Highest Money
        table.add(new Label("Highest Money Earned:", skin)).width(labelWidth).right().padRight(100);
        table.add(new Label(String.valueOf(App.getCurrentUser().getMaxMoneyEarnedInGame()), skin))
            .width(valueWidth).left().padBottom(20);
        table.row();

        // Games Played
        table.add(new Label("Games Played:", skin)).width(labelWidth).right().padRight(100);
        table.add(new Label(String.valueOf(App.getCurrentUser().getNumberOfGamesPlayed()), skin))
            .width(valueWidth).left().padBottom(40);
        table.row();

        // Back button centered
        table.add(backButton).colspan(2).padTop(20).width(200);

        stage.addActor(table);

        // Button listener
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenu(
                    new ProfileMenuController(),
                    GameAssetsManager.getInstance().getSkin()
                ));
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
}
