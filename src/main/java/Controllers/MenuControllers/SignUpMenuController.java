package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Game;
import Models.Result;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SignUpMenuController extends Controller {


    public Result showCurrentMenu(){
        return new Result(true, "SignUp Menu");
    }

    public Result goToLoginMenu(){
        Game.setCurrentMenu(Menu.LoginMenu);
        return new Result(true,"You are now in Login Menu");
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

        if (SignUpMenuCommands.PASSWORD_LENGTH.getMatcher(password) == null) {
            return new Result(false, "password must be longer that 8 characters!");
        }

        if (SignUpMenuCommands.PASSWORD_LETTERS.getMatcher(password) == null) {
            return new Result(false, "password must contain at least one lowercase and one uppercase!");
        }

        if (SignUpMenuCommands.PASSWORD_NUMBERS.getMatcher(password) == null) {
            return new Result(false, "password must contain at least one number!");
        }

        if (SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getMatcher(password) == null) {
            return new Result(false, "password must contain at least one special character!");
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

    private String randPass = "";

    public Result registerWithRandomPass(String username, String nickname, String email, String gender) {

        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return new Result(false, "This username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(username) == null) {
            return new Result(false, "Username format is invalid!");
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(email) == null) {
            return new Result(false, "Email format is invalid!");
        }

        if (SignUpMenuCommands.GENDER.getMatcher(gender) == null) {
            return new Result(false, "Gender can only be male or female!");
        }

        // Keep generating passwords until user accepts
        while (true) {
            randPass = generateRandomPassword();

            // Ask user if they want to accept this password
            Result promptResult = new Result(
                    true,
                    "Your new random password is: " + randPass + "\n" +
                            "Do you want to register with it? (Yes/No to generate new password)"
            );

            // In a real application, you would get user input here
            // For this example, we'll simulate the user saying "Yes" after 3 attempts
            // Replace this with actual user input logic in your implementation
            String userResponse = Game.scanner.nextLine().trim(); // This would be your input method

            if (userResponse.equalsIgnoreCase("Yes")) {
                // Create and add the user
                User newUser = new User(username, randPass, nickname, email, gender);
                User.users.add(newUser);
                showSecurityQuestions();
                return new Result(true, "User registered successfully with generated password");
            }
            else if (userResponse.equalsIgnoreCase("No")) {
                continue; // Generate new password
            }
            else {
                return new Result(false, "Invalid input. Please enter 'Yes' or 'No'");
            }
        }
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


    public static String generateRandomPassword() {
        // Define character sets
        final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String NUMBERS = "0123456789";
        final String SPECIAL_CHARS = "?><,\"';;:/|[]}{+=)(*&^%$#!";
        final int MIN_LENGTH = 8; // You can change this minimum length

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        // Ensure at least one of each required character type
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Combine all characters for remaining random selection
        String allChars = LOWERCASE + UPPERCASE + NUMBERS + SPECIAL_CHARS;

        // Fill remaining length with random characters (minimum 4 more to reach 8)
        for (int i = 4; i < MIN_LENGTH; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the characters to randomize positions
        List<Character> chars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars);

        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }

        return result.toString();
    }

    public void exitMenu() {
        Game.setCurrentMenu(Menu.ExitMenu);
    }


}

