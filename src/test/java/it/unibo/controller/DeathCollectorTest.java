package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.Brick;

/**
 * Test class for DeathCollector.
 */
class DeathCollectorTest {

    private Set<Brick> entities;
    private Brick aliveBrick;
    private Brick deadBrick;

    /**
     * Setup before each test.
     */
    @BeforeEach
    void setUp() {
        entities = new HashSet<>();

        final Point firstBrickPosition = new Point(10, 20);
        final Point secondBrickPosition = new Point(15, 25);
        final Dimension brickDimension = new Dimension(30, 10);
        final int firstBrickHealth = 1;
        final int secondBrickHealth = 0;

        aliveBrick = new Brick(firstBrickPosition, brickDimension, firstBrickHealth, Color.RED);
        deadBrick = new Brick(secondBrickPosition, brickDimension, secondBrickHealth, Color.GRAY);

        entities.add(aliveBrick);
        entities.add(deadBrick);
    }

    /**
     * Test that dead entities are removed from the set.
     */
    @Test
    void testCheckEntitiesRemovesDeadEntities() {
        DeathCollector.checkEntities(entities);
        assertTrue(entities.contains(aliveBrick));
        assertFalse(entities.contains(deadBrick));
    }

    /**
     * Test that no entities are removed when all are alive.
     */
    @Test
    void testCheckEntitiesWithAllAlive() {
        entities.remove(deadBrick);
        DeathCollector.checkEntities(entities);
        assertTrue(entities.contains(aliveBrick));
    }

    /**
     * Test that all entities are removed when all are dead.
     */
    @Test
    void testCheckEntitiesWithAllDead() {
        entities.remove(aliveBrick);
        DeathCollector.checkEntities(entities);
        assertTrue(entities.isEmpty());
    }

    /**
     * Test that checkEntities handles an empty set correctly.
     */
    @Test
    void testCheckEntitiesWithEmptySet() {
        entities.clear();
        DeathCollector.checkEntities(entities);
        assertTrue(entities.isEmpty());
    }
}
