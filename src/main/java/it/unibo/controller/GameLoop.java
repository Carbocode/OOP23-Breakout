package it.unibo.controller;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.model.BarImpl;
import it.unibo.view.GameView;
import it.unibo.view.SoundManagerImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import javax.swing.Timer;


/**
 * The GameLoop class implements the main game loop for updating game state
 * and rendering the game. It uses a Swing Timer to trigger periodic updates.
 */
public class GameLoop implements ActionListener {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;

    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private BarImpl paddle;

    private long lastUpdateTime;
    private Timer timer;

    private GameView ourView;

    /**
     * Constructs a new GameLoop object and initializes the game components.
     * Starts the game timer to trigger periodic updates.
     *
     * @param view the GameView object for rendering the game state
     */
    public GameLoop(final GameView view) {
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, (int) Math.floor(GameInfo.GAME_HEIGHT * 0.35));
        balls = new HashSet<>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new BarImpl(new Point((GameInfo.GAME_WIDTH / 2) - 100, GameInfo.GAME_HEIGHT),
        new Dimension(200, 5), 0, new Color(0));
        manager = new CollisionManager(balls, brickWall, paddle);
        ourView = view;
        ourView.updateGameState(balls, brickWall.getWall(), paddle);

        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }

    /**
     * Invoked when an action occurs. This method is called periodically by the Timer
     * to update the game state.
     *
     * @param e the action event
     */
    @Override
    public final void actionPerformed(final ActionEvent e) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastUpdateTime;

        if (elapsedTime >= UPDATE_INTERVAL) {
            update();
            lastUpdateTime = currentTime;
        }
    }

    /**
     * Updates the game state, including checking collisions, updating ball positions,
     * and removing dead entities.
     */
    private void update() {
        manager.checkAll();
        this.updateBalls();
        DeathCollector.checkEntities(balls);
        DeathCollector.checkEntities(brickWall.getWall());
    }

    /**
     * Updates the positions and states of all balls in the game.
     * Repaints the game view and updates the game state in the view.
     */
    private void updateBalls() {
        for (var b : balls) {
            b.update();
            // System.out.println(b.toString());
        }
        paddle.move();
        ourView.repaint();
        ourView.updateGameState(balls, brickWall.getWall(), paddle);
    }

    /**
     * Adds a new ball to the game by duplicating the specified ball.
     *
     * @param old the original Ball.
     */
    public final void multiplyBall(final Ball old) {
        balls.add(new Ball(old));
    }
}
