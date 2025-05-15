package Views;

import Models.App;
import Models.Enums.MenuCommands.Menu;
import Models.Game;
import Services.GameStorageService;
import Services.UserService;

import java.io.IOException;

public class GameView {

    private final GameStorageService storageService = new GameStorageService();
    private final UserService userService = new UserService();  // Add this

    public void run() {
        try {
            do {
                App.getCurrentMenu().checkCommand(App.scanner);
            } while (App.getCurrentMenu() != Menu.ExitMenu);

            // Save on exit - use proper services for each type
            if (App.getCurrentUser() != null) {
                // Save game state if in game
                if (App.getCurrentUser().getCurrentGame() != null) {
                    storageService.saveGameState(App.getCurrentUser().getCurrentGame());
                }
                // Save users through UserService
                userService.saveAllUsers();
            }
        } catch (IOException e) {
            System.err.println("Failed to save on exit: " + e.getMessage());
        }
    }
}
