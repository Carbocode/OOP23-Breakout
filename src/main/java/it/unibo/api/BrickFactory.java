package it.unibo.api;

import java.awt.*;

import it.unibo.controller.Brick;;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public interface BrickFactory {
    /**
     * Creates a standard Brick
     * 
     * @param graphics graphics characteristics of the brick
     */
    Brick createStandardBrick(Graphics2D graphics);

    /**
     * Creates a Brick that can't get destroyed
     * 
     * @param graphics graphics characteristics of the brick
     * 
     * @return Brick
     */
    Brick createIndestructibleBrick(Graphics2D graphics);

    /**
     * Creates a brick that should bit hitten multiple times to get destroyed
     * 
     * @param graphics graphics characteristics of the brick
     * @param health   the number of time you should hit the brick to destroy it
     * 
     * @return Brick
     */
    Brick createMultiHitBrick(Graphics2D graphics, int health);

}
