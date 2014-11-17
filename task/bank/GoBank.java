package crChopChopper.task.bank;

import crChopChopper.ChopChopper;
import crChopChopper.task.Task;
import crChopChopper.visual.ScriptPaint;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class GoBank extends Task<ClientContext> {
    public GoBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && ctx.players.local().animation() == -1
                && !ctx.bank.select().poll().inViewport();
    }

    @Override
    public void execute() {
        if (!ctx.bank.poll().inViewport()) {
            if (ChopChopper.b.distanceTo(ctx.players.local()) < 15) {
                ScriptPaint.status = "Turn to bank";
                ctx.camera.turnTo(ctx.bank.nearest());
            } else {
                ctx.movement.step(ChopChopper.b);
                ScriptPaint.status = "Walking to bank";
            }
        }
    }
}
