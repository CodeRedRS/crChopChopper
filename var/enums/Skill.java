package crChopChopper.var.enums;

import crChopChopper.visual.Formatting;
import org.powerbot.script.rt6.Constants;

/**
 * Created by CodeRed on 10/26/2014.
 */
public enum Skill {
    WOODCUTTING("Woodcutting", Constants.SKILLS_WOODCUTTING, Formatting.startXpWc, Formatting.startLvlWc),
    FIREMAKING("Firemaking", Constants.SKILLS_FIREMAKING, Formatting.startXpFm, Formatting.startLvlFm);

    private String name;
    private int skillID;
    private int startXp;
    private int startLevel;

    private Skill(String name, int skillID, int startXp, int startLevel) {
        this.name = name;
        this.skillID = skillID;
        this.startXp = startXp;
        this.startLevel = startLevel;
    }

    public String getName() {
        return name;
    }

    public int getSkillID() {
        return skillID;
    }

    public int getStartXp() {
        return startXp;
    }

    public int getStartLevel() {
        return startLevel;
    }

    @Override
    public String toString() {
        return getName();
    }
}
