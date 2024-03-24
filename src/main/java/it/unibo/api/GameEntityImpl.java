package it.unibo.api;

import java.awt.Dimension;
import java.awt.Point;

public abstract class GameEntityImpl implements GameEntity {

    final static int IMMORTAL_ENTITY_HEALTH = -1;
    private int health;
    protected Point pos;
    protected Dimension size;

    public GameEntityImpl(Point pos,Dimension size, int health) {
        this.pos = pos;
        this.size = size;
        this.health = health;
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