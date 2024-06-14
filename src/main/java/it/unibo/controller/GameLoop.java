package it.unibo.controller;


import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.view.SoundManagerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Set;
import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import it.unibo.view.*;

public class GameLoop implements ActionListener {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;

    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private Bar paddle;

    private long lastUpdateTime;
    private Timer timer;

    private GameView t;

    public GameLoop(GameView coso) {
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, (int) Math.floor(GameInfo.GAME_HEIGHT * 0.35));
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new BarImpl(new Point((GameInfo.GAME_WIDTH / 2) - 100, GameInfo.GAME_HEIGHT),
        GameInfo.BAR_DIMENSION, 0, new Color(0));
        manager = new CollisionManager(balls, brickWall, paddle);
        t = coso;
        t.updateGameState(balls, brickWall.getWall(), paddle);

        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastUpdateTime;

        if (elapsedTime >= GameInfo.REFRESH_RATE) {
            update();
            lastUpdateTime = currentTime;
        }
    }

    /**
     * Updates the gamestate
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
        t.repaint();
        t.updateGameState(balls, brickWall.getWall(), paddle);
    }

    public void multiplyBall(Ball old) {
        balls.add(new Ball(old));
    }

}
