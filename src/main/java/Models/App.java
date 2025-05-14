package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Types.ItemTypes.*;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

    final private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static Menu currentMenu = Menu.SignUpMenu;

    private static final HashMap<String, ItemType> allItemTypes = new HashMap<>();

    static {
        for (CropSeedsType cropSeedsType : CropSeedsType.values()) {
            allItemTypes.put(cropSeedsType.source, cropSeedsType);
        }
        for (FishType fishType : FishType.values()) {
            allItemTypes.put(fishType.name, fishType);
        }
        for (FoodType foodType : FoodType.values()) {
            allItemTypes.put(foodType.name, foodType);
        }
        for (ForagingMineralType foragingMineralsType : ForagingMineralType.values()) {
            allItemTypes.put(foragingMineralsType.name, foragingMineralsType);
        }
        for (TreeSeedsType treeSeedsType : TreeSeedsType.values()) {
            allItemTypes.put(treeSeedsType.name, treeSeedsType);
        }
        for (ElseType miscType : ElseType.values()) {
            allItemTypes.put(miscType.name, miscType);
        }
        for (ToolType toolType : ToolType.values()) {
            allItemTypes.put(toolType.name, toolType);
        }
    }


    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static HashMap<String, ItemType> getAllItemTypes() {
        return allItemTypes;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
