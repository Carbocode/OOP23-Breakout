package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Brick;

/**
 * Test class for BrickWallImpl.
 */
class BrickWallImplTest {

    private BrickWallImpl brickWall;
    private static final int WIDTH = 140;
    private static final int HEIGHT = 100;

    /**
     * Setup a BrickWall.
     */
    @BeforeEach
    void setUp() {
        brickWall = new BrickWallImpl(WIDTH, HEIGHT);
        BrickFactory.setSeed(0); // Ensure deterministic behavior by setting a seed
    }

    /**
     * Test if the Constructor works.
     */
    @Test
    void testConstructor() {
        assertEquals(WIDTH, brickWall.getWidth());
        assertEquals(HEIGHT, brickWall.getHeight());
    }

    /**
     * Test if the Layout generated is correct.
     */
    @Test
    void testGenerateLayout() {
        brickWall.generateLayout();
        final Set<Brick> wall = brickWall.getWall();
        assertNotNull(wall);
        assertFalse(wall.isEmpty());

        // Validate the structure of the wall, such as number of bricks
        final int expectedBrickCount = calculateExpectedBrickCount();
        assertEquals(expectedBrickCount, wall.size());
    }

    /**
     * Test if the Brick count generated is correct.
     * 
     * @return brick number in a wall
     */
    int calculateExpectedBrickCount() {
        // Simplified example of expected brick count calculation
        final int gcd = 20;
        final int brickWidth = gcd * BrickWallImpl.SCALAR;
        final int brickHeight = (int) (brickWidth / Brick.ASPECT_RATIO);
        final int numBricksRow = (int) Math.floor((double) WIDTH / brickWidth);
        final int numBricksColumn = (int) Math.floor((double) HEIGHT / brickHeight);

        // Adding the immortal bricks at the sides
        final int sideBricks = 2 * numBricksColumn;

        return numBricksRow * numBricksColumn + sideBricks;
    }

    /**
     * Test if the layout is resetted correctly.
     */
    @Test
    void testResetLayout() {
        brickWall.generateLayout();
        assertFalse(brickWall.getWall().isEmpty());

        brickWall.resetLayout();
        assertTrue(brickWall.getWall().isEmpty());
    }

    /**
     * Test if the Width generated is correct.
     */
    @Test
    void testSetAndGetWidth() {
        final int newWidth = 200;
        brickWall.setWidth(newWidth);
        assertEquals(newWidth, brickWall.getWidth());
    }

    /**
     * Test if the Height is correct.
     */
    @Test
    void testSetAndGetHeight() {
        final int newHeight = 150;
        brickWall.setHeight(newHeight);
        assertEquals(newHeight, brickWall.getHeight());
    }
}
