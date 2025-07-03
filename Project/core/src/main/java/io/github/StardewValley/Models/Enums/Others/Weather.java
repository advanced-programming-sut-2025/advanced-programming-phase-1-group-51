package io.github.StardewValley.Models.Enums.Others;

public enum Weather {
    SUNNY(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER}, "Sunny"),
    RAIN(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Rain"),
    STORM(new Season[]{Season.SPRING, Season.SUMMER, Season.FALL}, "Storm"),
    SNOW(new Season[]{Season.WINTER}, "Snow"),
    ;

    final public Season[] possibleSeasons;
    final private String name;

    Weather(Season[] possibleSeasons, String name) {
        this.name = name;
        this.possibleSeasons = possibleSeasons;
    }
}
