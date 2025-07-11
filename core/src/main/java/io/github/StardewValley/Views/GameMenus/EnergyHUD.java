package io.github.StardewValley.Views.GameMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StardewValley.Models.Player;

public class EnergyHUD {
    private final Player player;
    private final ProgressBar energyBar;
    private final Label energyLabel;
    private final Table table;
    private final Stage stage;

    public EnergyHUD(Player player, Skin skin, Viewport viewport) {
        this.player = player;
        this.stage = new Stage(viewport);

        // Create table
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create energy bar
        energyBar = new ProgressBar(0, player.getMaxEnergy(), 1, false, skin, "health");
        energyBar.setValue(player.getCurrentEnergy());

        // Create label
        energyLabel = new Label(String.format("%.0f/%.0f", player.getCurrentEnergy(), player.getMaxEnergy()), skin);

        // Add to table
        table.bottom().right();
        table.padBottom(40).padRight(20);
        table.add(new Label("Energy: ", skin)).left().padRight(5);
        table.add(energyBar).width(200).height(20);
        table.add(energyLabel).padLeft(5);
    }

    public void update(float delta) {
        energyBar.setValue(player.getCurrentEnergy());
        energyLabel.setText(String.format("%.0f/%.0f", player.getCurrentEnergy(), player.getMaxEnergy()));

        // Visual feedback for low energy
        if (player.getCurrentEnergy() < player.getMaxEnergy() * 0.3f) {
            energyLabel.setColor(1, 0.5f, 0.5f, 1);
        } else {
            energyLabel.setColor(1, 1, 1, 1);
        }

        stage.act(delta);
    }

    public void render() {
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
