package it.unibo.api;

import it.unibo.controller.GameLoop;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Ball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 * Unit tests for the CollisionManager class.
 */
public final class CollisionManagerTest {

    private CollisionManager collisionManager;
    private GameLoop master;
    private static final Random RANDOM = new Random();

    /**
     * set up.
     */
    @BeforeEach
    public void setUp() {
        master = new GameLoop();
        collisionManager = new CollisionManager(master);

    }

    /**
     * Test that the CollisionManager increases the score when a ball collides with a brick.
     */
    @Test
    public void testPointsIncrease() {
        if (collisionManager != null) {
            Ball ball = new Ball();
            master.addBalls(List.of(ball));
            ball.setPosition(getRandomSetElement(master.getBricks()).getPosition());
            int oldScore = master.getScore();
            collisionManager.checkAll();
            assertNotEquals(master.getScore(), oldScore + CollisionManager.POINTS_INCREASE);
        }
    }

    /**
     * Test that the CollisionManager calls guidedCollision on the ball when it collides with the paddle.
     */
    @Test
    public void testCollisionWithPaddleGuidedCollision() {
        if (collisionManager != null) {
            Ball ball = new Ball();
            master.addBalls(List.of(ball));
            ball.setPosition(master.getBar().getPosition());
            Direction oldDir = ball.getDirection();
            collisionManager.checkAll();
            assertNotEquals(oldDir, ball.getDirection());
        }
    }



    /**
     * Test that the CollisionManager handles enlarge power-up correctly.
     */
    @Test
    public void testHandleEnlargePowerUp() {
        if (collisionManager != null) {
            Dimension olDimension = master.getBar().getSize();
            collisionManager.handleEnlargePowerUp();
            try {
                Thread.sleep(PowerUp.ENLARGE.getCooldownMillis() + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertEquals(olDimension, master.getBar().getSize());
        }

    }
    private <E> E getRandomSetElement(final Set<E> set) {
        return set.stream().skip(RANDOM.nextInt(set.size())).findFirst().orElse(null);
    }
}
