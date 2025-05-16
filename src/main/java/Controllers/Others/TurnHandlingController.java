package Controllers.Others;

import Controllers.BaseController;
import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Season;
import Models.Enums.Others.Weather;
import Models.Maps.Position;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class TurnHandlingController extends BaseController {

    public Result goToMain() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        App.setCurrentMenu(Menu.MainMenu);
        return saveGameState(game)
                .combine(Result.success( "You are now in main menu"));
    }

    public Result showCurrentMenu(){
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        return saveGameState(game)
                .combine(Result.success( "Game Menu"));
    }

    public Result deleteGame(){
        return null;
    }


    public Result exitGame() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();

        if (game == null) {
            return  Result.failure( "No active game to exit");
        }

        // Check if current player is the game starter (first player)
        if (game.getCurrentPlayer() != game.getPlayers().get(0)) {
            return  Result.failure( "Only the game creator can exit the game on their turn");
        }

        try {
            // Save the game state (you'll need to implement this)
           // saveGameToFile(game);

            // Clear current game reference for all players
            for (Player player : game.getPlayers()) {
                player.getUser().setCurrentGame(null);
            }

            // Return to main menu
            App.setCurrentMenu(Menu.MainMenu);
            return saveGameState(game)
                .combine(Result.success( "Game saved successfully. You can load it later using 'load game'"));
        }
        catch (Exception e) {
            return  Result.failure( "Failed to save game: " + e.getMessage());
        }
    }

    public Result nextTurn() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();

        if (game == null) {
            return  Result.failure( "No active game found");
        }

        // Get all players in the game
        ArrayList<Player> players = game.getPlayers();
        if (players.isEmpty()) {
            return  Result.failure( "No players in the game");
        }

        // Find current player index
        int currentIndex = players.indexOf(game.getCurrentPlayer());
        if (currentIndex == -1) {
            return  Result.failure( "Current player not found in game");
        }

        // Determine next player
        int nextIndex = (currentIndex + 1) % players.size();
        Player nextPlayer = players.get(nextIndex);
        game.setCurrentPlayer(nextPlayer);

        // Update game time (only advance time when full cycle completes)
        if (nextIndex == 0) {
            advanceTime(game);
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (game.getCurrentPlayer().getWhoSentMassage() != null) {

            stringBuilder.append("its now next player's turn\n");
            stringBuilder.append("You have new massage from : \n");
            for (Player player : game.getCurrentPlayer().getWhoSentMassage()) {
                stringBuilder.append(player.getUser().getUsername()).append("\n");
            }
            game.getCurrentPlayer().getWhoSentMassage().clear();
        }
        if (game.getCurrentPlayer().getWhoSentGift() != null) {
            stringBuilder.append("\n");
            stringBuilder.append("You have new gifts from : \n");
            for (Player player : game.getCurrentPlayer().getWhoSentGift()) {
                stringBuilder.append(player.getUser().getUsername()).append("\n");
            }
            game.getCurrentPlayer().getWhoSentGift().clear();
        }
        if (game.getCurrentPlayer().getWhoWantsGetMarriage() != null) {
            stringBuilder.append("\n");
            stringBuilder.append("You have request for marriage from : \n");
            for (Player player : game.getCurrentPlayer().getWhoWantsGetMarriage()) {
                stringBuilder.append(player.getUser().getUsername()).append("\n");
            }
        }


        return saveGameState(game)
                .combine(Result.success( "It's now " + nextPlayer.getUser().getUsername() + "'s turn"));
    }

    private void advanceTime(Game game) {
        // Advance time by 1 hour
        LocalDateTime newTime = game.getDate().plusHours(1);
        game.setDate(newTime);

        // Check for 10 PM curfew
        if (newTime.getHour() == 22) { // 10 PM
            // Return all players to their houses
            for (Player player : game.getPlayers()) {
                returnPlayerToHouse(player);
            }
            // Fast forward to next morning at 9 AM
            newTime = newTime.plusHours(11); // 10 PM + 11 hours = 9 AM next day
            game.setDate(newTime);

            // Handle day change effects
            handleDayChange(game);
            return;
        }

        // Check for midnight (normal day change)
        if (newTime.getHour() == 0) { // Midnight
            handleDayChange(game);
        }
    }

    private void handleDayChange(Game game) {
        LocalDateTime date = game.getDate();

        // Reset player energies for new day
        for (Player player : game.getPlayers()) {
            player.setEnergy(player.getMaxEnergy());
            player.setCurrentTurnUsedEnergy(0);

            // Daily animal care reset
            resetAnimalCareStatus(player);
        }

        // Check for season change (28 days per season)
        int dayOfSeason = date.getDayOfMonth() % 28;
        if (dayOfSeason == 1) { // First day of new season
            Season currentSeason = game.getSeason();
            Season nextSeason = getNextSeason(currentSeason);
            game.setSeason(nextSeason);
        }

        // Generate new weather for the day
        generateDailyWeather(game);

        for (Player player : game.getPlayers()) {
            player.getFarm().handleNightlyCrowAttacks();
        }
    }

    private void returnPlayerToHouse(Player player) {
        // Set player position to their house coordinates
        player.setPosition(new Position(5, 10)); // Example house coordinates
        player.setInHouse(true);
        player.setInFarm(false);
        player.setInVillage(false);
        player.setCloseToLake(false);

        // Apply energy penalty for staying out late
        int energyPenalty = (int)(player.getEnergy() * 0.2); // Lose 20% energy
        player.setEnergy(player.getEnergy() - energyPenalty);

        // You might want to add a notification/message about being forced home
    }

    private void resetAnimalCareStatus(Player player) {
        for (Animal animal : player.getAnimals()) {
            animal.setHasBeenPetToday(false);
            animal.setHasBeenFedGrassToday(false);
            animal.setHasBeenFedHayToday(false);
        }
    }

    private void generateDailyWeather(Game game) {
        // Simple weather generation - can be enhanced
        Random random = new Random();
        Weather[] possibleWeathers = Weather.values();
        game.setWeatherToday(possibleWeathers[random.nextInt(possibleWeathers.length)]);

        // Set tomorrow's weather (50% chance of same weather)
        if (random.nextBoolean()) {
            game.setWeatherTomorrow(game.getWeatherToday());
        } else {
            game.setWeatherTomorrow(possibleWeathers[random.nextInt(possibleWeathers.length)]);
        }
    }

    private Season getNextSeason(Season currentSeason) {
        switch (currentSeason) {
            case SPRING: return Season.SUMMER;
            case SUMMER: return Season.FALL;
            case FALL: return Season.WINTER;
            case WINTER: return Season.SPRING;
            default: return Season.SPRING;
        }
    }



}
