package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Fishing extends Skill {
    @Override
    public void learn() {

    }

    public Fishing() {
        super();
    }

    public Fishing(SkillLevel level, int xp) {
        super(level, xp);
    }

    @Override
    public String toString() {
        return "Fishing";
    }
}
