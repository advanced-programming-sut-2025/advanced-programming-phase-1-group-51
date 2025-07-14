package io.github.StardewValley.Models.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FoodType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ToolType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingCropType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.TrashcanType;
import io.github.StardewValley.Models.Items.Food;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.Items.Mineral;
import io.github.StardewValley.Models.Items.Tool;

import java.util.HashMap;
import java.util.Map;

public class ForagingAssets implements Disposable {
    private static ForagingAssets instance;

    // Individual textures for each type
    private Map<ForagingTreeType, Texture> treeTextures;
    private Map<ForagingCropType, Texture> cropTextures;
    private Map<ForagingMineralType, Texture> mineralTextures;
    private Map<ToolType, Texture> toolTextures;
    private Map<TrashcanType, Texture> trashcanTexture;
    // Add to ForagingAssets class

    private void loadToolTextures() {
        toolTextures = new HashMap<>();
        toolTextures.put(ToolType.HOE, loadTexture("items/tools/Hoe.png"));
        toolTextures.put(ToolType.PICKAXE, loadTexture("items/tools/Pickaxe.png"));
        toolTextures.put(ToolType.AXE, loadTexture("items/tools/Axe.png"));
        toolTextures.put(ToolType.SCYTHE, loadTexture("items/tools/Scythe.png"));
        toolTextures.put(ToolType.WATERING_CAN_DEFAULT, loadTexture("items/tools/watering_can.png"));
        // ... other tools
    }
    private void loadTrashcanTextures() {
        trashcanTexture.put(TrashcanType.DEFAULT, loadTexture("items/trashcan/Trash_Can_Steel.png"));
    }

    public Texture getToolTexture(ToolType type) {
        return toolTextures.getOrDefault(type, createBlankTexture(32, 32));
    }

    public Texture getItemTexture(Item item) {
        if (item instanceof Tool) {
            return getToolTexture(((Tool) item).getType());
        }
        // Add other item types here as needed
        return createBlankTexture(32, 32);
    }

    private ForagingAssets() {
        loadTextures();
    }

    public static ForagingAssets getInstance() {
        if (instance == null) {
            instance = new ForagingAssets();
        }
        return instance;
    }

    private void loadTextures() {
        // Initialize the maps
        treeTextures = new HashMap<>();
        cropTextures = new HashMap<>();
        mineralTextures = new HashMap<>();

        // Load tree textures
        loadTreeTextures();

        // Load crop textures
        loadCropTextures();

        // Load mineral textures
        loadMineralTextures();

        loadToolTextures();
    }

    private void loadTreeTextures() {
        // Load individual textures for each tree type
        treeTextures.put(ForagingTreeType.OAK_TREE, loadTexture("foraging/trees/oak_tree.png"));
        treeTextures.put(ForagingTreeType.PINE_TREE, loadTexture("foraging/trees/pine_tree.png"));
        //        treeTextures.put(ForagingTreeType.MAPLE_TREE, loadTexture("foraging/trees/maple_tree.png"));
//        treeTextures.put(ForagingTreeType.MAHOGANY_TREE, loadTexture("foraging/trees/mahogany_tree.png"));
//        treeTextures.put(ForagingTreeType.NORMAL_TREE, loadTexture("foraging/trees/normal_tree.png"));
//        treeTextures.put(ForagingTreeType.BURNT_TREE, loadTexture("foraging/trees/burnt_tree.png"));
//        treeTextures.put(ForagingTreeType.TREE_BARK, loadTexture("foraging/trees/tree_bark.png"));
//        treeTextures.put(ForagingTreeType.APRICOT_TREE, loadTexture("foraging/trees/apricot_tree.png"));
//        treeTextures.put(ForagingTreeType.CHERRY_TREE, loadTexture("foraging/trees/cherry_tree.png"));
//        treeTextures.put(ForagingTreeType.BANANA_TREE, loadTexture("foraging/trees/banana_tree.png"));
//        treeTextures.put(ForagingTreeType.MANGO_TREE, loadTexture("foraging/trees/mango_tree.png"));
//        treeTextures.put(ForagingTreeType.ORANGE_TREE, loadTexture("foraging/trees/orange_tree.png"));
//        treeTextures.put(ForagingTreeType.PEACH_TREE, loadTexture("foraging/trees/peach_tree.png"));
//        treeTextures.put(ForagingTreeType.APPLE_TREE, loadTexture("foraging/trees/apple_tree.png"));
//        treeTextures.put(ForagingTreeType.POMEGRANATE_TREE, loadTexture("foraging/trees/pomegranate_tree.png"));
//        treeTextures.put(ForagingTreeType.MUSHROOM_TREE, loadTexture("foraging/trees/mushroom_tree.png"));
//        treeTextures.put(ForagingTreeType.MYSTIC_TREE, loadTexture("foraging/trees/mystic_tree.png"));
    }

