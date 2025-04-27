package Views;

import Models.Enums.MenuCommands.Menu;
import Models.Game;

public class GameView {

    public void run() {
        do {
            Game.getCurrentMenu().checkCommand(Game.scanner); // Use the shared Scanner
        } while (Game.getCurrentMenu() != Menu.ExitMenu);
    }
}
