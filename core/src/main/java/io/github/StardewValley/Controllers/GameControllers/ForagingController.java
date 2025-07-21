package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Enums.Others.Season;
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
    private GameController gameController;

    public ForagingController(GameController gameController) {
        this.gameController = gameController;
        this.foragingCrops = new ArrayList<>();
        this.foragingTrees = new ArrayList<>();
        this.mineralBlocks = new ArrayList<>();
        spawnInitialResources();
    }



    private void spawnInitialResources() {

        // Left Down
        spawnTrees(120,120,2640,1680);

        spawnForageCrops(120,120,2640,1680);

        spawnMineralBlocks(120,120,2640,1680 );

        // Left Up

        spawnTrees(120,8040,2640,1680 + 8040 - 120);

        spawnForageCrops(120,8040,2640,1680 + 8040 - 120);

        spawnMineralBlocks(120,8040,2640,1680 + 8040 - 120);

        // Right Down

        spawnTrees(8040,120,2640 + 8040,1680 );

        spawnForageCrops(8040,120,2640 + 8040,1680);

        spawnMineralBlocks(8040,120,2640 + 8040,1680);

        // Right Up

        spawnTrees(8040,8040,2640 + 8040,1680 + 8040 - 120);

        spawnForageCrops(8040,8040,2640 + 8040,1680 + 8040 - 120);

        spawnMineralBlocks(8040,8040,2640 + 8040,1680 + 8040 - 120);

    }

    private void spawnTrees(float minBoundX, float minBoundY, float maxBoundX, float maxBoundY) {

        Vector2 minBounds = new Vector2(minBoundX, minBoundY);
        Vector2 maxBounds = new Vector2(maxBoundX, maxBoundY);
        // Spawn different types of trees in the area
        int treeCount = MathUtils.random(80, 90); // Random number of trees

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
//            }
//            else {
//                type = ForagingTreeType.MAHOGANY_TREE;
//            }

            foragingTrees.add(new ForagingTreeBlock(x, y, type));
        }
    }

    private void spawnForageCrops(float minBoundX, float minBoundY, float maxBoundX, float maxBoundY) {
        // Spawn grass (available all seasons)
        Vector2 minBounds = new Vector2(minBoundX, minBoundY);
        Vector2 maxBounds = new Vector2(maxBoundX, maxBoundY);

        int grassCount = MathUtils.random(60, 70);
        for (int i = 0; i < grassCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.GRASS));
        }

        // Spawn common mushrooms (available all seasons)
        int commonMushroomCount = MathUtils.random(60, 70);
        for (int i = 0; i < commonMushroomCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.COMMON_MUSHROOM));
        }

        // Get current season
        Season currentSeason = App.getCurrentGame().getSeason();

        // Spring crops
        if (currentSeason == Season.SPRING) {
            // Spring Onions
            int springOnionCount = MathUtils.random(60, 70);
            for (int i = 0; i < springOnionCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.SPRING_ONION));
            }

            // Daffodils
            int daffodilCount = MathUtils.random(60, 70);
            for (int i = 0; i < daffodilCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.DAFFODIL));
            }

            // Dandelions
            int dandelionCount = MathUtils.random(40, 50);
            for (int i = 0; i < dandelionCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.DANDELION));
            }

            // Leeks
            int leekCount = MathUtils.random(30, 40);
            for (int i = 0; i < leekCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.LEEK));
            }

            // Morels
            int morelCount = MathUtils.random(20, 30);
            for (int i = 0; i < morelCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.MOREL));
            }

            // Wild Horseradish
            int horseradishCount = MathUtils.random(50, 60);
            for (int i = 0; i < horseradishCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.WILD_HORSERADISH));
            }
        }

        // Summer crops
        if (currentSeason == Season.SUMMER) {
            // Fiddlehead Fern
            int fernCount = MathUtils.random(30, 40);
            for (int i = 0; i < fernCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.FIDDLE_HEAD_FERN));
            }

            // Grapes
            int grapeCount = MathUtils.random(50, 60);
            for (int i = 0; i < grapeCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.GRAPE));
            }

            // Red Mushrooms
            int redMushroomCount = MathUtils.random(40, 50);
            for (int i = 0; i < redMushroomCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.RED_MUSHROOM));
            }

            // Spice Berries
            int spiceBerryCount = MathUtils.random(60, 70);
            for (int i = 0; i < spiceBerryCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.SPICE_BERRY));
            }

            // Sweet Peas
            int sweetPeaCount = MathUtils.random(50, 60);
            for (int i = 0; i < sweetPeaCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.SWEET_PEA));
            }
        }

        // Fall crops
        if (currentSeason == Season.FALL) {
            // Blackberries
            int blackberryCount = MathUtils.random(80, 90);
            for (int i = 0; i < blackberryCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.BLACKBERRY));
            }

            // Chanterelles
            int chanterelleCount = MathUtils.random(20, 30);
            for (int i = 0; i < chanterelleCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.CHANTERELLE));
            }

            // Hazelnuts
            int hazelnutCount = MathUtils.random(50, 60);
            for (int i = 0; i < hazelnutCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.HAZELNUT));
            }

            // Purple Mushrooms
            int purpleMushroomCount = MathUtils.random(30, 40);
            for (int i = 0; i < purpleMushroomCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.PURPLE_MUSHROOM));
            }

            // Wild Plums
            int wildPlumCount = MathUtils.random(60, 70);
            for (int i = 0; i < wildPlumCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.WILD_PLUM));
            }
        }

        // Winter crops
        if (currentSeason == Season.WINTER) {
            // Crocus
            int crocusCount = MathUtils.random(40, 50);
            for (int i = 0; i < crocusCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.CROCUS));
            }

            // Crystal Fruit
            int crystalFruitCount = MathUtils.random(30, 40);
            for (int i = 0; i < crystalFruitCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.CRYSTAL_FRUIT));
            }

            // Holly
            int hollyCount = MathUtils.random(50, 60);
            for (int i = 0; i < hollyCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.HOLLY));
            }

            // Snow Yams
            int snowYamCount = MathUtils.random(40, 50);
            for (int i = 0; i < snowYamCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.SNOW_YAM));
            }

            // Winter Roots
            int winterRootCount = MathUtils.random(60, 70);
            for (int i = 0; i < winterRootCount; i++) {
                float x = MathUtils.random(minBounds.x, maxBounds.x);
                float y = MathUtils.random(minBounds.y, maxBounds.y);
                foragingCrops.add(new ForagingCropBlock(x, y, ForagingCropType.WINTER_ROOT));
            }
        }
    }

    private void spawnMineralBlocks(float minBoundX, float minBoundY, float maxBoundX, float maxBoundY) {
        Vector2 minBounds = new Vector2(minBoundX, minBoundY);
        Vector2 maxBounds = new Vector2(maxBoundX, maxBoundY);

        // Spawn stones
        int stoneCount = MathUtils.random(140, 150);
        for (int i = 0; i < stoneCount; i++) {
            float x = MathUtils.random(minBounds.x, maxBounds.x);
            float y = MathUtils.random(minBounds.y, maxBounds.y);
            mineralBlocks.add(new ForagingMineralBlock(x, y, ForagingMineralType.STONE));
        }

        // Spawn wood (as mineral blocks for simplicity)
        int woodCount = MathUtils.random(140, 150);
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
        spawnForageCrops(120,120,2640,1680);
        spawnMineralBlocks(120,120,2640,1680);

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
        for(Wall wall :  gameController.getWorldController().getAllWalls()){
            wall.render(batch);
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
