package crChopChopper.visual;

import crChopChopper.ChopChopper;
import crChopChopper.var.Variables;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Constants;

import java.awt.*;

/**
 * Created by Dakota on 10/22/2014.
 */
public class ScriptPaint extends ClientAccessor {
    public static int paintX = 2, paintY = 2, paintW = 160, paintH;
    // TIMERS \\
    public static long startTime, runTime;
    // PAINT STUFF \\
    public static String status;
    public static String mode;
    public static int logC, burnC;
    static Color bg = new Color(0, 0, 0, 128);

    public ScriptPaint(ClientContext ctx) {
        super(ctx);
    }

    private static void drawPoint(Graphics g, Tile tile, String name, Color c, int s, ClientContext ctx) {
        Point p = tile.matrix(ctx).mapPoint();

        g.setColor(c);
        g.drawString(name, p.x + s, p.y + (s / 2));
        g.fillArc(p.x - (s / 2), p.y - (s / 2), s, s, 0, 360);
        g.setColor(Color.BLACK);
        g.drawArc(p.x - (s / 2), p.y - (s / 2), s, s, 0, 360);
    }

    public static void drawPaint(Graphics g1, ClientContext ctx) {
        // TIME \\
        runTime = ctx.controller.script().getTotalRuntime();
        if (status != null && startTime > 0 && Variables.loggedin) {
            Graphics2D g = (Graphics2D) g1;
            if (ctx.objects.select().id(Variables.selectedTreeID).nearest().poll() != null) {
                Graphics2D obj = (Graphics2D) g1.create();
                drawPoint(obj, ctx.objects.select().id(Variables.selectedTreeID).nearest().poll().tile(), "Current tree", Color.GREEN, 10, ctx);
            }

            g.setColor(bg);
            g.fillRect(paintX, paintY, paintW, 207);
            paintH = 207;
            g.setColor(Color.GREEN);
            g.drawRect(paintX, paintY, paintW, 207);
            Formatting.drawCenteredString("Chop Chopper v" + ChopChopper.version, 162, 20, g);
            g.drawLine(12, 20, 150, 20);

            g.drawString("Status: " + status, 5, 35);
            g.drawString("Run time: " + Formatting.formatTime(runTime), 5, 52);
            g.drawString("Tree: " + Variables.selectedTreeName + " (" + mode + ")", 5, 69);
            g.drawString("Logs: " + Formatting.format.format(logC), 5, 86);
            g.drawString("WC level: " + Formatting.currentLevel(Constants.SKILLS_WOODCUTTING, ctx)
                    + " (+" + Formatting.gainedLevels(Constants.SKILLS_WOODCUTTING, 1, ctx) + ")", 5, 103);

            g.drawString("XP gained:", 5, 120);
            g.drawString(Formatting.format.format(Formatting.gainedXp(Constants.SKILLS_WOODCUTTING, 1, ctx))
                    + " (" + Formatting.format.format(Formatting.hourlyXp(1, ctx)) + ")", 23, 137);

            g.drawString("To " + (Formatting.currentLevel(Constants.SKILLS_WOODCUTTING, ctx) + 1) + ":", 5, 154);
            g.drawString(Formatting.format.format(Formatting.xpTilLevel(Constants.SKILLS_WOODCUTTING, ctx))
                    + " (" + Formatting.getTimeToNextLevel(Constants.SKILLS_WOODCUTTING, Formatting.hourlyXp(1, ctx), ctx) + ")", 23, 171);

            g.drawString("To 99:", 5, 188);
            g.drawString(Formatting.format.format(Formatting.xpTil99(Constants.SKILLS_WOODCUTTING, ctx))
                    + " (" + Formatting.getTimeTo99(Constants.SKILLS_WOODCUTTING, Formatting.hourlyXp(1, ctx), ctx) + ")", 23, 205);

            if (Variables.burnLogs) {
                paintH = 348;
                g.setColor(bg);
                g.fillRect(2, 212, 160, 136);
                g.setColor(Color.ORANGE);
                g.drawRect(2, 212, 160, 136);
                g.drawString("Burned: " + burnC, 5, 225);
                g.drawString("FM level: " + Formatting.currentLevel(Constants.SKILLS_FIREMAKING, ctx)
                        + " (+" + Formatting.gainedLevels(Constants.SKILLS_FIREMAKING, 0, ctx) + ")", 5, 242);

                g.drawString("XP gained:", 5, 259);
                g.drawString(Formatting.format.format(Formatting.gainedXp(Constants.SKILLS_FIREMAKING, 0, ctx))
                        + " (" + Formatting.format.format(Formatting.hourlyXp(0, ctx)) + ")", 23, 276);

                g.drawString("To " + (Formatting.currentLevel(Constants.SKILLS_FIREMAKING, ctx) + 1) + ":", 5, 293);
                g.drawString(Formatting.format.format(Formatting.xpTilLevel(Constants.SKILLS_FIREMAKING, ctx))
                        + " (" + Formatting.getTimeToNextLevel(Constants.SKILLS_FIREMAKING, Formatting.hourlyXp(0, ctx), ctx) + ")", 23, 310);

                g.drawString("To 99:", 5, 327);
                g.drawString(Formatting.format.format(Formatting.xpTil99(Constants.SKILLS_FIREMAKING, ctx))
                        + " (" + Formatting.getTimeTo99(Constants.SKILLS_FIREMAKING, Formatting.hourlyXp(0, ctx), ctx) + ")", 23, 344);
            }

        } else {
            g1.setColor(new Color(125, 0, 0, 128));
            g1.fillRect(2, 2, 150, 22);
            g1.setColor(Color.WHITE);
            g1.drawRect(2, 2, 150, 22);
            if (ctx.game.loggedIn()) {
                Formatting.drawCenteredString("Setting up script!", 152, 24, g1);
            } else {
                Formatting.drawCenteredString("Logging in!", 152, 24, g1);
            }
        }
    }
}
