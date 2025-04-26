package Models.Enums.MenuComands;

public enum MainMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    LOGOUT("user\\s*logout"),
    GO_TO_AVATAR("menu\\s*enter\\s*Avatar\\s*menu"),
    GO_TO_PROFILE("menu\\s*enter\\s*Profile\\s*menu"),
    GO_TO_PREGAME("menu\\s*enter\\s*Game\\s*menu");

    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
