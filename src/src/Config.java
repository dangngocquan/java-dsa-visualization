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

    // Color
    public static Color COLOR_TEXT = new Color(20, 20, 20);
    public static Color BACKGROUND_COLOR_APP = new Color(222, 224, 236);
    public static Color COLOR_GREEN = new Color(200, 255, 200);
    public static Color COLOR_GRAY = new Color(210, 210, 210);
    public static Color COLOR_RED = new Color(255, 107, 107);
    // Font
    public static final Font ARIAL_BOLD_12 = new Font(Font.MONOSPACED, Font.BOLD, 12);
    public static final Font ARIAL_BOLD_14 = new Font(Font.MONOSPACED, Font.BOLD, 14);
    public static final Font ARIAL_BOLD_16 = new Font(Font.MONOSPACED, Font.BOLD, 16);
    public static final Font ARIAL_BOLD_18 = new Font(Font.MONOSPACED, Font.BOLD, 18);
}