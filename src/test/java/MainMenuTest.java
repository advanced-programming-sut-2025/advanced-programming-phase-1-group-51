import Models.Enums.MenuCommands.MainMenuCommands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuTest {

    @Test
    public void testShowCurrentMenu() {
        assertTrue("show current menu".matches(MainMenuCommands.SHOW_CURRENT_MENU.getPattern()));
        assertTrue("showcurrentmenu".matches(MainMenuCommands.SHOW_CURRENT_MENU.getPattern()));
    }

    @Test
    public void testLogout() {
        assertTrue("user logout".matches(MainMenuCommands.LOGOUT.getPattern()));
        assertTrue("userlogout".matches(MainMenuCommands.LOGOUT.getPattern()));
    }

    @Test
    public void testGoToAvatar() {
        assertTrue("menu enter avatar menu".matches(MainMenuCommands.GO_TO_AVATAR.getPattern()));
        assertTrue("menu enter avatarmenu".matches(MainMenuCommands.GO_TO_AVATAR.getPattern()));
    }

    @Test
    public void testGoToProfile() {
        assertTrue("menu enter profile menu".matches(MainMenuCommands.GO_TO_PROFILE.getPattern()));
        assertTrue("menu enter profilemenu".matches(MainMenuCommands.GO_TO_PROFILE.getPattern()));
    }

    @Test
    public void testGoToPregame() {
        assertTrue("menu enter pregame menu".matches(MainMenuCommands.GO_TO_PREGAME.getPattern()));
        assertTrue("menu enter pregamemenu".matches(MainMenuCommands.GO_TO_PREGAME.getPattern()));
    }
}
