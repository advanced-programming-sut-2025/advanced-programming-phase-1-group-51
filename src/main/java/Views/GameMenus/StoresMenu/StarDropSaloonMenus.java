package Views.GameMenus.StoresMenu;

import Controllers.Others.OthersController;
import Controllers.StoresControllers.StarDropSaloon;
import Models.App;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StarDropSaloonMenus implements PlayMenu {


    private final Controllers.StoresControllers.StarDropSaloon StarDropSaloon = new StarDropSaloon();
    private final Controllers.Others.OthersController OthersController = new OthersController();
    @Override
    public void check(Scanner scanner) {
        String input = App.scanner.nextLine();
        Matcher matcher;

        if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.ShowAllProducts());
        }
        else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.ShowAllAvailableProducts());
        }
        else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(StarDropSaloon.Purchase(name, count));
        }
        else if ((matcher = GameMenuCommands.SELL.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(OthersController.Sell(name,count));
        }
        else if ((matcher = GameMenuCommands.GET_OUT.getMatcher(input)) != null){
            System.out.println(StarDropSaloon.exitStore());
        }
        else{
            System.out.println("invalid command!");
        }
    }
}
