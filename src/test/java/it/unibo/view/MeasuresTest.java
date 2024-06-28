package it.unibo.view;

import static org.junit.Assert.assertEquals;

import java.awt.GraphicsEnvironment;

import org.junit.Test;

/**
 * JUnit test class for the Measures class.
 */
public class MeasuresTest {

    /**
     * Test the getScreenHeight method of the Measures class.
     */
    @Test
    public void testGetScreenHeight() {
        final Measures measures = new Measures();
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final int expectedHeight = ge.getMaximumWindowBounds().height;
        final int actualHeight = measures.getScreenHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    /**
     * Test the getScreenWidth method of the Measures class.
     */
    @Test
    public void testGetScreenWidth() {
        final Measures measures = new Measures();
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final int expectedWidth = ge.getMaximumWindowBounds().width;
        final int actualWidth = measures.getScreenWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    /**
     * Test the getGameAreaHeight method of the Measures class.
     */
    @Test
    public void testGetGameAreaHeight() {
        final Measures measures = new Measures();
        final int expectedHeight = measures.getScreenHeight() / 2;
        final int actualHeight = measures.getGameAreaHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    /**
     * Test the getGameAreaWidth method of the Measures class.
     */
    @Test
    public void testGetGameAreaWidth() {
        final Measures measures = new Measures();
        final int expectedWidth = measures.getScreenWidth() / 2;
        final int actualWidth = measures.getGameAreaWidth();
        assertEquals(expectedWidth, actualWidth);
    }
}
