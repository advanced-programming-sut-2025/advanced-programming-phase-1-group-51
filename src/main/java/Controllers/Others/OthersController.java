package Controllers.Others;

import Controllers.BaseController;
import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Quality;
import Models.Enums.Others.Weather;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ObjectsOnMapType.ForagingTreeType;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.BurntCell;
import Models.ObjectsShownOnMap.Crop;
import Models.ObjectsShownOnMap.Lake;
import Models.ObjectsShownOnMap.Tree;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OthersController  extends BaseController {


    public Result BuildBuilding(String buildingName, int x, int y){
        return null;
    }


    public Result CheatThor(int x, int y){
        Game game = App.getCurrentUser().getCurrentGame();
        Farm farm = game.getCurrentPlayer().getFarm();
            Cells cell = farm.findCellFarm(x, y);
            if (cell != null) {
                if (cell.getObjectOnCell() instanceof Tree) {
                    cell.setObjectOnCell(new Tree(ForagingTreeType.BURNT_TREE));
                }
                if (cell.getObjectOnCell() instanceof Crop) {
                    cell.setObjectOnCell(new BurntCell());
                }
            }
            return saveGameState(game)
                .combine(Result.success( "thor has hit to position: " + x + ", " + y));
    }



    public Result showMoney() {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        return saveGameState(game)
                .combine(Result.success( "player's money is : " + player.getMoney()));
    }


    public Result cheatAdvanceTime(int amountOfHours) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
//        LocalDateTime currentDateTime = App.getCurrentUser().getCurrentGame().getDate();
//        LocalDateTime nextDateTime;
//        Game currentGame = App.getCurrentUser().getCurrentGame();
//        int howManyDays = amountOfHours / 24;
//        int howManyHours = amountOfHours % 24;
//        int howManyMonths = howManyDays / 28;
//        howManyDays %= 28;
//        int currentHour = currentDateTime.getHour();
//        int currentDay = currentDateTime.getDayOfMonth();
//        if (howManyHours + currentHour > 22) {
//            howManyHours = 22 - currentHour;
//        }
//        if (howManyDays + currentDay > 28) {
//            howManyMonths++;
//            howManyDays -= 28;
//        }
//        nextDateTime = currentDateTime.plusDays(howManyDays);
//        nextDateTime = nextDateTime.plusHours(howManyHours);
//        nextDateTime = nextDateTime.plusMonths(howManyMonths);
//        boolean check = nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0
//                || nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0;
//        currentGame.setDate(nextDateTime);
//        currentGame.checkSeasonChange();
//        if (check) {
//            currentGame.newDayBackgroundChecks();
//        }
        return saveGameState(game)
                .combine(Result.success( "time changed successfully."));
    }

    public Result cheatAdvanceDate(int amountOfDays) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
