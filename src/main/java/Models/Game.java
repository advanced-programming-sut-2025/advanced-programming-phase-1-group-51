package Models;

import Models.Buildings.House;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Maps.*;
import Models.ObjectsShownOnMap.Lake;
import Models.ObjectsShownOnMap.Tree;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Game {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public ArrayList<Player> players;
    private boolean isGameOver = false;
    private Season season;
    private Map map;
    private Weather weather = Weather.SUNNY;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Player currentPlayer;




    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.parse("2025-01-01 09:00:00", dateTimeFormatter);
        this.weatherToday = Weather.SUNNY;
        this.weatherTomorrow = Weather.SUNNY;
        this.season = Season.SPRING;
        this.currentPlayer = null;
        this.map = Map.makeMap();
        initializeMapObjects();

    }


    private void initializeMapObjects() {
        // Initialize village
        Village village = map.getVillage();

        // Add village buildings
        for (Store store : village.getStores()) {
            Position storePos = new Position(store.getX(), store.getY());
            village.getCells().add(new Cells(storePos, store));
        }

        // Add paths, lakes, etc.
        // Example: add a lake
        for (int x = 50; x < 55; x++) {
            for (int y = 50; y < 55; y++) {
                village.getCells().add(new Cells(new Position(x, y), new Lake()));
            }
        }

        // Initialize farms with some random objects
        for (Farm farm : map.getFarms()) {
            initializeFarm(farm);
        }
    }

    private void initializeFarm(Farm farm) {
        // Add farmhouse at position (5,5) relative to farm
        Position housePos = new Position(5, 5);
        farm.getCells().add(new Cells(housePos, new House()));

        // Add some random trees
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 20);
            int y = (int) (Math.random() * 20);
            farm.getCells().add(new Cells(new Position(x, y), new Tree()));
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }



    public boolean isIsGameOver() {
        return isGameOver;
    }


    public Season getSeason() {
        return season;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Weather getWeatherToday() {
        return weatherToday;
    }

    public void setWeatherToday(Weather weatherToday) {
        this.weatherToday = weatherToday;
    }

    public Weather getWeatherTomorrow() {
        return weatherTomorrow;
    }

    public void setWeatherTomorrow(Weather weatherTomorrow) {
        this.weatherTomorrow = weatherTomorrow;
    }

    public Map getMap() {
        return map;
    }


    public Weather getWeather() {
        return weather;
    }


    public Farm getFarmByNumber(int number) {
        for (Player player : players) {
            if (player.getFarm().getFarmNumber() == number) {
                return player.getFarm();
            }
        }
        return null;
    }

    public Player findPlayerByUser(User user) {
        for (Player player : players) {
            if (player.getUser().getUsername().equals(user.getUsername())) {
                return player;
            }
        }
        return null;
    }

    public Player findPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getUser().getUsername().compareToIgnoreCase(username) == 0) {
                return player;
            }
        }
        return null;
    }



    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}

