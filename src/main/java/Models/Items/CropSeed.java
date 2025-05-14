package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ObjectsOnMapType.CropType;

public class CropSeed extends Item{

    private CropType cropSeedsType;

    public CropSeed() {
    }

    public CropType getCropType() {
        return cropSeedsType;
    }

    public void setCropType(CropType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
    }

    public CropSeed(CropType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
        this.name = cropSeedsType.source;
        this.quality = Quality.DEFAULT;
        this.value = cropSeedsType.baseSellPrice;
        this.energyCost = 0;
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

    public void setName(String name) {
        this.name = name;
    }
}
