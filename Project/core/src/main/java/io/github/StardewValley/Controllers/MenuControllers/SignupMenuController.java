package io.github.StardewValley.Controllers.MenuControllers;

import io.github.StardewValley.Controllers.MenuControllers.LoginControllers.LoginMenuController;
import io.github.StardewValley.Models.Enums.Regexes.SignupRegexes;
import io.github.StardewValley.Models.User;
import io.github.StardewValley.Views.MenusBeforeGame.FirstMenu;
import io.github.StardewValley.Views.MenusBeforeGame.LoginMenus.LoginMenu;
import io.github.StardewValley.Views.MenusBeforeGame.SignupMenu;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Assets.GameAssetsManager;

import java.security.SecureRandom;

public class SignupMenuController {
    private SignupMenu view;
    public void setView(SignupMenu view) {
        this.view = view;
    }

    public void handleSignUpMenuButtons() {
        if (view != null) {
            if (view.getGoToLoginButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());

                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String confirmPassword = view.getConfirmPasswordField().getText();
                String nickName = view.getNicknameField().getText();
                String email = view.getEmailField().getText();
                String securityQuestion = view.getEmailField().getText();
                String securityAnswer = view.getSecurityAnswerField().getText();

                if (App.isUsernameTaken(username)) {
                    view.showError("this username is taken.");
                }

                else if (SignupRegexes.USERNAME.getMatcher(username) == null) {
                    view.showError("username format is invalid!");
                }

                else if (SignupRegexes.EMAIL.getMatcher(email) == null) {
                    view.showError( "email format is invalid!");
                }

                else if(nickName.isEmpty()){
                    view.showError( "nickname field cannot be empty!");
                }
                else if(validatePassword(password, confirmPassword)) {
                    User newUser = new User(username, password, nickName, email, "male", securityQuestion, securityAnswer);
                    App.getUsers().add(newUser);
                    navigateToLogin();
                }
            }
            if (view.getGeneratePasswordButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                String generatedPassword = generateStrongPassword();
                view.setGeneratedPassword(generatedPassword);
            }
            else if(view.getBackButton().isPressed()){
                Main.playSound(Main.getButtonClickSound());
                navigateToFirst();
            }
        }
    }


    private boolean validatePassword(String password, String confirmPassword) {
        if (SignupRegexes.PASSWORD_LENGTH.getMatcher(password) == null) {
                view.showError("password must be longer than 8 characters!");
            return false;
        }
        if (SignupRegexes.PASSWORD_LETTERS.getMatcher(password) == null) {
                view.showError("password must contain at least one lowercase and one uppercase!");
            return false;
        }
        if (SignupRegexes.PASSWORD_NUMBERS.getMatcher(password) == null) {
                view.showError("password must contain at least one number!");
            return false;
        }
        if (SignupRegexes.PASSWORD_SPECIALS.getMatcher(password) == null) {
                view.showError("password must contain at least one special character!");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            view.showError( "confirmPassword does not match password");
            return false;
        }
        return true;
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


    public void navigateToLogin() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(
            new LoginMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }


    public void navigateToFirst() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new FirstMenu(
            new FirstMenuController(),
            GameAssetsManager.getInstance().getSkin()
        ));
    }

}
