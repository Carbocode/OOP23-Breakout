package it.unibo.view;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
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
import java.util.logging.Logger;

/**
 * This class implements the gameView, that listens to the keys for moving the
 * bar/paddle and create the components.
 * 
 */
public class GameView extends JPanel {
    public static final long serialVersionUID = 4328743;
    private transient Set<Ball> balls;
    private transient Set<Brick> bricks;
    private transient Bar bar;
    private final GameView game = this;
    private final transient SoundManager sound = new SoundManagerImpl();
    private int score;
    private static final int INFO_X = GameInfo.GAME_WIDTH - 100;
    private static final int SCORE_Y = GameInfo.GAME_HEIGHT - 25;
    private static final int BOMB_Y = SCORE_Y - 30;
    private static final int DUPLI_Y = BOMB_Y - 30;
    private static final int ENLARGE_Y = DUPLI_Y - 30;
    private static final int FONT_SIZE_SCORE = 23;
    private transient Image backgroundImage;
    private final transient Logger log;

    /**
     * GameView constructor.
     * 
     */
    public GameView() {
        setPreferredSize(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
        backgroundPanel();
        log = Logger.getLogger(GameView.class.getName());
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
            final URL url = getClass().getClassLoader().getResource("images/bg.jpg");
            backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            log.warning(e.getMessage());
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
        for (final Ball ball : balls) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());

        }

        for (final Brick brick : bricks) {
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
        g.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE_SCORE));
        g.drawString(score + "pts", INFO_X, SCORE_Y);
        g.setColor(Color.RED);
        g.drawString(PowerUp.BOMB.getCDInSecs() + "S Bomb", INFO_X, BOMB_Y);
        g.setColor(Color.CYAN);
        g.drawString(PowerUp.DUPLI.getCDInSecs() + "S Dup", INFO_X, DUPLI_Y);
        g.setColor(Color.GREEN);
        g.drawString(PowerUp.ENLARGE.getCDInSecs() + "S Enl", INFO_X, ENLARGE_Y);

    }

    /**
     * this method update the game state.
     * 
     * @param balls
     * @param bricks
     * @param bar
     * @param score
     */
    @SuppressFBWarnings
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
            Runtime.getRuntime().exit(0);
        }
        if (bricks.isEmpty()) {
            sound.playVictorySound();
            JOptionPane.showMessageDialog(game,
                    "HAI VINTO\n SEI UN FENOMENO!!",
                    "YOU WIN",
                    JOptionPane.INFORMATION_MESSAGE);
            // close the window
            Runtime.getRuntime().exit(0);
        }
    }

    /**
     * this method return the balls of the game.
     * 
     * @return balls
     */
    public Set<Ball> getBalls() {
        return this.balls;
    }

    /**
     * this method return the bricks of the game.
     * 
     * @return bricks
     */
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * this method return the bar of the game.
     * 
     * @return bar
     */
    public Bar getBar() {
        return this.bar;
    }

    /**
     * this method return the score of the game.
     * 
     * @return score
     */
    public int getScore() {
        return this.score;
    }
}
