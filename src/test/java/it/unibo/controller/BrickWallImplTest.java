package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Brick;

public class BrickWallImplTest {

    private BrickWallImpl brickWall;
    private static final int WIDTH = 140;
    private static final int HEIGHT = 100;

    /**
     * Setup a BrickWall.
     */
    @BeforeEach
    public void setUp() {
        brickWall = new BrickWallImpl(WIDTH, HEIGHT);
        BrickFactory.setSeed(0); // Ensure deterministic behavior by setting a seed
    }

    /**
     * Test if the Constructor works.
     */
    @Test
    public void testConstructor() {
        assertEquals(WIDTH, brickWall.getWidth());
        assertEquals(HEIGHT, brickWall.getHeight());
    }

    /**
     * Test if the Layout generated is correct.
     */
    @Test
    public void testGenerateLayout() {
        brickWall.generateLayout();
        Set<Brick> wall = brickWall.getWall();
        assertNotNull(wall);
        assertFalse(wall.isEmpty());

        // Validate the structure of the wall, such as number of bricks
        int expectedBrickCount = calculateExpectedBrickCount();
        assertEquals(expectedBrickCount, wall.size());
    }

    /**
     * Test if the Brick count generated is correct.
     */
    private int calculateExpectedBrickCount() {
        // Simplified example of expected brick count calculation
        int gcd = 20;
        int brickWidth = gcd * BrickWallImpl.SCALAR;
        int brickHeight = (int) (brickWidth / Brick.ASPECT_RATIO);
        int numBricksRow = (int) Math.floor(WIDTH / brickWidth);
        int numBricksColumn = (int) Math.floor(HEIGHT / brickHeight);

        // Adding the immortal bricks at the sides
        int sideBricks = 2 * numBricksColumn;

        return numBricksRow * numBricksColumn + sideBricks;
    }

    /**
     * Test if the layout is resetted correctly.
     */
    @Test
    public void testResetLayout() {
        brickWall.generateLayout();
        assertFalse(brickWall.getWall().isEmpty());

        brickWall.resetLayout();
        assertTrue(brickWall.getWall().isEmpty());
    }

    /**
     * Test if the Width generated is correct.
     */
    @Test
    public void testSetAndGetWidth() {
        int newWidth = 200;
        brickWall.setWidth(newWidth);
        assertEquals(newWidth, brickWall.getWidth());
    }

    /**
     * Test if the Height is correct.
     */
    @Test
    public void testSetAndGetHeight() {
        int newHeight = 150;
        brickWall.setHeight(newHeight);
        assertEquals(newHeight, brickWall.getHeight());
    }
}