//        LocalDateTime currentDateTime = App.getCurrentUser().getCurrentGame().getDate();
//        LocalDateTime nextDateTime;
//        Game currentGame = App.getCurrentUser().getCurrentGame();
//        int howManyDays = amountOfDays % 28;
//        int howManyMonths = amountOfDays / 28;
//        int currentDay = currentDateTime.getDayOfMonth();
//        if (howManyDays + currentDay > 28) {
//            howManyMonths++;
//            howManyDays -= 28;
//        }
//        nextDateTime = currentDateTime.plusDays(howManyDays);
//        nextDateTime = nextDateTime.plusMonths(howManyMonths);
//        boolean check = (nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0)
//                || (nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0);
//        currentGame.setDate(nextDateTime);
//        if (check) {
//            currentGame.newDayBackgroundChecks();
//        }
//        currentGame.checkSeasonChange();
        return saveGameState(game)
                .combine(Result.success( "Date changed successfully."));
    }


    public Result Time() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( App.getCurrentUser().getCurrentGame().getDate().toLocalTime().toString()));
    }

    public Result Date() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success(App.getCurrentUser().getCurrentGame().getDate().toLocalDate().toString()));
    }

    public Result DateTime() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( App.getCurrentUser().getCurrentGame()
                .getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")).toString()));
    }

    public Result DayOfTheWeek() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        LocalDateTime currentDateTime = App.getCurrentUser().getCurrentGame().getDate();
        int currentDay = currentDateTime.getDayOfMonth();
        int dayOfWeek = (currentDay - 1) % 7;
        String day = DayOfWeek.values()[dayOfWeek].toString().toLowerCase();
        return saveGameState(game)
                .combine(Result.success( day));
    }

    public Result season() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( App.getCurrentUser().getCurrentGame().getSeason().name()));
    }


    public Result weather() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( App.getCurrentUser().getCurrentGame().getWeatherToday().name()));
    }

    public Result weatherForecast() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( "Tomorrow's weather forecast is: "
                + App.getCurrentUser().getCurrentGame().getWeatherTomorrow().toString()));
    }

    public Result setWeatherCheat(String type) {
        Weather weather = Weather.getWeatherByName(type);
        Game game = App.getCurrentUser().getCurrentGame();
        if (weather == null) {
            return  Result.failure( "Weather type is invalid.");
        } else {
            game.setWeatherTomorrow(weather);
        }
        return saveGameState(game)
                .combine(Result.success( "Tomorrow's weather set successfully."));
    }

    public Result GreenhouseBuild() {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = player.getFarm();
        BackPack backpack = player.getInventory();

        Cells testCell = farm.findCellFarm(25, 4);

        if (testCell.getObjectOnCell() instanceof Lake) {
            return  Result.failure( "Greenhouse already built.");
        }

        Loot loot = backpack.findItemLoot(ElseType.WOOD.name);

        if (loot == null) {
            return  Result.failure( "You don't have any wood.");
        }

        if (loot.getCount() < 500) {
            return  Result.failure( "You don't have enough wood.");
        }

        if (player.getMoney() < 1000) {
            return  Result.failure( "You don't have enough money.");
        }

        loot.setCount(loot.getCount() - 500);

        if (loot.getCount() == 0) {
            backpack.getLoots().remove(loot);
        }

        player.setMoney(player.getMoney() - 1000);


        //Greenhouse runs from x : [22, 28] & y : [3, 10]
        for (int i = 23; i < 28; i++) {
            for (int j = 4; j < 10; j++) {
                Cells cell = farm.findCellFarm(i, j);
                cell.setObjectOnCell(new BurntCell());
            }
        }

        Cells cell = farm.findCellFarm(25, 10);
        cell.setObjectOnCell(new BurntCell());

        Cells cell1 = farm.findCellFarm(25, 4);
        cell1.setObjectOnCell(new Lake());


        return saveGameState(game)
                .combine(Result.success( "Greenhouse built successfully."));
    }


    public Result enterStore(String name) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (!player.isInVillage()) {
            return  Result.failure( "You are not in the village");
        }

        boolean open = false;
        if (name.equalsIgnoreCase("Blacksmith")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.BlacksmithShopMenu);
            }
        }

        else if (name.equalsIgnoreCase("JojaMart")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.JojaMartShopMenu);
            }
        }

        else if (name.equalsIgnoreCase("Pierre's General Store")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.PierreGeneralStoreMenu);
            }
        }

        else if (name.equalsIgnoreCase("Carpenter's Shop")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.CarpenterShopMenu);
            }
        }

        else if (name.equalsIgnoreCase("Fish Shop")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.FishShopMenu);
            }
        }

        else if (name.equalsIgnoreCase("The Stardrop Saloon")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.StardropSaloonMenu);
            }
        }

        else if (name.equalsIgnoreCase("Marnie's Ranch")) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                open = true;
                App.setCurrentMenu(Menu.MarnieRanchMenu);
            }
        }
        else {
            return  Result.failure( "this store does not exist");
        }

        if (!open) {
            return  Result.failure( "Store is closed now");
        }

        return saveGameState(game)
                .combine(Result.success( "You are now in " + name));
    }

    public Result Sell(String productName, int count) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Loot productLoot = player.getInventory().findItemLoot(productName);
        if (productLoot == null) {
            return  Result.failure( "You don't have this product in your inventory");
        }
        int x = productLoot.getCount();
        if(x < count){
            return  Result.failure("You don't have this amount in you inventory");
        }
        productLoot.setCount(x - count);
        if (productLoot.getCount() <= 0) {
            player.getInventory().removeLoot(productLoot);
        }

        double money = productLoot.getItem().getValue() * x;
        if (productLoot.getItem().getQuality() == Quality.SILVER) {
            money = money * 1.25;
        }
        else if (productLoot.getItem().getQuality() == Quality.GOLD) {
            money = money * 1.5;
        }
        else if (productLoot.getItem().getQuality() == Quality.IRIDIUM) {
            money = money * 2;
        }
        player.setMoney(player.getMoney() + (int) money);
        return saveGameState(game)
                .combine(Result.success("You have sold " + count + " of " + productName));
    }

    public Result cheatAddMoney(int amount) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setMoney(player.getMoney() + amount);
        return saveGameState(game)
                .combine(Result.success("you have " + player.getMoney() +" money now"));
    }


    public Result StartTrade() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        App.setCurrentMenu(Menu.TradeMenu);
        return saveGameState(game)
                .combine(Result.success("You are in Trade menu now"));
    }

}
