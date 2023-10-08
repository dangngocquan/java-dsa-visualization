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
    private Color backgroundColorClicked;
    private Color backgroundColor;
    private int borderWidth;
    private Color borderColor;
    private boolean isClicked;
    private boolean hasStatusClicked = false;

    public Button(int x, int y, int width, int height, String text) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = new Color(210, 210, 210);
        this.backgroundColorEntered = new Color(200, 255, 200);
        this.backgroundColorClicked = new Color(150, 150, 255);
        this.borderWidth = 2;
        this.borderColor = Color.BLACK;
        this.isClicked = false;

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

    public void setEnabledButton(boolean enable) {
        if (enable) {
            setBorderColor(Color.BLACK);
        } else {
            setBorderColor(Color.GRAY);
        }
        setEnabled(enable);
        setBackground(backgroundColor);
    }

    public void setIsClicked(boolean isClicked) {
        if (isEnabled() && hasStatusClicked) {
            this.isClicked = isClicked;
            if (isClicked) {
                setBackground(backgroundColorClicked);
            } else {
                setBackground(backgroundColor);
            }
        }
    }

    public void setHasStatusClicked(boolean hasStatusClicked) {
        this.hasStatusClicked = hasStatusClicked;
    }

    private Component getInstance() {
        return this;
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (hasStatusClicked) {
                if (tempTransitionColor != null) {
                    tempTransitionColor.stop();
                }
                if (getInstance().isEnabled()) {
                    isClicked = true;
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColorClicked,
                            0, 300
                    );
                    tempTransitionColor.start();
                }
            }
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
                if (isClicked) {
                    getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColor,
                            backgroundColorClicked,
                            0, 300
                    );
                } else {
                    getInstance().setCursor(new Cursor(Cursor.HAND_CURSOR));
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColor,
                            backgroundColorEntered,
                            0, 300
                    );
                }
                tempTransitionColor.start();
            } else {
                getInstance().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setBackground(backgroundColor);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (tempTransitionColor != null) {
                tempTransitionColor.stop();
            }
            if (getInstance().isEnabled()) {
                if (isClicked) {
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColorClicked,
                            0, 300
                    );
                } else {
                    tempTransitionColor = new TransitionColor(
                            getInstance(),
                            backgroundColorEntered,
                            backgroundColor,
                            0, 300
                    );
                }
                tempTransitionColor.start();
            } else {
                setBackground(backgroundColor);
            }

        }
    }
}
