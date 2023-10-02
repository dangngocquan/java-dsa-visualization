package src.components.base;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class ComboBox<E> extends JComboBox<E> {
    private int x;
    private int y;
    private int width;
    private int height;
    private E[] choices;
    private int borderWidth;
    private Color borderColor;

    public ComboBox(int x, int y, int width, int height, E[] choices) {
        super(choices);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.choices = choices;
        this.borderWidth = 1;
        this.borderColor = Color.BLACK;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setUI(new BasicComboBoxUI());
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof AbstractButton) {
                AbstractButton button = (AbstractButton) component;
                button.setFocusPainted(false);
                button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
                button.setUI(new BasicButtonUI());
                button.setBackground(new Color(210, 210, 210));
                button.setBorder(new LineBorder(
                        borderColor, borderWidth
                ));
            }
        }

        setVisible(true);
    }
}
