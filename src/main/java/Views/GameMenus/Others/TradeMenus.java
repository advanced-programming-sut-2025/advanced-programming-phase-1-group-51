package Views.GameMenus.Others;

import Controllers.Activity.Trading;
import Models.App;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenus implements PlayMenu {

    private final Controllers.Activity.Trading Trading = new Trading();
    @Override
    public void check(Scanner scanner) {
        String input = App.scanner.nextLine();
        Matcher matcher;

        if ((matcher = GameMenuCommands.TRADE.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            String type = matcher.group("type").trim();
            String item = matcher.group("item").trim();
            int amount = Integer.parseInt(matcher.group("amount").trim());
            System.out.println(Trading.TradeWithUser(username, type, item, amount));
        }
        else if ((matcher = GameMenuCommands.TRADE_LIST.getMatcher(input)) != null){
            System.out.println(Trading.TradeList());
        }
        else if ((matcher = GameMenuCommands.TRADE_RESPONSE.getMatcher(input)) != null){
            String response = matcher.group("response").trim();
            int id = Integer.parseInt(matcher.group("id").trim());
            System.out.println(Trading.TradeResponse(response, id));
        }
        else if ((matcher = GameMenuCommands.TRADE_HISTORY.getMatcher(input)) != null){
            System.out.println(Trading.TradeHistory());
        }
    }
}
