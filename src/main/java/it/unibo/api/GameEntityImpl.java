package it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public abstract class GameEntityImpl implements GameEntity {

    final static int IMMORTAL_ENTITY_HEALTH = -1;

    protected Point position;
    protected Dimension size;
    protected Color color;
    protected int health;

    public GameEntityImpl(Point position, Dimension size, int health, Color color) {
        this.position = position;
        this.size = size;
        this.health = health;
        this.color = color;
    }

    public GameEntityImpl(Point position, Dimension size, int health) {
        this.position = position;
        this.size = size;
        this.health = health;
        this.color = new Color(70, 70, 70);
    }

    public GameEntityImpl(Point position, Dimension size, Color color) {
        this.position = position;
        this.size = size;
        this.health = IMMORTAL_ENTITY_HEALTH;
        this.color = color;
    }

    public GameEntityImpl(Point position, Dimension size) {
        this.position = position;
        this.size = size;
        this.health = IMMORTAL_ENTITY_HEALTH;
        this.color = new Color(70, 70, 70);
    }

    @Override
    abstract public void onCollision();

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Color getColor() {
        return color;
    }

}