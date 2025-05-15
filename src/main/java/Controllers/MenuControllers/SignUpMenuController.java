package Controllers.MenuControllers;

import Models.Enums.MenuCommands.Menu;
import Models.Enums.MenuCommands.SignUpMenuCommands;
import Models.Result;
import Models.User;

import java.security.SecureRandom;
import java.util.*;

import Models.App;
import Services.UserService;

public class SignUpMenuController {

    private final UserService userService = new UserService();

    public Result showCurrentMenu(){
        return Result.success("SignUp Menu");
    }

    public Result goToLoginMenu(){
        App.setCurrentMenu(Menu.LoginMenu);
        return Result.success("You are now in Login Menu");
    }

    public Result register(String username, String password, String confirmPassword, String nickname, String email, String gender){

        for(User user : User.users){
            if(user.getUsername().equals(username)){
                return  Result.failure( "this username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(username) == null) {
            return  Result.failure( "username format is invalid!");
        }

        if (SignUpMenuCommands.PASSWORD_LENGTH.getMatcher(password) == null) {
            return  Result.failure( "password must be longer that 8 characters!");
        }

        if (SignUpMenuCommands.PASSWORD_LETTERS.getMatcher(password) == null) {
            return  Result.failure( "password must contain at least one lowercase and one uppercase!");
        }

        if (SignUpMenuCommands.PASSWORD_NUMBERS.getMatcher(password) == null) {
            return  Result.failure( "password must contain at least one number!");
        }

        if (SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getMatcher(password) == null) {
            return  Result.failure( "password must contain at least one special character!");
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(email) == null) {
            return  Result.failure( "email format is invalid!");
        }

        if (SignUpMenuCommands.GENDER.getMatcher(gender) == null) {
            return  Result.failure( "gender can only be male or female!");
        }

        if (!password.equals(confirmPassword)) {
            return  Result.failure( "confirmPassword does not match password");
        }

        User newUser = new User(username, password, nickname, email, gender);
        User.users.add(newUser);

        // Save all users after registration
        Result saveResult = userService.saveAllUsers();
        if (!saveResult.isSuccessful()) {
            return saveResult;
        }

        showSecurityQuestions();
        return Result.success("User registered successfully");
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
            return  Result.failure( "Invalid question number!");
        }

        if (!answer.equals(confirmAnswer)) {
            return  Result.failure( "Answers don't match!");
        }

        currentUser.setSecurityQuestionNumber(questionNumber);
        currentUser.setSecurityAnswer(answer);

        // Save user data after setting security question
        return userService.saveAllUsers()
                .combine(Result.success("Security question set successfully!"));
    }

    private String randPass = "";

    public Result registerWithRandomPass(String username, String nickname, String email, String gender) {

        for (User user : User.users) {
            if (user.getUsername().equals(username)) {
                return  Result.failure( "This username is taken.");
            }
        }

        if (SignUpMenuCommands.USERNAME.getMatcher(username) == null) {
            return  Result.failure( "Username format is invalid!");
        }

        if (SignUpMenuCommands.EMAIL.getMatcher(email) == null) {
            return  Result.failure( "Email format is invalid!");
        }

        if (SignUpMenuCommands.GENDER.getMatcher(gender) == null) {
            return  Result.failure( "Gender can only be male or female!");
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Generate a strong password
            String generatedPassword = generateStrongPassword();
            System.out.println("Generated password: " + generatedPassword);
            System.out.println("Options:");
            System.out.println("1. Accept this password");
            System.out.println("2. Generate new password");
            System.out.println("3. Cancel registration");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Use the generated password
                    return register(username, generatedPassword, generatedPassword,
                            nickname, email, gender);
                case "2":
                    // Loop again to generate new password
                    break;
                case "3":
                    return Result.failure("Registration cancelled");
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3");
            }
        }
    }

    private String generateStrongPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String allChars = upper + lower + digits + special;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one of each required character type
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        // Fill remaining length (minimum 12 characters total)
        for (int i = 4; i < 12; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the characters
        char[] chars = password.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }


    public void exitMenu() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


}

