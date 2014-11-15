package crChopChopper.task;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

/**
 * Created by Dakota on 10/21/2014.
 */
public abstract class Task<C extends ClientContext> extends ClientAccessor<C> {
    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean activate();

    public abstract void execute();
}