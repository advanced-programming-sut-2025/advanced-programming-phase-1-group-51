package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Mining extends Skill {

    @Override
    public void learn() {

    }

    public Mining() {
        super();
    }

    public Mining(SkillLevel level, int xp) {
        super(level, xp);
    }

    @Override
    public String toString() {
        return "Mining";
    }
}
