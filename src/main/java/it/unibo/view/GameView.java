package it.unibo.view;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.controller.ScoreManagerImpl;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Ball;
import it.unibo.model.Brick;
import it.unibo.model.Bar;

import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

/**
 * This class implements the gameView, that listens to the keys for moving the
 * bar/paddle and create the components.
 * 
 */
public class GameView extends JPanel {
    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Bar bar;
    private GameView game;
    private SoundManager sound = new SoundManagerImpl();
    private int score;
    private final int infoX = GameInfo.GAME_WIDTH - 100;
    private final int scoreY = GameInfo.GAME_HEIGHT - 25;
    private final int bombY = scoreY - 30;
    private final int dupliY = bombY - 30;
    private final int enlargeY = dupliY - 30;
    private final int fontSizeScore = 23;
    private Image backgroundImage;

    /**
     * GameView constructor.
     * 
     */
    public GameView() {
        setPreferredSize(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
        backgroundPanel();
    }

    private final class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(final KeyEvent e) {

            bar.buttonReleased(e);
        }

        @Override
        public void keyPressed(final KeyEvent e) {

            bar.buttonPressed(e);
        }
    }

    private void backgroundPanel() {
        try {
            // Carica l'immagine dall'URL
            URL url = getClass().getClassLoader().getResource("images/bg.jpg");
            backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method paint the components of the game.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.RED);
        for (Ball ball : balls) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());

        }

        for (Brick brick : bricks) {
            g.setColor(brick.getColor());
            if (brick.isAlive()) {
                g.fillRect((int) brick.getPosition().getX(), (int) brick.getPosition().getY(),
                        (int) brick.getSize().getWidth() - 1, (int) brick.getSize().getHeight() - 1);
            }

        }
        g.setColor(Color.MAGENTA);
        g.fillRect((int) (bar.getPosition().getX()),
                (int) (bar.getPosition().getY() - bar.getSize().getHeight() / 2),
                (int) bar.getSize().getWidth(), (int) bar.getSize().getHeight());

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Monospaced", Font.BOLD, fontSizeScore));
        g.drawString(score + "pts", infoX, scoreY);
        g.setColor(Color.RED);
        g.drawString(PowerUp.BOMB.getCDInSecs() + "S Bomb", infoX, bombY);
        g.setColor(Color.CYAN);
        g.drawString(PowerUp.DUPLI.getCDInSecs() + "S Dup", infoX, dupliY);
        g.setColor(Color.GREEN);
        g.drawString(PowerUp.ENLARGE.getCDInSecs() + "S Enl", infoX, enlargeY);

    }

    /**
     * this method update the game state.
     * 
     * @param balls
     * @param bricks
     * @param bar
     * @param score
     */
    public void updateGameState(final Set<Ball> balls, final Set<Brick> bricks, final Bar bar,
            final int score) {
        this.balls = balls;
        this.bricks = bricks;
        this.bar = bar;
        this.score = score;

        if (balls.isEmpty()) {
            sound.playGameOverSound();
            JOptionPane.showMessageDialog(game,
                    "HAI PERSO\nma d'altronde uomini forti destini forti\nuomini deboli destini deboli",
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            // close the window
            System.exit(0);
        }
        if (bricks.isEmpty()) {
            sound.playVictorySound();
            JOptionPane.showMessageDialog(game,
                    "HAI VINTO\n SEI UN FENOMENO!!",
                    "YOU WIN",
                    JOptionPane.INFORMATION_MESSAGE);
            // close the window
            System.exit(0);
        }
    }
}
