package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    private Villages Village;
    private ArrayList<Farm> Farms = new ArrayList<>();


    public Map(Villages village, ArrayList<Farm> farms) {
        Village = village;
        Farms = farms;
    }


    public Villages getVillage() {
        return Village;
    }

    public ArrayList<Farm> getFarms() {
        return Farms;
    }

    public void setVillage(Villages village) {
        Village = village;
    }

    public void setFarms(ArrayList<Farm> farms) {
        Farms = farms;
    }
}
