package Models;

import Models.Enums.Types.SeasonType;

public class Fishes {
    private double R;
    private double M;
    private int UserSkill;
    private String FishName;
    private int FishPrice;
    private SeasonType Season;


    public Fishes(String fishName, int fishPrice, SeasonType season) {
        FishName = fishName;
        FishPrice = fishPrice;
        Season = season;
    }

    public double getR() {
        return R;
    }

    public double getM() {
        return M;
    }

    public int getUserSkill() {
        return UserSkill;
    }

    public String getFishName() {
        return FishName;
    }

    public int getFishPrice() {
        return FishPrice;
    }

    public SeasonType getSeason() {
        return Season;
    }

    public void setR(double r) {
        R = r;
    }

    public void setM(double m) {
        M = m;
    }

    public void setUserSkill(int userSkill) {
        UserSkill = userSkill;
    }

    public void setFishName(String fishName) {
        FishName = fishName;
    }

    public void setFishPrice(int fishPrice) {
        FishPrice = fishPrice;
    }

    public void setSeason(SeasonType season) {
        Season = season;
    }
}
