package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Maps.Farm;
import Models.Maps.Map;
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

