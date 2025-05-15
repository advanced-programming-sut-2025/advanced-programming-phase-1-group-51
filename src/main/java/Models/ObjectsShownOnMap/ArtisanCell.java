package Models.ObjectsShownOnMap;

import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;
import Models.Loot;

import java.time.LocalDateTime;

public class ArtisanCell extends ObjectOnMap {

    private ArtisanBlockType artisanType;
    public LocalDateTime prepTime;
    public Loot productSlot;
    public boolean beingUsed;
    public boolean canBeCollected;

    public ArtisanCell() {super();}

    public ArtisanCell(ArtisanBlockType artisanType) {
        super(false, "artisanBlock", "red");
        this.artisanType = artisanType;
        this.beingUsed = false;
        this.canBeCollected = false;
    }

    public ArtisanBlockType getArtisanType() {
        return artisanType;
    }
}
