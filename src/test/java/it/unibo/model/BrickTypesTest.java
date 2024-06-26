package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibo.api.GameEntityImpl;

/**
 * Test class for BrickTypes.
 */
class BrickTypesTest {

    /**
     * Test if Health is correct.
     */
    @Test
    void testGetHealth() {
        assertEquals(GameEntityImpl.MIN_HEALTH, BrickTypes.ONE_HIT.getHealth());
        assertEquals(2, BrickTypes.DOUBLE_HIT.getHealth());
        assertEquals(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, BrickTypes.IMMORTAL.getHealth());
    }

    /**
     * Test if Occurrence is correct.
     */
    @Test
    void testGetOccurrence() {
        assertEquals(10, BrickTypes.ONE_HIT.getOccurrence());
        assertEquals(3, BrickTypes.DOUBLE_HIT.getOccurrence());
        assertEquals(1, BrickTypes.IMMORTAL.getOccurrence());
    }
}
