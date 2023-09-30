package src.services.animation;

import src.components.base.Panel;

import java.awt.*;

public class Animation {
    public static void translate(
            Panel panel, Location locationStart,
            int translateX, int translateY,
            int delay, int duration,
            int zOrder) {
        (new Transform(
                panel, locationStart,
                translateX, translateY,
                delay, duration, zOrder)
        ).start();
    }

    public static void transitionColor(
            Component component,
            Color initialColor,
            Color targetColor,
            int delay,
            int duration) {
        (new TransitionColor(
                component, initialColor, targetColor, delay, duration
        )).start();
    }
}
