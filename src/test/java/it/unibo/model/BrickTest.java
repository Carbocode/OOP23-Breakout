package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrickTest {

    private Brick brick;
    private Point position;
    private Dimension size;
    private int health;
    private Color color;

    /**
     * Setup a Brick for tests.
     */
    @BeforeEach
    public void setUp() {
        position = new Point(10, 20);
        size = new Dimension(30, 10);
        health = 3;
        color = Color.RED;
        brick = new Brick(position, size, health, color);
    }

    /**
     * Test if Constructor works.
     */
    @Test
    public void testConstructor() {
        assertEquals(position, brick.getPosition());
        assertEquals(size, brick.getSize());
        assertEquals(health, brick.getHealth());
        assertEquals(color, brick.getColor());
    }

    /**
     * Test if Collision interacts with Health correclty.
     */
    @Test
    public void testOnCollision() {
        brick.onCollision();
        assertEquals(health - 1, brick.getHealth());

        // Test health does not go below zero for normal bricks
        brick.setHealth(1);
        brick.onCollision();
        assertEquals(0, brick.getHealth());
        brick.onCollision();
        assertEquals(0, brick.getHealth());

        // Test immortal brick does not lose health
        brick.setHealth(-1);
        brick.onCollision();
        assertEquals(-1, brick.getHealth());
    }

    /**
     * Test if Brick Health works.
     */
    @Test
    public void testIsAlive() {
        assertTrue(brick.isAlive());

        brick.setHealth(0);
        assertFalse(brick.isAlive());

        // Test immortal brick is always alive
        brick.setHealth(-1);
        assertTrue(brick.isAlive());
    }

    /**
     * Test if Health is correct.
     */
    @Test
    public void testSetAndGetHealth() {
        brick.setHealth(5);
        assertEquals(5, brick.getHealth());
    }

    /**
     * Test if Brick is deterministic.
     */
    @Test
    public void testEqualsAndHashCode() {
        Brick anotherBrick = new Brick(new Point(10, 20), new Dimension(30, 10), 3, Color.RED);
        assertEquals(brick, anotherBrick);
        assertEquals(brick.hashCode(), anotherBrick.hashCode());

        Brick differentBrick = new Brick(new Point(15, 25), new Dimension(30, 10), 3, Color.RED);
        assertNotEquals(brick, differentBrick);
        assertNotEquals(brick.hashCode(), differentBrick.hashCode());
    }

    /**
     * Test if returned String is correct.
     */
    @Test
    public void testToString() {
        assertEquals("(10;20)", brick.toString());
    }

    /**
     * Test if Position is correct.
     */
    @Test
    public void testGetPosition() {
        assertEquals(position, brick.getPosition());
    }

    /**
     * Test if Size is correct.
     */
    @Test
    public void testGetSize() {
        assertEquals(size, brick.getSize());
    }
}
