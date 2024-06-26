package it.unibo.model;

import it.unibo.api.GameEntityImpl;

/**
 * Enum declaring all brick types.
 */
public enum BrickTypes {
    /**
     * Plain brick.
     */
    ONE_HIT(GameEntityImpl.MIN_HEALTH, 10),
    // DOUBLE_HIT(2, 40),
    /**
     * Grey, immortal bricks.
     */
    IMMORTAL(GameEntityImpl.IMMORTAL_ENTITY_HEALTH, 1);

    private int health;
    private int occurence;

    /**
     *  Constructor.
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
     * 
     * @return occurence of a brick type
     */
    public int getOccurence() {
        return this.occurence;
    }
}
