import Models.Enums.MenuCommands.ProfileMenuCommands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileMenuTests {

    @Test
    public void testShowCurrentMenu() {
        assertTrue("show current menu".matches(ProfileMenuCommands.SHOW_CURRENT_MENU.getPattern()));
        assertTrue("showcurrentmenu".matches(ProfileMenuCommands.SHOW_CURRENT_MENU.getPattern()));
    }

    @Test
    public void testChangeUsername() {
        String validCommand = "change username -u newusername";
        assertTrue(validCommand.matches(ProfileMenuCommands.CHANGE_USERNAME.getPattern()));

        String invalid = "changeusername -u newusername";
        assertFalse(invalid.matches(ProfileMenuCommands.CHANGE_USERNAME.getPattern()));
    }

    @Test
    public void testChangeNickname() {
        String validCommand = "change nickname -u newnickname";
        assertTrue(validCommand.matches(ProfileMenuCommands.CHANGE_NICKNAME.getPattern()));

        String invalid = "changenickname -u newnickname";
        assertFalse(invalid.matches(ProfileMenuCommands.CHANGE_NICKNAME.getPattern()));
    }

    @Test
    public void testChangeEmail() {
        String validCommand = "change email -e new@email.com";
        assertTrue(validCommand.matches(ProfileMenuCommands.CHANGE_EMAIL.getPattern()));

        String invalid = "changeemail -e new@email.com";
        assertFalse(invalid.matches(ProfileMenuCommands.CHANGE_EMAIL.getPattern()));
    }

    @Test
    public void testChangePassword() {
        String validCommand = "change password -p newPass123! -o oldPass";
        assertTrue(validCommand.matches(ProfileMenuCommands.CHANGE_PASSWORD.getPattern()));

        String missingField = "change password -p newPass123!";
        assertFalse(missingField.matches(ProfileMenuCommands.CHANGE_PASSWORD.getPattern()));
    }

    @Test
    public void testUserInfo() {
        assertTrue("user info".matches(ProfileMenuCommands.USER_INFO.getPattern()));
        assertTrue("userinfo".matches(ProfileMenuCommands.USER_INFO.getPattern()));
    }

    @Test
    public void testGoToMain() {
        assertTrue("go to main menu".matches(ProfileMenuCommands.GO_TO_MAIN.getPattern()));
        assertFalse("go to mainmenu".matches(ProfileMenuCommands.GO_TO_MAIN.getPattern()));
    }
}
