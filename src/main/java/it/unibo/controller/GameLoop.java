package it.unibo.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.api.CollisionManager;
import it.unibo.api.GameEntity;
import it.unibo.api.GameEntityImpl;
import it.unibo.model.Ball;
import java.util.Set;
import java.util.HashSet;
import java.awt.Point;

public class GameLoop {
    private static final int MAX_UPDATES_PER_SECOND = 20;
    private static final long UPDATE_INTERVAL = 1000 / MAX_UPDATES_PER_SECOND;
    private CollisionManager manager;
    private Set<Ball> balls;
    private Set<GameEntity> bricks;
    public GameLoop(){
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        //TODO: Generate bricks, paddle
        GameEntity paddle = null;
        manager = new CollisionManager(balls,bricks,paddle);
    }
    public void run() {
        long lastUpdateTime = System.currentTimeMillis();
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;

            if (elapsedTime >= UPDATE_INTERVAL) {
                update();
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

    private void update() {
        // Update the game state here
        System.out.println("Running!!");
        for(var b : balls){
            b.update();
            System.out.println(" I'm at :("+ b.getPosition().getX()+","+b.getPosition().getY()+")");
        }
    }
    public static void main(String[] args){
        var x = new GameLoop();
        x.run();
    }
}
