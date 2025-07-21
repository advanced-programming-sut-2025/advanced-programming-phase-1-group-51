package io.github.StardewValley.Models.Enums.Regexes;

public enum CheatCodesRegexes implements RegexInterface{

    TIME_CHEAT("advance\\s+time\\s+(?<X>\\S+)h"),
    DATE_CHEAT("advance\\s+date\\s+(?<X>\\S+)d"),
    THOR("Thor\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    WEATHER_SET("weather\\s+set\\s+(?<Type>\\S+)"),
    ENERGY_SET("energy\\s+set\\s+-v\\s+(?<value>\\S+)"),
    ENERGY_UNLIMITED("energy\\s+unlimited"),
    ADD_MONEY("add\\s+money\\s+(?<value>\\S+)"),
    SET_FRIENDSHIP("set\\s+friendship\\s+-n\\s+(?<animalName>\\S+)\\s+-c\\s+(?<amount>\\S+)"),
    ADD("add\\s+(?<count>\\S+)\\s+dollars"),
    ADD_ITEM("add\\s+item\\s+-n\\s+(?<itemName>.+)\\s+-c\\s+(?<count>.+)"),;

    private final String pattern;

    CheatCodesRegexes(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
