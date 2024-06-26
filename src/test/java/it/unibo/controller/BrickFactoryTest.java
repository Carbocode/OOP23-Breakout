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
class BrickFactoryTest {

    /**
     * Sets the seed for random generation.
     */
    @BeforeEach

    void setUp() {
        final int neutralSeed = 0;
        // Reset the seed before each test to ensure deterministic behavior
        BrickFactory.setSeed(neutralSeed);
    }

    /**
     * Test if the Brick generated is correct.
     */
    @Test
    void testCreateRandomBrick() {
        final Point position = new Point(10, 20);
        final Dimension size = new Dimension(30, 10);
        final Color color = BrickColors.getColor(position.y / size.height);

        final Brick brick = BrickFactory.createRandomBrick(position, size, color);

        assertNotNull(brick);
        assertEquals(position, brick.getPosition());
        assertEquals(size, brick.getSize());

        if (brick.getHealth() > 0) {
            assertEquals(BrickColors.CYAN.getColor(), brick.getColor());
        }
    }

    /**
     * Test if the Brick generated is correct
     */
    @Test
    void testCreateImmortalBrick() {
        final Point position = new Point(5, 15);
        final Dimension size = new Dimension(25, 8);

        final Brick brick = BrickFactory.createImmortalBrick(position, size);

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
    void testSetAndGetSeed() {
        final long seed = 12_345L;
        BrickFactory.setSeed(seed);
        assertEquals(seed, BrickFactory.getSeed());
    }
}
