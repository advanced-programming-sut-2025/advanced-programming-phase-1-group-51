package Controllers.Others;

import Controllers.BaseController;
import Models.*;
import Models.Friendships.Gift;
import Models.Friendships.Massage;

import java.util.ArrayList;

public class FriendShipController {



    public Result FriendShip() {
        Game game = App.getCurrentUser().getCurrentGame();
        ArrayList<FriendShip> friendShips = game.getCurrentPlayer().getFriendShips();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FriendShips List : \n");

        for (FriendShip friendShip : friendShips) {
            stringBuilder.append(friendShip.toString() + "\n");
        }

        return new Result(true, stringBuilder.toString());
    }

    public Result Talk(String username, String message) {
        Game game = App.getCurrentUser().getCurrentGame();

        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (!CheckPosition(game.getCurrentPlayer(), toPlayer)) {
            return new Result(false, "You are not in a valid position");
        }

        Massage massages = new Massage(game.getCurrentPlayer(), toPlayer, message);
        Game.AddMassages(massages);

        FriendShip toPlayerFriendship = getFriendShip(game.getCurrentPlayer(), toPlayer);
        if (toPlayerFriendship == null) {
            return new Result(false, "You are not in relation");
        }

        toPlayerFriendship.setXp(toPlayerFriendship.getXp() + 20);
        toPlayerFriendship = getFriendShip(toPlayer, game.getCurrentPlayer());
        toPlayerFriendship.setXp(toPlayerFriendship.getXp() + 20);

        return new Result(true, "Your massage has been sent");
    }

    public Result TalkHistory(String username) {
        Game game = App.getCurrentUser().getCurrentGame();


        StringBuilder stringBuilder = new StringBuilder();

        Player currentPlayer = game.getCurrentPlayer();
        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        stringBuilder.append("Your talking history by " + username + ": \n");
        for (Massage massage : Game.getMassages()){
            if ((massage.getFromPlayer().equals(currentPlayer) && massage.getToPlayer().equals(toPlayer))){
                stringBuilder.append("You sent : " + massage.getMessage() + " to " + toPlayer.getUser().getUsername() + "\n");
            }
            if ((massage.getFromPlayer().equals(toPlayer) && massage.getToPlayer().equals(currentPlayer))){
                stringBuilder.append(toPlayer.getUser().getUsername() + " sent : " + massage.getMessage() + " to you"+ "\n");
            }
        }
        return new Result(true, stringBuilder.toString());
    }

    public Result Gift(String username, String item, int amount) {
        Game game = App.getCurrentUser().getCurrentGame();

        Player toPlayer = getPlayer(username);
        ArrayList<FriendShip> friendShips = game.getCurrentPlayer().getFriendShips();

        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (!CheckPosition(game.getCurrentPlayer(), toPlayer)) {
            return new Result(false, "You are not in a valid position");
        }

        FriendShip toPlayerFriendship = null;
        for (FriendShip friendShip : friendShips) {
            if (friendShip.getPlayersInnRelation().equals(toPlayer)) {
                toPlayerFriendship = friendShip;
            }
        }
        if (toPlayerFriendship.getLevel() < 1){
            return new Result(false, "Your friendship level is too low to send gift!");
        }

        if (game.getCurrentPlayer().getInventory().findItemLoot(item).getCount() < amount){
            return new Result(false, "You do not have enough items in your inventory");
        }

        Loot ItemLoot = game.getCurrentPlayer().getInventory().findItemLoot(item);
        game.getCurrentPlayer().getInventory().findItemLoot(item).setCount(game.getCurrentPlayer().getInventory().findItemLoot(item).getCount() - amount);
        if (game.getCurrentPlayer().getInventory().findItemLoot(item).getCount() == 0){
            game.getCurrentPlayer().getInventory().removeLoot(ItemLoot);
        }

        int count = toPlayer.getInventory().findItemLoot(item).getCount() + amount;

        // we need one if here

        toPlayer.getInventory().findItemLoot(item).setCount(toPlayer.getInventory().findItemLoot(item).getCount() + amount);

        Gift gift = new Gift(game.getCurrentPlayer(), toPlayer, ItemLoot.getItem(), amount);
        Game.AddGifts(gift);

        return new Result(true, "You sent the gift successfully");

    }

    public Result GiftList() {
        Game game = App.getCurrentUser().getCurrentGame();


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your gifts List is : \n");

        int i = 0;
        for (Gift gift : Game.getGifts()){
            if (gift.getToPlayer().equals(game.getCurrentPlayer())){
                i++;
                stringBuilder.append(i + ". " + gift.getAmount() + gift.getItem().getName() + " from" + gift.getFromPlayer() + "\n");
            }
        }
        return new Result(true, stringBuilder.toString());
    }

    public Result GiftRate(int giftNumber, int giftRate) {
        Game game = App.getCurrentUser().getCurrentGame();


        if (giftNumber > game.getCurrentPlayer().getWhoSentMassage().size()){
            return new Result(false, "Invalid Gift number");
        }

        if (giftRate > 5 || giftRate < 0){
            return new Result(false, "Invalid Gift rate");
        }

        int xp = (giftRate - 3) * 30 + 15;
        int i = 1;
        for (Gift gift : Game.getGifts()){
            if (gift.getToPlayer().equals(game.getCurrentPlayer())){
                if (i == giftNumber){
                    for (FriendShip friendShip : game.getCurrentPlayer().getFriendShips()){
                        if (friendShip.getPlayersInnRelation().equals(gift.getFromPlayer())){
                            friendShip.setXp(friendShip.getXp() + xp);
                        }
                    }
                    Game.getGifts().remove(gift);
                }
                else{
                    i++;
                }
            }
        }
        return new Result(true, "Your gift rate successfully set");
    }

