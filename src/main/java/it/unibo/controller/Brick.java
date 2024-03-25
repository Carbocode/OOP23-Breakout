package it.unibo.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.api.GameEntityImpl;

public class Brick extends GameEntityImpl {

    public Brick(Point position, Dimension size, int health, Color color) {
        super(position, size, health, color);
    }

    public Brick(Point position, Dimension size, Color color) {
        super(position, size, color);
    }

    public Brick(Point position, Dimension size) {
        super(position, size);
    }

    @Override
    public void onCollision() {
        int health = super.getHealth();
        if (health > 0)
            super.setHealth(health--);
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Brick mattone = (Brick) o;

        return Objects.equals(getPosition(), mattone.getPosition());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }

    @Override
    public Point getPosition() {
        return super.getPosition();
    }

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

}
