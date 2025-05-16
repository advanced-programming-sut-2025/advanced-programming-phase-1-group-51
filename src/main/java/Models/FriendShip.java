package Models;

import java.util.ArrayList;

public class FriendShip {

    private int xp;
    private int level;
    private Player playersInRelation;


    public int getXp() {
        return xp;
    }

    public FriendShip(Player playersInRelation) {
        this.playersInRelation = playersInRelation;
        this.xp = 0;
        this.level = 0;
    }

    public void setXp(int xp) {
        this.xp = xp;
        int diff = this.xp - (level + 1) * 100;
        if (diff > 0) {
            this.xp = diff;
            this.level += 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void setPlayersInnRelation(Player playersInRelation) {
        this.playersInRelation = playersInRelation;
    }

    public Player getPlayersInnRelation() {
        return playersInRelation;
    }
}
