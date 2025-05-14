package Views.GameMenus.StoresMenu;

import Controllers.Others.OthersController;
import Controllers.StoresControllers.BlackSmithShop;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BlacksmithShopMenus implements PlayMenu {

    private final BlackSmithShop BlackSmithShop = new BlackSmithShop();
    private final OthersController OthersController = new OthersController();
    @Override
    public void check(Scanner scanner) {
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null){
            System.out.println(BlackSmithShop.ShowAllProducts());
        }
        else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE_PRODUCTS.getMatcher(input)) != null){
            System.out.println(BlackSmithShop.ShowAllAvailableProducts());
        }
        else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(BlackSmithShop.Purchase(name, count));
        }
        else if ((matcher = GameMenuCommands.SELL.getMatcher(input)) != null){
            String name = matcher.group("productName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(OthersController.Sell(name,count));
        }
        else if ((matcher = GameMenuCommands.TOOLS_UPGRADE.getMatcher(input)) != null){
            String name = matcher.group("toolName").trim();
            System.out.println(BlackSmithShop.toolUpgrade(name));
        }
        else if ((matcher = GameMenuCommands.GET_OUT.getMatcher(input)) != null){
            System.out.println(BlackSmithShop.exitStore());
        }
        else{
            System.out.println("invalid command!");
        }

    }
}
