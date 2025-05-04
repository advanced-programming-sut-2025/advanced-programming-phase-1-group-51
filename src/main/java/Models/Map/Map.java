package Models.Map;

import java.util.ArrayList;

public class Map {

    private Models.Map.Village Village;
    private ArrayList<Farm> Farms;


    public Map(Models.Map.Village village, ArrayList<Farm> farms) {
        Village = village;
        Farms = farms;
    }


    public Models.Map.Village getVillage() {
        return Village;
    }

    public ArrayList<Farm> getFarms() {
        return Farms;
    }

    public void setVillage(Models.Map.Village village) {
        Village = village;
    }

    public void setFarms(ArrayList<Farm> farms) {
        Farms = farms;
    }
}
