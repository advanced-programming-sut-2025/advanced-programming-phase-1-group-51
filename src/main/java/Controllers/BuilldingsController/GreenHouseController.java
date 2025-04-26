package Controllers.BuilldingsController;

import Models.Buildings.GreenHouse;
import Models.*;


public class GreenHouseController {

    public String CanBuildGreenHouse() {
        if (Game.getCurrentPlayer().getInventory().getNumberOfGold() >= 1000 &&
            Game.getCurrentPlayer().getInventory().getNumberOfWood() >= 500) {
            Game.getCurrentPlayer().increaseGold(-1000);
            Game.getCurrentPlayer().increaseWood(-500);
            GreenHouse.setCanBeUse(true);
            return ("<<<   CONGRATULATION!!!   Your Green house built successfully!  >>>");
        }
        else {
            GreenHouse.setCanBeUse(false);
            if (Game.getCurrentPlayer().getInventory().getNumberOfGold() <= 1000) {
                return ("<<<   You don't have enough gold!  >>>");
            }
            else {
                return ("<<<   You don't have enough wood!  >>>");
            }
        }
    }
}
