package it.unibo.api;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.Brick;
/**
 * 
 */
public interface GameLoopAccessor {

    /**
     * 
     * @param newBalls
     */
    void addBalls(List<Ball> newBalls);
    /**
     * this method returns the balls of the game.
     * 
     * @return unmodifiable set of balls
     */
    Set<Ball> getBalls();

    /**
     * this method returns the bricks of the game.
     * 
     * @return unmodifiable set of bricks
     */
    Set<Brick> getBricks();

    /**
     * this method return the bar of the game.
     * 
     * @return bar
     */
    Bar getBar();

    /**
     * 
     * @return ScoreManager
     */
    int getScore();

    /**
     * 
     * @param e
     */
    void handleKeyRelease(KeyEvent e);

    /**
     * 
     * @param e
     */
    void handleKeyPress(KeyEvent e);
     /**
     * 
     * @param amount
     */
    void increaseScore(int amount);
    /**
     * 
     */
    void extendPaddle();

}
