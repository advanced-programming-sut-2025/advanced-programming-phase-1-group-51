package Models;

import Models.Enums.Others.Season;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    private LocalDateTime dateAndTime;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-'Day' D");
    private int daysInCurrentSeason = 0;

    public DateAndTime() {
        this.dateAndTime = LocalDateTime.now()
                .withYear(1) // Using year 1 as our game's starting year
                .withMonth(1) // January (doesn't matter for our season system)
                .withDayOfMonth(1)
                .withHour(9)
                .withMinute(0);
        Game.setSeason(Season.SPRING); // Initialize first season
    }

    public void increaseHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        dateAndTime = dateAndTime.plusHours(hours);
        checkDayChange();
    }

    public void increaseDays(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days cannot be negative");
        }
        dateAndTime = dateAndTime.plusDays(days);
        daysInCurrentSeason += days;
        checkSeasonChange();
    }

    private void checkDayChange() {
        // Check if we've passed midnight
        if (dateAndTime.getHour() == 0 && dateAndTime.getMinute() == 0) {
            daysInCurrentSeason++;
            checkSeasonChange();
        }
    }

    private void checkSeasonChange() {
        // Change season after 28 days
        if (daysInCurrentSeason >= 28) {
            Season[] seasons = Season.values();
            int nextSeasonOrdinal = (Game.getSeason().ordinal() + 1) % seasons.length;
            Game.setSeason(seasons[nextSeasonOrdinal]);
            daysInCurrentSeason = 0; // Reset counter for new season

            // You could add seasonal effects here
            switch (Game.getSeason()) {
                case SPRING:
                    // Spring effects
                    break;
                case SUMMER:
                    // Summer effects
                    break;
                case FALL:
                    // Fall effects
                    break;
                case WINTER:
                    // Winter effects
                    break;
            }
        }
    }

    public String getTime() {
        return dateAndTime.format(TIME_FORMATTER);
    }

    public String getDate() {
        // Format shows year and day-of-year (e.g., "0001-Day 15")
        return dateAndTime.format(DATE_FORMATTER);
    }

    public String getDayOfWeek() {
        return dateAndTime.getDayOfWeek().toString();
    }

    public String getCurrentSeason() {
        return Game.getSeason().toString();
    }

    public int getDaysInCurrentSeason() {
        return daysInCurrentSeason;
    }

    public LocalDateTime getDateTime() {
        return dateAndTime;
    }
}
