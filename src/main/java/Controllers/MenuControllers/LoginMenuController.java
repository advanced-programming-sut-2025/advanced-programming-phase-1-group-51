package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.MenuComands.Menu;
import Models.Enums.MenuComands.SignUpMenuCommands;
import Models.Game;
import Models.Result;
import Models.User;

public class LoginMenuController extends Controller {

    public Result showCurrentMenu() {
        return new Result(true, Game.getCurrentMenu().name());
    }

    public Result login(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist!");
        }
        if (!user.getPassword().equals(password)) {
            return new Result(false, "password is incorrect!");
        }
        Game.setCurrentUser(user);
        Game.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "User logged in successfully");
    }

    private String recoveringUsername;

    public Result forgetPassword(String username) {
        User user = findUserByUsername(username);
        if (user == null) {
            return new Result(false, "Username doesn't exist!");
        }

        this.recoveringUsername = username;
        return new Result(true, "Please answer your security question:\n" +
                getQuestionText(user.getSecurityQuestionNumber()));
    }


    public Result checkAnswer(String answer) {
        User user = findUserByUsername(recoveringUsername);
        if (user == null) {
            return new Result(false, "User not found!");
        }

        if (!user.getSecurityAnswer().equals(answer)) {
            return new Result(false, "Incorrect answer!");
        }

        return new Result(true, "Answer correct! Enter your new password:");
    }

    public Result setNewPassword(String newPassword) {
        User user = findUserByUsername(recoveringUsername);
        if (user == null) {
            return new Result(false, "User not found!");
        }

        if (SignUpMenuCommands.PASSWORD.getMatcher(newPassword) == null) {
            return new Result(false, "Password format is invalid!");
        }

        user.setPassword(newPassword);
        recoveringUsername = null; // Reset recovery state
        return new Result(true, "Password changed successfully!");
    }

    private String getQuestionText(int questionNumber) {
        switch (questionNumber) {
            case 1: return "1. What was the name of your first pet?";
            case 2: return "2. What city were you born in?";
            case 3: return "3. What is your mother's name?";
            default: return "Invalid question";
        }
    }

    public Result goToSignUpMenu() {
        Game.setCurrentMenu(Menu.SignUpMenu);
        return new Result(true, "You are now in Signup menu");
    }

    public Result setRandomPassword(String password) {
        return null;
    }


    private User findUserByUsername(String username) {

        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void exitMenu() {
        Game.setCurrentMenu(Menu.ExitMenu);
    }

}
