package it.unibo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.api.GameInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class BarTest {

    private Bar bar;

    @BeforeEach
    public void setUp() {
        Point position = new Point(100, 200);
        Dimension size = new Dimension(80, 10);
        int health = 100;
        Color color = Color.RED;
        bar = new Bar(position, size, health, color);
    }

    @Test
    public void testMoveLeft() {
        simulateKeyPress(KeyEvent.VK_LEFT);
        bar.move();
        Point expectedPosition = new Point((int) (100 - 0.01 * GameInfo.GAME_WIDTH), 200);
        assertEquals(expectedPosition, bar.getPosition());
    }

    @Test
    public void testMoveRight() {
        simulateKeyPress(KeyEvent.VK_RIGHT);
        bar.move();
        Point expectedPosition = new Point((int) (100 + 0.01 * GameInfo.GAME_WIDTH), 200);
        assertEquals(expectedPosition, bar.getPosition());
    }

    @Test
    public void testMoveOutOfBounds() {
        bar.setPosition(new Point(0, 200));
        simulateKeyPress(KeyEvent.VK_LEFT);
        bar.move();
        assertEquals(new Point(0, 200), bar.getPosition());
    }

    @Test
    public void testSetWidth() {
        bar.setWidth(100);
        assertEquals(new Dimension(100, 10), bar.getSize());
    }

    @Test
    public void testButtonPressedLeft() {
        simulateKeyPress(KeyEvent.VK_LEFT);
        assertEquals(-1, bar.getDirection());
    }

    @Test
    public void testButtonPressedRight() {
        simulateKeyPress(KeyEvent.VK_RIGHT);
        assertEquals(1, bar.getDirection());
    }

    @Test
    public void testButtonReleasedLeft() {
        bar.setDirection(-1);
        simulateKeyRelease(KeyEvent.VK_LEFT);
        assertEquals(0, bar.getDirection());
    }

    @Test
    public void testButtonReleasedRight() {
        bar.setDirection(1);
        simulateKeyRelease(KeyEvent.VK_RIGHT);
        assertEquals(0, bar.getDirection());
    }

    // Metodo ausiliario per simulare la pressione di un tasto
    private void simulateKeyPress(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, 'Z');
        bar.buttonPressed(keyEvent);
    }

    // Metodo ausiliario per simulare il rilascio di un tasto
    private void simulateKeyRelease(int keyCode) {
        KeyEvent keyEvent = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, keyCode, 'Z');
        bar.buttonReleased(keyEvent);
    }
}
