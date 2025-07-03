package io.github.StardewValley.Models.Enums.Regexes;

public enum CheatCodesRegexes implements RegexInterface{

    TIME_CHEAT("cheat\\s+advance\\s+time\\s+(?<X>\\S+)h"),
    DATE_CHEAT("cheat\\s+advance\\s+date\\s+(?<X>\\S+)d"),
    THOR("cheat\\s+Thor\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    WEATHER_SET("cheat\\s+weather\\s+set\\s+(?<Type>\\S+)"),
    ENERGY_SET("energy\\s+set\\s+-v\\s+(?<value>\\S+)"),
    ENERGY_UNLIMITED("energy\\s+unlimited"),
    ADD_MONEY(""),
    SET_FRIENDSHIP("cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>\\S+)\\s+-c\\s+(?<amount>\\S+)"),
    ADD("cheat\\s+add\\s+(?<count>\\S+)\\s+dollars"),
    ADD_ITEM("cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-c\\s+(?<count>\\S+)"),;

    private final String pattern;

    CheatCodesRegexes(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
