package it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

/**
 * The abstract class GameEntityImpl implements the GameEntity interface.
 * It provides a base implementation for common properties and methods that all
 * game entities share.
 */
public abstract class GameEntityImpl implements GameEntity {

    /**
     * Constant for representing an immortal entity's health.
     */
    public static final int IMMORTAL_ENTITY_HEALTH = -1;

    /**
     * Minimum health value for an entity.
     */
    public static final int MIN_HEALTH = 1;

    /**
     * Maximum health value for an entity.
     */
    public static final int MAX_HEALTH = 2;

    /**
     * Default color for the entity.
     */
    public static final Color DEFAULT_COLOR = new Color(70, 70, 70);

    /**
     * The position of the entity in the game world.
     */
    private Point position;

    /**
     * The size of the entity.
     */
    private Dimension size;

    /**
     * The health of the entity.
     */
    private int health;

    /**
     * The color of the entity.
     */
    private Color color;

    /**
     * Constructs a new GameEntityImpl object with the specified position, size,
     * health, and color.
     *
     * @param position the position of the entity
     * @param size     the size of the entity
     * @param health   the health of the entity
     * @param color    the color of the entity
     */
    public GameEntityImpl(final Point position, final Dimension size, final int health, final Color color) {
        this.position = new Point(position);
        this.size = new Dimension(size);
        this.health = health;
        this.color = color;
    }

    /**
     * Constructs a new GameEntityImpl object with the specified position, size, and
     * health.
     * The entity's color is set to the default color.
     *
     * @param position the position of the entity
     * @param size     the size of the entity
     * @param health   the health of the entity
     */
    public GameEntityImpl(final Point position, final Dimension size, final int health) {
        this(position, size, health, DEFAULT_COLOR);
    }

    /**
     * Constructs a new GameEntityImpl object with the specified position, size, and
     * color.
     * The entity's health is set to immortal.
     *
     * @param position the position of the entity
     * @param size     the size of the entity
     * @param color    the color of the entity
     */
    public GameEntityImpl(final Point position, final Dimension size, final Color color) {
        this(position, size, IMMORTAL_ENTITY_HEALTH, color);
    }

    /**
     * Constructs a new GameEntityImpl object with the specified position and size.
     * The entity's health is set to immortal and color is set to the default color.
     *
     * @param position the position of the entity
     * @param size     the size of the entity
     */
    public GameEntityImpl(final Point position, final Dimension size) {
        this(position, size, IMMORTAL_ENTITY_HEALTH, DEFAULT_COLOR);
    }

    /**
     * Handles the collision event for the game entity.
     * This method must be implemented by subclasses.
     */
    @Override
    public abstract void onCollision();

    /**
     * Checks if the game entity is alive (health > 0).
     *
     * @return true if the entity is alive, false otherwise
     */
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Sets a new position.
     */
    @Override
    public void setPosition(final Point position) {
        this.position = new Point(position);
    }

    /**
     * Changes its size.
     */
    @Override
    public void setSize(final Dimension size) {
        this.size = new Dimension(size);
    }

    /**
     * Sets the health of the game entity.
     *
     * @param health the new health as an integer
     */
    @Override
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Sets the color of the game entity.
     *
     * @param color the new color as a Color
     */
    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    /**
     * Gets the position of the game entity.
     *
     * @return the current position as a Point
     */
    @Override
    public Point getPosition() {
        return new Point(position);
    }

    /**
     * Gets the size of the game entity.
     *
     * @return the current size as a Dimension
     */
    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    /**
     * Gets the health of the game entity.
     *
     * @return the current health as an integer
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Gets the color of the game entity.
     *
     * @return the current color as a Color
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Returns a string representation of the game entity.
     *
     * @return a string representation of the game entity
     */
    @Override
    public String toString() {
        return "position: " + getPosition()
                + " size: " + getSize()
                + " health: " + getHealth()
                + " color: " + getColor();
    }

    /**
     * Compares this game entity to the specified object. The result is true if and
     * only if
     * the argument is not null and is a GameEntity object that has the same
     * position, size, health, and color as this object.
     *
     * @param o the object to compare this GameEntity against
     * @return true if the given object represents a GameEntity equivalent to this
     *         game entity, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final GameEntity that = (GameEntityImpl) o;
        return health == that.getHealth()
                && Objects.equals(position, that.getPosition())
                && Objects.equals(size, that.getSize())
                && Objects.equals(color, that.getColor());
    }

    /**
     * Returns a hash code value for the game entity.
     *
     * @return a hash code value for this game entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(position, size, health, color);
    }
}
