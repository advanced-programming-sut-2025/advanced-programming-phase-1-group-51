import Models.Enums.MenuCommands.LoginMenuCommands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginMenuTests {

    @Test
    public void testShowCurrentMenu() {
        assertTrue("show current menu".matches(LoginMenuCommands.SHOW_CURRENT_MENU.getPattern()));
        assertFalse("showcurrentmenu".matches(LoginMenuCommands.SHOW_CURRENT_MENU.getPattern()));
    }

    @Test
    public void testMenuExit() {
        assertTrue("menu exit".matches(LoginMenuCommands.MENU_EXIT.getPattern()));
        assertFalse("menuexit".matches(LoginMenuCommands.MENU_EXIT.getPattern()));
    }

    @Test
    public void testLogin() {
        String basicLogin = "login -u john -p password";
        assertTrue(basicLogin.matches(LoginMenuCommands.LOGIN.getPattern()));

        String withStayLoggedIn = "login -u john -p password --stayLoggedIn";
        assertTrue(withStayLoggedIn.matches(LoginMenuCommands.LOGIN.getPattern()));

        String invalid = "login -u john";
        assertFalse(invalid.matches(LoginMenuCommands.LOGIN.getPattern()));
    }

    @Test
    public void testForgetPassword() {
        String validCommand = "forget password -u john";
        assertTrue(validCommand.matches(LoginMenuCommands.FORGET_PASSWORD.getPattern()));

        String invalid = "forgetpassword -u john";
        assertFalse(invalid.matches(LoginMenuCommands.FORGET_PASSWORD.getPattern()));
    }

    @Test
    public void testAnswer() {
        String validCommand = "answer -a myanswer";
        assertTrue(validCommand.matches(LoginMenuCommands.ANSWER.getPattern()));

        String invalid = "answer myanswer";
        assertFalse(invalid.matches(LoginMenuCommands.ANSWER.getPattern()));
    }

    @Test
    public void testNewPassword() {
        String validCommand = "new password : newPass123!";
        assertTrue(validCommand.matches(LoginMenuCommands.NEW_PASSWORD.getPattern()));

        String invalid = "newpassword: newPass123!";
        assertFalse(invalid.matches(LoginMenuCommands.NEW_PASSWORD.getPattern()));
    }

    @Test
    public void testGoToSignup() {
        assertTrue("go to signup menu".matches(LoginMenuCommands.GO_TO_SIGNUP.getPattern()));
        assertFalse("go tosignupmenu".matches(LoginMenuCommands.GO_TO_SIGNUP.getPattern()));
    }
}
