package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.CropType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Services.GameStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {


    public static final Scanner scanner = new Scanner(System.in);
    final private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static Menu currentMenu = Menu.SignUpMenu;
    private static final HashMap<String, ItemType> allItemTypes = new HashMap<>();

    static {
        try {
            // Load users at application start
            new GameStorageService().loadAllUsers();
        } catch (IOException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
        }
    }

    static {
        for (CropType cropSeedsType : CropType.values()) {
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
        for (TreeType treeSeedsType : TreeType.values()) {
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
