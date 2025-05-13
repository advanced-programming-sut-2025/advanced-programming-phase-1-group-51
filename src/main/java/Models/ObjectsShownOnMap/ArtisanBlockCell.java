package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;
import Models.Loot;

import java.time.LocalDateTime;

public class ArtisanBlockCell extends ObjectOnMap{

    private ArtisanBlockType artisanType;
    public LocalDateTime timeLength;
    public Loot productLoot;
    public boolean beingUsed;
    public boolean canBeCollected;


    public ArtisanBlockCell(ArtisanBlockType artisanType) {
        super(false, "artisanBlock", "pink");
        this.artisanType = artisanType;
        this.beingUsed = false;
        this.canBeCollected = false;
    }

}
