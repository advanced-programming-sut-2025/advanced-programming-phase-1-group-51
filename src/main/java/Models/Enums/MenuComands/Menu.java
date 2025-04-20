package Models.Enums.MenuComands;

import Views.*;

import java.util.Scanner;

public enum Menu {

    SignUpMenu(new SignUpMenu()),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),
    AvatarMenu(new AvatarMenu()),
    PreGameMenu(new PreGameMenu()),
    GameMenu(new GameMenu()),
    ExitMenu(new ExitMenu());

    private final PlayMenu menu;
    Menu(PlayMenu menu){
        this.menu = menu;
    }
    public void checkCommand(Scanner scanner){
        this.menu.check(scanner);
    }
}
