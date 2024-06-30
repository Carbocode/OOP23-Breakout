package it.unibo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;

/**
 * JUnit test class for the BarExtender class.
 */
 class BarExtenderTest {

    /**
     * Test the extendBar method of the BarExtender class.
     */
    @Test
     void testExtendBar() {
        // Create a Bar instance
        final int n1 = 100;
        final int n2 = 200;
        final int n3 = 80;
        final int n4 = 10;
        final Point position = new Point(n1, n2);
        final Dimension size = new Dimension(n3, n4);
        final int health = n1;
        final Color color = Color.RED;
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
