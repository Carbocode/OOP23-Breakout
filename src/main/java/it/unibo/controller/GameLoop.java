package it.unibo.controller;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.ScoreManager;
import it.unibo.model.Ball;
import it.unibo.model.Bar;

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
    /**
     * PowerUP description enum.
     */
    public enum PowerUp {
        /**
         * Bomb power up.
         */
        BOMB(15, 15_000),
        /**
         * Duplication power up.
         */
        DUPLI(50, 2_000),
        /**
         * Enlargement.
         */
        ENLARGE(10, 5_000);

        private final double probability;
        private final long cooldownMillis;
        private long lastUsedTime;

        /**
         * 
         * @param probability
         * @param cooldownMillis
         */
        PowerUp(final double probability, final long cooldownMillis) {
            this.probability = probability;
            this.cooldownMillis = cooldownMillis;
            this.lastUsedTime = 0; // Initialize lastUsedTime to 0 (not used yet)
        }

        /**
         * 
         * @return Probability
         */
        public double getProbability() {
            return probability;
        }

        /**
         * 
         * @return Cooldown of the power up.
         */
        public long getCooldownMillis() {
            return cooldownMillis;
        }

        /**
         * 
         * @return remaining CD
         */
        public int getCDInSecs() {
            if (!isOnCooldown()) {
                return 0;
            }
            return (int) (cooldownMillis - (System.currentTimeMillis() - lastUsedTime)) / 1000;
        }

        /**
         * 
         * @return is it on CD?
         */
        public boolean isOnCooldown() {
            return System.currentTimeMillis() - lastUsedTime < cooldownMillis;
        }

        /**
         * ALWAYS USE WHEN ACTIVATING POWER UP.
         */
        public void use() {
            lastUsedTime = System.currentTimeMillis();
        }
    }

    private static final double BRICK_PERCENT = 0.45;
    private CollisionManager manager;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private Bar paddle;
    private ScoreManager score;

    private long lastUpdateTime;

    private GameView ourView;

    /**
     * Initializer.
     * 
     * @param view
     */
    public GameLoop(final GameView view) {
        final Timer timer;
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, (int) Math.floor(GameInfo.GAME_HEIGHT * BRICK_PERCENT));
        balls = new HashSet<>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new Bar(new Point(GameInfo.GAME_WIDTH / 2 - 100, GameInfo.GAME_HEIGHT),
                GameInfo.BAR_DIMENSION, 0, new Color(0));
        score = new ScoreManagerImpl();
        manager = new CollisionManager(balls, brickWall, paddle, score);
        ourView = view;
        ourView.updateGameState(balls, brickWall.getWall(), paddle, 0);

        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        final long currentTime = System.nanoTime();
        final long elapsedTime = currentTime - lastUpdateTime;

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
        for (final var b : balls) {
            b.update();
            // System.out.println(b.toString());
        }
        paddle.move();
        ourView.repaint();
        ourView.updateGameState(balls, brickWall.getWall(), paddle, score.getScore());
    }

    /**
     * Ball duplication.
     * 
     * @param old
     */
    public final void multiplyBall(final Ball old) {
        balls.add(new Ball(old));
    }

}
