package src;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Config {
    public static Config instance = null;

    // Size
    public static int DEVICE_WIDTH = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getWidth());
    public static int DEVICE_HEIGHT = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getHeight()));
    public static int WIDTH = DEVICE_WIDTH - 14;
    public static int HEIGHT = DEVICE_HEIGHT - 36;
    public static int HEIGHT_VIEW_CONTROLLER = HEIGHT / 7;
    public static int HEIGHT_DESCRIPTION = 40;

    // Color
    public static Color COLOR_TEXT = new Color(20, 20, 20);
    public static Color BACKGROUND_COLOR_APP = new Color(222, 224, 236);
    public static Color COLOR_GREEN = new Color(0, 255, 0);
    public static Color COLOR_BLUE = new Color(0, 150, 255);
    public static Color COLOR_GRAY_1 = new Color(210, 210, 210);
    public static Color COLOR_GRAY_2 = new Color(150, 150, 150);
    public static Color COLOR_RED = new Color(255, 107, 107);
    public static Color COLOR_WHITE = new Color(255, 255, 255);
    public static Color COLOR_YELLOW = new Color(255, 255, 0);

    public static Color COLOR_BAR_PLAIN = COLOR_WHITE;
    public static Color COLOR_BAR_CHECKING = COLOR_YELLOW;
    public static Color COLOR_BAR_DONE = new Color(0, 255, 0);
    public static Color COLOR_BAR_TEMP_SORTED = COLOR_BLUE;
    public static Color COLOR_BAR_FLAG = new Color(255, 110, 110);
    public static Color COLOR_PINK = new Color(255, 210, 210);

    public static int DEFAULT_ANIMATION_SPEED = 1000;
    public static int MIN_ALGORITHM_ANIMATION_SPEED = 10;
    public static int MIN_DATASTRUCTURES_ANIMATION_SPEED = 1000;
    public static int MAX_NODE_TYPE_1 = 16;
    public static int MAX_NODE_TYPE_2 = 8;

    // Font
    public static final Font MONOSPACED_BOLD_6 = new Font(Font.MONOSPACED, Font.BOLD, 6);
    public static final Font MONOSPACED_BOLD_10 = new Font(Font.MONOSPACED, Font.BOLD, 10);
    public static final Font MONOSPACED_BOLD_12 = new Font(Font.MONOSPACED, Font.BOLD, 12);
    public static final Font MONOSPACED_BOLD_14 = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font MONOSPACED_BOLD_16 = new Font(Font.MONOSPACED, Font.BOLD, 16);
    public static final Font MONOSPACED_BOLD_18 = new Font(Font.MONOSPACED, Font.BOLD, 18);

    // Image
    public static ImageIcon LOGO = null;

    public Config() {
        instance = this;
        try {
            LOGO = new ImageIcon(
                    Objects.requireNonNull(instance.getClass().getResource("assets/images/logo.png")));
        } catch (Exception e) {
            System.out.println("Error when read img");
        }
    }
}