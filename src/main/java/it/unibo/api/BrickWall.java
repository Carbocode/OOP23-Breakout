package it.unibo.api;

import java.util.Set;

import it.unibo.model.Brick;

/**
 * Generates the level procedurally, based on difficulty.
 */
public interface BrickWall {
    /**
     * Create Level Layout.
     */
    void generateLayout();

    /**
     * Resets the level.
     */
    void resetLayout();

    /**
     * Resets the level.
     */
    void shiftLayout();

    /**
     * Resets the level.
     */
    void removeDeathBricks();

    void setHeight(int height);

    void setWidth(int width);

    int getHeight();

    int getWidth();

    Set<Brick> getWall();
}
