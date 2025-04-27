package Views;

import Controllers.MenuControllers.MainMenuController;
import Models.Enums.MenuCommands.MainMenuCommands;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements PlayMenu{

    private final MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(SignUpMenuCommands.SHOW_CURRENT_MENU.getMatcher(input) != null){
            System.out.println(controller.showCurrentMenu());
        }
         else if(MainMenuCommands.LOGOUT.getMatcher(input) != null){
            System.out.println(controller.logout());
        }
        else if(MainMenuCommands.GO_TO_PROFILE.getMatcher(input) != null){
            System.out.println(controller.goToProfileMenu());
        }
        else if(MainMenuCommands.GO_TO_AVATAR.getMatcher(input) != null){
            System.out.println(controller.goToAvatarMenu());
        }
        else if(MainMenuCommands.GO_TO_PREGAME.getMatcher(input) != null){
            System.out.println(controller.goToGameMenu());
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
