package Views.GameMenus.StoresMenu;

import Controllers.Others.OthersController;
import Controllers.StoresControllers.MarnieRanch;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarnieRanchMenus implements PlayMenu {
    private final Controllers.StoresControllers.MarnieRanch MarnieRanch = new MarnieRanch();
    private final Controllers.Others.OthersController OthersController = new OthersController();
    @Override
    public void check(Scanner scanner) {
        String input = Game.scanner.nextLine();
        Matcher matcher;
        if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null){
            System.out.println(MarnieRanch.ShowAllProducts());
        }
        else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input)) != null){
            System.out.println(MarnieRanch.ShowAllAvailableProducts());
        }
        else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(MarnieRanch.Purchase(name, count));
        }
        else if ((matcher = GameMenuCommands.SELL.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(OthersController.Sell(name,count));
        }
        else if ((matcher = GameMenuCommands.BUY_ANIMALS.getMatcher(input)) != null){
            String animalType = matcher.group("animal").trim();
            String name = matcher.group("name").trim();
            System.out.println(MarnieRanch.BuyAnimal(animalType, name));
        }
        else if ((matcher = GameMenuCommands.GET_OUT.getMatcher(input)) != null){
            System.out.println(MarnieRanch.exitStore());
        }
        else{
            System.out.println("invalid command!");
        }
    }
}
