package it.unibo.api;

import java.awt.*;;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public interface BrickFactory {
    /**
     * Creates a standard Brick
     * 
     * @param position position on the GUI
     * @param size     brick size
     * @param color
     * @return Brick
     */
    Brick createStandardBrick(Point position, Dimension size, Color color);

    /**
     * Creates a Brick that can't get destroyed
     * 
     * @param position position on the GUI
     * @param size     brick size
     * 
     * @return Brick
     */
    Brick createIndestructibleBrick(Point position, Dimension size);

    /**
     * Creates a brick that should bit hitten multiple times to get destroyed
     * 
     * @param position position on the GUI
     * @param size     brick size
     * @param color
     * @param health   the number of time you should hit the brick to destroy it
     * 
     * @return Brick
     */
    Brick createMultiHitBrick(Point position, Dimension size, Color color, int health);

}
