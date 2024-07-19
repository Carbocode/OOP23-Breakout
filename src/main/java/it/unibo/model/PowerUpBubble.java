package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import it.unibo.api.GameEntityImpl;

public class PowerUpBubble extends GameEntityImpl {
    
    private int speed;
    private PowerUp type;
    private static final Dimension PUB_DIMENSION = new Dimension(5, 5);

    public PowerUpBubble(Point position) {
        super(position, PUB_DIMENSION, IMMORTAL_ENTITY_HEALTH);
        this.speed = 2; // Velocità di discesa
    }

    public void update() {
        setPosition(new Point(getPosition().x, getPosition().y + speed));
    }

    public PowerUp getType() {
        return type;
    }

    @Override
    public void onCollision() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }

}