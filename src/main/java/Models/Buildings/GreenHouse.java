package Models.Buildings;

public class GreenHouse extends Building {

    private static boolean CanBeUsed = false;



    public static boolean isCanBeUsed(){
        return CanBeUsed;
    }

    public static void setCanBeUse(boolean CanBeUse) {
        GreenHouse.CanBeUsed = CanBeUse;
    }
}
