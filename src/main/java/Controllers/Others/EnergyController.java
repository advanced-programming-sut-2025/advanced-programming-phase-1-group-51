package Controllers.Others;

import Controllers.BaseController;
import Models.*;

import java.util.Currency;

public class EnergyController extends BaseController {

    public Result energyShow() {
        User user = App.getCurrentUser();
        if (user == null || user.getCurrentGame() == null) {
            return Result.failure("No active game session");
        }

        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        // Save game state and combine with energy status
        return saveGameState(game)
                .combine(Result.success("Current energy: " + player.getEnergy() + "/" + player.getMaxEnergy()));
    }

    public Result cheatEnergySet(int energy) {
        User user = App.getCurrentUser();
        if (user == null || user.getCurrentGame() == null) {
            return Result.failure("No active game session");
        }

        if (energy < 0) {
            return Result.failure("Energy cannot be negative");
        }

        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setEnergy(energy);

        // Save after modification and return combined result
        return saveGameState(game)
                .combine(Result.success("Energy set to " + energy));
    }

    public Result cheatEnergyUnlimited() {
        User user = App.getCurrentUser();
        if (user == null || user.getCurrentGame() == null) {
            return Result.failure("No active game session");
        }

        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setEnergy(Integer.MAX_VALUE); // Better than arbitrary large number

        // Save after modification and return combined result
        return saveGameState(game)
                .combine(Result.success("Energy set to unlimited (MAX_VALUE)"));
    }
}
