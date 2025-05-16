package Models.Friendships;

import Models.Items.Item;
import Models.Player;

public class Gift {
    private Player FromPlayer;
    private Player ToPlayer;
    private Item item;
    private int amount;
    private int rate;

    public int getRate() {


        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Gift(Player fromPlayer, Player toPlayer, Item item, int amount) {
        FromPlayer = fromPlayer;
        ToPlayer = toPlayer;
        this.item = item;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Player getFromPlayer() {
        return FromPlayer;
    }

    public void setFromPlayer(Player fromPlayer) {
        FromPlayer = fromPlayer;
    }

    public Player getToPlayer() {
        return ToPlayer;
    }

    public void setToPlayer(Player toPlayer) {
        ToPlayer = toPlayer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
