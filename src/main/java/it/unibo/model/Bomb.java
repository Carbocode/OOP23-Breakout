package it.unibo.model;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.api.GameEntityImpl;

/**
 * Bomb power up class.
 */
public class Bomb extends GameEntityImpl{

    /**
     * bomb general constructor.
     * @param position position of the bomb (the same of the ball)
     * @param size size of the explosion
     */
    public Bomb(final Point position, final Dimension size) {
        super(position, size);
    }
    @Override
    public void onCollision() {
        // nothing
    }
}
