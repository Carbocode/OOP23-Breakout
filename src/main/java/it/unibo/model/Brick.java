package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.api.GameEntityImpl;

/**
 * Brick class.
 */
public class Brick extends GameEntityImpl {
    public static final long serialVersionUID = 5328743;
    /**
     * Aspect Ratio.
     */
    public static final double ASPECT_RATIO = 7.0 / 3;

    /**
     * Constructor.
     * 
     * @param position the position of the brick
     * @param size     the size of the brick
     * @param health   the health of the brick
     * @param color    the color of the brick
     */
    public Brick(final Point position, final Dimension size, final int health, final Color color) {
        super(position, size, health, color);
    }

    /**
     * Handles collision with the brick.
     */
    @Override
    public final void onCollision() {
        int health = super.getHealth();
        if (health > 0) {
            super.setHealth(--health);
        }
    }

    /**
     * Checks if the brick is alive.
     * 
     * @return true if the brick is alive, false otherwise
     */
    @Override
    public final boolean isAlive() {
        return this.getHealth() == -1 || super.isAlive();
    }

    /**
     * Sets the health of the brick.
     * 
     * @param health the new health value
     */
    @Override
    public final void setHealth(final int health) {
        super.setHealth(health);
    }

    /**
     * Gets the health of the brick.
     * 
     * @return the health of the brick
     */
    @Override
    public final int getHealth() {
        return super.getHealth();
    }

    /**
     * Checks if this brick is equal to another object.
     * 
     * @param o the object to compare with
     * @return true if this brick is equal to the specified object, false otherwise
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Brick brick = (Brick) o;
        return Objects.equals(getPosition(), brick.getPosition());
    }

    /**
     * Returns a hash code value for the brick.
     * 
     * @return a hash code value for this brick
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getPosition());
    }

    /**
     * Gets the position of the brick.
     * 
     * @return the position of the brick
     */
    @Override
    public final Point getPosition() {
        return super.getPosition();
    }

    /**
     * Gets the size of the brick.
     * 
     * @return the size of the brick
     */
    @Override
    public final Dimension getSize() {
        return super.getSize();
    }

    /**
     * Returns a string representation of the brick.
     * 
     * @return a string representation of the brick
     */
    @Override
    public final String toString() {
        return "(" + getPosition().x + ";" + getPosition().y + ")";
    }
}
