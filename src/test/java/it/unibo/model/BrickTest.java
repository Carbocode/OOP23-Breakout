package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.GameEntityImpl;

/**
 * Test class for Brick.
 */
class BrickTest {

    private Brick brick;
    private Point position;
    private Dimension size;
    private int health;
    private Color color;

    /**
     * Setup a Brick for tests.
     */
    @BeforeEach
    void setUp() {
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
    void testConstructor() {
        assertEquals(position, brick.getPosition());
        assertEquals(size, brick.getSize());
        assertEquals(health, brick.getHealth());
        assertEquals(color, brick.getColor());
    }

    /**
     * Test if Collision interacts with Health correclty.
     */
    @Test
    void testOnCollision() {
        final int damage = -1;

        brick.onCollision();
        assertEquals(health + damage, brick.getHealth());

        // Test health does not go below zero for normal bricks
        brick.setHealth(GameEntityImpl.MIN_HEALTH);
        brick.onCollision();
        assertEquals(0, brick.getHealth());
        brick.onCollision();
        assertEquals(0, brick.getHealth());

        // Test immortal brick does not lose health
        brick.setHealth(GameEntityImpl.IMMORTAL_ENTITY_HEALTH);
        brick.onCollision();
        assertEquals(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, brick.getHealth());
    }

    /**
     * Test if Brick Health works.
     */
    @Test
    void testIsAlive() {
        assertTrue(brick.isAlive());

        final int deathHealth = 0;
        brick.setHealth(deathHealth);
        assertFalse(brick.isAlive());

        // Test immortal brick is always alive
        brick.setHealth(GameEntityImpl.IMMORTAL_ENTITY_HEALTH);
        assertTrue(brick.isAlive());
    }

    /**
     * Test if Health is correct.
     */
    @Test
    void testSetAndGetHealth() {
        final int health = 5;
        brick.setHealth(health);
        assertEquals(health, brick.getHealth());
    }

    /**
     * Test if Brick is deterministic.
     */
    @Test
    void testEqualsAndHashCode() {
        final Point firstBrickPosition = new Point(10, 20);
        final Point secondBrickPosition = new Point(15, 25);
        final Dimension brickDimension = new Dimension(30, 10);
        final int brickHealth = 3;

        final Brick anotherBrick = new Brick(firstBrickPosition, brickDimension, brickHealth, Color.RED);
        assertEquals(brick, anotherBrick);
        assertEquals(brick.hashCode(), anotherBrick.hashCode());

        final Brick differentBrick = new Brick(secondBrickPosition, brickDimension, brickHealth, Color.RED);
        assertNotEquals(brick, differentBrick);
        assertNotEquals(brick.hashCode(), differentBrick.hashCode());
    }

    /**
     * Test if returned String is correct.
     */
    @Test
    void testToString() {
        final String expectedOutput = "(10;20)";
        assertEquals(expectedOutput, brick.toString());
    }

    /**
     * Test if Position is correct.
     */
    @Test
    void testGetPosition() {
        assertEquals(position, brick.getPosition());
    }

    /**
     * Test if Size is correct.
     */
    @Test
    void testGetSize() {
        assertEquals(size, brick.getSize());
    }
}
