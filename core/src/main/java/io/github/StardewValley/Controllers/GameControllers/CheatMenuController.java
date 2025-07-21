package io.github.StardewValley.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import io.github.StardewValley.Main;
import io.github.StardewValley.Models.App;
import io.github.StardewValley.Models.BackPack;
import io.github.StardewValley.Models.Enums.Others.Season;
import io.github.StardewValley.Models.Enums.Regexes.CheatCodesRegexes;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.*;
import io.github.StardewValley.Models.Game;
import io.github.StardewValley.Models.Player;
import io.github.StardewValley.Views.GameMenus.CheatMenu;

import java.time.LocalTime;
import java.util.regex.Matcher;

public class CheatMenuController {
    private CheatMenu view;
    private GameController gameController;
    private Game game = App.getCurrentGame();

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setView(CheatMenu view) {
        this.view = view;
    }

    private void handleCheatCode(String cheatCode) {
        Matcher timeMatcher = CheatCodesRegexes.TIME_CHEAT.getMatcher(cheatCode);
        Matcher dateMatcher = CheatCodesRegexes.DATE_CHEAT.getMatcher(cheatCode);
        Matcher energySet = CheatCodesRegexes.ENERGY_SET.getMatcher(cheatCode);
        Matcher energyUnlimited = CheatCodesRegexes.ENERGY_UNLIMITED.getMatcher(cheatCode);
        Matcher addItem = CheatCodesRegexes.ADD_ITEM.getMatcher(cheatCode);

        if (timeMatcher != null && timeMatcher.matches()) {
            handleTimeCheat(timeMatcher);
        }
        else if (dateMatcher != null && dateMatcher.matches()) {
            handleDateCheat(dateMatcher);
        }
        else if (energySet != null && energySet.matches()) {
           handleEnergyCheat(energySet, false);
        }
        else if (energyUnlimited != null && energyUnlimited.matches()) {
           handleEnergyCheat(energyUnlimited, true);
        }
        else if (addItem != null && addItem.matches()) {
            handleAddItem(addItem);
        }
        else {
            // Invalid cheat code
            view.showError("Invalid cheat code!");
        }
    }

    private void handleTimeCheat(Matcher matcher) {
        try {
            String hoursStr = matcher.group("X");
            int hours = Integer.parseInt(hoursStr);

            LocalTime currentTime = game.getTime();
            LocalTime newTime = currentTime.plusHours(hours);

            // Wrap around if past midnight (22:00 is latest normal time)
            if (newTime.isAfter(LocalTime.of(22, 0))) {
                newTime = LocalTime.of(9, 0); // Reset to morning
                advanceDay(); // Advance to next day
            }

            game.setTime(newTime);
            view.showError("Time advanced by " + hours + " hours!");
        } catch (NumberFormatException e) {
            view.showError("Invalid time value! Use format: 'cheat advance time Xh'");
        }
    }

    private void handleDateCheat(Matcher matcher) {
        try {
            String daysStr = matcher.group("X");
            int days = Integer.parseInt(daysStr);

            // Advance the specified number of days
            for (int i = 0; i < days; i++) {
                advanceDay();
            }

            view.showError("Date advanced by " + days + " days!");
        } catch (NumberFormatException e) {
            view.showError("Invalid day value! Use format: 'cheat advance date Xd'");
        }
    }

    private void advanceDay() {
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

    private void handleEnergyCheat(Matcher matcher, boolean isUnlimited) {
        Player player = game.getCurrentPlayer();
        if(!isUnlimited){
            player.setCurrentEnergy(Float.parseFloat(matcher.group("value")));
            view.showError("Energy set to " + matcher.group("value") + ".");
        }
        else{
            player.setCurrentEnergy(Float.MAX_VALUE);
            view.showError("Unlimited Energy set.");
        }
    }

    private void handleAddItem(Matcher matcher) {
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();
        String itemName = matcher.group("itemName").trim();
        int count = Integer.parseInt(matcher.group("count"));

        try {
            // Try to find the item in all item type enums
            ItemType itemType = findItemTypeByName(itemName);

            if (itemType != null) {
                backpack.addItem(itemType, count);
                view.showError("Added " + count + " " + itemName + " to inventory!");
            } else {
                view.showError("Item not found: " + itemName);
            }
        } catch (Exception e) {
            view.showError("Error adding item: " + e.getMessage());
            Gdx.app.error("CheatMenu", "Error adding item", e);
        }
    }

    private ItemType findItemTypeByName(String itemName) {
        // Check all item type enums for a match

        // Food items
        FoodType foodType = FoodType.findFoodByName(itemName);
        if (foodType != null) return foodType;

        // Misc items
        MiscType miscType = MiscType.getElseTypeByName(itemName);
        if (miscType != null) return miscType;

        // Minerals
        for (ForagingMineralType mineral : ForagingMineralType.values()) {
            if (mineral.getName().equalsIgnoreCase(itemName)) {
                return mineral;
            }
        }

        // Tools
        ToolType toolType = ToolType.findToolTypeByName(itemName);
        if (toolType != null) return toolType;

        // Crops
        CropType cropType = CropType.findCropByName(itemName);
        if (cropType != null) return cropType;

        // Crop seeds
        CropSeedType cropSeedType = CropSeedType.findCropSeedTypeByName(itemName);
        if (cropSeedType != null) return cropSeedType;

        // Fish
        FishType fishType = FishType.findFishByName(itemName);
        if (fishType != null) return fishType;

        // Tree seeds
        TreeSeedType treeSeedType = TreeSeedType.findTreeSeedTypeByName(itemName);
        if (treeSeedType != null) return treeSeedType;

        // Foraging crops
        for (ForagingCropType foragingCrop : ForagingCropType.values()) {
            if (foragingCrop.name.equalsIgnoreCase(itemName)) {
                return foragingCrop;
            }
        }

        return null; // Item not found
    }

    public void handleSubmit() {
        String cheatCode = view.getCheatCodeField().getText();
        handleCheatCode(cheatCode);
    }

    public void handleBack() {
        resumeGame();
    }



    private void resumeGame() {
        gameController.resumeGame();
        Main.getMain().setScreen(gameController.getView());
    }
}
