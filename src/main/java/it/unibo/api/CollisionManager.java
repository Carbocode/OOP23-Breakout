package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;
import java.util.Random;
import it.unibo.controller.ScoreManagerImpl;
import it.unibo.model.Ball;
import it.unibo.model.Bar;

/**
 * Class that checks for collisions.
 */
public class CollisionManager {
    private BrickWall bricks;
    private Set<Ball> balls;
    private Bar paddle;
    private ScoreManager score;
    private Random rnd;

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
        for (Ball ball : balls) {
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
                if (ball.getPosition().x < paddle.getPosition().x + (paddle.getSize().width / 2)) {
                    ball.barCollision(-1);
                } else {
                    ball.barCollision(1);
                }
            }
            if (collision) {
                ball.onCollision();
                rnd.nextInt(100);
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
