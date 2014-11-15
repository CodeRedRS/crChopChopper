package crChopChopper.task;

import crChopChopper.var.WIDGET;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by CodeRed on 11/12/2014.
 */
public class Interface extends Task<ClientContext> {
    public Interface(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return WIDGET.HERO_INTERFACE_EXIT.visible() || WIDGET.CRAFT_INTERFACE.visible() || WIDGET.COOK_INTERFACE.visible();
    }

    @Override
    public void execute() {
        if (WIDGET.CRAFT_INTERFACE.visible()) {
            WIDGET.CRAFT_INTERFACE.click();
            Condition.sleep(Random.nextInt(900, 1500));
        } else if (WIDGET.HERO_INTERFACE_EXIT.visible()) {
            WIDGET.HERO_INTERFACE_EXIT.click();
            Condition.sleep(Random.nextInt(900, 1500));
        } else if (WIDGET.COOK_INTERFACE.visible()) {
            WIDGET.COOK_INTERFACE.click();
            Condition.sleep(Random.nextInt(900, 1500));
        }
    }
}
