package io.github.StardewValley.Controllers.GameControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Views.GameMenus.InventoryMenus.BackpackMenu;

public class InventoryController {
    private BackpackMenu view;
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setView(BackpackMenu view) {
        this.view = view;
    }


    public PlayerController getPlayerController() {
        return gameController.getPlayerController();
    }

    private void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }
}
