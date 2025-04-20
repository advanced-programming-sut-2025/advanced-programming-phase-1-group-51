package Models;

import java.util.ArrayList;

public class FriendShip {

    private int xp;
    private int level;
    private ArrayList<Player> playersInnRelation;
    public int getXp() {
        return xp;
    }

    public FriendShip(ArrayList<Player> playersInnRelation) {
        this.playersInnRelation = playersInnRelation;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Player> getPlayersInnRelation() {
        return playersInnRelation;
    }

    public void setPlayersInnRelation(ArrayList<Player> playersInnRelation) {
        this.playersInnRelation = playersInnRelation;
    }
}
