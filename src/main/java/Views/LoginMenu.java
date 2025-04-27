package Views;

import Controllers.MenuControllers.LoginMenuController;
import Models.Enums.MenuCommands.LoginMenuCommands;
import Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements  PlayMenu{

    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(LoginMenuCommands.SHOW_CURRENT_MENU.getMatcher(input) != null){
            System.out.println(controller.showCurrentMenu());
        }
        else if(LoginMenuCommands.MENU_EXIT.getMatcher(input) != null){
            controller.exitMenu();
        }
        else if((matcher = LoginMenuCommands.LOGIN.getMatcher(input)) != null){
            System.out.println(controller.login(matcher.group("username").trim(),
                    matcher.group("password").trim(),matcher.group("stayLoggedIn").trim()));
        }
        else if((matcher = LoginMenuCommands.FORGET_PASSWORD.getMatcher(input)) != null){
            System.out.println(controller.forgetPassword(matcher.group("username").trim()));
        }
        else if((matcher = LoginMenuCommands.ANSWER.getMatcher(input)) != null){
            System.out.println(controller.checkAnswer(
                    matcher.group("answer").trim()));
        }
        else if((matcher = LoginMenuCommands.NEW_PASSWORD.getMatcher(input)) != null){
            System.out.println(controller.setNewPassword(matcher.group("newPassword").trim()));
        }
        else if( LoginMenuCommands.GO_TO_SIGNUP.getMatcher(input) != null){
            System.out.println(controller.goToSignUpMenu());
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
