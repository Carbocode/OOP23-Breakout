package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Bomb;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class that checks for collisions.
 */
public class CollisionManager {
    private BrickWall bricks;
    private Set<Ball> balls;
    private Bar paddle;
    private final int BOMB_SIZE_RATIO = 5;
    private final int ENLARGE_SIZE = 100;
    private final int MAX_BALLS= 10;
    private ScoreManager score;
    private Random rnd;
    private int gridSize = 100;
    private ScheduledExecutorService scheduler;

    /**
     * Initializes CollisionManager.
     * 
     * @param balls
     * @param brickWall
     * @param paddle
     * @param score
     */
    public CollisionManager(final Set<Ball> balls, final BrickWall brickWall, final Bar paddle,
            final ScoreManager score) {
        this.balls = balls;
        this.bricks = brickWall;
        this.paddle = paddle;
        this.score = score;
        rnd = new Random();
        scheduler = Executors.newScheduledThreadPool(1);
    }

    /**
     * Checks all objects for collision.
     */
    public final void checkAll() {
        List<Ball> newBalls = new ArrayList<>();
        long startTime = System.nanoTime();
    
        for (Ball ball : new ArrayList<>(balls)) {  // Create a copy to iterate over
            boolean collision = false;
            if (!ball.isAlive()) {
                continue;
            }
            
            // Collision with bricks
            long brickStartTime = System.nanoTime();
            for (GameEntity brick : bricks.getWall()) {
                if (!brick.isAlive()) {
                    continue;
                }
                if (collides(ball, brick)) {
                    if (GameInfo.DEBUG_MODE) {
                        System.out.println("Ball at  (" + ball.getPosition().toString()
                                + ") collides with (" + brick.getPosition().toString() + ")");
                    }
                    collision = true;
                    if (!(brick.getHealth() == -1)) {
                        score.increment(points);
                    }
                    brick.onCollision();
                }
            }
            long brickEndTime = System.nanoTime();
            DBGPrint("Brick Collision", brickEndTime - brickStartTime);
    
            // Collision with paddle
            long paddleStartTime = System.nanoTime();
            if (collides(ball, paddle)) {
                System.out.println("Paddle hit");
                if (ball.getPosition().x < paddle.getPosition().x + (paddle.getSize().width / 2)) {
                    ball.barCollision(-1);
                } else {
                    ball.barCollision(1);
                }
            }
            long paddleEndTime = System.nanoTime();
            DBGPrint("Paddle Collision check", paddleEndTime - paddleStartTime);
    
            // Handle collisions
            if (collision) {
                long powerUPStartTime = System.nanoTime();
                ball.onCollision();
                for (PowerUp pu : PowerUp.values()) {
                    if (rnd.nextInt(100) <= pu.getProbability() && !pu.isOnCooldown()) {
                        switch (pu) {
                            case ENLARGE:
                                handleEnlargePowerUp();
                                break;
                            case BOMB:
                                bomb(ball);
                                break;
                            case DUPLI:
                                newBalls.add(new Ball(ball));  // Collect new ball
                                PowerUp.DUPLI.use();
                                break;
                            default:
                                break;
                        }
                    }
                }
                long PowerUpEndTime = System.nanoTime();
                DBGPrint("Power Up Handling", PowerUpEndTime-powerUPStartTime);
            }
        }
    
        // Add new balls
        long addBallsStartTime = System.nanoTime();
        if (balls.size() + newBalls.size() <= MAX_BALLS) {
            balls.addAll(newBalls);
        }
        long addBallsEndTime = System.nanoTime();
        DBGPrint("Add Balls ", addBallsEndTime-addBallsStartTime);
    
        long endTime = System.nanoTime();
        DBGPrint("TOTAL", endTime-startTime);
    }
    private void DBGPrint(String name, long difference) {
        if (!GameInfo.DEBUG_MODE) {
            return;
        }
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(difference);
        String output = String.format("%s took %d ms", name.toUpperCase(), milliseconds);
        if (milliseconds > 10) {
            System.out.println("\u001B[31m" + output + "\u001B[0m");  // ANSI escape code for red color
        } else {
            //System.out.println(output);
        }
    }
    private void handleEnlargePowerUp() {
        PowerUp.ENLARGE.use();
        Dimension originalSize = paddle.getSize();
        paddle.setSize(new Dimension((int)paddle.getSize().getWidth() + ENLARGE_SIZE, 
                                     (int)paddle.getSize().getHeight()));
        
        // Schedule a task to reverse the ENLARGE effect after 5 seconds
        scheduler.schedule(() -> {
            paddle.setSize(originalSize);
        }, 5, TimeUnit.SECONDS);
    }
    private void bomb(GameEntity ball){
        PowerUp.BOMB.use();
        Bomb bomb = new Bomb(new Point(ball.getPosition().x-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2),
        ball.getPosition().y-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2)),
        new Dimension(GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO,
        GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO));
        for (GameEntity brick : bricks.getWall()) {
            if (!brick.isAlive()) {
                continue;
            }
            if (collides(bomb, brick)) {
                brick.onCollision();
            }
        }
    }
    private boolean collides(final GameEntity a, final GameEntity b) {
        Point posA = a.getPosition();
        Dimension sizeA = a.getSize();
        Point posB = b.getPosition();
        Dimension sizeB = b.getSize();
        Rectangle aR = new Rectangle(posA, sizeA);
        Rectangle bR = new Rectangle(posB, sizeB);
        return aR.intersects(bR);
    }
}
