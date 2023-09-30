package src.components.base;

import src.services.animation.Animation;
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

    public Button(int x, int y, int width, int height, String text) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 18));
        setUI(new BasicButtonUI());
        addMouseListener(new MouseHandler());
        setBackground(new Color(210, 210, 210));
        setBorder(new LineBorder(
                new Color(0, 0, 0), 1
        ));
        setVisible(true);
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
            tempTransitionColor = new TransitionColor(
                    getInstance(),
                    new Color(210, 210, 210),
                    new Color(200, 255, 200),
                    0, 300
            );
            tempTransitionColor.start();
            if (getInstance().isEnabled()) {
                getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                getInstance().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            tempTransitionColor = new TransitionColor(
                    getInstance(),
                    new Color(200, 255, 200),
                    new Color(210, 210, 210),
                    0, 300
            );
            tempTransitionColor.start();
        }
    }
}
