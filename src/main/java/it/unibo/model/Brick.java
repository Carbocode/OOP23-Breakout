package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.api.GameEntityImpl;

public class Brick extends GameEntityImpl {

    final public static double ASPECT_RATIO = 7.0 / 3;

    public Brick(Point position, Dimension size, int health, Color color) {
        super(position, size, health, color);
    }

    @Override
    public void onCollision() {
        System.out.println(super.getPosition());
        int health = super.getHealth();
        if (health > 0)
            super.setHealth(--health);
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() != -1 ? super.isAlive() : true;
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

    @Override
    public String toString() {
        return "(" + position.x + ";" + position.y + ")";
    }
}
