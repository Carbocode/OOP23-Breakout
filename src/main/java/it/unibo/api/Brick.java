package it.unibo.api;

import java.awt.*;

/**
 * Components to destory to win the match that increment your points
 */
public interface Brick {
    /**
     * Method called when the Brick is being hitted by the ball
     * 
     * @return void
     */
    void hit();

    /**
     * 
     * @return
     */
    boolean isDestroyed();

    /**
     * Get the Position of the brick
     * 
     * @return
     */
    Point getPosition();

    /**
     * Get the Size of the brick
     */
    Dimension getSize();

    /**
     * Get the color of the brick
     * 
     * @return Color
     */
    Color getColor();

    /**
     * Places the Brick on the GUI
     * 
     * @param g
     */
    void draw(Graphics g);
}
