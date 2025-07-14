package io.github.StardewValley.Views.GameMenus.InventoryMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Types.BackpackType;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Models.Slot;

public abstract class BaseMenu implements Screen {
    protected final Skin skin;
    protected Stage stage;
    protected final TextButton backButton;
    protected final TextButton skillsButton;
    protected final TextButton backpackButton;
    protected final TextButton mapButton;
    protected final TextButton socialButton;
    protected final TextButton settingButton;
    protected final TextButton missionsButton;
    protected Label menuTitle;
    protected Table mainTable;
    protected Table contentTable;
    protected GameController gameController;
    protected Player player;
    protected Label errorLabel;

    public BaseMenu(GameController gameController, Skin skin, String title) {
        this.skin = skin;
        this.player = App.getCurrentGame().getCurrentPlayer();
        this.gameController = gameController;
        this.backButton = new TextButton("Back", skin);
        this.backpackButton = new TextButton("Backpack", skin);
        this.skillsButton = new TextButton("Skills", skin);
        this.mapButton = new TextButton("Map", skin);
        this.socialButton = new TextButton("Social", skin);
        this.settingButton = new TextButton("Setting", skin);
        this.missionsButton = new TextButton("Missions", skin);
        this.menuTitle = new Label(title, skin);
        this.mainTable = new Table();
        this.contentTable = new Table();
        setupListeners();
    }

    protected void setupListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                resumeGame();
            }
        });
        backpackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToBackpack();
            }
        });
        skillsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToSkills();
            }
        });
        mapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToMap();
            }
        });
        socialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToSocials();
            }
        });
        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToSetting();
            }
        });
        missionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.playSound(Main.getButtonClickSound());
                navigateToMissions();
            }
        });
    }

    protected void navigateToBackpack() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new BackpackMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void navigateToSkills() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SkillsMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void navigateToMap() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MapMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void navigateToSetting() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SettingMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void navigateToSocials() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new SocialsMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void navigateToMissions() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MissionsMenu(
            gameController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    protected void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }

    protected void setupMainTable() {
        mainTable.clear();
        mainTable.setFillParent(true);
        mainTable.center();

        // Title
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        menuTitle.setFontScale(0.7f);
        mainTable.add(menuTitle).colspan(2).padBottom(20).center();
        mainTable.row();

        // Navigation buttons
        Table navTable = new Table();
        navTable.add(backpackButton).padRight(10);
        navTable.add(skillsButton).padRight(10);
        navTable.add(mapButton).padRight(10);
        navTable.add(socialButton).padRight(10);
        navTable.add(settingButton).padRight(10);
        navTable.add(missionsButton).padRight(10);
        mainTable.add(navTable).colspan(2).padBottom(40);
        mainTable.row();

        // Content
        mainTable.add(contentTable).colspan(2);
        mainTable.row();

        // Back button
        mainTable.add(backButton).colspan(2).padTop(40).width(200);
    }

    protected Texture createPlaceholderTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAGENTA);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        setupMainTable();
        stage.addActor(mainTable);
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
        stage.getViewport().update(width, height, true);
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
        if (stage != null) {
            stage.dispose();
        }
    }
}
