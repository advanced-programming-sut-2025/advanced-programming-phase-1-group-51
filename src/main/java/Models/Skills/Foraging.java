package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Foraging extends Skill {

    @Override
    public void learn() {

    }

    public Foraging() {
        super();
    }

    public Foraging(SkillLevel level, int xp) {
        super(level, xp);
    }

    @Override
    public String toString() {
        return "Foraging";
    }
}
