package crChopChopper.task.antiban;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Hud;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author: Zohan
 * @platform: RSBot
 */
public class AntiPattern extends ClientAccessor<ClientContext> {

    private final Component SKILL_WINDOW;
    private final Component SKILL_CONTAINER;


    public AntiPattern(ClientContext arg0) {
        super(arg0);
        SKILL_WINDOW = ctx.widgets.component(1466, 2);
        SKILL_CONTAINER = ctx.widgets.component(1466, 7);
    }

    public void mouseOffScreen() {
        ctx.input.move(getOffScreenX(), getOffScreenY());
    }

    public void skillHover(AntiPattern.Skill s, final int min, final int max) {
        if (!ctx.hud.opened(Hud.Window.SKILLS)) {
            ctx.hud.open(Hud.Window.SKILLS);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.hud.opened(Hud.Window.SKILLS);
                }
            });
        }
        final Component skill = SKILL_CONTAINER.component(s.getIndex());
        final Rectangle windowRect = new Rectangle(SKILL_WINDOW.screenPoint().x, SKILL_WINDOW.screenPoint().y,
                SKILL_WINDOW.scrollWidth(), SKILL_WINDOW.scrollHeight());
        if (!windowRect.contains(skill.boundingRect())) {
            ctx.input.move(SKILL_WINDOW.nextPoint());
            while (SKILL_WINDOW.scrollY() > 0) {
                ctx.input.scroll(false);
            }
            while (!windowRect.contains(skill.boundingRect())) {
                ctx.input.scroll(true);
                Condition.sleep(Random.nextInt(35, 65));
            }
        }
        ctx.input.move(skill.nextPoint());
        Condition.sleep(Random.nextInt(min, max));
    }

    public void misclick(final Component component, final int min, final int max) {
        int x, y;
        if (Random.nextBoolean()) {
            x = component.boundingRect().x - Random.nextInt(min, max);
        } else {
            x = component.boundingRect().x + component.boundingRect().width + Random.nextInt(min, max);
        }
        if (Random.nextBoolean()) {
            y = component.boundingRect().y - Random.nextInt(min, max);
        } else {
            y = component.boundingRect().y + component.boundingRect().height + Random.nextInt(min, max);
        }
        ctx.input.move(x, y);
        if (ctx.input.click(true)) {
            Condition.sleep(Random.nextInt(50, 350));
        }
    }

    public void openRandomHud() {
        final List<Hud.Window> windows = Arrays.asList(Hud.Window.values());
        final Hud.Window target = windows.get(Random.nextInt(0, windows.size() - 1));
        if (!ctx.hud.open(target)) {
            ctx.hud.open(target);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.hud.opened(target);
                }
            });
        }
    }

    private int getOffScreenX() {
        if (Random.nextBoolean()) {
            final int width = ctx.client().getCanvas().getWidth();
            return width + Random.nextInt(20, 50);
        } else {
            return 0 - Random.nextInt(20, 50);
        }
    }

    private int getOffScreenY() {
        if (Random.nextBoolean()) {
            final int height = ctx.client().getCanvas().getHeight();
            return height + Random.nextInt(20, 50);
        } else {
            return 0 - Random.nextInt(20, 50);
        }
    }

    public enum Skill {
        ATTACK(0),
        CONSITUTION(1),
        MINING(2),
        STRENGTH(3),
        AGILITY(4),
        SMITHING(5),
        DEFENCE(6),
        HERBLORE(7),
        FISHING(8),
        RANGED(9),
        THIEVING(10),
        COOKING(11),
        PRAYER(12),
        CRAFTING(13),
        FIREMAKING(14),
        MAGIC(15),
        FLETCHING(16),
        WOODCUTTING(17),
        RUNECRAFTING(18),
        SLAYER(19),
        FARMING(20),
        CONSTRUCTION(21),
        HUNTER(22),
        SUMMONING(23),
        DUNGEONEERING(24),
        DIVINATION(25);

        private final int index;

        Skill(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

}