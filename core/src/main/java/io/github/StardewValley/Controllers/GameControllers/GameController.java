package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.math.Vector2;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Buildings.BlackSmith;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Views.GameMenus.CheatMenu;
import io.github.StardewValley.Views.GameMenus.GameMenu;
import io.github.StardewValley.Main;
import io.github.StardewValley.Views.GameMenus.InventoryMenus.BackpackMenu;
import io.github.StardewValley.Views.GameMenus.StoresMenus.AllProductsMenu;

import java.time.LocalTime;

public class GameController {
    private GameMenu view;
    private PlayerController playerController;
    private WorldController worldController;
    private CheatMenuController cheatMenuController;
    private Game game;


    private LocalTime MaxTime = LocalTime.of(22, 0);
    private LocalTime MinTime = LocalTime.of(9, 0);
    private boolean gameEnded = false;

    public GameController() {
        GameAssetsManager.getInstance().getNotificationAssets().load();
        this.game = App.getCurrentGame();
        this.cheatMenuController = new CheatMenuController();
        this.cheatMenuController.setGameController(this);

        // Initialize player with current game's player if available
        Player player = App.getCurrentGame() != null ?
            App.getCurrentGame().getCurrentPlayer() :
            new Player(App.getCurrentUser());

        this.playerController = new PlayerController(player, this);
        this.worldController = new WorldController(playerController);
    }

    // In GameController.java
    public void updateGame(float deltaTime) {
        if (!gameEnded && !Main.isGamePaused()) {
            playerController.update(deltaTime);
            playerController.getPlayer().updateNotifications(deltaTime);
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
        Main.getMain().setScreen(new BackpackMenu(
            this,
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void openStoreMenu() {
        Vector2 playerPosition = playerController.getPlayer().getPosition();
        float x = playerPosition.x;
        float y = playerPosition.y;
        if(x > 3120 && x < 3840 && y > 5265 && y < 5985){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "Black Smith"
            ));
        }
        else if(x > 6600 && x < 7680 && y > 3600 && y < 4680){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "Carpenter's Shop"
            ));
        }
        else if(x > 3120 && x < 3840 && y > 3720 && y < 4440){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "Fish Shop"
            ));
        }
        else if(x > 3600 && x < 4680 && y > 7305 && y < 8265){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "JojaMart"
            ));
        }
        else if(x > 6600 && x < 7780 && y > 5760 && y < 6840){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "Marnie's Ranch"
            ));
        }
        else if(x > 4560 && x < 5640 && y > 2520 && y < 3480){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "Pierre General Store"
            ));
        }
        else if(x > 5280 && x < 6480 && y > 7305 && y < 8025){
            Main.getMain().setScreen(new AllProductsMenu(
                this,
                GameAssetsManager.getInstance().getSkin(), "StarDrop Saloon"
            ));
        }
        else {
            playerController.getPlayer().addNotification("You have to fist enter a store");
        }
    }

    public void ChangeTime(char c) {
        LocalTime newTime = game.getTime();

        if (c == 'H') {
            newTime = newTime.plusHours(1);
        } else if (c == 'M') {
            newTime = newTime.plusMinutes(10);
        }

        if (newTime.isAfter(MaxTime)) {
            newTime = MinTime;

            // Advance day and season if needed
            int currentDay = game.getCurrentDay() + 1;
            int currentSeason = game.getCurrentSeason();

            if (currentDay > 28) {
                currentDay = 1;
                currentSeason++;

                if (currentSeason > 4) {
                    currentSeason = 1;
                }

                game.setCurrentSeason(currentSeason);
                System.out.println("Season changed to: " + getSeasonName(currentSeason));
            }

            game.setCurrentDay(currentDay);
            System.out.println("New Day: " + currentDay);
        }

        game.setTime(newTime);
        System.out.println("Time: " + game.getTime());
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
