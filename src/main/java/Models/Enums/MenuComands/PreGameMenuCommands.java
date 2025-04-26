package Models.Enums.MenuComands;

public enum PreGameMenuCommands implements Command {

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    GAME_NEW("^game\\s*new\\s*-u\\s*(?<username1>\\S*)(?:\\s+(?<username2>\\S*))?(?:\\s+(?<username3>\\S*))?(?:\\s+(?<extraInvalid>\\S*))?$"),
    GAME_MAP("game\\s*map\\s*(?<mapNumber>.+)"),
    LOAD_GAME("load\\s*game");

    private final String pattern;

    PreGameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
