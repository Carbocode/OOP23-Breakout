package it.unibo.api;

/* An interface modelling a factory of Bar.
 * The general idea is that the software will use a Bar controlled by the player
 * to deflect the ball against the bricks in the top of the game area  */

public interface Bar {

    /**
     * @return void 
     * move the bar to the left or right
     * @param distance used to choose the amount of space to move (if the amount is positive the bar will move right, if it's negative it will move left)
     */
    void move(int distance);

    /**
     * @return void 
     * set the width of the bar to the given one
     * @param width new width to set the bar
     */
    void setWidth(int width);
}