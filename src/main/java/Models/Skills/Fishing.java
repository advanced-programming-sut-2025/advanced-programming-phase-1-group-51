package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Fishing extends Skill {

    @Override
    public void learnSkill() {
        super.learnSkill();
    }

    public Fishing(int energyCost, int xp, SkillLevel level) {
        super(energyCost, xp, level);
    }
}
