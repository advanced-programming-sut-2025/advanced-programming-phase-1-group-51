package Views;

import Controllers.MenuControllers.SignUpMenuController;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenu implements PlayMenu{

    private final SignUpMenuController controller = new SignUpMenuController();

    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(SignUpMenuCommands.SHOW_CURRENT_MENU.getMatcher(input) != null){
            System.out.println(controller.showCurrentMenu());
        }
        else if(SignUpMenuCommands.MENU_EXIT.getMatcher(input) != null){
            controller.exitMenu();
        }
        else if((matcher = SignUpMenuCommands.REGISTER.getMatcher(input)) != null){
            System.out.println(controller.register(matcher.group("username").trim(),
                    matcher.group("password").trim(), matcher.group("passwordConfirm").trim(),
                    matcher.group("nickname").trim(),matcher.group("email").trim(),matcher.group("gender").trim()));
        }
        else if((matcher = SignUpMenuCommands.REGISTER_RANDOM_PASS.getMatcher(input)) != null){
            System.out.println(controller.registerWithRandomPass(matcher.group("username").trim(),
                    matcher.group("nickname").trim(),matcher.group("email").trim(),matcher.group("gender").trim()));
        }
        else if((matcher = SignUpMenuCommands.PICK_QUESTION.getMatcher(input)) != null){
            System.out.println(controller.pickQuestion(Integer.parseInt(matcher.group("questionNumber").trim()),
                    matcher.group("answer").trim(), matcher.group("answerConfirm").trim()));
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
