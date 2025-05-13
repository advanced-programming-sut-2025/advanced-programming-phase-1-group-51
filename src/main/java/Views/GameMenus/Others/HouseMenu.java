package Views.GameMenus.Others;

import Models.Game;
import Views.PlayMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class HouseMenu implements PlayMenu {

    private final Controllers.Activity.Dealing Dealing = new Dealing();
    @Override
    public void check(Scanner scanner) {
        String input = Game.scanner.nextLine();
        Matcher matcher;

    }
}
