package it.unibo.api;

import java.util.Set;

import it.unibo.model.Brick;

/**
 * Generates the level procedurally, based on difficulty.
 */
public interface BrickWall {
    /**
     * Creates the level layout.
     */
    void generateLayout();

    /**
     * Resets the level.
     */
    void resetLayout();

    /**
     * Sets the height of the level.
     * 
     * @param height the height of the level
     */
    void setHeight(int height);

    /**
     * Sets the width of the level.
     * 
     * @param width the width of the level
     */
    void setWidth(int width);

    /**
     * Gets the height of the level.
     * 
     * @return the height of the level
     */
    int getHeight();

    /**
     * Gets the width of the level.
     * 
     * @return the width of the level
     */
    int getWidth();

    /**
     * Gets the set of bricks in the level.
     * 
     * @return the set of bricks
     */
    Set<Brick> getWall();
}
