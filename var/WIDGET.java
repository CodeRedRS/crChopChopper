package crChopChopper.var;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;

/**
 * Created by CodeRed on 10/29/2014.
 */
public class WIDGET {
    public static Component CRAFT_INTERFACE;
    public static Component COOK_INTERFACE;
    public static Component HERO_INTERFACE_EXIT;
    public static Component SKILL_WINDOW;
    public static Component SKILL_CONTAINER;

    public static void initWidgets(ClientContext ctx) {
        CRAFT_INTERFACE = ctx.widgets.widget(1179).component(33);
        COOK_INTERFACE = ctx.widgets.widget(1371).component(20);
        HERO_INTERFACE_EXIT = ctx.widgets.widget(1477).component(379).component(1);
        SKILL_WINDOW = ctx.widgets.widget(1466).component(2);
        SKILL_CONTAINER = ctx.widgets.widget(1466).component(7);
    }
}
