package io.github.StardewValley.Views.MenusBeforeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.MenuControllers.PregameMenuController;
import io.github.StardewValley.Main;

public class PregameMenu implements Screen {
    private final Skin skin;
    private Stage stage;
    private final TextButton backButton;
    private final TextButton newGameButton;
    private final TextButton loadGameButton;
    private final TextField username1;
    private final TextField username2;
    private final TextField username3;
    private final Label errorLabel;
    private final SelectBox<String> mapSelection;
    private final Label menuTitle;
    public Table table;
    private final PregameMenuController controller;

    public PregameMenu(PregameMenuController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        this.backButton = new TextButton("Back", skin);
        this.menuTitle = new Label("Pregame Menu", skin);
        this.newGameButton = new TextButton("New Game",skin);
        this.loadGameButton = new TextButton("Load Game",skin);
        this.errorLabel = new Label("", skin);
        this.errorLabel.setColor(1, 0, 0, 1);
        this.username1 = new TextField("",skin);
        this.username2 = new TextField("",skin);
        this.username3 = new TextField("",skin);
        this.mapSelection = new SelectBox<>(skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                event.stop(); // Important - stops event propagation
                Main.playSound(Main.getButtonClickSound());
                controller.handleBack();
            }
        });
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                event.stop(); // Important - stops event propagation
                Main.playSound(Main.getButtonClickSound());
                controller.handleNewGame();
            }
        });
        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                event.stop(); // Important - stops event propagation
                Main.playSound(Main.getButtonClickSound());
                controller.handleLoadGame();
            }
        });

        // Clear and setup table
        table.clear();
        table.setFillParent(true);
        table.center();

        // Title with styling
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        table.add(menuTitle).colspan(2).padBottom(30);
        table.row();

        // Map selection row
        Table mapRow = new Table();
        mapRow.add(new Label("Select Map:", skin)).padRight(10);
        mapRow.add(mapSelection).width(200);
        table.add(mapRow).colspan(2).padBottom(30);
        table.row();

        // Player input section
        float labelWidth = 120;
        float fieldWidth = 300;

        // Player 1 (always visible)
        table.add(new Label("Player 1:", skin)).width(labelWidth).right().padRight(10);
        table.add(username1).width(fieldWidth).padBottom(15);
        table.row();

        // Player 2 (optional)
        table.add(new Label("Player 2:", skin)).width(labelWidth).right().padRight(10);
        table.add(username2).width(fieldWidth).padBottom(15);
        table.row();

        // Player 3 (optional)
        table.add(new Label("Player 3:", skin)).width(labelWidth).right().padRight(10);
        table.add(username3).width(fieldWidth).padBottom(30);
        table.row();

        // Error message
        table.add(errorLabel).colspan(2).padBottom(20);
        table.row();

        // Button row
        Table buttonTable = new Table();
        buttonTable.add(newGameButton).padRight(20);
        buttonTable.add(loadGameButton).padRight(20);
        buttonTable.add(backButton);
        table.add(buttonTable).colspan(2);

        stage.addActor(table);

        // Initialize map selection
        Array<String> maps = new Array<>();
        maps.add("Farm Valley");
        maps.add("Forest Retreat");
        maps.add("Mountain View");
        mapSelection.setItems(maps);
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

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getNewGameButton() {
        return newGameButton;
    }

    public TextButton getLoadGameButton() {
        return loadGameButton;
    }

    public TextField getUsername1() {
        return username1;
    }

    public TextField getUsername2() {
        return username2;
    }

    public TextField getUsername3() {
        return username3;
    }

    public SelectBox<String> getMapSelection() {
        return mapSelection;
    }

    public void showError(String message) {
        errorLabel.setText(message);
    }
}
