package src.service.animation;

import javax.swing.*;

public class Animation {
    public static void transform(
            JPanel panel,
            int x0, int y0,
            int x1, int y1,
            int delay, int duration) {
        (new PanelTransform(
                panel,
                new Location(x0, y0),
                new Location(x1, y1),
                delay, duration)
        ).start();
    }
}
