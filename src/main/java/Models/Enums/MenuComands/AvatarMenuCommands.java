package Models.Enums.MenuComands;

public enum AvatarMenuCommands implements Command {

    SHOW_CURRENT_MENU("");
    private final String pattern;

    AvatarMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
