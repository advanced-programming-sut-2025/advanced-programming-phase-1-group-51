package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.MenuComands.Menu;
import Models.Enums.MenuComands.SignUpMenuCommands;
import Models.Game;
import Models.Result;
import Models.User;

public class SignUpMenuController extends Controller {


    public Result showCurrentMenu(){
        return new Result(true, Game.getCurrentMenu().name());
    }

    public Result register(String username, String password, String confirmPassword, String nickname, String email, String gender){

        for(User user : User.users){
            if(user.getUsername().equals(username)){
                return new Result(false, "this username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(username) == null) {
            return new Result(false, "username format is invalid!");
        }

        if (SignUpMenuCommands.PASSWORD.getMatcher(password) == null) {
            return new Result(false, "password format is invalid!");
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(email) == null) {
            return new Result(false, "email format is invalid!");
        }

        if (SignUpMenuCommands.GENDER.getMatcher(gender) == null) {
            return new Result(false, "gender can only be male or female!");
        }

        if (!password.equals(confirmPassword)) {
            return new Result(false, "confirmPassword does not match password");
        }

        User newUser = new User(username, password, nickname, email, gender);
        User.users.add(newUser);

        showSecurityQuestions();

        return new Result(true, "User registered successfully");
    }

    public void showSecurityQuestions(){
        System.out.println("Please pick a security question:");
        System.out.println("1. What was the name of your first pet?");
        System.out.println("2. What city were you born in?");
        System.out.println("3. What is your mother's name?");
    }

    public Result pickQuestion(int questionNumber, String answer, String confirmAnswer) {
        User currentUser = User.users.get(User.users.size() - 1); // Get the last registered user

        if (questionNumber < 1 || questionNumber > 3) {
            return new Result(false, "Invalid question number!");
        }

        if (!answer.equals(confirmAnswer)) {
            return new Result(false, "Answers don't match!");
        }

        currentUser.setSecurityQuestionNumber(questionNumber);
        currentUser.setSecurityAnswer(answer);

        return new Result(true, "Security question set successfully!");
    }

    public void exitMenu() {
        Game.setCurrentMenu(Menu.ExitMenu);
    }


}

