package Models.Skills;

import Models.Enums.Others.SkillLevel;

public abstract class Skill {
    int energyCost;
    int xp;
    SkillLevel level;

    public Skill(int energyCost, int xp, SkillLevel level) {
        this.energyCost = energyCost;
        this.xp = xp;
        this.level = level;
    }

    public void learnSkill(){}



}
