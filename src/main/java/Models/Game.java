package Models;

import Models.Buildings.House;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Enums.Types.ObjectsOnMapType.ForagingCropType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Friendships.Gift;
import Models.Friendships.Massage;
import Models.Items.Mineral;
import Models.Items.TreeSeed;
import Models.Maps.*;
import Models.Maps.Map;
import Models.ObjectsShownOnMap.*;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class Game {
    private final List<Trade> trades = new ArrayList<>();
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
    private static ArrayList<Massage> massages = new ArrayList<>();
    private static ArrayList<Gift> gifts = new ArrayList<>();
    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public Optional<Trade> getTradeById(UUID id) {
        return trades.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
    public List<Trade> getTradesByPlayer(Player player) {
        return trades.stream()
                .filter(t -> t.getSender().equals(player) ||(t.getReceiver().equals(player)))
                .collect(Collectors.toList());
    }
    public List<Trade> playertraderequests(Player player) {
        return trades.stream()
                .filter(t ->(t.getReceiver().equals(player) && t.getStatus().equals("PENDING")))
                .collect(Collectors.toList());
    }




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

    public List<Player> getAvailablePlayers(Player excludePlayer) {
        return players.stream().filter(p -> !p.equals(excludePlayer)).collect(Collectors.toList());
    }



    public static ArrayList<Gift> getGifts() {
        return gifts;
    }

    public static void AddGifts(Gift gift) {
        gifts.add(gift);
    }


    public static ArrayList<Massage> getMassages() {
        return massages;
    }

    public static void AddMassages(Massage massage) {
        massages.add(massage);
    }


    private void initializeMapObjects() {
        // Initialize village
        Village village = map.getVillage();


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
        // Set fixed position for farmhouse (different for each farm)
        Position housePos = getFarmHousePosition(farm.getFarmNumber());
        farm.getCells().add(new Cells(housePos, new HouseCell()));

        // Add some random trees (both regular and foraging)
        spawnRandomTrees(farm);

        // Add random foraging items
        spawnRandomForagingItems(farm);

        // Add random minerals
        spawnRandomMinerals(farm);
    }

    private Position getFarmHousePosition(int farmNumber) {
        // Define fixed positions for each farm's house
        switch (farmNumber) {
            case 1: return new Position(10, 10);  // NW farm
            case 2: return new Position(10, 90);  // NE farm
            case 3: return new Position(90, 10);  // SW farm
            case 4: return new Position(90, 90);  // SE farm
            default: return new Position(10, 10);
        }
    }

    private void spawnRandomTrees(Farm farm) {
        Random random = new Random();
        int treeCount = 10 + random.nextInt(10); // 10-20 trees

        for (int i = 0; i < treeCount; i++) {
            int x = random.nextInt(30); // Within farm bounds
            int y = random.nextInt(30);

            // 50% chance for regular tree, 50% for foraging tree
            if (random.nextBoolean()) {
                farm.getCells().add(new Cells(new Position(x, y), new Tree(TreeType.OAK_TREE))); // Regular tree
            } else {
                farm.getCells().add(new Cells(new Position(x, y), new ForagingTree()));
            }
        }
    }

    private void spawnRandomForagingItems(Farm farm) {
        Random random = new Random();
        Season currentSeason = this.season;

        // Spawn foraging crops
        for (int i = 0; i < 5 + random.nextInt(5); i++) {
            int x = random.nextInt(30);
            int y = random.nextInt(30);
            ForagingCropType randomCrop = getRandomForagingCrop(currentSeason);
            farm.getCells().add(new Cells(new Position(x, y), new ForagingCrop(randomCrop)));
        }

    }

    private ForagingCropType getRandomForagingCrop(Season season) {
        // Implement logic to return a random foraging crop type for the current season
        // Example:
        if (season == Season.SPRING) return ForagingCropType.SPRING_ONION;
        if (season == Season.SUMMER) return ForagingCropType.GRAPE;
        // ... other seasons
        return ForagingCropType.WILD_HORSERADISH;
    }

    private void spawnRandomMinerals(Farm farm) {
        Random random = new Random();
        for (int i = 0; i < 5 + random.nextInt(5); i++) {
            int x = random.nextInt(30);
            int y = random.nextInt(30);
            ForagingMineralType randomMineral = ForagingMineralType.values()[
                    random.nextInt(ForagingMineralType.values().length)
                    ];
            farm.getCells().add(new Cells(new Position(x, y), new MineralCell(randomMineral)));
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

