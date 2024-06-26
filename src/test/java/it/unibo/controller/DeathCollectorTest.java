package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.*;

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

        aliveBrick = new Brick(new Point(10, 20), new Dimension(30, 10), 1, Color.RED);
        deadBrick = new Brick(new Point(15, 25), new Dimension(30, 10), 0, Color.GRAY);

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
