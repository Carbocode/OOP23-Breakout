package it.unibo.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.awt.Dimension;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.unibo.api.GameInfo;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.Brick;


/**
 * JUnit test class for the GameView class.
 */
public class GameViewTest {

    private GameView gameView;
    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Bar bar;
    private int score;

    /**
     * Set up the test environment before each test case.
     */
    @Before
    public void setUp() {
        gameView = new GameView();
        balls = new HashSet<>();
        bricks = new HashSet<>();
        bar = new Bar(null, null, score, null);
        score = 100;
    }

    /**
     * Test the constructor of the GameView class.
     */
    @Test
    public void testConstructor() {
        assertNotNull(gameView);
        assertEquals(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT), gameView.getPreferredSize());
    }

    /**
     * Test the updateGameState method of the GameView class.
     */
    @Test
    public void testUpdateGameState() {
    }

}
