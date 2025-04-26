package Models;

public class Fish{
    double R;
    double M;
    private String FishName;
    private int FishPrice;
    private Models.Enums.Others.Season Season;


    public Fish(String fishName, int fishPrice, Models.Enums.Others.Season season) {
        FishName = fishName;
        FishPrice = fishPrice;
        Season = season;
    }


    public String getFishName() {
        return FishName;
    }

    public int getFishPrice() {
        return FishPrice;
    }

    public Models.Enums.Others.Season getSeason() {
        return Season;
    }

    public void setFishName(String fishName) {
        FishName = fishName;
    }

    public void setFishPrice(int fishPrice) {
        FishPrice = fishPrice;
    }

    public void setSeason(Models.Enums.Others.Season season) {
        Season = season;
    }


}
