package it.unibo.controller;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.view.SoundManagerImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Set;
import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import it.unibo.view.GameView;
/**
 * Class that handles the main GameLoop.
 */
public class GameLoop implements ActionListener {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;
    private static final double BRICK_PERCENT = 0.35;

    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private Bar paddle;

    private long lastUpdateTime;
    private Timer timer;



    private GameView ourView;
    /**
     * Initializer.
     * @param view
     */
    public GameLoop(final GameView view) {
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, (int) Math.floor(GameInfo.GAME_HEIGHT * BRICK_PERCENT));
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new Bar(new Point((GameInfo.GAME_WIDTH / 2) - 100, GameInfo.GAME_HEIGHT),
                GameInfo.BAR_DIMENSION, 0, new Color(0));
        manager = new CollisionManager(balls, brickWall, paddle);
        ourView = view;
        ourView.updateGameState(balls, brickWall.getWall(), paddle);

        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastUpdateTime;

        if (elapsedTime >= GameInfo.REFRESH_RATE) {
            update();
            lastUpdateTime = currentTime;
        }
    }

    /**
     * Updates the gamestate.
     */
    private void update() {

        manager.checkAll();
        this.updateBalls();
        DeathCollector.checkEntities(balls);
        DeathCollector.checkEntities(brickWall.getWall());
    }

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
     * Ball duplication.
     * @param old
     */
    public final void multiplyBall(final Ball old) {
        balls.add(new Ball(old));
    }

}
