package it.unibo.view;

import javax.swing.*;
import java.awt.*;

import it.unibo.model.*;

public class BarView {
    // coords of the bar
    private int x;
    private int y;
    // measures
    private int width;
    private int height;

    private boolean isAlive;
    private JPanel panel;

    public BarView(int x, int y, int width, int height, JPanel panel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isAlive = true;
        this.panel = panel;
    }

    public void draw(Graphics g) {
        if (isAlive) {
            g.setColor(Color.RED);
            g.fillOval(this.x, this.y, this.width, this.height);
        }
        panel.repaint();
    }
}
