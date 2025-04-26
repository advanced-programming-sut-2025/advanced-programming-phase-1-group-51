package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.MenuComands.SignUpMenuCommands;
import Models.Game;
import Models.Result;
import Models.User;

public class ProfileMenuController extends Controller {


    public Result showCurrentMenu() {
        return new Result(true, "Profile Menu");
    }



    public void showCurrentUserInfo() {

        User currentUser = Game.getCurrentUser();

        System.out.println("<<<<<    USER INFORMATION     >>>>>");
        System.out.println("USERNAME : " + currentUser.getUsername());
        System.out.println("NICKNAME : " + currentUser.getNickName());
        System.out.println("BEST SCORE : ");
        System.out.println("GAMES PLAYED : " + currentUser.getGamesPlayed());
    }


    public Result changeUsername(String newUsername) {

        User currentUser = Game.getCurrentUser();

        if (newUsername.equals(currentUser.getUsername())) {
            return new Result(false, "please enter a new username!");
        }

        for (User user : User.users) {
            if (user.getUsername().equals(newUsername)) {
                return new Result(false, "this username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(newUsername) == null) {
            return new Result(false, "new username format is invalid!");
        }

        currentUser.setUsername(newUsername);
        return new Result(true, "your username changed to " + newUsername + " successfully!");
    }




    public Result changeNickname(String newNickname) {
        User currentUser = Game.getCurrentUser();

        if (newNickname.equals(currentUser.getUsername())) {
            return new Result(false, "please enter a new nickname!");
        }

        currentUser.setNickName(newNickname);
        return new Result(true, "your username changed to " + newNickname + " successfully!");
    }




    public Result changeEmail(String newEmail) {

        User currentUser = Game.getCurrentUser();

        if (newEmail.equals(currentUser.getEmail())) {
            return new Result(false, "please enter a new email!");
        }

        for (User user : User.users) {
            if (user.getEmail().equals(newEmail)) {
                return new Result(false, "this email is already used.");
            }
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(newEmail) == null) {
            return new Result(false, "new email format is invalid!");
        }

        currentUser.setUsername(newEmail);
        return new Result(true, "your email changed to " + newEmail + " successfully!");
    }




    public Result changePassword(String newPassword, String oldPassword) {

        User currentUser = Game.getCurrentUser();

        if (!oldPassword.equals(currentUser.getUsername())) {
            return new Result(false, "Old password is incorrect!");
        }

        if (newPassword.equals(currentUser.getPassword())) {
            return new Result(false, "please enter a new email!");
        }

        if (SignUpMenuCommands.PASSWORD_LENGTH.getMatcher(newPassword) == null) {
            return new Result(false, "new password must be longer that 8 characters!");
        }

        if (SignUpMenuCommands.PASSWORD_LETTERS.getMatcher(newPassword) == null) {
            return new Result(false, "new password must contain at least one lowercase and one uppercase!");
        }

        if (SignUpMenuCommands.PASSWORD_NUMBERS.getMatcher(newPassword) == null) {
            return new Result(false, "new password must contain at least one number!");
        }

        if (SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getMatcher(newPassword) == null) {
            return new Result(false, "new password must contain at least one special character!");
        }

        currentUser.setPassword(newPassword);
        return new Result(true, "your password changed to " + newPassword + " successfully!");
    }

}
