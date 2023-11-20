package src.services;

import javax.swing.*;
import java.awt.*;

public class ServiceComponent extends Service {
    public static JFrame getFrame(Component theComponent) {
        Component currParent = theComponent;
        JFrame theFrame = null;
        while (currParent != null) {
            if (currParent instanceof JFrame) {
                theFrame = (JFrame) currParent;
                break;
            }
            currParent = currParent.getParent();
        }
        return theFrame;
    }

    public static void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h, Color color) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xM = D - d, xN = xM, yM = h, yN = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xM * cos - yM * sin + x1;
        yM = xM * sin + yM * cos + y1;
        xM = x;

        x = xN * cos - yN * sin + x1;
        yN = xN * sin + yN * cos + y1;
        xN = x;

        int[] xPoints = { x2, (int) xM, (int) xN };
        int[] yPoints = { y2, (int) yM, (int) yN };

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        int x3 = x1 == x2? x1 : x2 - 4 * dx / (Math.abs(dx));
        int y3 = y1 == y2? y1 : y2 - 4 * dy / (Math.abs(dy));
        g2d.drawLine(x1, y1, x3, y3);

        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    public static void drawPatternArrow1(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        int xCenter;
        if ((y2-y1) * (x2-x1) <= 0) {
            xCenter = (x1 + 2 * x2) / 3;
        } else {
            xCenter = (2 * x1 + x2) / 3;
        }
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.drawLine(x1, y1, xCenter, y1);
        g2d.drawLine(xCenter, y1, xCenter, y2);
        drawArrowLine(g, xCenter, y2, x2, y2, 10, 4, color);
    }

    public static void drawPatternArrow2(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y1);
        drawArrowLine(g, x2, y1, x2, y2, 10, 4, color);
    }
}
