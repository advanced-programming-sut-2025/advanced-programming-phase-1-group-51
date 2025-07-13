package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingTreeType;

public class ForagingTree extends ObjectOnMap {
    private ForagingTreeType foragingTreeType;

    public ForagingTree(float x, float y, ForagingTreeType foragingTreeType) {
        super(x, y, 100, 200,50,50, "foragingTree");
        this.foragingTreeType = foragingTreeType;

        // Initialize sprite
        this.sprite = new Sprite(GameAssetsManager.getInstance()
            .getForagingAssetsManager()
            .getTreeTexture(foragingTreeType));
        this.sprite.setSize(spriteWidth, spriteHeight);
        this.sprite.setPosition(x, y);
    }

    public ForagingTreeType getForagingTreeType() {
        return foragingTreeType;
    }
}
