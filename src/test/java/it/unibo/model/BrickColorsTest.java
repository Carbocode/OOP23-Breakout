package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for BrickColors.
 */
class BrickColorsTest {

    /**
     * Test if color layout is correct.
     */
    @Test
    void testGetColorByRow() {
        int row = 0;

        assertEquals(BrickColors.PURPLE.getColor(), BrickColors.getColor(row));
        assertEquals(BrickColors.BLUE.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.CYAN.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.YELLOW.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.MAGENTA.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.RED.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.DARK_PURPLE.getColor(), BrickColors.getColor(++row));

        assertEquals(BrickColors.PURPLE.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.BLUE.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.CYAN.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.YELLOW.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.MAGENTA.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.RED.getColor(), BrickColors.getColor(++row));
        assertEquals(BrickColors.DARK_PURPLE.getColor(), BrickColors.getColor(++row));
    }
}
