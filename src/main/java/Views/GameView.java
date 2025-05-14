package Views;

import Models.App;
import Models.Enums.MenuCommands.Menu;
import Models.Game;
import Services.GameStorageService;

import java.io.IOException;

public class GameView {

    private final GameStorageService storageService = new GameStorageService();

    public void run() {
        try {
            do {
                App.getCurrentMenu().checkCommand(App.scanner);
            } while (App.getCurrentMenu() != Menu.ExitMenu);

            // Save on exit
            if (App.getCurrentUser() != null && App.getCurrentUser().getCurrentGame() != null) {
                storageService.saveGameState(App.getCurrentUser().getCurrentGame());
            }
            storageService.saveAllUsers();
        } catch (IOException e) {
            System.err.println("Failed to save game on exit: " + e.getMessage());
        }
    }
}
