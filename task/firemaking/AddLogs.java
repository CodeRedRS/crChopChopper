package crChopChopper.task.firemaking;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.var.WIDGET;
import crChopChopper.visual.Paint;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

/**
 * Created by CodeRed on 10/28/2014.
 */
public class AddLogs extends Task<ClientContext> {

    public AddLogs(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().id(Variables.OBJECT.LOGS).count() > 16
                && ctx.players.local().idle();
    }

    @Override
    public void execute() {
        GameObject fire = ctx.objects.select().id(Variables.OBJECT.FIRE).nearest().poll();

        int backpackLogs = ctx.backpack.select().id(Variables.OBJECT.LOGS).count();
        if (ctx.players.local().idle()) {
            if (backpackLogs > 0) {
                if (fire.inViewport()) {
                    fire.interact(false, "Use");
                    Paint.status = "Adding to fire...";
                    Condition.sleep(Random.nextInt(750, 1000));
                    if (ctx.players.local().idle()) {
                        if (WIDGET.CRAFT_INTERFACE.visible()) {
                            WIDGET.CRAFT_INTERFACE.click();
                            Condition.sleep(Random.nextInt(750, 1000));
                        }
                    }
                    Condition.sleep(Random.nextInt(750, 1000));
                }
            }
        }
    }
}