    public Result GiftHistory(String username) {
        StringBuilder stringBuilder = new StringBuilder();
        Game game = App.getCurrentUser().getCurrentGame();


        Player currentPlayer = game.getCurrentPlayer();
        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        stringBuilder.append("Your gifts history by " + username + ": \n");
        for (Gift gifts : Game.getGifts()){
            if ((gifts.getFromPlayer().equals(currentPlayer) && gifts.getToPlayer().equals(toPlayer))){
                stringBuilder.append("You sent : " + gifts.getItem().getName() + " to " + toPlayer.getUser().getUsername() + "\n");
            }
            if ((gifts.getFromPlayer().equals(toPlayer) && gifts.getToPlayer().equals(currentPlayer))){
                stringBuilder.append(toPlayer.getUser().getUsername() + " sent : " + gifts.getItem().getName() + " to you"+ "\n");
            }
        }
        return new Result(true, stringBuilder.toString());
    }

    public Result Hug(String username) {
        Game game = App.getCurrentUser().getCurrentGame();

        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        if (!CheckPosition(toPlayer, game.getCurrentPlayer())){
            return new Result(false, "You are not in a valid position");
        }
        FriendShip toPlayerFriendship = getFriendShip(game.getCurrentPlayer(), toPlayer);
        if (toPlayerFriendship == null) {
            return new Result(false, "You are not in relation");
        }
        if (toPlayerFriendship.getLevel() < 2){
            return new Result(false, "Not enough level");
        }
        toPlayerFriendship.setXp(toPlayerFriendship.getXp() + 60);
        toPlayerFriendship = getFriendShip(toPlayer, game.getCurrentPlayer());
        toPlayerFriendship.setXp(toPlayerFriendship.getXp() + 60);
        return new Result(true, "You huged successfully");
    }

    public Result Flower(String username) {
        Game game = App.getCurrentUser().getCurrentGame();
        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        if (!CheckPosition(toPlayer, game.getCurrentPlayer())){
            return new Result(false, "You are not in a valid position");
        }
        FriendShip toPlayerFriendship = getFriendShip(game.getCurrentPlayer(), toPlayer);
        if (toPlayerFriendship == null) {
            return new Result(false, "You are not in relation");
        }
        if (toPlayerFriendship.getLevel() < 2){
            return new Result(false, "Not enough level");
        }
        toPlayerFriendship.setLevel(3);
        toPlayerFriendship = getFriendShip(toPlayer, game.getCurrentPlayer());
        toPlayerFriendship.setLevel(3);
        return new Result(true, "You huged successfully");
    }

    public Result AskMarriage(String username, String ring) {
        Game game = App.getCurrentUser().getCurrentGame();

        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        if (!game.getCurrentPlayer().getUser().getGender().equals("male")){
            return new Result(false, "You cant ask marriage");
        }
        if (toPlayer.getUser().getGender().equals("male")){
            return new Result(false, "You cant ask marriage from male");
        }
        FriendShip toPlayerFriendship = getFriendShip(game.getCurrentPlayer(), toPlayer);
        if (toPlayerFriendship == null) {
            return new Result(false, "You are not in relation");
        }
        if (toPlayerFriendship.getLevel() < 3){
            return new Result(false, "Not enough level");
        }
        if (!CheckPosition(toPlayer, game.getCurrentPlayer())){
            return new Result(false, "You are not in a valid position");
        }
        toPlayer.setWhoWantsGetMarriage(game.getCurrentPlayer());
        return new Result(true, "You request marriage sent");
    }

    public Result Respond(String respond,String username){
        Game game = App.getCurrentUser().getCurrentGame();

        Player toPlayer = getPlayer(username);
        if (toPlayer == null) {
            return new Result(false, "Player not found");
        }
        FriendShip toPlayerFriendship = getFriendShip(game.getCurrentPlayer(), toPlayer);
        if (toPlayerFriendship == null) {
            return new Result(false, "You are not in relation");
        }
        game.getCurrentPlayer().getWhoWantsGetMarriage().remove(toPlayer);
        if (respond.equals("reject")){

            return new Result(false, "You rejected his request");
        }

        toPlayerFriendship.setLevel(4);
        toPlayerFriendship = getFriendShip(toPlayer, game.getCurrentPlayer());
        toPlayerFriendship.setLevel(4);

        return new Result(true, "You accepted his request");
    }




    private Player getPlayer(String username) {


        for (Player player : App.getCurrentUser().getCurrentGame().getPlayers()) {
            if (player.getUser().getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }


    private boolean CheckPosition (Player player1, Player player2) {
        if (Math.abs(player1.getPosition().getX() - player2.getPosition().getX()) <= 1 &&
                Math.abs(player1.getPosition().getY() - player2.getPosition().getY()) <= 1) {
            return true;
        }
        return false;
    }

    private FriendShip getFriendShip(Player player1, Player player2) {
        for (FriendShip friendShip : player1.getFriendShips()) {
            if (friendShip.getPlayersInnRelation().equals(player2)) {
                return friendShip;
            }
        }
        return null;
    }

}
