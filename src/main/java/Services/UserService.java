package Services;

import Models.App;
import Models.Result;
import Models.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserService {
    private static final String USERS_FILE = "saved_data/users.json";
    private static final String ACTIVE_USER_FILE = "saved_data/active_user.json";
    private final ObjectMapper objectMapper;

    public Result saveAllUsers() {
        try {
            ensureDirectoryExists();
            objectMapper.writeValue(new File(USERS_FILE), User.getUsers());
            return Result.success("User data saved successfully");
        } catch (IOException e) {
            return Result.failure("Failed to save users: " + e.getMessage());
        }
    }

    public Result saveActiveUser(User user) {
        try {
            ensureDirectoryExists();
            objectMapper.writeValue(new File(ACTIVE_USER_FILE), user.getUsername());
            return Result.success("Active user saved");
        } catch (IOException e) {
            return Result.failure("Failed to save active user: " + e.getMessage());
        }
    }

    public UserService() {
        this.objectMapper = new ObjectMapper();
        // Configure ObjectMapper to be more permissive
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Result loadAllUsers() {
        try {
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                return Result.success("No existing user data found");
            }

            // Use this more robust reading method
            JavaType type = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, User.class);
            List<User> users = objectMapper.readValue(file, type);

            User.setUsers(users);
            return Result.success("User data loaded successfully");
        } catch (IOException e) {
            return Result.failure("Failed to load users: " + e.getMessage());
        }
    }

    public Result loadActiveUser() {
        try {
            File file = new File(ACTIVE_USER_FILE);
            if (!file.exists()) {
                return Result.success("No active user found");
            }

            String username = objectMapper.readValue(file, String.class);
            User user = findUserByUsername(username);
            if (user != null && user.shouldStayLoggedIn()) {
                App.setCurrentUser(user);
                return Result.success("Automatically logged in as " + username);
            }
            return Result.success("No active user to restore");
        } catch (IOException e) {
            return Result.failure("Failed to load active user: " + e.getMessage());
        }
    }

    private User findUserByUsername(String username) {
        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    static {
        new UserService().loadAllUsers();
        new UserService().loadActiveUser();
    }

    private void ensureDirectoryExists() throws IOException {
        Files.createDirectories(Paths.get("saved_data"));
    }
}
