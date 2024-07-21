package it.unibo.controller;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.GameLoopAccessor;
import it.unibo.api.ScoreManager;
import it.unibo.api.View;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.BarExtender;
import it.unibo.model.Brick;
import it.unibo.model.PowerUpBubble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import java.util.Set;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Class that handles the main GameLoop.
 */
public class GameLoop implements ActionListener, GameLoopAccessor {

    private static final double BRICK_PERCENT = 0.45;
    private final CollisionManager manager;
    private final BrickWall brickWall;
    private final Set<Ball> balls;
    private final Bar paddle;
    private final ScoreManager score;
    private final List<PowerUpBubble> powerUpBubbles;

    private long lastUpdateTime;

    private final List<View> ourViews;

    /**
     * Initializer.
     * 
     */
    public GameLoop() {
        final Timer timer;
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, (int) (GameInfo.GAME_HEIGHT * BRICK_PERCENT));
        this.powerUpBubbles = new ArrayList<>();
        balls = new HashSet<>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new Bar(new Point(GameInfo.GAME_WIDTH / 2 - 100, GameInfo.GAME_HEIGHT),
                GameInfo.BAR_DIMENSION, 0, new Color(0));
        score = new ScoreManagerImpl();
        manager = new CollisionManager(this);
        ourViews = new ArrayList<>();

        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }

    /**
     * 
     * @param gw
     */
    public void addView(final View gw) {
        ourViews.add(gw);
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
        this.updatePowerUpBubbles();
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
        for (final View w : ourViews) {
            w.updateGameState(getScore());
        }
    }

    private void updatePowerUpBubbles() {
        for (final PowerUpBubble bubble : powerUpBubbles) {
            bubble.update();
        }
        powerUpBubbles.removeIf(bubble -> bubble.getPosition().y > GameInfo.GAME_HEIGHT);
    }

    /**
     * this method returns the score of the game.
     * 
     * @param bubble
     */
    @Override
    public void addPowerUpBubble(final PowerUpBubble bubble) {
        powerUpBubbles.add(bubble);
    }

    /**
     * Ball duplication.
     * 
     * @param old
     */
    public final void multiplyBall(final Ball old) {
        balls.add(new Ball(old));
    }

    /**
     * this method returns the balls of the game.
     * 
     * @return unmodifiable set of balls
     */
    @Override
    public Set<Ball> getBalls() {
        return Collections.unmodifiableSet(this.balls);
    }

    /**
     * this method returns the bricks of the game.
     * 
     * @return unmodifiable set of bricks
     */
    @Override
    public Set<Brick> getBricks() {
        return Collections.unmodifiableSet(this.brickWall.getWall());
    }

    /**
     * this method return the bar of the game.
     * 
     * @return bar
     */
    @Override
    public Bar getBar() {
        return new Bar(paddle.getPosition(), paddle.getSize(), paddle.getHealth(), paddle.getColor());
    }

    /**
     * 
     * @return ScoreManager
     */
    @Override
    public int getScore() {
        return this.score.getScore();
    }

    /**
     * 
     * @param amount
     */
    @Override
    public void increaseScore(final int amount) {
        this.score.increment(amount);
    }

    /**
     * 
     */
    @Override
    public final void extendPaddle() {
        BarExtender.extendBar(paddle);
    }

    /**
     * @param newBalls
     * 
     */
    @Override
    public void addBalls(final List<Ball> newBalls) {
        balls.addAll(newBalls);
    }

    /**
     * 
     * @param e
     */
    @Override
    public void handleKeyRelease(final KeyEvent e) {
        paddle.buttonReleased(e);
    }

    /**
     * 
     * @param e
     */
    @Override
    public void handleKeyPress(final KeyEvent e) {
        paddle.buttonPressed(e);
    }

    /**
     * 
     * @return Access only game loop.
     */
    public final GameLoopAccessor getAccessor() {
        return new GameLoopAccessorImpl(this);
    }

    /**
     * this method returns the power up bubbles of the game.
     * 
     * @return unmodifiable list of power up bubbles
     */
    @Override
    public List<PowerUpBubble> getPowerUpBubbles() {
        return Collections.unmodifiableList(this.powerUpBubbles);
    }

    // Additional methods for game loop logic

    private class GameLoopAccessorImpl implements GameLoopAccessor {
        private final GameLoop gameLoop;

        GameLoopAccessorImpl(final GameLoop gameLoop) {
            this.gameLoop = gameLoop;
        }

        @Override
        public void addBalls(final List<Ball> newBalls) {
            balls.addAll(newBalls);
        }

        @Override
        public Set<Ball> getBalls() {
            return gameLoop.getBalls(); // Accessing original method from GameLoop
        }

        @Override
        public Set<Brick> getBricks() {
            return gameLoop.getBricks(); // Accessing original method from GameLoop
        }

        @Override
        public Bar getBar() {
            return gameLoop.getBar(); // Accessing original method from GameLoop
        }

        @Override
        public int getScore() {
            return gameLoop.getScore(); // Accessing original method from GameLoop
        }

        @Override
        public void handleKeyRelease(final KeyEvent e) {
            gameLoop.handleKeyRelease(e); // Accessing original method from GameLoop
        }

        @Override
        public void handleKeyPress(final KeyEvent e) {
            gameLoop.handleKeyPress(e); // Accessing original method from GameLoop
        }

        /**
         * 
         * @param amount
         */
        @Override
        public final void increaseScore(final int amount) {
            gameLoop.increaseScore(amount);
        }

        /**
         * 
         */
        @Override
        public final void extendPaddle() {
            gameLoop.extendPaddle();
        }

        @Override
        public List<PowerUpBubble> getPowerUpBubbles() {
            return List.copyOf(powerUpBubbles);
        }

        @Override
        public void addPowerUpBubble(final PowerUpBubble bubble) {
            gameLoop.addPowerUpBubble(bubble);
        }

    }

}
