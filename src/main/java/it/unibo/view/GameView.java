package it.unibo.view;

import it.unibo.model.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GameView extends JFrame{
    private JPanel panel;
    private Graphics g;

    public void render(Set<Ball> balls, Set<Brick> bricks, BarImpl bar) {
        for (Ball ball : balls) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());
            g.setColor(Color.RED);
        }
        for (Brick brick : bricks) {
            g.fillRect((int) brick.getPosition().getX(), (int) brick.getPosition().getY(),
                    (int) brick.getSize().getWidth(), (int) brick.getSize().getHeight());
            g.setColor(brick.getColor());
        }

        g.fillRect((int) bar.getPosition().getX(), (int) bar.getPosition().getY(),
                (int) bar.getSize().getWidth(), (int) bar.getSize().getHeight());
        g.setColor(Color.MAGENTA);

        panel.repaint();
    }
    public GameView(){
        
        
    }
}
