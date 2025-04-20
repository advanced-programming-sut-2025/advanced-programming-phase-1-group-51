package Models.Enums.MenuComands;

public enum SignUpMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    MENU_EXIT("\\s*menu\\s*exit\\s*"),
    REGISTER("register\\s*-u\\s*(?<username>.+)\\s*-p\\s*(?<password>.+)\\s*(?<passwordConfirm>.+)\\s*-n\\s*(?<nickname>.+)\\s*-e\\s*(?<email>.+)\\s*-g\\s*(?<gender>.+)"),
    USERNAME("^[a-zA-Z0-9-]+$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[?><,\"';:\\|\\[\\]\\{\\}\\+=\\(\\)\\*&^%$#!]).{8,}$"),
    EMAIL("^[A-Za-z0-9][A-Za-z0-9_.-]*[A-Za-z0-9]@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$"),
    GENDER("(male|female)"),
    PICK_QUESTION("pick\\s*question\\s*-q\\s*(?<questionNumber>.+)\\s*-a\\s*(?<answer>.+)\\s*-c\\s*(?<answerConfirm>.+)"),
    QUESTION_NUMBER(""),
    ANSWER("");

    private final String pattern;

    SignUpMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
