package Controllers.MenuControllers;

import Models.App;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Result;
import Models.User;
import Controllers.Services.UserService;

public class LoginMenuController {

    private final UserService userService = new UserService();
    private String recoveringUsername;

    public Result showCurrentMenu() {
        return Result.success("Login Menu");
    }



    public Result login(String username, String password, String loggedInFlag) {
        User user = findUserByUsername(username);
        if (user == null) {
            return Result.failure("Username doesn't exist!");
        }
        if (!user.getPassword().equals(password)) {
            return Result.failure("Password is incorrect!");
        }

        // Handle persistent login flag
        boolean stayLoggedIn = loggedInFlag != null && !loggedInFlag.isEmpty();
        user.setStayLoggedIn(stayLoggedIn);

        App.setCurrentUser(user);
        App.setCurrentMenu(Menu.MainMenu);

        // Save the active user if stay logged in is enabled
        if (stayLoggedIn) {
            Result saveResult = userService.saveActiveUser(user);
            if (!saveResult.isSuccessful()) {
                return saveResult;
            }
        }

        return Result.success("User logged in successfully" +
                (stayLoggedIn ? " (persistent session)" : ""));
    }

    public Result forgetPassword(String username) {
        User user = findUserByUsername(username);
        if (user == null) {
            return Result.failure("Username doesn't exist!");
        }

        this.recoveringUsername = username;
        return Result.success("Please answer your security question:\n" +
                getQuestionText(user.getSecurityQuestionNumber()));
    }


    public Result checkAnswer(String answer) {
        User user = findUserByUsername(recoveringUsername);
        if (user == null) {
            return  Result.failure( "User not found!");
        }

        if (!user.getSecurityAnswer().equals(answer)) {
            return  Result.failure( "Incorrect answer!");
        }

        return new Result(true, "Answer correct! Enter your new password:");
    }

    public Result setNewPassword(String newPassword) {
        User user = findUserByUsername(recoveringUsername);
        if (user == null) {
            return  Result.failure( "User not found!");
        }

        if (SignUpMenuCommands.PASSWORD_LENGTH.getMatcher(newPassword) == null) {
            return  Result.failure( "password must be longer that 8 characters!");
        }

        if (SignUpMenuCommands.PASSWORD_LETTERS.getMatcher(newPassword) == null) {
            return  Result.failure( "password must contain at least one lowercase and one uppercase!");
        }

        if (SignUpMenuCommands.PASSWORD_NUMBERS.getMatcher(newPassword) == null) {
            return  Result.failure( "password must contain at least one number!");
        }

        if (SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getMatcher(newPassword) == null) {
            return  Result.failure( "password must contain at least one special character!");
        }

        user.setPassword(newPassword);
        recoveringUsername = null; // Reset recovery state
        return userService.saveAllUsers()
                .combine(Result.success("Password changed successfully!"));
    }

    private String getQuestionText(int questionNumber) {
        switch (questionNumber) {
            case 1: return "1. What was the name of your first pet?";
            case 2: return "2. What city were you born in?";
            case 3: return "3. What is your father's name?";
            default: return "Invalid question";
        }
    }

    public Result goToSignUpMenu() {
        App.setCurrentMenu(Menu.SignUpMenu);
        return new Result(true, "You are now in Signup menu");
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
        App.setCurrentMenu(Menu.ExitMenu);
    }

}
