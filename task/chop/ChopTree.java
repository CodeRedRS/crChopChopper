package crChopChopper.task.chop;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.visual.ScriptPaint;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import java.util.concurrent.Callable;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class ChopTree extends Task<ClientContext> {
    public ChopTree(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28
                && ctx.players.local().animation() == -1
                && ctx.objects.select().id(Variables.selectedTreeID).nearest().poll().inViewport()
                && !ctx.objects.select().id(Variables.selectedTreeID).isEmpty();
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.id(Variables.selectedTreeID).nearest().poll();
        tree.interact("Chop", tree.name());
        ScriptPaint.status = "Chopping tree";
        Condition.sleep(Random.nextInt(50, 100));

        if (ctx.players.local().inMotion()) {
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.players.local().inMotion() && ctx.players.local().animation() == -1;
                }
            });
        }
    }
}