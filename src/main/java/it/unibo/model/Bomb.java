package it.unibo.model;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.api.GameEntityImpl;

public class Bomb extends GameEntityImpl{

    public Bomb(Point position, Dimension size) {
        super(position, size);
    }

    @Override
    public void onCollision() {
        // nothing
    }
    
    
}
