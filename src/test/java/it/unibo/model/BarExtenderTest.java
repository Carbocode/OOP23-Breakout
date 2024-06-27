package it.unibo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;

/**
 * JUnit test class for the BarExtender class.
 */
public class BarExtenderTest {

    /**
     * Test the extendBar method of the BarExtender class.
     */
    @Test
    public void testExtendBar() {
        // Create a Bar instance
        Point position = new Point(100, 200);
        Dimension size = new Dimension(80, 10);
        int health = 100;
        Color color = Color.RED;
        final Bar bar = new Bar(position, size, health, color);

        // Get the initial width of the bar
        final int initialWidth = bar.getSize().width;

        // Extend the bar
        BarExtender.extendBar(bar);

        // Get the new width of the bar
        final int newWidth = bar.getSize().width;

        // Assert that the new width is increased by the expected amount
        assertEquals(initialWidth + BarExtender.getIncreaseAmount(), newWidth);
    }
}
