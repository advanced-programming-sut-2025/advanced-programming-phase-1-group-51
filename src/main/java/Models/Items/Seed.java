package Models.Items;

import Models.Enums.Types.AllCropsType;

public class Seed extends Item{

    private AllCropsType cropSeedsType;
    private String name;


    public AllCropsType getCropType() {
        return cropSeedsType;
    }

    public void setCropType(AllCropsType cropSeedsType) {
        this.cropSeedsType = cropSeedsType;
    }

    public Seed(AllCropsType cropSeedsType, String name) {
        this.cropSeedsType = cropSeedsType;
        this.name = name;
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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
