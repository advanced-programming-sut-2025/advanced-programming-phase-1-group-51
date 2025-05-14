package Models;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Maps.Farm;
import Models.Maps.Map;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

    public static final Scanner scanner = new Scanner(System.in);
    public ArrayList<Player> players;
    private boolean isGameOver = false;
    private Season season;
    private Map map;
    private Weather weather = Weather.SUNNY;
    private LocalDateTime date;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private Player currentPlayer;
    public boolean hasTurnCycleFinished;
    private Player GameStarterPlayer;
    private Player secondPlayer;
    private Player thirdPlayer;
    private Player fourthPlayer;


    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.hasTurnCycleFinished = false;
        this.players = players;
        this.currentPlayer = currentPlayer;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = LocalDateTime.parse("2025-01-01 09:00:00", dateTimeFormatter);
        this.weatherToday = Weather.SUNNY;
        this.weatherTomorrow = Weather.SUNNY;
        this.season = Season.SPRING;
        this.currentPlayer = null;
        this.GameStarterPlayer = null;
        this.secondPlayer = null;
        this.thirdPlayer = null;
        this.fourthPlayer = null;
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




    public Player getGameStarterPlayer() {
        return GameStarterPlayer;
    }

    public void setGameStarterPlayer(Player gameStarterPlayer) {
        GameStarterPlayer = gameStarterPlayer;
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


    public boolean isHasTurnCycleFinished() {
        return hasTurnCycleFinished;
    }

    public void setHasTurnCycleFinished(boolean hasTurnCycleFinished) {
        this.hasTurnCycleFinished = hasTurnCycleFinished;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Player getThirdPlayer() {
        return thirdPlayer;
    }


    public Player getFourthPlayer() {
        return fourthPlayer;
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

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void setThirdPlayer(Player thirdPlayer) {
        this.thirdPlayer = thirdPlayer;
    }

    public void setFourthPlayer(Player fourthPlayer) {
        this.fourthPlayer = fourthPlayer;
    }
}

