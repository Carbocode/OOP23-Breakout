package main.java.it.unibo.api;

import java.awt.Graphics2D;

public interface GameEntity {

    public Graphics2D getGraphics();

    public void onCollision();

    public boolean isAlive();

    public void setHealth(int health);

    public int getHealth();

}