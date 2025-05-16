package Views;

import Controllers.MenuControllers.PreGameMenuController;
import Models.App;
import Models.Enums.MenuCommands.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu implements PlayMenu {


    private final PreGameMenuController controller = new PreGameMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = App.scanner.nextLine();
        Matcher matcher;
        if ((GameMenuCommands.SHOW_CURRENT_MENU.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if (GameMenuCommands.GO_TO_MAIN.getMatcher(input) != null) {
            System.out.println(controller.goToMain());
        } else if ((matcher = GameMenuCommands.GAME_NEW.getMatcher(input)) != null) {
            System.out.println(controller.newGame(matcher.group("username1"), matcher.group("username2"),
                    matcher.group("username3"), matcher.group("extraInvalid")));
        } else if ((matcher = GameMenuCommands.GAME_MAP.getMatcher(input)) != null) {
            System.out.println(controller.gameMap(Integer.parseInt(matcher.group("mapNumber").trim())));
        } else if (GameMenuCommands.LOAD_GAME.getMatcher(input) != null) {
            System.out.println(controller.loadGame());
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
