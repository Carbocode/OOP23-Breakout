package it.unibo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import it.unibo.api.GameEntityImpl;

public class BrickTypesTest {

    @Test
    public void testGetHealth() {
        assertEquals(GameEntityImpl.MIN_HEALTH, BrickTypes.ONE_HIT.getHealth());
        assertEquals(2, BrickTypes.DOUBLE_HIT.getHealth());
        assertEquals(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, BrickTypes.IMMORTAL.getHealth());
    }

    @Test
    public void testGetOccurrence() {
        assertEquals(10, BrickTypes.ONE_HIT.getOccurrence());
        assertEquals(3, BrickTypes.DOUBLE_HIT.getOccurrence());
        assertEquals(1, BrickTypes.IMMORTAL.getOccurrence());
    }

    /*
     * @Test
     * public void testGetRandomHealth() {
     * Random rand = new Random(0); // Use a fixed seed for deterministic behavior
     * int health = BrickTypes.getRandomHealth(rand);
     * 
     * // Depending on the Random seed and distribution, adjust the expected value
     * assertTrue(
     * health == GameEntityImpl.MIN_HEALTH || health == 2 || health ==
     * GameEntityImpl.IMMORTAL_ENTITY_HEALTH);
     * 
     * // To further validate, we can check if the distribution matches expected
     * // probabilities
     * int oneHitCount = 0;
     * int doubleHitCount = 0;
     * int immortalCount = 0;
     * int totalTrials = 100000;
     * 
     * for (int i = 0; i < totalTrials; i++) {
     * int h = BrickTypes.getRandomHealth(rand);
     * if (h == GameEntityImpl.MIN_HEALTH) {
     * oneHitCount++;
     * } else if (h == 2) {
     * doubleHitCount++;
     * } else if (h == GameEntityImpl.IMMORTAL_ENTITY_HEALTH) {
     * immortalCount++;
     * }
     * }
     * 
     * double oneHitProbability = (double) oneHitCount / totalTrials;
     * double doubleHitProbability = (double) doubleHitCount / totalTrials;
     * double immortalProbability = (double) immortalCount / totalTrials;
     * 
     * // Check if probabilities are roughly as expected (0.769, 0.230, 0.007)
     * assertTrue(oneHitProbability > 0.76 && oneHitProbability < 0.78);
     * assertTrue(doubleHitProbability > 0.22 && doubleHitProbability < 0.24);
     * assertTrue(immortalProbability > 0.006 && immortalProbability < 0.008);
     * }
     */
}
