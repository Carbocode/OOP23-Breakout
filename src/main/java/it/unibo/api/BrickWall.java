package it.unibo.api;

import java.util.Set;
import it.unibo.controller.Brick;

/**
 * Generates the level procedurally, based on difficulty
 */
public interface BrickWall {
    /**
     * Create Level Layout
     */
    public void generateLayout();

    /**
     * Resets the level
     */
    public void resetLayout();

    /**
     * Resets the level
     */
    public void shiftLayout();

    /**
     * Resets the level
     */
    public void removeDeathBricks();

    public void setHeight(int height);

    public void setWidth(int width);

    public void setSeed(String seed);

    public int getHeight();

    public int getWidth();

    public String getSeed();

    public Set<Brick> getWall();
}
