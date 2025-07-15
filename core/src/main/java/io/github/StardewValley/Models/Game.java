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
    private LocalTime time = LocalTime.of(9, 0);
    private int currentDay = 1;
    private int currentSeason = 1;
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

    public int getCurrentSeason() {
        return currentSeason;
    }


    public int getCurrentDay() {
        return currentDay;
    }


    public  LocalTime getTime() {
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWeatherTomorrow(Weather weatherTomorrow) {
        this.weatherTomorrow = weatherTomorrow;
    }

    public void setWeatherToday(Weather weatherToday) {
        this.weatherToday = weatherToday;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
