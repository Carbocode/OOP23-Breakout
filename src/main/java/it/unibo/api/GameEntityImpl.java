package it.unibo.api;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class GameEntityImpl implements GameEntity {

    final static int IMMORTAL_ENTITY_HEALTH = -1;
    private int health;

    public GameEntityImpl(Point pos,Dimension size, int health) {
        
        this.health = health;
    }

    public GameEntityImpl(Point pos, Dimension size) {
        this(pos,size, IMMORTAL_ENTITY_HEALTH);
    }


    @Override
    abstract public void onCollision();

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

}