    private void loadCropTextures() {
        // Load individual textures for each crop type
        cropTextures.put(ForagingCropType.GRASS, loadTexture("foraging/crops/grass.png"));
        cropTextures.put(ForagingCropType.SPRING_ONION, loadTexture("foraging/crops/spring_onion.png"));
//        cropTextures.put(ForagingCropType.DAFFODIL, loadTexture("foraging/crops/daffodil.png"));
//        cropTextures.put(ForagingCropType.DANDELION, loadTexture("foraging/crops/dandelion.png"));
//        cropTextures.put(ForagingCropType.LEEK, loadTexture("foraging/crops/leek.png"));
//        cropTextures.put(ForagingCropType.MOREL, loadTexture("foraging/crops/morel.png"));
        // Add all other crop types...
    }

    private void loadMineralTextures() {
        // Load individual textures for each mineral type
        mineralTextures.put(ForagingMineralType.STONE, loadTexture("foraging/minerals/stone.png"));
        mineralTextures.put(ForagingMineralType.WOOD, loadTexture("foraging/minerals/wood.png"));
//        mineralTextures.put(ForagingMineralType.QUARTZ, loadTexture("foraging/minerals/quartz.png"));
//        mineralTextures.put(ForagingMineralType.EARTH_CRYSTAL, loadTexture("foraging/minerals/earth_crystal.png"));
//        mineralTextures.put(ForagingMineralType.FROZEN_TEAR, loadTexture("foraging/minerals/frozen_tear.png"));
        // Add all other mineral types...
    }

    private Texture loadTexture(String path) {
        try {
            // First try to load the requested texture
            Texture texture = new Texture(Gdx.files.internal(path));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            return texture;
        } catch (Exception e) {
            Gdx.app.error("ForagingAssetsManager", "Failed to load texture: " + path, e);

            // Try to load a default placeholder
            try {
                return new Texture(Gdx.files.internal("foraging/placeholder.png"));
            } catch (Exception e2) {
                // If even placeholder fails, create a blank texture
                Gdx.app.error("ForagingAssetsManager", "Failed to load placeholder texture", e2);
                return createBlankTexture(32, 32); // Create a fallback
            }
        }
    }

    private Texture createBlankTexture(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAGENTA); // Easy-to-spot debug color
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture getTreeTexture(ForagingTreeType type) {
        Texture tex = treeTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 64); // Trees are taller
    }

    public Texture getCropTexture(ForagingCropType type) {
        Texture tex = cropTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    public Texture getMineralTexture(ForagingMineralType type) {
        Texture tex = mineralTextures.get(type);
        return tex != null ? tex : createBlankTexture(32, 32);
    }

    @Override
    public void dispose() {
        // Dispose all textures
        treeTextures.values().forEach(Texture::dispose);
        cropTextures.values().forEach(Texture::dispose);
        mineralTextures.values().forEach(Texture::dispose);
    }
}
