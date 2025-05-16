import Models.Enums.MenuCommands.SignUpMenuCommands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterMenuTests {

    @Test
    public void testShowCurrentMenu() {
        assertTrue("show current menu".matches(SignUpMenuCommands.SHOW_CURRENT_MENU.getPattern()));
        assertFalse("showcurrentmenu".matches(SignUpMenuCommands.SHOW_CURRENT_MENU.getPattern()));
    }

    @Test
    public void testMenuExit() {
        assertTrue("menu exit".matches(SignUpMenuCommands.MENU_EXIT.getPattern()));
        assertFalse("menuexit".matches(SignUpMenuCommands.MENU_EXIT.getPattern()));
    }

    @Test
    public void testRegister() {
        String validCommand = "register -u john_doe -p Pass123! -p Pass123! -n John -e john@example.com -g male";
        assertTrue(validCommand.matches(SignUpMenuCommands.REGISTER.getPattern()));

        String missingField = "register -u john_doe -p Pass123! -p Pass123! -n John -e john@example.com";
        assertFalse(missingField.matches(SignUpMenuCommands.REGISTER.getPattern()));
    }

    @Test
    public void testRegisterRandomPass() {
        String validCommand = "register -u john_doe -r random pass -n John -e john@example.com -g female";
        assertTrue(validCommand.matches(SignUpMenuCommands.REGISTER_RANDOM_PASS.getPattern()));

        String invalidCommand = "register -u john_doe -r pass -n John -e john@example.com -g female";
        assertFalse(invalidCommand.matches(SignUpMenuCommands.REGISTER_RANDOM_PASS.getPattern()));
    }

    @Test
    public void testGoToLogin() {
        assertTrue("go to login menu".matches(SignUpMenuCommands.GO_TO_LOGIN.getPattern()));
        assertTrue("go to loginmenu".matches(SignUpMenuCommands.GO_TO_LOGIN.getPattern()));
    }

    @Test
    public void testUsernamePattern() {
        assertTrue("john123".matches(SignUpMenuCommands.USERNAME.getPattern()));
        assertTrue("john-doe".matches(SignUpMenuCommands.USERNAME.getPattern()));
        assertFalse("john@doe".matches(SignUpMenuCommands.USERNAME.getPattern()));
    }

    @Test
    public void testPasswordPatterns() {
        // Length
        assertTrue("12345678".matches(SignUpMenuCommands.PASSWORD_LENGTH.getPattern()));
        assertFalse("1234567".matches(SignUpMenuCommands.PASSWORD_LENGTH.getPattern()));

        // Letters
        assertTrue("Pass".matches(SignUpMenuCommands.PASSWORD_LETTERS.getPattern()));
        assertFalse("pass".matches(SignUpMenuCommands.PASSWORD_LETTERS.getPattern()));

        // Numbers
        assertTrue("Pass1".matches(SignUpMenuCommands.PASSWORD_NUMBERS.getPattern()));
        assertFalse("Pass".matches(SignUpMenuCommands.PASSWORD_NUMBERS.getPattern()));

        // Special chars
        assertTrue("Pass!".matches(SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getPattern()));
        assertFalse("Pass1".matches(SignUpMenuCommands.PASSWORD_SPECIAL_CHARACTERS.getPattern()));
    }

    @Test
    public void testEmailPattern() {
        assertTrue("john@example.com".matches(SignUpMenuCommands.EMAIL.getPattern()));
        assertTrue("john.doe@example.co.uk".matches(SignUpMenuCommands.EMAIL.getPattern()));
        assertFalse("@example.com".matches(SignUpMenuCommands.EMAIL.getPattern()));
    }

    @Test
    public void testGenderPattern() {
        assertTrue("male".matches(SignUpMenuCommands.GENDER.getPattern()));
        assertTrue("female".matches(SignUpMenuCommands.GENDER.getPattern()));
        assertFalse("other".matches(SignUpMenuCommands.GENDER.getPattern()));
    }

    @Test
    public void testPickQuestion() {
        String validCommand = "pick question -q 1 -a answer -c answer";
        assertTrue(validCommand.matches(SignUpMenuCommands.PICK_QUESTION.getPattern()));

        String mismatch = "pick question -q 1 -a answer -c different";
        assertTrue(mismatch.matches(SignUpMenuCommands.PICK_QUESTION.getPattern())); // Still matches pattern, just values differ
    }
}
