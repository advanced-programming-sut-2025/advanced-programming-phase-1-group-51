package Models.Enums.MenuComands;

public enum LoginMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    MENU_EXIT("menu\\s*exit"),
    LOGIN("login\\s*-u\\s*(?<username>.+)\\s*-p\\s*(?<password>.+)\\s*–stay-logged-in"),
    FORGET_PASSWORD("forget\\s*password\\s*-u\\s*(?<username>.+)"),
    ANSWER("answer\\s*-a\\s*(?<answer>.+)"),
    NEW_PASSWORD("new\\s*password\\s*:\\s*(?<newPassword>.+)\\s*"),
    GO_TO_SIGNUP("go\\s*to\\s*signup\\s*menu");

    private final String pattern;

    LoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
