package crChopChopper;

import crChopChopper.task.Task;
import crChopChopper.var.Variables;
import crChopChopper.var.WIDGET;
import crChopChopper.visual.ChopChopperGUI;
import crChopChopper.visual.Formatting;
import crChopChopper.visual.MousePaint;
import crChopChopper.visual.ScriptPaint;
import org.powerbot.script.*;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Hud;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Dakota on 10/21/2014.
 */
@Script.Manifest(name = "ChopChopper", description = "Basic woodcutter with drop, burn, and banking support", properties = "client=6")
public class ChopChopper extends PollingScript<ClientContext> implements PaintListener, MessageListener {
    public static double version = 0.09;
    final int width = ctx.game.dimensions().width, height = ctx.game.dimensions().height;
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


    public void repaint(Graphics g) {
        MousePaint.drawMouse(g, ctx);
        ScriptPaint.drawPaint(g, ctx);
    }

    @Override
    public void stop() {
        Formatting f = new Formatting();
        savePaint(f.dateToStr);
    }

    public void savePaint(String name) {
        System.out.println("Screenshot saved at: " + ctx.controller.script().getStorageDirectory().getPath());
        repaint(img.createGraphics());
        img = img.getSubimage(ScriptPaint.paintX, ScriptPaint.paintY, ScriptPaint.paintW + 1, ScriptPaint.paintH + 1);
        final File path = new File(ctx.controller.script().getStorageDirectory().getPath(), name + ".png");

        try {
            ImageIO.write(img, "png", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start() {
        ScriptPaint.status = "Starting script";
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChopChopperGUI frame = new ChopChopperGUI(ctx);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        WIDGET.initWidgets(ctx);
    }

    @Override
    public void poll() {

        if (!ctx.game.loggedIn()) {
            Variables.loggedin = false;
        } else {
            Variables.loggedin = true;
        }

        if (!ctx.hud.opened(Hud.Window.BACKPACK)) {
            ctx.hud.open(Hud.Window.BACKPACK);
        }

        while (ctx.players.local().animation() == 16700 || ctx.players.local().stance() == 16701) {
            ScriptPaint.status = "Firemaking";
            Condition.sleep(Random.nextInt(50, 100));
        }


        if (ctx.game.loggedIn()) {
            if (ctx.camera.pitch() < 50) {
                ctx.camera.pitch(50);
            }
            for (Task task : ChopChopperGUI.taskList) {
                if (task.activate()) {
                    task.execute();
                }
            }
        } else {
            Condition.sleep(Random.nextInt(50, 100));
        }

    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        String s = messageEvent.text();

        if (s.contains("You get some")) {
            ScriptPaint.logC++;
        }
        if (s.contains("The fire catches")) {
            ScriptPaint.burnC++;
        }
        if (s.contains("You add a log to the fire")) {
            ScriptPaint.burnC++;
        }
    }
}