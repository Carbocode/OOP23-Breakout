package it.unibo.controller;

import java.util.concurrent.TimeUnit;

import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameEntity;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.view.SoundManagerImpl;

import java.util.Set;
import java.util.HashSet;
public class GameLoop {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;
    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    public GameLoop(){
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT/5);
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        brickWall.generateLayout();
        //TODO: Generate bricks, paddle
        GameEntity paddle = null;

        manager = new CollisionManager(balls,brickWall,paddle);
    }
    public void run() {
        //soundPlayer.playBackgroundSound();
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

    /**
     * Updates the gamestate
     */
    private void update() {
        // Update the game state here
        System.out.println("Running!!");
        manager.checkAll();
        for(var b : balls){
            b.update();
            System.out.println(" I'm at :("+ b.getPosition().getX()+","+b.getPosition().getY()+")");
        }
    }
    public void multiplyBall(Ball old){

    }
    public static void main(String[] args){
        var x = new GameLoop();
        
        x.run();
    }
}
