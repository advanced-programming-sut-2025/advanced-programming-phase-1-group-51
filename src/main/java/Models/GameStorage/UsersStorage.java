package Models.GameStorage;

import Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UsersStorage {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String filePath = "SaveUsers.json";

    public static void SaveUsers() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(User.users, writer);
        } catch (IOException e) {
            System.err.println("Error while saving users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void load() {
        if (Files.exists(Paths.get(filePath))) {
            try (Reader reader = new FileReader(filePath)) {
                User.users = gson.fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
                System.out.println("Users loaded");
            } catch (IOException e) {
                System.err.println("Error while loading users: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Creating new users...");
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }
    }
}