package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingCropType;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.ObjectsOnMap.*;

import java.util.ArrayList;

public class ForagingController {
    private ArrayList<ForagingCropBlock> foragingCrops;
    private ArrayList<ForagingTreeBlock> foragingTrees;
    private ArrayList<ForagingMineralBlock> mineralBlocks;
    private final Vector2 minBounds = new Vector2(120, 120);
    private final Vector2 maxBounds = new Vector2(2640, 1680);

    public ForagingController() {
        this.foragingCrops = new ArrayList<>();
        this.foragingTrees = new ArrayList<>();
        this.mineralBlocks = new ArrayList<>();
        spawnInitialResources();
    }



    private void spawnInitialResources() {
        // Spawn trees
        spawnTrees();

        // Spawn forage crops (grass, spring onions)
        spawnForageCrops();

        // Spawn mineral blocks (stones, wood)
        spawnMineralBlocks();
    }

    private void spawnTrees() {
        // Spawn different types of trees in the area
        int treeCount = MathUtils.random(15, 25); // Random number of trees

        for (int i = 0; i < treeCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);

            // Randomly choose tree type (weighted toward common trees)
            float treeType = MathUtils.random();
            ForagingTreeType type;

            if (treeType < 0.4f) {
                type = ForagingTreeType.PINE_TREE;
            } else {
                type = ForagingTreeType.OAK_TREE;
            }
//            else if (treeType < 0.9f) {
//                type = ForagingTreeType.MAPLE_TREE;
//            } else {
//                type = ForagingTreeType.MAHOGANY_TREE;
//            }

            foragingTrees.add(new ForagingTreeBlock(x, y, type));
        }
    }

    private void spawnForageCrops() {
        // Spawn grass
        int grassCount = MathUtils.random(30, 50);
        for (int i = 0; i < grassCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.GRASS));
        }

        // Spawn spring onions (only in spring)
        if (App.getCurrentGame().getCurrentSeason() == 1) { // Spring
            int onionCount = MathUtils.random(5, 15);
            for (int i = 0; i < onionCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.SPRING_ONION));
            }
        }
    }

    private void spawnMineralBlocks() {
        // Spawn stones
        int stoneCount = MathUtils.random(20, 40);
        for (int i = 0; i < stoneCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            mineralBlocks.add(new ForagingMineralBlock(x, y, ForagingMineralType.STONE));
        }

        // Spawn wood (as mineral blocks for simplicity)
        int woodCount = MathUtils.random(10, 20);
        for (int i = 0; i < woodCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            mineralBlocks.add(new ForagingMineralBlock(x, y, ForagingMineralType.WOOD));
        }
    }

    public void respawnDailyResources() {
        // Remove some existing resources
        removeSomeResources();

        // Spawn new ones
        spawnForageCrops();
        spawnMineralBlocks();

        // Trees don't respawn daily, but you could add regrowth logic here
    }

    private void removeSomeResources() {
        // Remove about 30% of crops and minerals
        int cropsToRemove = (int)(foragingCrops.size() * 0.3f);
        for (int i = 0; i < cropsToRemove; i++) {
            if (!foragingCrops.isEmpty()) {
                foragingCrops.remove(MathUtils.random(foragingCrops.size() - 1));
            }
        }

        int mineralsToRemove = (int)(mineralBlocks.size() * 0.3f);
        for (int i = 0; i < mineralsToRemove; i++) {
            if (!mineralBlocks.isEmpty()) {
                mineralBlocks.remove(MathUtils.random(mineralBlocks.size() - 1));
            }
        }
    }

    public void render(SpriteBatch batch) {
        // Render all foraging objects
        for (ForagingCropBlock crop : foragingCrops) {
            crop.render(batch);
        }

        for (ForagingTreeBlock tree : foragingTrees) {
            tree.render(batch);
        }

        for (ForagingMineralBlock mineral : mineralBlocks) {
            mineral.render(batch);
        }
    }

    public ArrayList<ForagingCropBlock> getForagingCrops() {
        return foragingCrops;
    }

    public ArrayList<ForagingTreeBlock> getForagingTrees() {
        return foragingTrees;
    }

    public ArrayList<ForagingMineralBlock> getMineralBlocks() {
        return mineralBlocks;
    }
}
