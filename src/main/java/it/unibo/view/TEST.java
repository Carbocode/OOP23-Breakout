package it.unibo.view;

import javax.swing.*;

import it.unibo.model.*;
import java.util.*;

import java.awt.*;

public class TEST extends JPanel {
    private Set<Ball> balls;
    private Set<Brick> bricks;
    private BarImpl bar;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Ball ball : balls) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());

        }

        for (Brick brick : bricks) {
            g.setColor(brick.getColor());
            g.fillRect((int) brick.getPosition().getX(), (int) brick.getPosition().getY(),
                    (int) brick.getSize().getWidth(), (int) brick.getSize().getHeight());
        }
        g.setColor(Color.MAGENTA);
        g.fillRect((int) bar.getPosition().getX(), (int) bar.getPosition().getY(),
                (int) bar.getSize().getWidth(), (int) bar.getSize().getHeight());

    }

    public void updateGameState(Set<Ball> balls, Set<Brick> bricks, BarImpl bar) {
        this.balls = balls;
        this.bricks = bricks;
        this.bar = bar;

    }
}
