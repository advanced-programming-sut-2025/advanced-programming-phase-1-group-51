package io.github.StardewValley.Controllers.GameControllers;

import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Regexes.CheatCodesRegexes;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Views.GameMenus.CheatMenu;

import java.time.LocalTime;
import java.util.regex.Matcher;

public class CheatMenuController {
    private CheatMenu view;
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setView(CheatMenu view) {
        this.view = view;
    }

    public void handleCheatMenuButtons() {
        if (view != null) {
            String cheatCode = view.getCheatCodeField().getText();
            Game game = App.getCurrentGame();

            if (view.getSubmitButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                handleCheatCode(cheatCode, game);
            } else if (view.getBackButton().isPressed()) {
                Main.playSound(Main.getButtonClickSound());
                resumeGame();
            }
        }
    }

    private void handleCheatCode(String cheatCode, Game game) {
        Matcher timeMatcher = CheatCodesRegexes.TIME_CHEAT.getMatcher(cheatCode);
        Matcher dateMatcher = CheatCodesRegexes.DATE_CHEAT.getMatcher(cheatCode);

        if (timeMatcher != null && timeMatcher.matches()) {
            handleTimeCheat(timeMatcher, game);
        }
        else if (dateMatcher != null && dateMatcher.matches()) {
            handleDateCheat(dateMatcher, game);
        }
        else {
            // Invalid cheat code
            view.showError("Invalid cheat code!");
        }
    }

    private void handleTimeCheat(Matcher matcher, Game game) {
        try {
            String hoursStr = matcher.group("X");
            int hours = Integer.parseInt(hoursStr);

            LocalTime currentTime = game.getTime();
            LocalTime newTime = currentTime.plusHours(hours);

            // Wrap around if past midnight (22:00 is latest normal time)
            if (newTime.isAfter(LocalTime.of(22, 0))) {
                newTime = LocalTime.of(9, 0); // Reset to morning
                advanceDay(game); // Advance to next day
            }

            game.setTime(newTime);
            view.showError("Time advanced by " + hours + " hours!");
        } catch (NumberFormatException e) {
            view.showError("Invalid time value! Use format: 'cheat advance time Xh'");
        }
    }

    private void handleDateCheat(Matcher matcher, Game game) {
        try {
            String daysStr = matcher.group("X");
            int days = Integer.parseInt(daysStr);

            // Advance the specified number of days
            for (int i = 0; i < days; i++) {
                advanceDay(game);
            }

            view.showError("Date advanced by " + days + " days!");
        } catch (NumberFormatException e) {
            view.showError("Invalid day value! Use format: 'cheat advance date Xd'");
        }
    }

    private void advanceDay(Game game) {
        int currentDay = game.getCurrentDay();
        int currentSeason = game.getCurrentSeason();

        // Advance day
        currentDay++;

        // Handle season/year transition
        if (currentDay > 28) {
            currentDay = 1;
            currentSeason++;

            if (currentSeason > 4) {
                currentSeason = 1;
                // You could add year advancement here if tracking years
            }

            // Update season
            game.setSeason(Season.values()[currentSeason - 1]);
        }

        // Update game state
        game.setCurrentDay(currentDay);
        game.setTime(LocalTime.of(9, 0)); // Reset to morning

        // You might want to add other day-advancement logic here:
        // - Regrow crops
        // - Reset energy
        // - Process daily events
    }

    private void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }
}
