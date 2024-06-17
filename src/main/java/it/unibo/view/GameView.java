package it.unibo.view;

import javax.swing.JPanel;

import it.unibo.api.GameInfo;
import it.unibo.model.Ball;
import it.unibo.model.Brick;
import it.unibo.model.Bar;

import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class implements the gameView, that listens to the keys for moving the
 * bar/paddle and create the components.
 * 
 */
public class GameView extends JPanel {
    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Bar bar;

    /**
     * GameView constructor.
     * 
     */
    public GameView() {
        setPreferredSize(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(final KeyEvent e) {

            bar.buttonReleased(e);
        }

        @Override
        public void keyPressed(final KeyEvent e) {

            bar.buttonPressed(e);
        }
    }

    /**
     * This method paint the components of the game.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Ball ball : balls) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());

        }

        for (Brick brick : bricks) {
            g.setColor(brick.getColor());
            if (brick.isAlive()) {
                g.fillRect((int) brick.getPosition().getX(), (int) brick.getPosition().getY(),
                        (int) brick.getSize().getWidth(), (int) brick.getSize().getHeight());
            }

        }
        g.setColor(Color.MAGENTA);
        g.fillRect((int) (bar.getPosition().getX()),
                (int) (bar.getPosition().getY() - bar.getSize().getHeight() / 2),
                (int) bar.getSize().getWidth(), (int) bar.getSize().getHeight());

    }

    /**
     * this method update the game state.
     * 
     * @param balls
     * @param bricks
     * @param bar
     */
    public void updateGameState(final Set<Ball> balls, final Set<Brick> bricks, final Bar bar) {
        this.balls = balls;
        this.bricks = bricks;
        this.bar = bar;

    }
}
