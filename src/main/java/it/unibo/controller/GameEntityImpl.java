package it.unibo.controller;

import java.awt.Dimension;
import java.awt.Point;

abstract class GameEntityImpl implements GameEntity {

    final static int IMMORTAL_ENTITY_HEALTH = -1;
    private Graphics2D graphics;
    private int health;
    protected Point pos;
    protected Dimension size;

    public GameEntityImpl(Point pos,Dimension size, int health) {
        this.pos = pos;
        this.size = size;
        this.health = health;
    }

    public GameEntityImpl(Graphics2D graphics) {
        this(graphics, IMMORTAL_ENTITY_HEALTH);
    }

    @Override
    public Graphics2D getGraphics() {
        return graphics;
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
