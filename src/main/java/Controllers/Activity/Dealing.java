package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.MenuCommands.Menu;

import static Models.Game.season;

public class Dealing extends Controller {


    public Result enterStore(String name) {
        User user =  Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        if(!player.isInVillage()){
            return new Result(false, "You are not in the village");
        }
        boolean check = false;
        if (name.compareToIgnoreCase("Blacksmith") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(Game.getCurrentDateAndTime())) {
                check = true;
            }
        } else if (name.compareToIgnoreCase("JojaMart") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                check = true;
            }
        } else if (name.compareToIgnoreCase("Pierre's General Store") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                check = true;
            }
        } else if (name.compareToIgnoreCase("Carpenter's Shop") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                check = true;
            }
        } else if (name.compareToIgnoreCase("Fish Shop") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                check = true;
            }
        } else if (name.compareToIgnoreCase("The Stardrop Saloon") == 0) {
            Store store = game.getMap().getVillage().getStore(name);
            if (store.isOpen(game.getDate().getHour())) {
                check = true;
            }
        } else {
            return new Result(false, "Invalid shop name");
        }
        if (!check) {
            return new Result(false, "Shop is closed now");
        }
        return new Result(true, "You are now in" + name);
    }

    public Result ShowAllProducts(String StoreName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Store store = game.getMap().getVillage().getStore(StoreName);
        StringBuilder output = new StringBuilder();
        
        for (StoreProduct product : store.getProducts()) {
            output.append("name ").append(": ").append(product.getType().getName()).append("\n");
            output.append("price ").append(": ");
            if(product.getType().isInSeason(Game.getSeason())){
                output.append(product.getType().getPrice());
            }else {
                output.append(product.getType().getOutOfSeasonPrice());
            }
            output.append("\n");
        }
        return new Result(true, output.toString());
    }

    public Result ShowAllAvailableProducts() {
        return null;
    }

    public Result Purchase(String productName, int count) {
        return null;
    }

    public Result Sell(String productName, int count) {
        return null;
    }

    public Result cheatAddMoney(int count) {
        return null;
    }

    public Result exitStore() {
        return null;
    }


}
