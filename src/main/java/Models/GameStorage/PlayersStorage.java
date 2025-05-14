package Models.GameStorage;

import Models.Player;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayersStorage {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String filePath = "SavePlayers.json";

    public static void SavePlayers() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(Player.players, writer);
        } catch (IOException e) {
            System.err.println("Error while saving players: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void load() {
        if (Files.exists(Paths.get(filePath))) {
            try (Reader reader = new FileReader(filePath)) {
                Player.players = gson.fromJson(reader, new TypeToken<ArrayList<Player>>() {}.getType());
                System.out.println("Player loaded");
            } catch (IOException e) {
                System.err.println("Error while loading players: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Creating new players...");
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }
    }
}
