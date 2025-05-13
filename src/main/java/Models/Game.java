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
    public static ArrayList<Player> players = new ArrayList<>();
    private static Menu currentMenu = Menu.SignUpMenu;
    private static User currentUser = null;
    private static boolean isGameOver = false;
    private static Season season = Season.SPRING;
    private static Map map;
    private static Weather weather = Weather.SUNNY;
    private LocalDateTime date;
    private Weather weatherToday;
    private Weather weatherTomorrow;
    private static Player currentPlayer;
    public boolean hasTurnCycleFinished;
    private static Player GameStarterPlayer;
    private static Player secondPlayer;
    private static Player thirdPlayer;
    private static Player fourthPlayer;


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

    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }
    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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

    public static Player getSecondPlayer() {
        return secondPlayer;
    }

    public static void setSecondPlayer(Player secondPlayer) {
        Game.secondPlayer = secondPlayer;
    }

    public static Player getThirdPlayer() {
        return thirdPlayer;
    }

    public static void setThirdPlayer(Player thirdPlayer) {
        Game.thirdPlayer = thirdPlayer;
    }

    public static Player getFourthPlayer() {
        return fourthPlayer;
    }

    public static void setFourthPlayer(Player fourthPlayer) {
        Game.fourthPlayer = fourthPlayer;
    }


}

