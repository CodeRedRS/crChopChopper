package crChopChopper.task;

import crChopChopper.visual.Paint;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

/**
 * Created by Dakota on 10/21/2014.
 */
public class Drop extends Task<ClientContext> {

    public Drop(ClientContext ctx) {
        super(ctx);
    }

    public void dropAllLogs() {
        for (Item i : ctx.backpack.items()) {
            if (i.name().toLowerCase().contains("logs")) {
                i.interact("Drop");
            }
        }
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
        Paint.status = "Dropping...";
        dropAllLogs();
    }
}
