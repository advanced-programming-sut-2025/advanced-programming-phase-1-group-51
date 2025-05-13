package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Farming extends Skill {
    @Override
    public void learn() {
    }

    public Farming() {
        super();
    }

    public Farming(SkillLevel level, int xp) {
        super(level, xp);
    }

    @Override
    public String toString() {
        return "Farming";
    }
}
