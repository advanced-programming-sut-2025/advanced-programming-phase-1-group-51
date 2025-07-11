package io.github.StardewValley.Controllers.GameControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Enums.Regexes.CheatCodesRegexes;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Views.GameMenus.CheatMenu;

public class CheatMenuController {
    private CheatMenu view;
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setView(CheatMenu view) {
        this.view = view;
    }

    public void handleCheatMenuButtons() {
        Main.playSound(Main.getButtonClickSound());
        if(view != null){
            String cheatCode = view.getCheatCodeField().getText();
            Game game = App.getCurrentGame();
           if(view.getSubmitButton().isPressed()){
               if(CheatCodesRegexes.TIME_CHEAT.getMatcher(cheatCode) != null){
//                   game.setTime();
               }
               else if(CheatCodesRegexes.DATE_CHEAT.getMatcher(cheatCode) != null){

               }

            }

           else if (view.getBackButton().isPressed()) {
               Main.playSound(Main.getButtonClickSound());
               resumeGame();
           }
        }
    }


    private void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }

}
