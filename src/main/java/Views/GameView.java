package Views;

import Models.Enums.MenuComands.Menu;
import Models.Game;

public class GameView {

    public void run() {
        do {
            Game.getCurrentMenu().checkCommand(Game.scanner); // Use the shared Scanner
        } while (Game.getCurrentMenu() != Menu.ExitMenu);
    }
}
