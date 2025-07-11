package io.github.StardewValley.Models.Enums.Regexes;

public enum SignupRegexes implements RegexInterface {
    USERNAME("^[a-zA-Z0-9-]+$"),
    PASSWORD_LENGTH("^.{8,}$"),
    PASSWORD_LETTERS("^(?=.*[a-z])(?=.*[A-Z]).+$"),
    PASSWORD_NUMBERS("^.*[0-9].*$"),
    PASSWORD_SPECIALS("^(?=.*[?><,\"';:\\\\/|\\[\\]}{+=)(*&^%$#!]).+$"),
    EMAIL("^[A-Za-z0-9][A-Za-z0-9_.-]*[A-Za-z0-9]@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$"),
    GENDER("(male|female)");

    private final String pattern;

    SignupRegexes(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
