package Services;

import Models.Game;
import Models.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class GameStorageService {
    private static final String SAVE_DIRECTORY = "saved_games/";
    private static final String USERS_FILE = "users.json";
    private final Gson gson;

    public GameStorageService() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    // Save the entire game state
    public void saveGameState(Game game) throws IOException {
        ensureSaveDirectoryExists();
        String gameFilename = SAVE_DIRECTORY + "game_" + game.getPlayers().get(0).getUser().getUsername() + ".json";
        Files.writeString(Paths.get(gameFilename), gson.toJson(game));
        saveAllUsers();
    }

    // Save all users
    public void saveAllUsers() throws IOException {
        ensureSaveDirectoryExists();
        Files.writeString(Paths.get(SAVE_DIRECTORY + USERS_FILE), gson.toJson(User.getUsers()));
    }

    // Load all users
    public void loadAllUsers() throws IOException {
        File usersFile = new File(SAVE_DIRECTORY + USERS_FILE);
        if (usersFile.exists()) {
            String json = Files.readString(usersFile.toPath());
            List<User> loadedUsers = gson.fromJson(json, new TypeToken<List<User>>(){}.getType());
            User.setUsers(loadedUsers);
        }
    }

    // Load a specific game
    public Game loadGame(String username) throws IOException {
        File gameFile = new File(SAVE_DIRECTORY + "game_" + username + ".json");
        if (gameFile.exists()) {
            String json = Files.readString(gameFile.toPath());
            return gson.fromJson(json, Game.class);
        }
        return null;
    }

    private void ensureSaveDirectoryExists() throws IOException {
        Files.createDirectories(Paths.get(SAVE_DIRECTORY));
    }
}
