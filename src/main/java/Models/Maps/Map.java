package Models.Maps;

import java.util.ArrayList;

public class Map {

    private Village Village;
    private ArrayList<Farm> Farms;


    public Map(Models.Maps.Village village, ArrayList<Farm> farms) {
        Village = village;
        Farms = farms;
    }


    public Village getVillage() {
        return Village;
    }

    public ArrayList<Farm> getFarms() {
        return Farms;
    }

    public void setVillage(Village village) {
        Village = village;
    }

    public void setFarms(ArrayList<Farm> farms) {
        Farms = farms;
    }
}
