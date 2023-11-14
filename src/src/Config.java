package src;

import java.awt.*;

public class Config {
    // Size
    public static int DEVICE_WIDTH = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getWidth());
    public static int DEVICE_HEIGHT = (int) ((GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds().getHeight()));
    public static int WIDTH = DEVICE_WIDTH - 14;
    public static int HEIGHT = DEVICE_HEIGHT - 36;
    public static int HEIGHT_VIEW_CONTROLLER = HEIGHT / 7;

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
    public static Color COLOR_BAR_SMALLER_PIVOT = new Color(255, 210, 210);

    // Font
    public static final Font MONOSPACED_BOLD_6 = new Font(Font.MONOSPACED, Font.BOLD, 6);
    public static final Font MONOSPACED_BOLD_12 = new Font(Font.MONOSPACED, Font.BOLD, 12);
    public static final Font MONOSPACED_BOLD_14 = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font MONOSPACED_BOLD_16 = new Font(Font.MONOSPACED, Font.BOLD, 16);
    public static final Font MONOSPACED_BOLD_18 = new Font(Font.MONOSPACED, Font.BOLD, 18);
}