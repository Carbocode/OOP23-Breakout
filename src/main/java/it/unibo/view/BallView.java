package it.unibo.view;

import it.unibo.model.*;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class BallView extends Ball {
    // coords of the ball
    private int x;
    private int y;
    // diameter of the ball
    private int diameter;

    private boolean isAlive;
    private JPanel panel;

    public BallView(int x, int y, int diameter, JPanel panel) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.isAlive = super.isAlive();
        this.panel = panel;
    }

    public void draw(Graphics g) {
        if (isAlive) {
            g.setColor(Color.RED);
            g.fillOval(this.x, this.y, this.diameter, this.diameter);
        }
        panel.repaint();
    }
}
