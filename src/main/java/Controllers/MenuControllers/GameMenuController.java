package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.Types.SeasonType;
import Models.Enums.Types.WeatherType;
import Models.Game;
import Models.Result;

public class GameMenuController extends Controller {

    public  Result showCurrentMenu(){
        return new Result(true, Game.getCurrentMenu().name());
    }

    public  Result exitGame(){
        return null;
    }

    public Result setSeason(SeasonType season) {
        Game.setSeason(season);
        return new Result(true, "Season changed to " + season.name());
    }

    public Result setWeather(WeatherType weather) {
        Game.setWeather(weather);
        return new Result(true, "Weather changed to " + weather.name());
    }

    public Result goToTradeMenu(){


        return new Result(true, "You are now in Trade menu");
    }

}
