package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BrickColorsTest {

    /**
     * Test if color layout is correct.
     */
    @Test
    public void testGetColorByRow() {
        assertEquals(BrickColors.PURPLE.getColor(), BrickColors.getColor(0));
        assertEquals(BrickColors.BLUE.getColor(), BrickColors.getColor(1));
        assertEquals(BrickColors.CYAN.getColor(), BrickColors.getColor(2));
        assertEquals(BrickColors.YELLOW.getColor(), BrickColors.getColor(3));
        assertEquals(BrickColors.MAGENTA.getColor(), BrickColors.getColor(4));
        assertEquals(BrickColors.RED.getColor(), BrickColors.getColor(5));
        assertEquals(BrickColors.DARK_PURPLE.getColor(), BrickColors.getColor(6));

        assertEquals(BrickColors.PURPLE.getColor(), BrickColors.getColor(7));
        assertEquals(BrickColors.BLUE.getColor(), BrickColors.getColor(8));
        assertEquals(BrickColors.CYAN.getColor(), BrickColors.getColor(9));
        assertEquals(BrickColors.YELLOW.getColor(), BrickColors.getColor(10));
        assertEquals(BrickColors.MAGENTA.getColor(), BrickColors.getColor(11));
        assertEquals(BrickColors.RED.getColor(), BrickColors.getColor(12));
        assertEquals(BrickColors.DARK_PURPLE.getColor(), BrickColors.getColor(13));
    }
}
