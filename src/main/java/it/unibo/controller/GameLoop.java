package it.unibo.controller;

import java.util.concurrent.TimeUnit;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameEntity;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.model.BarImpl;
import it.unibo.view.SoundManagerImpl;

import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import it.unibo.view.*;

public class GameLoop {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;
    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private BarImpl paddle;
    private TEST t;

    public GameLoop() {
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT / 5);
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new BarImpl(new Point(GameInfo.GAME_WIDTH / 2, GameInfo.GAME_HEIGHT), new Dimension(30, 5), 0,
                new Color(0));
        manager = new CollisionManager(balls, brickWall, paddle);
        t = new TEST();
        t.updateGameState(balls, brickWall.getWall(), paddle);
    }

    public void run() {
        // soundPlayer.playBackgroundSound();
        long lastUpdateTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            if (elapsedTime >= UPDATE_INTERVAL) {
                update();
                t.updateGameState(balls, brickWall.getWall(), paddle);
                elapsedTime = 0;
            }

            // Sleep the remaining time, if any, to ensure a consistent update interval
            long sleepTime = UPDATE_INTERVAL - elapsedTime;
            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Updates the gamestate
     */
    private void update() {

        manager.checkAll();
        for (var b : balls) {
            b.update();
            // System.out.println(b.toString());
        }
    }

    public void multiplyBall(Ball old) {

    }

    public static void main(String[] args) {
        System.out.println("Running!!");
        var x = new GameLoop();
        x.run();
    }
}
