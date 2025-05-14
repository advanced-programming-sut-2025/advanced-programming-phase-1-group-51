package Models;

import java.util.ArrayList;

public class FriendShip {

    private int xp;
    private int level;
    public int getXp() {
        return xp;
    }

    public FriendShip() {

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

}
