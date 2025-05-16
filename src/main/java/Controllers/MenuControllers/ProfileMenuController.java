package Controllers.MenuControllers;

import Models.App;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Result;
import Models.User;
import Controllers.Services.UserService;

public class ProfileMenuController {

    private final UserService userService = new UserService();

    public Result showCurrentMenu() {
        return Result.success("Profile Menu");
    }

    public Result goToMain() {
        App.setCurrentMenu(Menu.MainMenu);
        return Result.success("You are now in main menu");
    }

    public void showCurrentUserInfo() {

        User currentUser = App.getCurrentUser();

        System.out.println("<<<<<    USER INFORMATION     >>>>>");
        System.out.println("USERNAME : " + currentUser.getUsername());
        System.out.println("NICKNAME : " + currentUser.getNickName());
        System.out.println("BEST SCORE : ");
        System.out.println("GAMES PLAYED : " + currentUser.getGamesPlayed());
    }


    public Result changeUsername(String newUsername) {

        User currentUser = App.getCurrentUser();

        if (newUsername.equals(currentUser.getUsername())) {
            return  Result.failure( "please enter a new username!");
        }

        for (User user : User.users) {
            if (user.getUsername().equals(newUsername)) {
                return  Result.failure( "this username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(newUsername) == null) {
            return  Result.failure( "new username format is invalid!");
        }

        currentUser.setUsername(newUsername);
        return userService.saveAllUsers()
                .combine(Result.success("Username changed to " + newUsername));
    }



    public Result changeNickname(String newNickname) {
        User currentUser = App.getCurrentUser();

        if (newNickname.equals(currentUser.getUsername())) {
            return  Result.failure( "please enter a new nickname!");
        }

        currentUser.setNickName(newNickname);
        return userService.saveAllUsers()
                .combine(Result.success("Nickname changed to " + newNickname));
    }


    public Result changeEmail(String newEmail) {

        User currentUser = App.getCurrentUser();

        if (newEmail.equals(currentUser.getEmail())) {
            return  Result.failure( "please enter a new email!");
        }

        for (User user : User.users) {
            if (user.getEmail().equals(newEmail)) {
                return  Result.failure( "this email is already used.");
            }
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(newEmail) == null) {
            return  Result.failure( "new email format is invalid!");
        }

        currentUser.setUsername(newEmail);
        return userService.saveAllUsers()
                .combine(Result.success("Email changed to " + newEmail));
    }



    public Result changePassword(String newPassword, String oldPassword) {

        User currentUser = App.getCurrentUser();

        if (!oldPassword.equals(currentUser.getUsername())) {
            return  Result.failure( "Old password is incorrect!");
        }

        if (newPassword.equals(currentUser.getPassword())) {
            return  Result.failure( "please enter a new email!");
        }

        if (SignUpMenuCommands.PASSWORD_LENGTH.getMatcher(newPassword) == null) {
            return  Result.failure( "new password must be longer that 8 characters!");
        }

        if (SignUpMenuCommands.PASSWORD_LETTERS.getMatcher(newPassword) == null) {
            return  Result.failure( "new password must contain at least one lowercase and one uppercase!");
        }

        if (SignUpMenuCommands.PASSWORD_NUMBERS.getMatcher(newPassword) == null) {
            return  Result.failure( "new password must contain at least one number!");
        }

        if (SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getMatcher(newPassword) == null) {
            return  Result.failure( "new password must contain at least one special character!");
        }

        currentUser.setPassword(newPassword);
        return userService.saveAllUsers()
                .combine(Result.success("Password changed successfully"));
    }

}
