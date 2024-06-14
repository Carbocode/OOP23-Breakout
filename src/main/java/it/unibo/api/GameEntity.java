package it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * The GameEntity interface defines the basic properties and behaviors
 * that any game entity must implement.
 */
public interface GameEntity {

    /**
     * Gets the position of the game entity.
     *
     * @return the current position as a Point
     */
    Point getPosition();

    /**
     * Gets the size of the game entity.
     *
     * @return the current size as a Dimension
     */
    Dimension getSize();

    /**
     * Gets the health of the game entity.
     *
     * @return the current health as an integer
     */
    int getHealth();

    /**
     * Gets the color of the game entity.
     *
     * @return the current color as a Color
     */
    Color getColor();

    /**
     * Handles the collision event for the game entity.
     */
    void onCollision();

    /**
     * Checks if the game entity is alive (health > 0).
     *
     * @return true if the entity is alive, false otherwise
     */
    boolean isAlive();

    /**
     * Sets the position of the game entity.
     *
     * @param position the new position as a Point
     */
    void setPosition(Point position);

    /**
     * Sets the size of the game entity.
     *
     * @param size the new size as a Dimension
     */
    void setSize(Dimension size);

    /**
     * Sets the health of the game entity.
     *
     * @param health the new health as an integer
     */
    void setHealth(int health);

    /**
     * Sets the color of the game entity.
     *
     * @param color the new color as a Color
     */
    void setColor(Color color);
}
