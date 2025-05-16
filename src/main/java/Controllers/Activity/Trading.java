package Controllers.Activity;

import Controllers.BaseController;
import Controllers.BaseController;
import Models.*;
import Models.Items.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Trading extends BaseController {
    private boolean isItemOfType(Item item, String type) {
        switch (type.toLowerCase()) {
            case "food":
                return item instanceof Food;
            case "tool":
                return item instanceof Tool;
            case "fish":
                return item instanceof Fish;
            case "mineral":
                return item instanceof Mineral ;
            case "treeseed":
                return item instanceof TreeSeed ;
            case "else":
                return item instanceof Else ;
            case"cropseed":
                return item instanceof CropSeed ;
            // Add more types as needed
            default:
                return false;
        }
    }

    /// /////////////////////////////////////////////////////////////////////////////
    public Result startTrade() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        List<Player> availablePlayers = game.getAvailablePlayers(currentPlayer);

        if (availablePlayers.isEmpty()) {
            return new Result(false, "No other players available for trading");
        }

        // Format player list with just usernames
        String playerList = availablePlayers.stream()
                .map(p -> p.getUser().getUsername())
                .collect(Collectors.joining("\n"));

        return new Result(true,
                "Available players for trading:\n" + playerList);
    }

    // set request for trade/////////////////////////////////////////////////////////////
    public Result TradeWithUser(String username, String type, String item, int amount) {
        // 1. Validate inputs
        if (username == null || type == null || item == null || amount <= 0) {
            return new Result(false, "Invalid input parameters");
        }

        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();

        // 2. Find players
        Player currentPlayer = game.getCurrentPlayer();
        Player targetPlayer = game.findPlayerByUsername(username);

        if (targetPlayer == null) {
            return new Result(false, "Player not found with username: " + username);
        }

        if (currentPlayer.equals(targetPlayer)) {
            return new Result(false, "Cannot trade with yourself");
        }

        BackPack currentBackpack = currentPlayer.getInventory();
        BackPack targetBackpack = targetPlayer.getInventory();

        // 3. Check if item exists in current player's backpack
        Loot tradingLoot = currentBackpack.findItemLoot(item);
        if (tradingLoot == null) {
            return new Result(false, "You don't have " + item + " in your inventory");
        }

        // 4. Check if item matches the requested type
        if (!isItemOfType(tradingLoot.getItem(), type)) {
            return new Result(false, item + " is not of type " + type);
        }

        // 5. Check if enough amount exists
        if (tradingLoot.getCount() < amount) {
            return new Result(false, "Not enough " + item + " (You have " + tradingLoot.getCount() + ")");
        }


        // 6. Perform the trade
//        currentBackpack.removeItem(item, amount);
//        targetBackpack.addItem(tradingLoot.getItem(), amount);
        Trade newTrade = new Trade(currentPlayer, targetPlayer, item, amount);
        game.addTrade(newTrade);

//        return new Result(true, "Trade request sent! ID: " + newTrade.getId());

        return new Result(true, "trade set up successfully and its ID: " + newTrade.getId());
    }

    /// //////////////////////////////////////////////////////////////////////////
    public Result TradeList() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();

        Player currentPlayer = game.getCurrentPlayer();
        List<Trade> trades = game.playertraderequests(currentPlayer);
        if (trades.isEmpty()) {
            return new Result(false, "No trades available");
        }

        String tradeList = trades.stream()
                .map(this::formatTrade)
                .collect(Collectors.joining("\n\n"));

        return new Result(true,
                "Your trade requests:\n" +
                        "-------------------\n" +
                        tradeList);
    }

    private String formatTrade(Trade trade) {
        String direction = trade.getSender().getUser().getUsername() + " → You";

        String status;
        switch (trade.getStatus()) {
            case PENDING: status = "🟡 Pending"; break;

            default: status = "invalid status"; break;
        }

        return String.format(
                "ID: %s\n" +
                        "Parties: %s\n" +
                        "Item: %s (%d)\n" +
                        "Status: %s\n" ,

                trade.getId().toString().substring(0, 8), // نمایش 8 کاراکتر اول ID برای سادگی
                direction,
                trade.getItemName(),
                trade.getAmount(),
                status

        );
    }
    /// ////////////////////////////////////////////////////////////////////////////
    public Result TradeResponse(String response, UUID id) {
        if (response == null || id == null) {
            return new Result(false, "Invalid input parameters");
        }

        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        // پیدا کردن ترید بر اساس ID
        Optional<Trade> tradeOpt = game.getTradeById(id);
        if (!tradeOpt.isPresent()) {
            return new Result(false, "Trade not found with ID: " + id);
        }

        Trade trade = tradeOpt.get();

        // بررسی آیا بازیکن جاری دریافت‌کننده ترید است
        if (!trade.getReceiver().equals(currentPlayer)) {
            return new Result(false, "You are not the receiver of this trade");
        }

        // بررسی وضعیت ترید
        if (trade.getStatus() != Trade.Status.PENDING) {
            return new Result(false, "This trade is already processed");
        }

        // پردازش پاسخ
        try {
            Trade.Status status = Trade.Status.valueOf(response.toUpperCase());

            switch (status) {
                case ACCEPTED:
                    // اجرای ترید
                    executeTrade(trade);
                    trade.setStatus(Trade.Status.ACCEPTED);
                    trade.setResponseMessage("Trade accepted");
                    return new Result(true, "Trade accepted successfully");

                case REJECTED:
                    trade.setStatus(Trade.Status.REJECTED);
                    trade.setResponseMessage("Trade rejected");
                    return new Result(true, "Trade rejected");

                default:
                    return new Result(false, "Invalid response type");
            }
        } catch (IllegalArgumentException e) {
            return new Result(false, "Invalid response. Use 'accept' or 'reject'");
        }
    }

    private void executeTrade(Trade trade) {
        Player sender = trade.getSender();
        Player receiver = trade.getReceiver();

        // پیدا کردن آیتم در کیف فرستنده
        Loot loot = sender.getInventory().findItemLoot(trade.getItemName());
        loot.setCount(trade.getAmount());

        if (loot != null && loot.getCount() >= trade.getAmount()) {
            // انتقال آیتم
            sender.getInventory().removeLoot(loot);
            receiver.getInventory().addLoot(loot);
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////
    public Result TradeHistory() {
        User currentUser = App.getCurrentUser();
        Game game = currentUser.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();

        List<Trade> history = game.getTrades().stream()
                .filter(t -> t.getSender().equals(currentPlayer) ||
                        t.getReceiver().equals(currentPlayer))
                .filter(t -> t.getStatus() != Trade.Status.PENDING)
                .collect(Collectors.toList());

        if (history.isEmpty()) {
            return new Result(false, "No trade history found");
        }

        String historyList = history.stream()
                .map(this::formatTradeHistory)
                .collect(Collectors.joining("\n\n"));

        return new Result(true,
                "Your trade history:\n" +
                        "------------------\n" +
                        historyList);
    }

    private String formatTradeHistory(Trade trade) {
        String direction = trade.getSender().equals(App.getCurrentUser().getCurrentGame().getCurrentPlayer())
                ? "You → " + trade.getReceiver().getUser().getUsername()
                : trade.getSender().getUser().getUsername() + " → You";

        return String.format(
                "Item: %s (%d)\n" +
                        "Parties: %s\n" +
                        "Status: %s\n",
                trade.getItemName(),
                trade.getAmount(),
                direction,
                trade.getStatus().toString()
        );
    }
}
