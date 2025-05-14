package Models.Maps;

import java.util.ArrayList;

public class Map {

    private ArrayList<Farm> farms;
    private Village village;


    private Map(ArrayList<Farm> farms, Village village) {
        this.farms = farms;
        this.village = village;
    }

    public static Map makeMap() {
        ArrayList<Farm> farms = new ArrayList<>();
        Village village = new Village();
        return new Map(farms, village);
    }



    public void addFarm(Farm farm) {
        farms.add(farm);
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public Village getVillage() {
        return village;
    }
}
