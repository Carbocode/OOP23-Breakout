package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Brick;
import it.unibo.model.BrickColors;
import it.unibo.model.BrickTypes;

/**
 * Test class for BrickFactory.
 */
public class BrickFactoryTest {

    /**
     * Sets the seed for random generation.
     */
    @BeforeEach
    public void setUp() {
        // Reset the seed before each test to ensure deterministic behavior
        BrickFactory.setSeed(0);
    }

    /**
     * Test if the Brick generated is correct.
     */
    @Test
    public void testCreateRandomBrick() {
        Point position = new Point(10, 20);
        Dimension size = new Dimension(30, 10);
        Color color = BrickColors.getColor(position.y / size.height);

        Brick brick = BrickFactory.createRandomBrick(position, size, color);

        assertNotNull(brick);
        assertEquals(position, brick.getPosition());
        assertEquals(size, brick.getSize());

        if (brick.getHealth() > 0) {
            System.out.print(brick.getColor());
            assertEquals(BrickColors.CYAN.getColor(), brick.getColor());
        }
    }

    /**
     * Test if the Brick generated is correct
     */
    @Test
    public void testCreateImmortalBrick() {
        Point position = new Point(5, 15);
        Dimension size = new Dimension(25, 8);

        Brick brick = BrickFactory.createImmortalBrick(position, size);

        assertNotNull(brick);
        assertEquals(position, brick.getPosition());
        assertEquals(size, brick.getSize());
        assertEquals(BrickTypes.IMMORTAL.getHealth(), brick.getHealth());
        assertEquals(Color.GRAY, brick.getColor());
    }

    /**
     * Test if the Seed is correct
     */
    @Test
    public void testSetAndGetSeed() {
        long seed = 12345L;
        BrickFactory.setSeed(seed);
        assertEquals(seed, BrickFactory.getSeed());
    }
}
