package crChopChopper.task.bank;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.visual.Paint;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class Deposite extends Task<ClientContext> {
    public Deposite(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.opened()
                && ctx.backpack.select().count() == 28
                && ctx.backpack.select().id(Variables.OBJECT.LOGS).count() > 0;
    }

    @Override
    public void execute() {
        Paint.status = "Depositing...";
        ctx.bank.depositInventory();
    }
}
