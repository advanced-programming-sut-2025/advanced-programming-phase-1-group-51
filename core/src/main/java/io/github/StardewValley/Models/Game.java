package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Others.Weather;
import io.github.StardewValley.Models.Maps.Map;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Game {
    private Map map;
    private static LocalTime time = LocalTime.of(9, 0);
    private static int currentDay = 1;
    private static int currentSeason = 1;
    public ArrayList<Player> players;
    private boolean isGameOver = false;
    private Season season;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Player currentPlayer;

    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.players = players;
        this.currentPlayer = currentPlayer;  // Keep this assignment
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.weatherToday = Weather.SUNNY;
        this.weatherTomorrow = Weather.SUNNY;
        this.season = Season.SPRING;
        // Remove this line: this.currentPlayer = null;
        this.map = Map.makeMap();
    }

    public static int getCurrentSeason() {
        return currentSeason;
    }

    public static void setCurrentSeason(int currentSeason) {
        Game.currentSeason = currentSeason;
    }

    public static int getCurrentDay() {
        return currentDay;
    }

    public static void setCurrentDay(int currentDay) {
        Game.currentDay = currentDay;
    }

    public static void setTime(LocalTime time) {
        Game.time = time;
    }

    public static LocalTime getTime() {
        return time;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Season getSeason() {
        return season;
    }

    public Weather getWeatherToday() {
        return weatherToday;
    }

    public Weather getWeatherTomorrow() {
        return weatherTomorrow;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}
