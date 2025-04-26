package Views;

import Controllers.MenuControllers.PreGameMenuController;
import Models.Enums.MenuComands.PreGameMenuCommands;
import Models.Enums.MenuComands.ProfileMenuCommands;
import Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class PreGameMenu implements PlayMenu{


    private final PreGameMenuController controller = new PreGameMenuController();

    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(PreGameMenuCommands.SHOW_CURRENT_MENU.getMatcher(input) != null){
            System.out.println(controller.showCurrentMenu());
        }
        else if((matcher = PreGameMenuCommands.GAME_NEW.getMatcher(input)) != null){
            System.out.println(controller.newGame(matcher.group("username1").trim(),matcher.group("username2").trim(),
                    matcher.group("username3").trim(),matcher.group("username4").trim()));
        }
        else if((matcher = PreGameMenuCommands.GAME_MAP.getMatcher(input)) != null){
            System.out.println(controller.gameMap(Integer.parseInt(matcher.group("mapNumber").trim())));
        }
        else if(PreGameMenuCommands.LOAD_GAME.getMatcher(input) != null){
            System.out.println(controller.loadGame());
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
