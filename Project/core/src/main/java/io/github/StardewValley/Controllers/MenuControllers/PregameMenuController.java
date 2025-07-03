package io.github.StardewValley.Controllers.MenuControllers;

import io.github.StardewValley.Controllers.GameControllers.GameController;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Models.User;
import io.github.StardewValley.Views.GameMenus.GameMenu;
import io.github.StardewValley.Views.MenusBeforeGame.MainMenu;
import io.github.StardewValley.Views.MenusBeforeGame.PregameMenu;

import java.util.ArrayList;

public class PregameMenuController {
    private PregameMenu view;
    public void setView(PregameMenu view) {
        this.view = view;
    }

    public void handlePregameMenuButtons() {
        if(view != null){

            String username1 = view.getUsername1().getText();
            String username2 = view.getUsername2().getText();
            String username3 = view.getUsername3().getText();

            if(view.getNewGameButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                ArrayList<String> usernames = new ArrayList<>();

                if(!username1.isEmpty()){
                    usernames.add(username1);
                }
                if(!username2.isEmpty()){
                    usernames.add(username2);
                }
                if(!username3.isEmpty()){
                    usernames.add(username3);
                }

                ArrayList<Player> players = new ArrayList<>();
                players.add(new Player(App.getCurrentUser()));

                boolean check = true;

                for (String username : usernames) {
                    if (username.isEmpty() || username.equals(App.getCurrentUser().getUsername())) {
                        continue;
                    }

                    User user = App.findUserByUsername(username);
                    if (user == null) {
                        view.showError("User " + username + " not found");
                        check = false;
                    }
                    else if (user.getCurrentGame() != null) {
                        view.showError("User " + username + " is already in a game");
                        check = false;
                    }
                    else if (username.equals(App.getCurrentUser().getUsername())) {
                        view.showError("You can't add yourself.");
                        check = false;
                    }
                    else {
                        Player player = new Player(user);
                        players.add(player);
                    }
                }
                if(check){
                    Game game = new Game(players, players.get(0));

                    StringBuilder message = new StringBuilder();
                    message.append("Game created successfully with < You, ");
                    for(String username : usernames){
                        message.append(username).append(", ");
                    }
                    message.append(">");
                    view.showError(message.toString());
                    for (Player player : players) {
                        player.getUser().setCurrentGame(game);
                    }
                    App.setCurrentGame(game);
                }

            }
            else if(view.getLoadGameButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                if(App.getCurrentGame() == null){
                    view.showError("You have to create a new game first!");
                }
                else{
                    navigateToGame();
                }
            }
            else if(view.getBackButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToMain();
            }
        }
    }

    public void navigateToMain() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(
            new MainMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

    public void navigateToGame() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameMenu(
            new GameController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }
}
