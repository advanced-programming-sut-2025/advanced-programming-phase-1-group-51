package Controllers.Others;

import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Weather;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.BurntCell;
import Models.ObjectsShownOnMap.Lake;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OthersController {

    public Result CheatAdvanceTime(String input) {

        return null;
    }

    public Result CheatAdvanceDate(String input) {

        return null;
    }

    public Result showCurrentSeason() {
        return new Result(true, Game.getSeason().name());
    }

    public Result showCurrentWeather() {
        return new Result(true, Game.getWeather().name());
    }

    public Result weatherForecast(){

        return null;
    }

    // set tomorrow weather
    public Result cheatTomorrowWeatherSet(String weather){

        return  null;
    }

    public Result CheatThor(int x, int y){

        Game currentGame = Game.getCurrentUser().getCurrentGame();
        currentGame.getCurrentPlayer().getFarm().thor(x, y);
        return null;
    }

    public Result BuildBuilding(String buildingName, int x, int y){
        return null;
    }

    
    
    
    // estfgh


    public static Result showMoney() {
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        String money = String.valueOf(player.getMoney(game));
        return new Result(true, money);
    }

    public static Result Time() {
        Result Result = new Result();
        Result.setSuccess(true);
        Result.setMessage(Game.getCurrentUser().getCurrentGame().getDate().toLocalTime().toString());
        return Result;
    }

    public static Result Date() {
        Result Result = new Result();
        Result.setSuccess(true);
        Result.setMessage(Game.getCurrentUser().getCurrentGame().getDate().toLocalDate().toString());
        return Result;
    }

    public static Result DateTime() {
        Result Result = new Result();
        Result.setSuccess(true);
        Result.setMessage(Game.getCurrentUser().getCurrentGame()
                .getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")).toString());
        return Result;
    }

    public static Result DayOfTheWeek() {
        Result Result = new Result();
        Result.setSuccess(true);
        LocalDateTime currentDateTime = Game.getCurrentUser().getCurrentGame().getDate();
        int currentDay = currentDateTime.getDayOfMonth();
        int dayOfWeek = (currentDay - 1) % 7;
        Result.setMessage(DayOfWeek.values()[dayOfWeek].toString().toLowerCase());
        return Result;
    }

    public static Result handleCheatAdvanceTime(String input) {
        int amountOfHours = Integer.parseInt(request.body.get("X"));
        LocalDateTime currentDateTime = Game.getCurrentUser().getCurrentGame().getDate();
        LocalDateTime nextDateTime;
        Game currentGame = Game.getCurrentUser().getCurrentGame();
        int howManyDays = amountOfHours / 24;
        int howManyHours = amountOfHours % 24;
        int howManyMonths = howManyDays / 28;
        howManyDays %= 28;
        int currentHour = currentDateTime.getHour();
        int currentDay = currentDateTime.getDayOfMonth();
        if (howManyHours + currentHour > 22) {
            howManyHours = 22 - currentHour;
        }
        if (howManyDays + currentDay > 28) {
            howManyMonths++;
            howManyDays -= 28;
        }
        nextDateTime = currentDateTime.plusDays(howManyDays);
        nextDateTime = nextDateTime.plusHours(howManyHours);
        nextDateTime = nextDateTime.plusMonths(howManyMonths);
        boolean check = nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0
                || nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0;
        currentGame.setDate(nextDateTime);
        currentGame.checkSeasonChange();
        if (check) {
            currentGame.newDayBackgroundChecks();
        }
        return new Result(true, "Date and time set successfully.");
    }

    public static Result handleCheatAdvanceDate(int amountOfDays) {
        LocalDateTime currentDateTime = Game.getCurrentUser().getCurrentGame().getDate();
        LocalDateTime nextDateTime;
        Game currentGame = Game.getCurrentUser().getCurrentGame();
        int howManyDays = amountOfDays % 28;
        int howManyMonths = amountOfDays / 28;
        int currentDay = currentDateTime.getDayOfMonth();
        if (howManyDays + currentDay > 28) {
            howManyMonths++;
            howManyDays -= 28;
        }
        nextDateTime = currentDateTime.plusDays(howManyDays);
        nextDateTime = nextDateTime.plusMonths(howManyMonths);
        boolean check = (nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0)
                || (nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0);
        currentGame.setDate(nextDateTime);
        if (check) {
            currentGame.newDayBackgroundChecks();
        }
        currentGame.checkSeasonChange();
        return new Result(true, "Date set successfully.");
    }


    public static Result Season() {
        return new Result(true, Game.getCurrentUser().getCurrentGame().getSeason().toString());
    }


    public static Result weather() {
        return new Result(true, Game.getCurrentUser().getCurrentGame().getWeatherToday().toString());
    }

    public static Result WeatherForecast() {
        return new Result(true, "Tomorrow's weather forecast is: "
                + Game.getCurrentUser().getCurrentGame().getWeatherTomorrow().toString());
    }

    public static Result setWeatherCheat(String type) {
        Weather weather = Weather.getWeatherByName(type);
        Game game = Game.getCurrentUser().getCurrentGame();
        if (weather == null) {
            return new Result(false, "Weather type is invalid.");
        } else {
            game.setWeatherTomorrow(weather);
        }
        return new Result(true, "Tomorrow's weather set successfully.");
    }

    public static Result GreenhouseBuild() {
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = player.getFarm();
        BackPack backpack = player.getInventory();

        Cells testCell = farm.findCell(25, 4);

        if (testCell.getObjectOnCell() instanceof Lake) {
            return new Result(false, "Greenhouse already built.");
        }

        Loot loot = backpack.findItemLoot(ElseType.WOOD.name);

        if (loot == null) {
            return new Result(false, "You don't have any wood.");
        }

        if (loot.getCount() < 500) {
            return new Result(false, "You don't have enough wood.");
        }

        if (player.getMoney() < 1000) {
            return new Result(false, "You don't have enough money.");
        }

        loot.setCount(loot.getCount() - 500);

        if (loot.getCount() == 0) {
            backpack.getLoots().remove(loot);
        }

        player.setMoney(player.getMoney() - 1000);


        //Greenhouse runs from x : [22, 28] & y : [3, 10]
        for (int i = 23; i < 28; i++) {
            for (int j = 4; j < 10; j++) {
                Cells cell = farm.findCell(i, j);
                cell.setObjectOnCell(new BurntCell());
            }
        }

        Cells cell = farm.findCell(25, 10);
        cell.setObjectOnCell(new BurntCell());

        Cells cell1 = farm.findCell(25, 4);
        cell1.setObjectOnCell(new Lake());


        return new Result(true, "Greenhouse built successfully.");
    }


    public Result enterStore(String name) {
        Game game = Game.getCurrentUser().getCurrentGame();
        Player player = Game.getCurrentPlayer();
        if (!player.isInVillage()) {
            return new Result(false, "You are not in the village");
        }

        boolean open = false;
        if (name.compareToIgnoreCase("Blacksmith") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.BlacksmithShopMenu);
            }
        }

        else if (name.compareToIgnoreCase("JojaMart") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.JojaMartShopMenu);
            }
        }

        else if (name.compareToIgnoreCase("Pierre's General Store") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.PierreGeneralStoreMenu);
            }
        }

        else if (name.compareToIgnoreCase("Carpenter's Shop") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.CarpenterShopMenu);
            }
        }

        else if (name.compareToIgnoreCase("Fish Shop") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.FishShopMenu);
            }
        }

        else if (name.compareToIgnoreCase("The Stardrop Saloon") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.StardropSaloonMenu);
            }
        }

        else if (name.compareToIgnoreCase("Marnie's Ranch") == 0) {
            Store store = Game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                Game.setCurrentMenu(Menu.MarnieRanchMenu);
            }
        }
        else {
            return new Result(false, "this store does not exist");
        }

        if (!open) {
            return new Result(false, "Store is closed now");
        }

        return new Result(true, "You are now in " + name);
    }

    public Result cheatAddMoney(int amount) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setMoney(player.getMoney() + amount);
        return new Result(true,"you have " + player.getMoney() +" money now");
    }

}
