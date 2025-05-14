package Controllers;

import Models.Game;
import Models.Result;
import Services.GameStorageService;

import java.io.IOException;

public abstract class BaseController {
    protected final GameStorageService storageService;

    public BaseController() {
        this.storageService = new GameStorageService();
    }

    protected Result saveGameState(Game game) {
        try {
            storageService.saveGameState(game);
            return new Result(true, "Game progress saved");
        } catch (IOException e) {
            return new Result(false, "Failed to save game: " + e.getMessage());
        }
    }

    protected Result saveAllUsers() {
        try {
            storageService.saveAllUsers();
            return new Result(true, "User data saved");
        } catch (IOException e) {
            return new Result(false, "Failed to save users: " + e.getMessage());
        }
    }
}
