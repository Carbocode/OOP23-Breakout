package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Brick;
import it.unibo.model.BrickTypes;

public class BrickWallImplTest {

    private BrickWallImpl brickWall;
    private static final int WIDTH = 140;
    private static final int HEIGHT = 100;

    @BeforeEach
    public void setUp() {
        brickWall = new BrickWallImpl(WIDTH, HEIGHT);
        BrickFactory.setSeed(0); // Ensure deterministic behavior by setting a seed
    }

    @Test
    public void testConstructor() {
        assertEquals(WIDTH, brickWall.getWidth());
        assertEquals(HEIGHT, brickWall.getHeight());
    }

    /*
     * @Test
     * public void testGenerateLayout() {
     * brickWall.generateLayout();
     * Set<Brick> wall = brickWall.getWall();
     * assertNotNull(wall);
     * assertFalse(wall.isEmpty());
     * 
     * // Validate the structure of the wall, such as number of bricks
     * int expectedBrickCount = calculateExpectedBrickCount();
     * assertEquals(expectedBrickCount, wall.size());
     * }
     * 
     * 
     * private int calculateExpectedBrickCount() {
     * // Simplified example of expected brick count calculation
     * int gcd = calculateGcd(WIDTH, HEIGHT);
     * int brickWidth = gcd * BrickWallImpl.SCALAR;
     * int brickHeight = (int) (brickWidth / Brick.ASPECT_RATIO);
     * int numBricksRow = (int) Math.floor(WIDTH / brickWidth);
     * int numBricksColumn = (int) Math.floor(HEIGHT / brickHeight);
     * 
     * // Adding the immortal bricks at the sides
     * int sideBricks = 2 * numBricksColumn;
     * 
     * return numBricksRow * numBricksColumn + sideBricks;
     * }
     */

    private int calculateGcd(int x, int y) {
        while (y > 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x;
    }

    @Test
    public void testResetLayout() {
        brickWall.generateLayout();
        assertFalse(brickWall.getWall().isEmpty());

        brickWall.resetLayout();
        assertTrue(brickWall.getWall().isEmpty());
    }

    @Test
    public void testSetAndGetWidth() {
        int newWidth = 200;
        brickWall.setWidth(newWidth);
        assertEquals(newWidth, brickWall.getWidth());
    }

    @Test
    public void testSetAndGetHeight() {
        int newHeight = 150;
        brickWall.setHeight(newHeight);
        assertEquals(newHeight, brickWall.getHeight());
    }

    /*
     * @Test
     * public void testAddRandomBrick() {
     * brickWall.resetLayout();
     * Point position = new Point(10, 10);
     * Dimension size = new Dimension(30, 10);
     * 
     * brickWall.generateLayout(); // Generate layout to ensure wall is initialized
     * brickWall.getWall().clear(); // Clear wall to test single brick addition
     * brickWall.addRandomBrick(0, 0, size.width, size.height);
     * 
     * assertEquals(1, brickWall.getWall().size());
     * Brick brick = brickWall.getWall().iterator().next();
     * assertEquals(position, brick.getPosition());
     * assertEquals(size, brick.getSize());
     * }
     */

    /*
     * @Test
     * public void testAddImmortalBrick() {
     * brickWall.resetLayout();
     * Point position = new Point(0, 0);
     * Dimension size = new Dimension(30, 10);
     * 
     * brickWall.generateLayout(); // Generate layout to ensure wall is initialized
     * brickWall.getWall().clear(); // Clear wall to test single brick addition
     * brickWall.addImmortalBrick(position, size);
     * 
     * assertEquals(1, brickWall.getWall().size());
     * Brick brick = brickWall.getWall().iterator().next();
     * assertEquals(position, brick.getPosition());
     * assertEquals(size, brick.getSize());
     * assertEquals(BrickTypes.IMMORTAL.getHealth(), brick.getHealth());
     * assertEquals(Color.GRAY, brick.getColor());
     * }
     */
}
