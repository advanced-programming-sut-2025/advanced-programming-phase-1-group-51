package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingCropType;

public class ForagingCropBlock extends ObjectOnMap {
    private ForagingCropType foragingCropType;

    public ForagingCropBlock(float x, float y, ForagingCropType foragingCropType) {
        super(x, y, 45, 45,30,30, "foragingMineral");
        this.foragingCropType = foragingCropType;

        // Initialize sprite
        this.sprite = new Sprite(GameAssetsManager.getInstance()
            .getForagingAssetsManager()
            .getForagingCropTexture(foragingCropType));
        this.sprite.setSize(spriteWidth, spriteHeight);
        this.sprite.setPosition(x, y);
    }

    public ForagingCropType getForagingCropType() {
        return foragingCropType;
    }
}
