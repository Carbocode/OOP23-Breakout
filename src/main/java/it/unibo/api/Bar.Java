package main.java.it.unibo.api;

/**
 * An interface modelling a factory of Bar.
 * The general idea is that the software will use a Bar controlled by the player
 * to deflect the ball against the bricks in the top of the game area
 */

 public interface Bar {


    /**
     * @return void 
     * move the bar to the left or right
     * @param direction used to select the direction, true = go left , false = go right
     * @param distance used to choose the amount of space to move
     */
    void move(boolean direction,int distance);
}