package Views;

import Controllers.MenuControllers.GameMenuController;
import Controllers.Others.MovementAndMapController;
import Models.Enums.MenuComands.GameMenuCommands;
import Models.Enums.MenuComands.LoginMenuCommands;
import Models.Game;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements PlayMenu{


    private final GameMenuController GameMenuController = new GameMenuController();
    private final MovementAndMapController MovementAndMapController = new MovementAndMapController();
    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(GameMenuCommands.SHOW_CURRENT_MENU.getMatcher(input)!= null){
            System.out.println(GameMenuController.showCurrentMenu());
        }
        else if((matcher = GameMenuCommands.WALK.getMatcher(input)) != null){
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            System.out.println(MovementAndMapController.Walking(x, y));
        }
    }
}
