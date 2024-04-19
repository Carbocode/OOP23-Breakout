package it.unibo.api;

/* An interface modelling a factory of Bar.
 * The general idea is that the software will use a Bar controlled by the player
 * to deflect the ball against the bricks in the top of the game area 
 */
public interface Bar {
    /**
     * @return void 
     * move the bar to the left or right
     */
    void move();

    /**
     * @return void 
     * set the width of the bar to the given one
     * @param width new width to set the bar
     */
    void setWidth(int width);
}
