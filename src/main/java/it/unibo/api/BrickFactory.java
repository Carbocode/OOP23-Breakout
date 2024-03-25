package it.unibo.api;

import java.awt.*;

import it.unibo.controller.Brick;;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public interface BrickFactory {
    /**
     * Creates a brick that should bit hitten multiple times to get destroyed
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @param color    color of the brick
     * 
     * @return Brick
     */
    Brick createStandardBrick(Point position, Dimension size, Color color);

    /**
     * Creates a brick that should bit hitten multiple times to get destroyed
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * 
     * @return Brick
     */
    Brick createIndestructibleBrick(Point position, Dimension size);

    /**
     * Creates a brick that should be hitten multiple times to get destroyed
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @param color    color of the brick
     * @param health   the number of time you should hit the brick to destroy it
     * 
     * @return Brick
     */
    Brick createMultiHitBrick(Point position, Dimension size, Color color, int health);

}
