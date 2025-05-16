package Controllers;

import Models.Game;
import Models.Result;
import Controllers.Services.GameStorageService;
import Controllers.Services.UserService;

import java.io.IOException;

public abstract class BaseController {
    protected final GameStorageService storageService;
    protected final UserService userService;

    public BaseController() {
        this.storageService = new GameStorageService();
        this.userService = new UserService();
    }

    protected Result saveGameState(Game game) {
        try {
            storageService.saveGameState(game);
            return Result.success("Game progress saved");
        } catch (IOException e) {
            return Result.failure("Failed to save game: " + e.getMessage());
        }
    }

    protected Result saveAllUsers() {
        return userService.saveAllUsers(); // Delegate to UserService
    }
}
