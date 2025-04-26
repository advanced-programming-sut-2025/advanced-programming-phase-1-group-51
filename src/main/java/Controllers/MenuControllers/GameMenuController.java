package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Game;
import Models.Result;

public class GameMenuController extends Controller {

    public Result showCurrentMenu(){
        return new Result(true, "Game Menu");
    }

    public Result exitGame(){
        return null;
    }

    public Result setSeason(Season season) {
        Game.setSeason(season);
        return new Result(true, "Season changed to " + season.name());
    }

    public Result setWeather(Weather weather) {
        Game.setWeather(weather);
        return new Result(true, "Weather changed to " + weather.name());
    }

    public Result goToTradeMenu(){

        return new Result(true, "You are now in Trade menu");
    }

}
