package it.unibo.api;

import java.util.List;

import it.unibo.controller.Brick;

/**
 * Generates the level procedurally, based on difficulty
 */
public interface BrickWall {
    /**
     * Create Level Layout
     * 
     * @return
     */
    List<Brick> generateLayout();

    /**
     * Resets the level
     */
    void resetLayout();

    /**
     * Resets the level
     */
    void shiftLayout();

    /**
     * Resets the level
     */
    void removeDeathBricks();
}
