package it.unibo.model;

import it.unibo.api.GameInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * JUnit tests for the {@link Ball} class.
 */
class BallTest {

    private static final int DEFAULT_HEALTH = 1;
    private static final int BALL_WIDTH = 5;
    private static final int BALL_HEIGHT = 5;
    private static final int START_POSITION_X = GameInfo.GAME_WIDTH / 2;
    private static final int START_POSITION_Y = GameInfo.GAME_HEIGHT - 50;
    private static final int OUT_OF_BOUNDS_RIGHT = GameInfo.GAME_WIDTH - 5;
    private static final int OUT_OF_BOUNDS_TOP = 0;
    private static final int OUT_OF_BOUNDS_BOTTOM = GameInfo.GAME_HEIGHT;
    private static final int COLLISION_DIRECTION_CHANGE = -1;
    private static final int GUIDED_COLLISION_DIRECTION = 1;

    private Ball ball;

    /**
     * Sets up a new {@link Ball} object before each test.
     */
    @BeforeEach
    void setUp() {
        ball = new Ball();
    }

    /**
     * Tests the default constructor of {@link Ball}.
     * Verifies that the ball is initialized with correct attributes.
     */
    @Test
    void testDefaultConstructor() {
        final Point startPoint = new Point(START_POSITION_X, START_POSITION_Y);
        assertEquals(startPoint, ball.getPosition(), "Initial position should match start point");
        assertEquals(BALL_WIDTH, ball.getSize().width, "Ball width should be " + BALL_WIDTH);
        assertEquals(BALL_HEIGHT, ball.getSize().height, "Ball height should be " + BALL_HEIGHT);
        assertEquals(DEFAULT_HEALTH, ball.getHealth(), "Initial health should be " + DEFAULT_HEALTH);
    }

    /**
     * Tests the {@link Ball#update()} method for movement and collision handling.
     * Verifies the behavior when the ball moves out of bounds or falls out of the
     * game area.
     */
    @Test
    void testUpdateOutOfBounds() {
        // Move ball out of bounds to the right
        ball.setPosition(new Point(OUT_OF_BOUNDS_RIGHT, 0));
        ball.update();
        assertEquals(COLLISION_DIRECTION_CHANGE, ball.getDirection().getHorizontalVelocity(),
                "Horizontal velocity should be reversed");

        // Move ball out of bounds to the top
        ball.setPosition(new Point(0, OUT_OF_BOUNDS_TOP));
        ball.onCollision();
        final var oldVert = ball.getDirection().getVerticalVelocity();
        ball.update();
        assertEquals(oldVert, -ball.getDirection().getVerticalVelocity(),
                "Vertical velocity should be reversed");

        // Move ball out of bounds to the bottom (health decrease)
        ball.setPosition(new Point(0, OUT_OF_BOUNDS_BOTTOM));
        ball.update();
        assertEquals(0, ball.getHealth(), "Health should decrease when ball falls out of game area");
    }

    /**
     * Tests the {@link Ball#onCollision()} method for collision handling.
     * Verifies that the ball's direction changes after collision.
     */
    @Test
    void testOnCollision() {
        // Test collision handling by changing direction
        final Point originalPosition = ball.getPosition();
        ball.onCollision();
        ball.update();
        assertNotEquals(originalPosition, ball.getPosition(), "Position should change after collision");
    }

    /**
     * Tests the {@link Ball#guidedCollision(int)} method for guided collision
     * behavior.
     * Verifies that the ball's direction changes as expected based on the input
     * direction.
     */
    @Test
    void testGuidedCollision() {
        // Test guided collision behavior
        ball.guidedCollision(GUIDED_COLLISION_DIRECTION);
        assertEquals(GUIDED_COLLISION_DIRECTION, ball.getDirection().getHorizontalVelocity(),
                "Horizontal velocity should change to " + GUIDED_COLLISION_DIRECTION);
    }
    /**
     * Test for duplication Power Up.
     */
    @Test
    void testDuplication() {
        Ball b2 = new Ball(ball);
        assertEquals(-b2.getDirection().getHorizontalVelocity(), ball.getDirection().getHorizontalVelocity());
    }
}
