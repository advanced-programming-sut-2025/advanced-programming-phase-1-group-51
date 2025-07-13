package io.github.StardewValley.Controllers.GameControllers;

import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Views.GameMenus.CheatMenu;
import io.github.StardewValley.Views.GameMenus.GameMenu;
import io.github.StardewValley.Main;
import io.github.StardewValley.Views.GameMenus.InventoryMenu;

import java.time.LocalTime;

public class GameController {
    private GameMenu view;
    private PlayerController playerController;
    private WorldController worldController;
    private CheatMenuController cheatMenuController;
    private InventoryController inventoryController;


    private LocalTime MaxTime = LocalTime.of(22, 0);
    private LocalTime MinTime = LocalTime.of(9, 0);
    private boolean gameEnded = false;

    public GameController() {
        this.cheatMenuController = new CheatMenuController();
        this.cheatMenuController.setGameController(this);
        this.inventoryController = new InventoryController();
        this.inventoryController.setGameController(this);

        // Initialize player with current game's player if available
        Player player = App.getCurrentGame() != null ?
            App.getCurrentGame().getCurrentPlayer() :
            new Player(App.getCurrentUser());

        this.playerController = new PlayerController(player, this);
        this.worldController = new WorldController(playerController);
    }

    public void updateGame(float deltaTime) {
        if (!gameEnded && !Main.isGamePaused()) {
            playerController.update(deltaTime);
            worldController.update();
        }
    }

    public void goToCheatMenu() {
        if (cheatMenuController == null) {
            cheatMenuController = new CheatMenuController();
            cheatMenuController.setGameController(this);
        }

        Main.getMain().setScreen(new CheatMenu(
            cheatMenuController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void goToInventory() {
        if (inventoryController == null) {
            inventoryController = new InventoryController();
            inventoryController.setGameController(this);
        }

        // Ensure player has an inventory
        if (playerController.getPlayer().getInventory() == null) {
            playerController.getPlayer().initializeInventory();
        }

        Main.getMain().setScreen(new InventoryMenu(
            inventoryController,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void ChangeTime(char c) {
        LocalTime newTime = Game.getTime();

        if (c == 'H') {
            newTime = newTime.plusHours(1);
        } else if (c == 'M') {
            newTime = newTime.plusMinutes(10);
        }

        if (newTime.isAfter(MaxTime)) {
            newTime = MinTime;

            // Advance day and season if needed
            int currentDay = Game.getCurrentDay() + 1;
            int currentSeason = Game.getCurrentSeason();

            if (currentDay > 28) {
                currentDay = 1;
                currentSeason++;

                if (currentSeason > 4) {
                    currentSeason = 1;
                }

                Game.setCurrentSeason(currentSeason);
                System.out.println("Season changed to: " + getSeasonName(currentSeason));
            }

            Game.setCurrentDay(currentDay);
            System.out.println("New Day: " + currentDay);
        }

        Game.setTime(newTime);
        System.out.println("Time: " + Game.getTime());
    }

    private String getSeasonName(int seasonNumber) {
        switch (seasonNumber % 4) {
            case 1:
                return "Spring";
            case 2:
                return "Summer";
            case 3:
                return "Fall";
            case 0:
                return "Winter";
            default:
                return "???";
        }
    }



    public void resumeGame() {
        Main.getMain().setScreen(this.view);
    }

    public void setView(GameMenu view) {
        this.view = view;
    }

    public GameMenu getView() {
        return view;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

}
