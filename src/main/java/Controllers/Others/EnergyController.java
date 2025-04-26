package Controllers.Others;

import Models.*;

import java.util.Currency;

public class EnergyController {

    Player currentPlayer = Game.getCurrentPlayer();

    public Result energyShow() {
        return new Result(true , "Your Energy Is " + currentPlayer.getEnergy());
    }

    public Result cheatEnergySet(int energy) {
        currentPlayer.setEnergy(energy);
        return new Result(true , "Your Energy Is " + currentPlayer.getEnergy());
    }

    public Result cheatEnergyUnlimited() {
        currentPlayer.setEnergy(1_000_000_000);
         return new Result(true, "Energy set unlimited successfully!");
    }
}
