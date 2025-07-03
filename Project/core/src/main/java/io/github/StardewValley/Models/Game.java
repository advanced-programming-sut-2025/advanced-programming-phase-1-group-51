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


    public static void setTime(LocalTime time) {
        Game.time = time;
    }

    public static LocalTime getTime() {
        return time;
    }

    public ArrayList<Player> players;
    private boolean isGameOver = false;
    private Season season;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Player currentPlayer;
    private ArrayList<Notif> messages = new ArrayList<>();

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

    private ArrayList<Gift> gifts = new ArrayList<>();
    public ArrayList<Trade> tradingHistory = new ArrayList<>();



    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.weatherToday = Weather.SUNNY;
        this.weatherTomorrow = Weather.SUNNY;
        this.season = Season.SPRING;
        this.currentPlayer = null;
        this.map = Map.makeMap();


    }
}
