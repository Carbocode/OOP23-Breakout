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

public class BrickFactoryTest {

    @BeforeEach
    public void setUp() {
        // Reset the seed before each test to ensure deterministic behavior
        BrickFactory.setSeed(0);
    }

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

    @Test
    public void testSetAndGetSeed() {
        long seed = 12345L;
        BrickFactory.setSeed(seed);
        assertEquals(seed, BrickFactory.getSeed());
    }

    /*
     * @Test
     * public void testRandomBrickHealthVariation() {
     * Point position = new Point(10, 20);
     * Dimension size = new Dimension(30, 10);
     * 
     * BrickFactory.setSeed(1);
     * Brick brick1 = BrickFactory.createRandomBrick(position, size);
     * BrickFactory.setSeed(2);
     * Brick brick2 = BrickFactory.createRandomBrick(position, size);
     * 
     * assertNotEquals(brick1.getHealth(), brick2.getHealth());
     * }
     */
}
