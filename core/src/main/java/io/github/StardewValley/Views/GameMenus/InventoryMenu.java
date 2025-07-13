package io.github.StardewValley.Views.GameMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.StardewValley.Controllers.GameControllers.InventoryController;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Types.BackpackType;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Models.Slot;

public class InventoryMenu extends BaseMenu {
    private final TextButton backButton;
    private final TextButton skillsButton;
    private final Label menuTitle;
    public Table mainTable;
    private final InventoryController controller;
    private Table slotsTable;
    private boolean showingSkills = false;

    public InventoryMenu(InventoryController controller, Skin skin) {
        super(skin);
        this.controller = controller;
        this.backButton = new TextButton("Back", skin);
        this.skillsButton = new TextButton("Skills", skin);
        this.menuTitle = new Label("Inventory", skin);
        this.mainTable = new Table();
        this.slotsTable = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        super.show();
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        mainTable.clear();
        mainTable.setFillParent(true);
        mainTable.center();

        // Title and navigation buttons
        menuTitle.setStyle(skin.get("title", Label.LabelStyle.class));
        mainTable.add(menuTitle).colspan(2).padBottom(30);
        mainTable.row();

        // Navigation buttons
        Table navTable = new Table();
        navTable.add(new TextButton("Backpack", skin)).padRight(10);
        navTable.add(skillsButton).padRight(10);
        mainTable.add(navTable).colspan(2).padBottom(20);
        mainTable.row();

        // Display content based on current view
        if (showingSkills) {
            showSkills();
        } else {
            showInventory();
        }

        mainTable.add(backButton).colspan(2).padTop(30).width(200);

        stage.addActor(mainTable);
    }

    private void showInventory() {
        menuTitle.setText("Backpack");
        slotsTable.clear();

        Player player = controller.getPlayerController().getPlayer();
        if (player == null) {
            Gdx.app.error("Inventory", "Player is null!");
            return;
        }

        BackPack backpack = player.getInventory();
        if (backpack == null) {
            Gdx.app.log("Inventory", "Initializing new backpack");
            backpack = new BackPack(BackpackType.DEFAULT);
            player.initializeInventory();
        }

        // Debug logging
        Gdx.app.log("Inventory", "Showing inventory with " + backpack.getSLots().size() + " slots");

        int itemsPerRow = 4;
        int itemCount = 0;

        slotsTable.align(Align.center);

        for (Slot slot : backpack.getSLots()) {
            if (slot != null && slot.getItem() != null) {
                Texture itemTexture = GameAssetsManager.getInstance()
                    .getForagingAssetsManager()
                    .getItemTexture(slot.getItem());

                if (itemTexture == null) {
                    Gdx.app.error("Inventory", "Missing texture for: " + slot.getItem().getName());
                    itemTexture = createPlaceholderTexture();
                }
                Table itemCell = new Table();
                 itemCell.setBackground(skin.getDrawable("window"));

                // Item image (placeholder - you'll replace with actual texture)
                Image itemImage = new Image(new TextureRegionDrawable(
                    GameAssetsManager.getInstance().getForagingAssetsManager()
                        .getItemTexture(slot.getItem())
                ));
                itemCell.add(itemImage).size(64).pad(5);

                // Item count label
                Label countLabel = new Label(String.valueOf(slot.getCount()), skin);
                itemCell.row();
                itemCell.add(countLabel).padBottom(5);

                slotsTable.add(itemCell).pad(5);

                itemCount++;
                if (itemCount % itemsPerRow == 0) {
                    slotsTable.row();
                }
            }
        }

        // Remove previous slots table if exists
        if (mainTable.getCells().size > 3) {
            mainTable.getCells().removeIndex(2);
        }

        mainTable.add(slotsTable).colspan(2).padBottom(20);
    }

    private Texture createPlaceholderTexture() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAGENTA);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private void showSkills() {
        menuTitle.setText("Skills");
        // Simple placeholder for skills screen
        Label skillsLabel = new Label("Skills will be shown here", skin);

        // Remove previous content if exists
        if (mainTable.getCells().size > 3) {
            mainTable.getCells().removeIndex(2);
        }

        mainTable.add(skillsLabel).colspan(2).padBottom(20);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        controller.handleButtons();
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextButton getSkillsButton() {
        return skillsButton;
    }

    public boolean isShowingSkills() {
        return showingSkills;
    }

    public void setShowingSkills(boolean showingSkills) {
        this.showingSkills = showingSkills;
    }
}
