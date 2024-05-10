package it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public interface GameEntity {
    /**
     * @return Position of the entity
     */
    Point getPosition();
    /**
     * @return Dimension of the entity
     */
    Dimension getSize();
    /**
     * @return Health of the entity
     */
    int getHealth();
    /**
     * @return Color of the entity
     */
    Color getColor();
    /**
     * 
     */
    void onCollision();
    /**
     * @return true if the entity is alive, false otherwise
     */
    boolean isAlive();
    /**
     * @param position
     */
    void setPosition(Point position);
    /**
     * @param size
     */
    void setSize(Dimension size);
    /**
     * @param health
     */
    void setHealth(int health);
    /**
     * @param color
     */
    void setColor(Color color);
}