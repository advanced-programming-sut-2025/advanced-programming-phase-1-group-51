package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.CropType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Controllers.Services.UserService;

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
        // Initialize item types
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

        // Initialize user data through UserService only
        initializeUserData();
    }

    private static void initializeUserData() {
        UserService userService = new UserService();
        Result loadResult = userService.loadAllUsers();

        if (!loadResult.isSuccessful()) {
            System.err.println("Failed to load users: " + loadResult.getMessage());
        }

        // Check for active session
        Result activeUserResult = userService.loadActiveUser();
        if (activeUserResult.isSuccessful() && getCurrentUser() != null) {
            setCurrentMenu(Menu.MainMenu);
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

    public static ArrayList<User> getUsers() {
        return users;
    }


}
