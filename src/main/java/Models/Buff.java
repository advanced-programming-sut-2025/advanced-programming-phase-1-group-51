package Models;

public class Buff {

    private final int MaxEnergyIncrease;
    private final int duration;
    private final String affectedSkill;

    public Buff(int maxEnergyIncrease, int duration, String affectedSkill) {
        MaxEnergyIncrease = maxEnergyIncrease;
        this.duration = duration;
        this.affectedSkill = affectedSkill;
    }



    public int getMaxEnergyIncrease() {
        return MaxEnergyIncrease;
    }

    public int getDuration() {
        return duration;
    }

    public String getAffectedSkill() {
        return affectedSkill;
    }
}
