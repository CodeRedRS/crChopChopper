package crChopChopper.visual;

import org.powerbot.script.rt6.ClientContext;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by CodeRed on 10/30/2014.
 */
public class MousePaint implements MouseListener {
    public static Color c = Color.GREEN;
    static MouseTrail trail = new MouseTrail();

    public static void drawMouse(Graphics g1, ClientContext ctx) {
        ((Graphics2D) g1).setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        Point p = ctx.input.getLocation();
        final int outerSize = 10;

        // MOUSE ROTATION \\
        /*
        Graphics2D spinG = (Graphics2D) g1.create();
        Graphics2D spinGRev = (Graphics2D) g1.create();
        Graphics2D spinG2 = (Graphics2D) g1.create();

        spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * 360d * Math.PI / 180.0, p.x, p.y);
        spinG2.rotate(System.currentTimeMillis() % 2000d / 2000d * 360d * Math.PI / 180.0, p.x, p.y);
        spinGRev.rotate(System.currentTimeMillis() % 2500d / 2500d * (-360d) * 2 * Math.PI / 180.0, p.x, p.y);
        */

        // MOUSE TRAIL \\
        trail.add(p);
        trail.drawMouseTrail(g1);
        g1.setColor(c);


        // X CROSS \\
        g1.drawLine(p.x + (outerSize / 2), p.y + (outerSize / 2), p.x - (outerSize / 2), p.y - (outerSize / 2));
        g1.drawLine(p.x + (outerSize / 2), p.y - (outerSize / 2), p.x - (outerSize / 2), p.y + (outerSize / 2));

        // + CROSS \\
        /*
        g1.drawLine(p.x + (outerSize / 2), p.y, p.x - (outerSize / 2), p.y);
        g1.drawLine(p.x, p.y - (outerSize / 2), p.x, p.y + (outerSize / 2));
        */

        g1.drawArc(p.x - (outerSize), p.y - (outerSize), outerSize * 2, outerSize * 2, 0, 360);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        c.equals(Color.RED);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        c.equals(Color.GREEN);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private static class MouseTrail {
        private final static int SIZE = 15;

        private Point[] points;
        private int index;

        public MouseTrail() {
            points = new Point[SIZE];
            index = 0;
        }

        public void add(Point p) {
            points[index++] = p;
            index %= SIZE;
        }

        public void drawMouseTrail(Graphics g) {
            for (int i = index; i != (index == 0 ? SIZE - 1 : index - 1); i = (i + 1)
                    % SIZE) {
                if (points[i] != null && points[(i + 1) % SIZE] != null) {
                    Graphics2D mouse = (Graphics2D) g.create();
                    mouse.setColor(Color.GREEN);
                    mouse.drawLine(points[i].x, points[i].y, points[(i + 1) % SIZE].x, points[(i + 1) % SIZE].y);
                }
            }
        }
    }
}
