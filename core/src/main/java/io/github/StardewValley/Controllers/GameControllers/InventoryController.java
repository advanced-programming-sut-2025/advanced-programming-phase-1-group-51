package io.github.StardewValley.Controllers.GameControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Views.GameMenus.InventoryMenu;

public class InventoryController {
    private InventoryMenu view;
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setView(InventoryMenu view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getBackButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                resumeGame();
            }

            if (view.getSkillsButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                view.setShowingSkills(!view.isShowingSkills());
                view.show(); // Refresh the view
            }
        }
    }

    public PlayerController getPlayerController() {
        return gameController.getPlayerController();
    }

    private void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }
}
