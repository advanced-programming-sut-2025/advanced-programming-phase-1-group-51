package Models.Buildings;

public class GreenHouse extends Building {

    private static boolean CanBeUse = false;

    public static boolean isCanBeUse(){
        return CanBeUse;
    }

    public static void setCanBeUse(boolean CanBeUse) {
        GreenHouse.CanBeUse = CanBeUse;
    }
}
