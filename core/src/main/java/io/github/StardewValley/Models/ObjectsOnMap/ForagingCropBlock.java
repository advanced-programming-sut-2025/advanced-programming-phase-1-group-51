package io.github.StardewValley.Models.ObjectsOnMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Enums.Types.ObjectShownOnMap.ForagingCropType;

public class ForagingCropBlock extends ObjectOnMap {
    private ForagingCropType foragingCropType;

    public ForagingCropBlock(float x, float y, ForagingCropType foragingCropType) {
        super(x, y, 42, 42,42,42, "foragingCrop");
        this.foragingCropType = foragingCropType;

        try {
            Texture texture = GameAssetsManager.getInstance()
                .getForagingAssetsManager()
                .getForagingCropTexture(foragingCropType);

            if (texture == null) {
                throw new RuntimeException("Null texture for crop type: " + foragingCropType);
            }

            this.sprite = new Sprite(texture);
            this.sprite.setSize(spriteWidth, spriteWidth);
            this.sprite.setPosition(x, y);
        } catch (Exception e) {
            Gdx.app.error("ForagingCrop", "Error creating sprite", e);
            throw e; // Re-throw to see the error
        }
    }

    public ForagingCropType getForagingCropType() {
        return foragingCropType;
    }
}
