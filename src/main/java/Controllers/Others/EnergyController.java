package Controllers.Others;

import Models.*;

import java.util.Currency;

public class EnergyController {


    public Result energyShow() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        return new Result(true , "Your Energy Is " + player.getEnergy());
    }

    public Result cheatEnergySet(int energy) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setEnergy(energy);
        return new Result(true , "Your Energy Is " + player.getEnergy());
    }

    public Result cheatEnergyUnlimited() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setEnergy(1_000_000_000);
         return new Result(true, "Energy set unlimited successfully!");
    }
}
