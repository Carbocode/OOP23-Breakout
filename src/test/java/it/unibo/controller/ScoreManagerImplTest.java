package it.unibo.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the ScoreManagerImpl class.
 */
public class ScoreManagerImplTest {

    private ScoreManagerImpl scoreManager;

    /**
     * Set up the test environment before each test case.
     */
    @Before
    public void setUp() {
        scoreManager = new ScoreManagerImpl();
    }

    /**
     * Test the constructor of the ScoreManagerImpl class.
     */
    @Test
    public void testConstructor() {
        assertEquals(0, scoreManager.getScore());
    }

    /**
     * Test the increment method of the ScoreManagerImpl class.
     */
    @Test
    public void testIncrement() {
        int points = 100;
        scoreManager.increment(points);
        assertEquals(points, scoreManager.getScore());

        points = 50;
        scoreManager.increment(points);
        assertEquals(150, scoreManager.getScore());
    }

    /**
     * Test the getScore method of the ScoreManagerImpl class.
     */
    @Test
    public void testGetScore() {
        assertEquals(0, scoreManager.getScore());

        scoreManager.increment(100);
        assertEquals(100, scoreManager.getScore());

        scoreManager.increment(50);
        assertEquals(150, scoreManager.getScore());
    }
}
