package Models.Enums.MenuCommands;

public enum ProfileMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    CHANGE_USERNAME("change\\s*username\\s*-u\\s*(?<username>.+)"),
    CHANGE_NICKNAME("change\\s*nickname\\s*-u\\s*(?<nickname>.+)"),
    CHANGE_EMAIL("change\\s*email\\s*-e\\s*(?<email>.+)"),
    CHANGE_PASSWORD("change\\s*password\\s*-p\\s*(?<newPassword>.+)\\s+-o\\s*(?<oldPassword>.+)"),
    USER_INFO("user\\s*info"),
    GO_TO_MAIN("go\\s+to\\s+main\\s+menu");
    private final String pattern;

    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
