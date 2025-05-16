package Models.Enums.MenuCommands;

public enum MainMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    LOGOUT("user\\s*logout"),
    GO_TO_AVATAR("menu\\s*enter\\s*avatar\\s*menu"),
    GO_TO_PROFILE("menu\\s*enter\\s*profile\\s*menu"),
    GO_TO_PREGAME("menu\\s*enter\\s*pregame\\s*menu");

    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
