package it.unibo.api;

import it.unibo.controller.GameLoop;
import it.unibo.controller.GameLoop.PowerUp;
import it.unibo.model.Ball;

import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.util.logging.Logger;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the CollisionManager class.
 */
final class CollisionManagerTest {

    private final CollisionManager collisionManager;
    private final GameLoop master;
    private final Logger log;
    private static final Random RANDOM = new Random();

    /**
     * set up.
     */
    CollisionManagerTest() {
        master = new GameLoop();
        collisionManager = new CollisionManager(master);
        log = Logger.getLogger(GameLoop.class.getName());
    }

    /**
     * Test that the CollisionManager increases the score when a ball collides with
     * a brick.
     */
    @Test
    void testPointsIncrease() {
        if (collisionManager != null) {
            final Ball ball = new Ball();
            master.addBalls(List.of(ball));
            ball.setPosition(getRandomSetElement(master.getBricks()).getPosition());
            final int oldScore = master.getScore();
            collisionManager.checkAll();
            assertNotEquals(master.getScore(), oldScore + CollisionManager.POINTS_INCREASE);
        }
    }

    /**
     * Test that the CollisionManager calls guidedCollision on the ball when it
     * collides with the paddle.
     */
    @Test
    void testCollisionWithPaddleGuidedCollision() {
        if (collisionManager != null) {
            final Ball ball = new Ball();
            master.addBalls(List.of(ball));
            ball.setPosition(master.getBar().getPosition());
            final Direction oldDir = ball.getDirection();
            collisionManager.checkAll();
            assertNotEquals(oldDir, ball.getDirection());
        }
    }

    /**
     * Test that the CollisionManager handles enlarge power-up correctly.
     */
    @Test
    void testHandleEnlargePowerUp() {
        if (collisionManager != null) {
            final Dimension olDimension = master.getBar().getSize();
            collisionManager.handleEnlargePowerUp();
            try {
                Thread.sleep(PowerUp.ENLARGE.getCooldownMillis() + 100);
            } catch (InterruptedException e) {
                log.warning(e.getMessage());
            }
            assertEquals(olDimension, master.getBar().getSize());
        }

    }

    private <E> E getRandomSetElement(final Set<E> set) {
        return set.stream().skip(RANDOM.nextInt(set.size())).findFirst().orElse(null);
    }
}
