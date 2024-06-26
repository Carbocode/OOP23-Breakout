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

/**
 * Class that checks for collisions.
 */
public class CollisionManager {
    private BrickWall bricks;
    private Set<Ball> balls;
    private Bar paddle;
    private final int BOMB_SIZE_RATIO = 5;
    private ScoreManager score;
    private Random rnd;
    private int gridSize = 100;

    /**
     * Initializes CollisionManager.
     * 
     * @param balls
     * @param brickWall
     * @param paddle
     */
    public CollisionManager(final Set<Ball> balls, final BrickWall brickWall, final Bar paddle, final ScoreManager score) {
        this.balls = balls;
        this.bricks = brickWall;
        this.paddle = paddle;
        this.score = score;
        rnd = new Random();
    }

    /**
     * Checks all objects for collision.
     */
    public final void checkAll() {
        List<Ball> newBalls = new ArrayList<Ball>();
        for (Ball ball : new ArrayList<>(balls)) {  // Create a copy to iterate over, gives Error if not used
            boolean collision = false;
            if (!ball.isAlive()) {
                continue;
            }
            for (GameEntity brick : bricks.getWall()) {
                if (!brick.isAlive()) {
                    continue;
                }
                if (collides(ball, brick)) {
                    // Sometimes the ball collides with multiple bricks at the same time.
                    // this calls its onCollision twice, thus having no effect
                    if (GameInfo.DEBUG_MODE) {
                        System.out.println("Ball at  (" + ball.getPosition().toString()
                                + ") collides with (" + brick.getPosition().toString() + ")");
                    }
                    collision = true;
                    score.increment(200);
                    brick.onCollision();
                }
            }
            // then we check with paddle
            if (collides(ball, paddle)) {
                System.out.println("Paddle hit");
                collision = true;
            }
            if (collision) {
                ball.onCollision();
                for (PowerUp pu : PowerUp.values()) {
                    if (rnd.nextInt(100) <= pu.getProbability()) {
                        switch (pu) {
                            case ENLARGE:
                                // handle ENLARGE power-up
                                break;
                            case BOMB:
                                // handle BOMB power-up
                                break;
                            case DUPLI:
                                newBalls.add(new Ball(ball));  // Collect new ball
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        balls.addAll(newBalls);  // Add new balls after iteration
    }
    private void bomb(GameEntity ball){
        
        Bomb bomb = new Bomb(new Point(ball.getPosition().x-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2),ball.getPosition().y-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2)),new Dimension(GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO, GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO));

        for (GameEntity brick : bricks.getWall()) {
            if (!brick.isAlive()) {
                continue;
            }
            if (collides(bomb, brick)) {
                // Sometimes the ball collides with multiple bricks at the same time.
                // this calls its onCollision twice, thus having no effect
                if (GameInfo.DEBUG_MODE) {
                    System.out.println("Ball at  (" + ball.getPosition().toString()
                             + ") collides with (" + brick.getPosition().toString() + ")");
                }
                brick.onCollision();
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
