package src.services.serviceanimations;


import src.components.base.Button;
import src.components.base.Panel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TransitionBorderColor {
    private Timer timer;
    private final Component component;
    private final Color initialColor;
    private final Color targetColor;
    private final int absR;
    private final int absG;
    private final int absB;
    private final int delay;
    private final int duration;
    private long msStart;
    private Component dependencyComponent;


    public TransitionBorderColor(Component component, Color initialColor, Color targetColor, int delay, int duration) {
        this.component = component;
        this.initialColor = initialColor;
        this.targetColor = targetColor;
        this.absR = targetColor.getRed() - initialColor.getRed();
        this.absG = targetColor.getGreen() - initialColor.getGreen();
        this.absB = targetColor.getBlue() - initialColor.getBlue();
        this.delay = delay;
        this.duration = duration;
    }

    public void setDependencyComponent(Component panel) {
        this.dependencyComponent = panel;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
        if (component instanceof Panel) {
            ((Panel) component).setBorderColor(targetColor);
        } else if (component instanceof Button){
            ((Button) component).setBorderColor(targetColor);
        }
        if (dependencyComponent != null) dependencyComponent.repaint();
    }

    private class TaskAnimation extends TimerTask {
        @Override
        public void run() {
            long temp = System.currentTimeMillis() - msStart;
            double process = temp * 1.0 / duration;
            if (process >= 1f) {
                stop();
                return;
            }
            if (component instanceof Panel) {
                ((Panel) component).setBorderColor(
                        new Color(
                                initialColor.getRed() + (int) (absR * process),
                                initialColor.getGreen() + (int) (absG * process),
                                initialColor.getBlue() + (int) (absB * process)
                        )
                );
            } else if (component instanceof Button){
                ((Button) component).setBorderColor(
                        new Color(
                                initialColor.getRed() + (int) (absR * process),
                                initialColor.getGreen() + (int) (absG * process),
                                initialColor.getBlue() + (int) (absB * process)
                        )
                );
            }
            if (dependencyComponent != null) dependencyComponent.repaint();
        }
    }
}
