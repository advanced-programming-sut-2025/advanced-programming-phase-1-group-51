package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Maps.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static final Scanner scanner = new Scanner(System.in);
    private static Menu currentMenu = Menu.SignUpMenu;
    private static User currentUser = null;
    private static Player GameStarterPlayer = null;
    private static boolean isGameOver = false;
    private static Season season = Season.SPRING;
    private static Map map;
    private static Weather weather = Weather.SUNNY;
    private static DateAndTime currentDateAndTime;
    private static Player currentPlayer = null;


    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }
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

    public static Season getSeason() {
        return season;
    }

    public static void setSeason(Season season) {
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

    public static Weather getWeather() {
        return weather;
    }

    public static void setWeather(Weather weather) {
        Game.weather = weather;
    }

    public static Result showCurrentMenu() {
        return new Result(true, currentMenu.name());
    }

    public static Player getGameStarterPlayer() {
        return GameStarterPlayer;
    }

    public static void setGameStarterPlayer(Player gameStarterPlayer) {
        GameStarterPlayer = gameStarterPlayer;
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
        if (getSeason() == Season.SPRING) {
            setWeather(rand < 0.6 ? Weather.RAIN : Weather.SUNNY);
        } else if (getSeason() == Season.SUMMER) {
            setWeather(rand < 0.1 ? Weather.STORM : Weather.SUNNY);
        } else if (getSeason() == Season.FALL) {
            setWeather(rand < 0.4 ? Weather.RAIN : Weather.SUNNY);
        } else { // WINTER
            setWeather(rand < 0.7 ? Weather.SNOW : Weather.SUNNY);
        }
    }




}

