package Models.Skills;

import Models.Enums.Others.SkillLevel;

public class Mining extends Skill {

    @Override
    public void learnSkill() {
        super.learnSkill();
    }

    public Mining(int energyCost, int xp, SkillLevel level) {
        super(energyCost, xp, level);
    }
}
