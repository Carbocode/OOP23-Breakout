package it.unibo.api;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

public abstract class GameEntityImpl implements GameEntity {

    final public static int IMMORTAL_ENTITY_HEALTH = -1;
    final public static int MIN_HEALTH = 1;
    final public static int MAX_HEALTH = 2;
    final public static Color DEFAULT_COLOR = new Color(70, 70, 70);

    protected Point position;
    protected Dimension size;
    protected int health;
    protected Color color;

    public GameEntityImpl(final Point position, final Dimension size, int health, final Color color) {
        this.position = position;
        this.size = size;
        this.health = health;
        this.color = color;
    }

    /*
     * Game Entity Default Color is rgb(70, 70, 70)
     * 
     * @param position
     * @param size
     * @param health
     */
    public GameEntityImpl(final Point position, final Dimension size, int health) {
        this.position = position;
        this.size = size;
        this.health = health;
        this.color = new Color(DEFAULT_COLOR.getRGB());
    }

    /*
     * Game Entity Default Health is IMMORTAL_ENTITY_HEALTH
     * 
     * @param position
     * @param size
     * @param color
     */
    public GameEntityImpl(final Point position, final Dimension size, final Color color) {
        this.position = position;
        this.size = size;
        this.health = IMMORTAL_ENTITY_HEALTH;
        this.color = color;
    }

    /**
     * Game Entity Default Health is IMMORTAL_ENTITY_HEALTH and Default Color is
     * rgb(70, 70, 70)
     * 
     * @param position
     * @param size
     */
    public GameEntityImpl(final Point position, final Dimension size) {
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
    public void setPosition(final Point position) {
        this.position = position;
    }

    @Override
    public void setSize(final Dimension size) {
        this.size = size;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setColor(final Color color) {
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

    @Override
    public String toString() {
        return "position: " + getPosition()
                + " size: " + getSize()
                + " health: " + getHealth()
                + " color: " + getColor();
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o){
            return true;
        } 
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        GameEntity that = (GameEntityImpl) o;
        return health == that.getHealth() 
        && Objects.equals(position, that.getPosition()) 
        && Objects.equals(size, that.getSize()) 
        && Objects.equals(color, that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, size, health, color);
    }

}