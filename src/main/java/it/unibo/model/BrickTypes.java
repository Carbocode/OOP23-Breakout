package it.unibo.model;

import java.util.ArrayList;
import java.util.Random;

import it.unibo.api.GameEntityImpl;

/**
 * Enum declaring all brick types.
 */
public enum BrickTypes {
    /**
     * Plain brick.
     */
    ONE_HIT(GameEntityImpl.MIN_HEALTH, 10),
    /**
     * Double hit brick.
     */
    DOUBLE_HIT(2, 3),
    /**
     * Grey, immortal bricks.
     */
    IMMORTAL(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, 1);

    private final int health;
    private final int occurrence;

    /**
     * Constructor.
     * 
     * @param health     the health of the brick
     * @param occurrence the occurrence rate of the brick type
     */
    BrickTypes(final int health, final int occurrence) {
        this.health = health;
        this.occurrence = occurrence;
    }

    /**
     * Gets the health of the brick.
     * 
     * @return the health of the brick
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Chooses a random health for the brick.
     * 
     * @param rand the Random object to use for generating random numbers
     * @return a random health value for the brick
     */
    public static int getHealth(final Random rand) {
        ArrayList<BrickTypes> weightedList = new ArrayList<>();
        for (BrickTypes type : BrickTypes.values()) {
            for (int i = 0; i < type.getOccurrence(); i++) {
                weightedList.add(type);
            }
        }
        return weightedList.get(rand.nextInt(weightedList.size())).getHealth();
    }

    /**
     * Gets the occurrence rate of the brick type.
     * 
     * @return the occurrence rate of the brick type
     */
    public int getOccurrence() {
        return this.occurrence;
    }
}
