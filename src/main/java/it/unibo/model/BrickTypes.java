package it.unibo.model;

import java.util.ArrayList;
import java.util.Random;

import it.unibo.api.GameEntityImpl;
import it.unibo.controller.BrickFactory;

/**
 * Enum declaring all brick types.
 */
public enum BrickTypes {
    /**
     * Plain brick.
     */
    ONE_HIT(GameEntityImpl.MIN_HEALTH, 10),
    DOUBLE_HIT(2, 3),
    /**
     * Grey, immortal bricks.
     */
    IMMORTAL(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, 1);

    private int health;
    private int occurence;

    /**
     * Constructor.
     * 
     * @param health
     * @param occurence
     */
    BrickTypes(final int health, final int occurence) {
        this.health = health;
        this.occurence = occurence;
    }

    /**
     * 
     * @return health of a brick
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Choses a Health for the Brick
     * 
     * @return a Health for the Brick
     */
    static public int getRandomHealth(Random rand) {
        ArrayList<BrickTypes> weightedList = new ArrayList<>();
        for (BrickTypes type : BrickTypes.values()) {
            for (int i = 0; i < type.getOccurence(); i++) {
                weightedList.add(type);
            }
        }
        return weightedList.get(rand.nextInt(weightedList.size())).getHealth();
    }

    /**
     * 
     * @return occurence of a brick type
     */
    public int getOccurence() {
        return this.occurence;
    }
}
