package it.unibo.view;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.api.GameInfo;
import it.unibo.api.GameLoopAccessor;
import it.unibo.api.SoundManager;
import it.unibo.api.View;
import it.unibo.controller.Match;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Ball;
import it.unibo.model.Brick;
import it.unibo.model.ScoreboardImpl;
import it.unibo.model.Bar;
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
public class GameView extends JPanel implements View {
    public static final long serialVersionUID = 4328743;
    private final transient GameLoopAccessor gl;
    private final transient ScoreboardImpl sb = new ScoreboardImpl();
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
     */
    public GameView() {
        setPreferredSize(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT));
        addKeyListener(new TAdapter());
        setFocusable(true);
        backgroundPanel();
        log = Logger.getLogger(GameView.class.getName());
        gl = Match.getGameLoop();
    }

    private final class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(final KeyEvent e) {
            gl.handleKeyRelease(e);
        }

        @Override
        public void keyPressed(final KeyEvent e) {
            gl.handleKeyPress(e);
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

        g.setColor(Color.RED);
        for (final Ball ball : gl.getBalls()) {
            g.fillOval((int) ball.getPosition().getX(), (int) ball.getPosition().getY(),
                    (int) ball.getSize().getWidth(), (int) ball.getSize().getHeight());

        }

        for (final Brick brick : gl.getBricks()) {
            g.setColor(brick.getColor());
            if (brick.isAlive()) {
                g.fillRect((int) brick.getPosition().getX(), (int) brick.getPosition().getY(),
                        (int) brick.getSize().getWidth() - 1, (int) brick.getSize().getHeight() - 1);
            }

        }
        g.setColor(Color.MAGENTA);
        final Bar bar = gl.getBar();
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
     * @param score current score
     */
    @Override
    public void updateGameState(final int score) {
        this.repaint();
        this.score = score;

        //if in the game area there are no more balls the player lost
        if (gl.getBalls().isEmpty()) {
            sound.playGameOverSound();

            String input;
            do {
                // Prompt for a 3-character string input
                input = JOptionPane.showInputDialog(null,
                "YOU LOST! :(\ninsert your name (3 characters only uppercase [A-Z])",
                "YOU LOST!",
                JOptionPane.QUESTION_MESSAGE);
            } while (!input.matches("^[A-Z]{3}$"));

            //add to scoreboard
            sb.add(input, score);
            // close the window
            Runtime.getRuntime().exit(0);
        }

        //if game area has no more bricks tha player completed the challenge -> won
        if (gl.getBricks().isEmpty()) {
            sound.playVictorySound();
            String input;
            do {
                // Prompt for a 3-character string input
                input = JOptionPane.showInputDialog(null,
            "Congratulation YOU WON!!\ninsert your name (3 characters only uppercase [A-Z])",
                "YOU WON!",
                JOptionPane.QUESTION_MESSAGE);
            } while (!input.matches("^[A-Z]{3}$"));

            //add to scoreboard
            sb.add(input, score);
            // close the window
            Runtime.getRuntime().exit(0);
        }
    }

}
