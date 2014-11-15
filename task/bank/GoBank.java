package crChopChopper.task.bank;

import crChopChopper.task.Task;
import crChopChopper.visual.Paint;
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
                && !ctx.bank.inViewport();
    }

    @Override
    public void execute() {
        Paint.status = "Going to bank...";
        ctx.movement.step(ctx.bank.nearest());
        if (ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 15) {
            Paint.status = "Turn to bank...";
            ctx.camera.turnTo(ctx.bank.nearest());
        }
    }
}
