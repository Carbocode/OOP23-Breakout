package it.unibo.api;

/**
 * An interface modelling the score manager.
 * the idea is to pass to the increment method the points of the bricks
 * destroyed than the method will increment the
 * score and the getScore return the score
 */

public interface ScoreManager {

    /**
     * this function increments the score by the points that the bricks contain.
     * 
     * @param points
     */
    void increment(int points);

    /**
     * this function return the score of the game that the player is playing.
     * 
     * @return int
     */
    int getScore();
}
