package Models;

import Models.Enums.MenuComands.Menu;
import Models.Enums.Types.SeasonType;
import Models.Enums.Types.WeatherType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static final Scanner scanner = new Scanner(System.in);
    private static Menu currentMenu = Menu.SignUpMenu;
    private static User currentUser = null;
    private static boolean isGameOver = false;
    private static SeasonType season = SeasonType.SPRING;
    private static Map map;
    private static WeatherType weather = WeatherType.SUNNY;
    private static DateAndTime currentDateAndTime;


    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        Game.currentMenu = currentMenu;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }

    public static boolean isIsGameOver() {
        return isGameOver;
    }

    public static void setIsGameOver(boolean isGameOver) {
        Game.isGameOver = isGameOver;
    }

    public static SeasonType getSeason() {
        return season;
    }

    public static void setSeason(SeasonType season) {
        Game.season = season;
    }

    public static DateAndTime getCurrentDateAndTime() {
        return currentDateAndTime;
    }

    public static void setCurrentDateAndTime(DateAndTime currentDateAndTime) {
        Game.currentDateAndTime = currentDateAndTime;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        Game.map = map;
    }

    public static WeatherType getWeather() {
        return weather;
    }

    public static void setWeather(WeatherType weather) {
        Game.weather = weather;
    }

    public static Result showCurrentMenu() {
        return new Result(true, currentMenu.name());
    }

    public static void advanceTime(int hours) {
        currentDateAndTime.increaseHours(hours);
        // Update weather based on time of day
        updateWeather();
    }

    public static void advanceDate(int days) {
        currentDateAndTime.increaseDays(days);
        // Update weather based on season
        updateWeather();
    }

    private static void updateWeather() {
        // Implement weather change logic based on season/time
        // This is just an example - adjust probabilities as needed
        double rand = Math.random();
        if (getSeason() == SeasonType.SPRING) {
            setWeather(rand < 0.6 ? WeatherType.RAIN : WeatherType.SUNNY);
        } else if (getSeason() == SeasonType.SUMMER) {
            setWeather(rand < 0.1 ? WeatherType.STORM : WeatherType.SUNNY);
        } else if (getSeason() == SeasonType.FALL) {
            setWeather(rand < 0.4 ? WeatherType.RAIN : WeatherType.SUNNY);
        } else { // WINTER
            setWeather(rand < 0.7 ? WeatherType.SNOW : WeatherType.SUNNY);
        }
    }




}

