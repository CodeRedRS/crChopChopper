package crChopChopper.task.antiban;

import crChopChopper.task.Task;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by CodeRed on 11/13/2014.
 */
public class Antiban extends Task<ClientContext> {

    public Antiban(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return Random.nextInt(0, 10000) == 5000;
    }

    @Override
    public void execute() {
        AntiPattern ap = new AntiPattern(ctx);
        switch (Random.nextInt(0, 2)) {
            case 0:
                System.out.println("MouseOffScreen");
                ap.mouseOffScreen();
                break;
            case 1:
                System.out.println("RanHud");
                ap.openRandomHud();
                break;
            case 2:
                System.out.println("SkillHover");
                ap.skillHover(AntiPattern.Skill.WOODCUTTING, 100, 1500);
                break;
            default:
                System.out.println("No Anti");
                break;
        }
    }
}
