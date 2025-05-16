package Models.Friendships;

import Models.Player;

public class Massage {
    private Player fromPlayer;
    private Player toPlayer;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getToPlayer() {
        return toPlayer;
    }

    public void setToPlayer(Player toPlayer) {
        this.toPlayer = toPlayer;
    }

    public Player getFromPlayer() {
        return fromPlayer;
    }

    public void setFromPlayer(Player fromPlayer) {
        this.fromPlayer = fromPlayer;
    }

    public Massage(Player fromPlayer, Player toPlayer, String message) {
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.message = message;
    }



}
