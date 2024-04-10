package main.java.it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public interface GameEntity {

    public Point getPosition();

    public Dimension getSize();

    public int getHealth();

    public Color getColor();

    public void onCollision();

    public boolean isAlive();

    public void setPosition(final Point position);

    public void setSize(final Dimension size);

    public void setHealth(int health);

    public void setColor(final Color color);

}