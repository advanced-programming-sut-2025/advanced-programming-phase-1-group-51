package Models.Enums.MenuCommands;

public enum SignUpMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    MENU_EXIT("menu\\s+exit"),
    REGISTER("register\\s+-u\\s+(?<username>.+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>.+)\\s+-e\\s+(?<email>.+)\\s+-g\\s+(?<gender>.+)"),
    REGISTER_RANDOM_PASS("register\\s+-u\\s+(?<username>.+)\\s+-r\\s+random\\s+pass\\s+-n\\s+(?<nickname>.+)\\s+-e\\s+(?<email>.+)\\s+-g\\s+(?<gender>.+)"),
    GO_TO_LOGIN("go\\s+to\\s+login\\s*menu"),
    USERNAME("^[a-zA-Z0-9-]+$"),
    PASSWORD_LENGTH("^.{8,}$"),
    PASSWORD_LETTERS("^(?=.*[a-z])(?=.*[A-Z]).+$"),
    PASSWORD_NUMBERS("^.*[0-9].*$"),
    PASSWORD_SPECIAL_CHARACTERS("^(?=.*[?><,\"';:\\\\/|\\[\\]}{+=)(*&^%$#!]).+$"),
    EMAIL("^[A-Za-z0-9][A-Za-z0-9_.-]*[A-Za-z0-9]@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$"),
    GENDER("(male|female)"),
    PICK_QUESTION("pick\\s+question\\s+-q\\s+(?<questionNumber>.+)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerConfirm>.+)");

    private final String pattern;

    SignUpMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
