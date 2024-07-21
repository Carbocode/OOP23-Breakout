package it.unibo.model;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.api.GameEntityImpl;

/**
 * PowerUpBubble class.
 */
public class PowerUpBubble extends GameEntityImpl {

    private final int speed;
    private static final Dimension PUB_DIMENSION = new Dimension(7, 7);

    /**
     * PowerUpBubble constructor.
     * 
     * @param position
     */
    public PowerUpBubble(final Point position) {
        super(position, PUB_DIMENSION, IMMORTAL_ENTITY_HEALTH);
        this.speed = 2; // Velocità di discesa
    }

    /**
     * this method returns the speed of the power up bubble.
     */
    public void update() {
        setPosition(new Point(getPosition().x, getPosition().y + speed));
    }

    /**
     * 
     */
    @Override
    public void onCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }

}
