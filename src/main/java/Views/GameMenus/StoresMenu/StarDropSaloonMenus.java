package Views.GameMenus.StoresMenu;

import Controllers.StoresControllers.StarDropSaloon;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StarDropSaloonMenus implements PlayMenu {


    private final Controllers.StoresControllers.StarDropSaloon StarDropSaloon = new StarDropSaloon();
    @Override
    public void check(Scanner scanner) {
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.ShowAllProducts(matcher.group()));
        }
        else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.ShowAllAvailableProducts(matcher.group("storeName")));
        }
        else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            String storeName = matcher.group("storeName");
            System.out.println(StarDropSaloon.Purchase(storeName,name, count));
        }
        else if ((matcher = GameMenuCommands.SELL.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(StarDropSaloon.Sell(name,count));
        }
        else if ((matcher = GameMenuCommands.GET_OUT.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.exitStore());
        }
        else{
            System.out.println("invalid command!");
        }
    }
}
