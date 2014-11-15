package crChopChopper.task.firemaking;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;

/**
 * Created by CodeRed on 10/28/2014.
 */
public class LightFire extends Task<ClientContext> {

    public LightFire(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && ctx.backpack.select().id(Variables.OBJECT.LOGS).count() > 0
                && ctx.objects.select().id(Variables.OBJECT.FIRE).poll().inViewport();
    }

    @Override
    public void execute() {
        GameObject fire = ctx.objects.select().id(Variables.OBJECT.FIRE).nearest().poll();
        Item log = ctx.backpack.select().id(Variables.OBJECT.LOGS).poll();
        log.interact("light");
        Condition.sleep(Random.nextInt(900, 1500));
    }
}
