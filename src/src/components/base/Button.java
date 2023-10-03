package src.components.base;

import src.services.animation.TransitionColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {
    private int x;
    private int y;
    private int width;
    private int height;
    private TransitionColor tempTransitionColor;
    private Color backgroundColorEntered;
    private Color backgroundColor;
    private int borderWidth;
    private Color borderColor;

    public Button(int x, int y, int width, int height, String text) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = new Color(210, 210, 210);
        this.backgroundColorEntered = new Color(200, 255, 200);
        this.borderWidth = 2;
        this.borderColor = Color.BLACK;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFocusPainted(false);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        setUI(new BasicButtonUI());
        addMouseListener(new MouseHandler());
        setBackground(new Color(210, 210, 210));
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
        setVisible(true);
    }

    public void setBackgroundColorEntered(Color backgroundColorEntered) {
        this.backgroundColorEntered = backgroundColorEntered;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        setBorder(new LineBorder(
                borderColor, borderWidth
        ));
    }

    private Component getInstance() {
        return this;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            if (getInstance().isEnabled()) {
                getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
                tempTransitionColor = new TransitionColor(
                        getInstance(),
                        backgroundColor,
                        backgroundColorEntered,
                        0, 300
                );
                tempTransitionColor.start();
            } else {
                getInstance().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            if (getInstance().isEnabled()) {
                tempTransitionColor = new TransitionColor(
                        getInstance(),
                        backgroundColorEntered,
                        backgroundColor,
                        0, 300
                );
                tempTransitionColor.start();
            }

        }
    }
}
