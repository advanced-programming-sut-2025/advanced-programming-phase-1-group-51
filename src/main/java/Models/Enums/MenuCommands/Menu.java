package Models.Enums.MenuCommands;

import Views.*;
import Views.GameMenus.Others.HouseMenu;
import Views.GameMenus.Others.TradeMenus;
import Views.GameMenus.StoresMenu.*;

import java.util.Scanner;

public enum Menu {

    SignUpMenu(new SignUpMenu()),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),
    AvatarMenu(new AvatarMenu()),
    GameMenu(new GameMenu()),
    BlacksmithShopMenu(new BlacksmithShopMenus()),
    JojaMartShopMenu(new JojaMartShopMenus()),
    PierreGeneralStoreMenu(new PierreGeneralStoreMenus()),
    CarpenterShopMenu(new CarpenterShopMenus()),
    FishShopMenu(new FishShopMenus()),
    StardropSaloonMenu(new StarDropSaloonMenus()),
    MarnieRanchMenu(new MarnieRanchMenus()),
    HouseMenu(new HouseMenu()),
    TradeMenu(new TradeMenus()),
    ExitMenu(new ExitMenu());

    private final PlayMenu menu;
    Menu(PlayMenu menu){
        this.menu = menu;
    }
    public void checkCommand(Scanner scanner){
        this.menu.check(scanner);
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        if (this == Menu.ExitMenu) {
            return "Exit Menu";
        } else if (this == Menu.AvatarMenu) {
            return "Avatar Menu";
        } else if (this == Menu.GameMenu) {
            return "Game Menu";
        } else if (this == Menu.MainMenu) {
            return "Main Menu";
        } else if (this == Menu.ProfileMenu) {
            return "Profile Menu";
        } else if (this == Menu.SignUpMenu) {
            return "Sign In Menu";
        } else if (this == Menu.CarpenterShopMenu) {
            return "Carpenter Shop Menu";
        } else if (this == Menu.MarnieRanchMenu) {
            return "Marnie Ranch Menu";
        } else if (this == Menu.BlacksmithShopMenu) {
            return "Blacksmith Shop Menu";
        } else if (this == Menu.JojaMartShopMenu) {
            return "Joja Mart Shop Menu";
        } else if (this == Menu.PierreGeneralStoreMenu) {
            return "Pierre General Store Menu";
        } else if (this == Menu.FishShopMenu) {
            return "Fish Shop Menu";
        } else if (this == Menu.StardropSaloonMenu) {
            return "The Stardrop Saloon Menu";
        } else if (this == Menu.HouseMenu) {
            return "Player Home Menu";
        } else if (this == Menu.TradeMenu) {
            return "Trade Menu";
        } else {
            return "";
        }
    }
}
