package it.unibo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.GameInfo;
import java.awt.Point;
import java.awt.Dimension;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 * Class able to test Bar features.
 */
class BarTest {

    private Bar bar;

    private static final int TEST_X_POSITION = 100;
    private static final int TEST_Y_POSITION = 200;
    private static final int TEST_WIDTH = 80;
    private static final int TEST_HEIGHT = 10;

    private static final int TEST_OUT_OF_BOUND_Y = 200;
    private static final int TEST_OUT_OF_BOUND_X = 0;

    private static final int TEST_MOVE_X = 100;
    private static final int TEST_MOVE_Y = 200;
    private static final double TEST_MOVE_AMOUNT = 0.01;

    /**
     * setup for each task.
     */
    @BeforeEach
    final void setUp() {
        final Point position = new Point(TEST_X_POSITION, TEST_Y_POSITION);
        final Dimension size = new Dimension(TEST_WIDTH, TEST_HEIGHT);
        final int health = 100;
        final Color color = Color.RED;
        bar = new Bar(position, size, health, color);
    }

    /**
     * test left movement.
     */
    @Test
    void testMoveLeft() {
        simulateKeyPress(KeyEvent.VK_LEFT);
        bar.move();
        final Point expectedPosition = new Point((int) (TEST_MOVE_X - TEST_MOVE_AMOUNT * GameInfo.GAME_WIDTH),
                TEST_MOVE_Y);
        assertEquals(expectedPosition, bar.getPosition());
    }

    /**
     * test right movement.
     */
    @Test
    void testMoveRight() {
        simulateKeyPress(KeyEvent.VK_RIGHT);
        bar.move();
        final Point expectedPosition = new Point((int) (TEST_MOVE_X + TEST_MOVE_AMOUNT * GameInfo.GAME_WIDTH),
                TEST_MOVE_Y);
        assertEquals(expectedPosition, bar.getPosition());
    }

    /**
     * test move out of game bounds.
     */
    @Test
    void testMoveOutOfBounds() {
        bar.setPosition(new Point(TEST_OUT_OF_BOUND_X, TEST_OUT_OF_BOUND_Y));
        simulateKeyPress(KeyEvent.VK_LEFT);
        bar.move();
        assertEquals(new Point(TEST_OUT_OF_BOUND_X, TEST_OUT_OF_BOUND_Y), bar.getPosition());
    }

    /**
     * test width setter.
     */
    @Test
    void testSetWidth() {
        bar.setWidth(100);
        assertEquals(new Dimension(100, 10), bar.getSize());
    }

    /**
     * test butto press left.
     */
    @Test
    void testButtonPressedLeft() {
        simulateKeyPress(KeyEvent.VK_LEFT);
        assertEquals(-1, bar.getDirection());
    }

    /**
     * test butto press right.
     */
    @Test
    void testButtonPressedRight() {
        simulateKeyPress(KeyEvent.VK_RIGHT);
        assertEquals(1, bar.getDirection());
    }

    /**
     * test button release left.
     */
    @Test
    void testButtonReleasedLeft() {
        bar.setDirection(-1);
        simulateKeyRelease(KeyEvent.VK_LEFT);
        assertEquals(0, bar.getDirection());
    }

    /**
     * test butto released right.
     */
    @Test
    void testButtonReleasedRight() {
        bar.setDirection(1);
        simulateKeyRelease(KeyEvent.VK_RIGHT);
        assertEquals(0, bar.getDirection());
    }

    // Metodo ausiliario per simulare la pressione di un tasto
    private void simulateKeyPress(final int keyCode) {
        final KeyEvent keyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                keyCode,
                'Z');
        bar.buttonPressed(keyEvent);
    }

    // Metodo ausiliario per simulare il rilascio di un tasto
    private void simulateKeyRelease(final int keyCode) {
        final KeyEvent keyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,
                keyCode,
                'Z');
        bar.buttonReleased(keyEvent);
    }
}
