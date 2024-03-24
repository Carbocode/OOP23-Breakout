package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;



public interface GameEntity {

    Point getPosition();
    Dimension getSize();
    void onCollision();
    boolean isAlive();

    public void setHealth(int health);

    public int getHealth();

}