package crChopChopper.task.bank;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.visual.ScriptPaint;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class CloseBank extends Task<ClientContext> {
    public CloseBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened()
                && ctx.backpack.select().count() == 0
                && ctx.backpack.select().id(Variables.OBJECT.LOGS).count() < 1;
    }

    @Override
    public void execute() {
        ScriptPaint.status = "Closing Bank";
        ctx.bank.close();
    }
}
