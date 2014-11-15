package crChopChopper.visual;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Constants;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CodeRed on 10/27/2014.
 */
public class Formatting {
    public static int startLvlWc;
    public static int startLvlFm;
    public static int startXpWc;
    public static int startXpFm;
    public static NumberFormat format = new DecimalFormat("###,###,###");

    Date curDate = new Date();
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-M-yyyy (hh-mm-ss)");
    public String dateToStr = formatDate.format(curDate);

    public static String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
                + (s < 10 ? "0" + s : s);
    }

    /**
     * Centers the input string.
     *
     * @param s String to output
     * @param w Area width
     * @param h Area height
     * @param g
     */
    public static void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    /**
     * Returns the time till your character advances a level in the selected skill.
     *
     * @param skill     The skill to track
     * @param xpPerHour The hourly experience rate
     * @param ctx
     * @return
     */
    public static String getTimeToNextLevel(final int skill, final long xpPerHour, ClientContext ctx) {
        if (xpPerHour < 1) {
            return formatTime(0L);
        }
        return formatTime((long) ((ctx.skills.experienceAt(ctx.skills.level(skill) + 1) - ctx.skills.experience(skill)) * 3600000D / xpPerHour));
    }

    /**
     * Returns the time till your character reaches max level in the selected skill.
     *
     * @param skill     The skill to track
     * @param xpPerHour The hourly experience rate
     * @param ctx
     * @return
     */
    public static String getTimeTo99(final int skill, final long xpPerHour, ClientContext ctx) {
        if (xpPerHour < 1) {
            return formatTime(0L);
        }
        return formatTime((long) ((ctx.skills.experienceAt(99) - ctx.skills.experience(skill)) * 3600000D / xpPerHour));
    }

    /**
     * Returns the current level of the selected skill.
     *
     * @param skill The skill to track
     * @param ctx
     * @return
     */
    public static int currentLevel(final int skill, ClientContext ctx) {
        return ctx.skills.level(skill);
    }

    /**
     * Returns the gained levels in the selected skill.
     *
     * @param skill The skill to track
     * @param s     0 for Firemaking
     * @param ctx
     * @return
     */
    public static int gainedLevels(final int skill, final int s, ClientContext ctx) {
        if (s == 0) {
            return ctx.skills.level(skill) - startLvlFm;
        }
        return ctx.skills.level(skill) - startLvlWc;
    }

    /**
     * Returns the gained experience in the selected skill.
     *
     * @param skill The skill to track
     * @param s     0 for Firemaking
     * @param ctx
     * @return
     */
    public static int gainedXp(final int skill, final int s, ClientContext ctx) {
        if (s == 0) {
            return ctx.skills.experience(skill) - startXpFm;
        }
        return ctx.skills.experience(skill) - startXpWc;
    }

    /**
     * Returns the experience needed to advance the selected skill.
     *
     * @param skill The skill to track
     * @param ctx
     * @return
     */
    public static int xpTilLevel(final int skill, ClientContext ctx) {
        return ctx.skills.experienceAt(ctx.skills.level(skill) + 1) - ctx.skills.experience(skill);
    }

    /**
     * Returns the experience needed to reach max level in the selected skill.
     *
     * @param skill The skill to track
     * @param ctx
     * @return
     */
    public static int xpTil99(final int skill, ClientContext ctx) {
        return ctx.skills.experienceAt(99) - ctx.skills.experience(skill);
    }

    /**
     * Returns the hourly experience rate for the selected skill.
     *
     * @param s   0 for Firemaking
     * @param ctx
     * @return
     */
    public static long hourlyXp(final int s, ClientContext ctx) {
        if (s == 0) {
            return ((long) gainedXp(Constants.SKILLS_FIREMAKING, 0, ctx) * 3600000 / ctx.controller.script().getTotalRuntime());
        }
        return ((long) gainedXp(Constants.SKILLS_WOODCUTTING, 1, ctx) * 3600000 / ctx.controller.script().getTotalRuntime());
    }


}
