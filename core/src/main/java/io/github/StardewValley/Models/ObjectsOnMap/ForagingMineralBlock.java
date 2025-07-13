package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;

public class ForagingMineralBlock extends ObjectOnMap {
    private ForagingMineralType foragingMineralType;

    public ForagingMineralBlock(float x, float y, ForagingMineralType foragingMineralType) {
        super(x, y, 45, 45,30,30, "foragingMineral");
        this.foragingMineralType = foragingMineralType;

        // Initialize sprite
        this.sprite = new Sprite(GameAssetsManager.getInstance()
            .getForagingAssetsManager()
            .getMineralTexture(foragingMineralType));
        this.sprite.setSize(spriteWidth, spriteHeight);
        this.sprite.setPosition(x, y);
    }

    public ForagingMineralType getForagingMineralType() {
        return foragingMineralType;
    }
}
