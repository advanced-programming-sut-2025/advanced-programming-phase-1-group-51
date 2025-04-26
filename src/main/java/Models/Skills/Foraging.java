package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Foraging extends Skill {

    @Override
    public void learnSkill() {
        super.learnSkill();
    }

    public Foraging(int energyCost, int xp, SkillLevel level) {
        super(energyCost, xp, level);
    }
}
