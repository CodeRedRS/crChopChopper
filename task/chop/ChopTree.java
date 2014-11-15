package crChopChopper.task.chop;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.visual.Paint;
import org.powerbot.script.Condition;
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
                && ctx.objects.select().id(Variables.selectedTreeID).nearest().poll().tile().distanceTo(ctx.players.local()) < 15
                && !ctx.objects.select().id(Variables.selectedTreeID).isEmpty();
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.nearest().poll();
        Paint.status = "Chopping...";
        tree.interact("Chop", tree.name());
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != 21177;
            }
        });
    }
}
