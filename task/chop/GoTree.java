package crChopChopper.task.chop;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.visual.Paint;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class GoTree extends Task<ClientContext> {

    public GoTree(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28
                && ctx.players.local().animation() == -1
                && !ctx.objects.select().id(Variables.selectedTreeID).nearest().poll().inViewport()
                && !ctx.objects.select().id(Variables.selectedTreeID).isEmpty();
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.nearest().poll();
        if (tree.tile().distanceTo(ctx.players.local()) < 15) {
            Paint.status = "Turn to tree...";
            ctx.camera.turnTo(tree);
        } else {
            ctx.movement.step(tree.tile());
        }
    }
}
