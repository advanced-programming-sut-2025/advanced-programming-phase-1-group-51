package Models.Items;

import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;
import Models.Loot;
import Models.ObjectsShownOnMap.ObjectOnMap;

import java.time.LocalDateTime;

public class ArtisanBlockCell extends Item {

    private ArtisanBlockType artisanType;
    public LocalDateTime timeLength;
    public Loot productLoot;
    public boolean beingUsed;
    public boolean canBeCollected;

    public ArtisanBlockCell(ArtisanBlockType artisanType) {
        this.artisanType = artisanType;
    }

    public ArtisanBlockType getArtisanType() {
        return artisanType;
    }

    public void setArtisanType(ArtisanBlockType artisanType) {
        this.artisanType = artisanType;
    }

    public LocalDateTime getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(LocalDateTime timeLength) {
        this.timeLength = timeLength;
    }

    public Loot getProductLoot() {
        return productLoot;
    }

    public void setProductLoot(Loot productLoot) {
        this.productLoot = productLoot;
    }

    public boolean isBeingUsed() {
        return beingUsed;
    }

    public void setBeingUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    public boolean isCanBeCollected() {
        return canBeCollected;
    }

    public void setCanBeCollected(boolean canBeCollected) {
        this.canBeCollected = canBeCollected;
    }

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }
}
