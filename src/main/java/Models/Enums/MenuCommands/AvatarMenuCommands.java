package Models.Enums.MenuCommands;

public enum AvatarMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    GO_TO_MAIN("go\\s+to\\s+main\\s+menu");
    private final String pattern;

    AvatarMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
