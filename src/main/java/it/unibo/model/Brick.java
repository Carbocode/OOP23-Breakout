package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.api.GameEntityImpl;

public class Brick extends GameEntityImpl {

    public static final double ASPECT_RATIO = 7.0 / 3;

    public Brick(final Point position, final Dimension size, final int health, final Color color) {
        super(position, size, health, color);
    }

    @Override
    public final void onCollision() {
        System.out.println(super.getPosition());
        int health = super.getHealth();
        if (health > 0) {
            super.setHealth(--health);
        }
    }

    @Override
    public final boolean isAlive() {
        return this.getHealth() != -1 ? super.isAlive() : true;
    }

    @Override
    public final void setHealth(final int health) {
        super.setHealth(health);
    }

    @Override
    public final int getHealth() {
        return super.getHealth();
    }

    @Override
    public final boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Brick mattone = (Brick) o;

        return Objects.equals(getPosition(), mattone.getPosition());

    }

    @Override
    public final int hashCode() {
        return Objects.hash(getPosition());
    }

    @Override
    public final Point getPosition() {
        return super.getPosition();
    }

    @Override
    public final Dimension getSize() {
        return super.getSize();
    }

    @Override
    public final String toString() {
        return "(" + getPosition().x + ";" + getPosition().y + ")";
    }
}
