package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.CropSeedsType;

public class Seed extends Item{

    private CropSeedsType cropSeedsType;

    public Seed() {
    }

    public CropSeedsType getCropType() {
        return cropSeedsType;
    }

    public void setCropType(CropSeedsType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
    }

    public Seed(CropSeedsType cropSeedsType) {
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
