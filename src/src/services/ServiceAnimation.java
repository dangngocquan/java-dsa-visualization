package src.services;

import src.components.base.Panel;
import src.services.serviceanimations.*;

import java.awt.*;

public class ServiceAnimation extends Service {
    public static void translate(
            Panel panel, Location locationStart,
            int translateX, int translateY,
            int delay, int duration) {
        (new Transform(
                panel, locationStart,
                translateX, translateY,
                delay, duration)
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

    public static void transitionColor1(
            Component component,
            Component dependencyComponent,
            Color initialColor,
            Color targetColor,
            int delay,
            int duration) {
        TransitionColor animation = new TransitionColor(
                component, initialColor, targetColor, delay, duration
        );
        animation.setDependencyComponent(dependencyComponent);
        animation.start();
    }

    public static void transitionBorderColor(
            Component component,
            Color initialColor,
            Color targetColor,
            int delay,
            int duration) {
        (new TransitionBorderColor(
                component, initialColor, targetColor, delay, duration
        )).start();
    }

    public static void transitionBorderColor1(
            Component component,
            Component dependencyComponent,
            Color initialColor,
            Color targetColor,
            int delay,
            int duration) {
        TransitionBorderColor animation = new TransitionBorderColor(
                component, initialColor, targetColor, delay, duration
        );
        animation.setDependencyComponent(dependencyComponent);
        animation.start();
    }

    public static void delayVisible(
            Component component,
            boolean visible,
            int delay,
            int duration) {
        (new DelayVisible(
                component, visible, delay, duration
        )).start();
    }

    public static void transformArrowPanelNode(
            Panel rootScreen,
            int[] start,
            int[] end,
            int delay,
            int duration) {
        (new TransformArrowOfPanelNode(
                rootScreen, start, end, delay, duration
        )).start();
    }
}
