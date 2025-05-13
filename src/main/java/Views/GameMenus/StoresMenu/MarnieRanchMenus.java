package Views.GameMenus.StoresMenu;

import Controllers.StoresControllers.MarnieRanch;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarnieRanchMenus implements PlayMenu {
    private final Controllers.StoresControllers.MarnieRanch MarnieRanch = new MarnieRanch();
    @Override
    public void check(Scanner scanner) {
        String input = Game.scanner.nextLine();
        Matcher matcher;
        if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null){
            System.out.println(MarnieRanch.ShowAllProducts(matcher.group()));
        }
        else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input)) != null){
            System.out.println(MarnieRanch.ShowAllAvailableProducts(matcher.group("storeName")));
        }
        else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            String storeName = matcher.group("storeName");
            System.out.println(MarnieRanch.Purchase(storeName,name, count));
        }
        else if ((matcher = GameMenuCommands.SELL.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(MarnieRanch.Sell(name,count));
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
