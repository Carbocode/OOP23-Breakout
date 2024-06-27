package it.unibo.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.unibo.api.GameInfo;
import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.Brick;
import it.unibo.model.BrickColors;

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
        final Point position = new Point(10, 20);
        final Dimension size = new Dimension(30, 10);
        final Color color = BrickColors.getColor(position.y / size.height);
        final Ball ball = new Ball();
        final Brick brick = new Brick(position, size, 0, color);
        balls.add(ball);
        bricks.add(brick);

        gameView.updateGameState(balls, bricks, bar, score);

        assertEquals(balls, gameView.getBalls());
        assertEquals(bricks, gameView.getBricks());
        assertEquals(bar, gameView.getBar());
        assertEquals(score, gameView.getScore());
    }

}
