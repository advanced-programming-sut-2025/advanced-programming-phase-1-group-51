package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Weather;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.AnimalCell;
import Models.ObjectsShownOnMap.BurntCell;

public class Husbandry extends Controller {


    public Result BuyAnimal(String animalType, String animalName) {
        return null;
    }

    public Result Pet(String animalName) {
        return null;
    }

    public Result animals() {
        return null;
    }

    public Result ShepherdAnimals(String animalName, int x, int y) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = Game.getCurrentPlayer().getCurrentFarm(game);
        Animal animal = player.getAnimalByName(animalName);
        Cells cell = farm.findCell(x, y);
        if (cell == null || !(cell.getObjectOnCell() instanceof BurntCell) || animal == null) {
            return new Result(false, "cell not found or not empty or no animal found");
        }
        if (game.getWeather() == Weather.SNOW || game.getWeather() == Weather.STORM || game.getWeather() == Weather.RAIN) {
            return new Result(true, "bad weather quality");
        }
        cell.setObjectOnCell(new AnimalCell(animal));
        return new Result(true, "you have shepherd");
    }

    public Result FeedHay(String animalName) {
        return null;
    }

    public Result Produces() {
        return null;
    }

    public Result CollectProduce(String name) {
        return null;
    }

    public Result SellAnimal(String animalName) {
        return null;
    }

    public Result fishing(String fishingPole) {
        return null;
    }

    public Result cheatSetFriendship(String animalName, int amount) {
        return null;
    }
}
