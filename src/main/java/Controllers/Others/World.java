package Controllers.Others;

import Models.Enums.Types.SeasonType;
import Models.Enums.Types.WeatherType;
import Models.Game;
import Models.Result;

public class World {

    public Result Time() {
        return new Result(true, "Current time: " + Game.getCurrentDateAndTime().getTime());
    }

    public Result Date() {
        return new Result(true, "Current date: " + Game.getCurrentDateAndTime().getDate());
    }

    public Result DateTime() {
        return new Result(true, "Current date and time: " +
                Game.getCurrentDateAndTime().getDate() + " " +
                Game.getCurrentDateAndTime().getTime());
    }

    public Result DayOfTheWeek() {
        return new Result(true, "Today is: " + Game.getCurrentDateAndTime().getDayOfWeek());
    }

    public Result CheatAdvanceTime(String input) {
        try {
            int hours = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            if (hours < 0) {
                return new Result(false, "Hours cannot be negative");
            }
            Game.advanceTime(hours);
            return new Result(true, "Advanced time by " + hours + " hours");
        } catch (NumberFormatException e) {
            return new Result(false, "Invalid time format");
        }
    }

    public Result CheatAdvanceDate(String input) {
        try {
            int days = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            if (days < 0) {
                return new Result(false, "Days cannot be negative");
            }
            Game.advanceDate(days);
            return new Result(true, "Advanced date by " + days + " days");
        } catch (NumberFormatException e) {
            return new Result(false, "Invalid date format");
        }
    }

    public Result showCurrentSeason(SeasonType season) {
        return new Result(true, Game.getSeason().name());
    }

    public Result showCurrentWeather() {
        return new Result(true, Game.getWeather().name());
    }

    public Result weatherForecast(){

        return null;
    }

    public Result cheatTomorrowWeatherSet(WeatherType tomorrowWeather){
        Game.setWeather(tomorrowWeather);
        return new Result(true, "Tomorrow weather changed to " + Game.getWeather().name() + " successfully");
    }

    public Result CheatThor(){
        return null;
    }

    public Result GreenhouseBuild(){
        return null;
    }

    public Result BuildBuilding(){
        return null;
    }

    public Result ToolUse(){
        return null;
    }

}
