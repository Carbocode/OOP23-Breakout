package it.unibo.controller;

import java.awt.Graphics2D;

import it.unibo.api.GameEntity;

abstract class GameEntityImpl implements GameEntity {

    final static int IMMORTAL_ENTITY_HEALTH = -1;
    private Graphics2D graphics;
    private int health;

    public GameEntityImpl(Graphics2D graphics, int health) {
        this.graphics = graphics;
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
