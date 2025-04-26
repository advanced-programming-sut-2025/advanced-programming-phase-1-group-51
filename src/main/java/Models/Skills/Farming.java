package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Farming extends Skill {

    @Override
    public void learnSkill() {
        super.learnSkill();
    }

    public Farming(int energyCost, int xp, SkillLevel level) {
        super(energyCost, xp, level);
    }
}
