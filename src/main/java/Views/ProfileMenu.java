package Views;

import Controllers.MenuControllers.ProfileMenuController;
import Models.Enums.MenuCommands.ProfileMenuCommands;
import Models.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements  PlayMenu {


    private final ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void check(Scanner scanner){
        String input = Game.scanner.nextLine();
        Matcher matcher;

        if(ProfileMenuCommands.SHOW_CURRENT_MENU.getMatcher(input) != null){
            System.out.println(controller.showCurrentMenu());
        }
        else if((matcher = ProfileMenuCommands.CHANGE_USERNAME.getMatcher(input)) != null){
            System.out.println(controller.changeUsername(matcher.group("username").trim()));
        }
        else if((matcher = ProfileMenuCommands.CHANGE_NICKNAME.getMatcher(input)) != null){
            System.out.println(controller.changeNickname(matcher.group("nickname").trim()));
        }
        else if((matcher = ProfileMenuCommands.CHANGE_EMAIL.getMatcher(input)) != null){
            System.out.println(controller.changeEmail(matcher.group("email").trim()));
        }
        else if((matcher = ProfileMenuCommands.CHANGE_PASSWORD.getMatcher(input)) != null){
            System.out.println(controller.changePassword(matcher.group("newPassword").trim(), matcher.group("oldPassword").trim()));
        }
        else if(ProfileMenuCommands.USER_INFO.getMatcher(input) != null){
            controller.showCurrentUserInfo();
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
