package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

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
        BrickColors[] colors = BrickColors.values();

        for (int i = 0; i < (colors.length * 2); i++) {
            assertEquals(colors[i % colors.length].getColor(), BrickColors.getColor(i));
        }
    }
}
