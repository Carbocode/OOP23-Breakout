package it.unibo.controller;

import java.awt.Graphics2D;

import it.unibo.api.BrickFactory;

public class BrickFactoryImpl implements BrickFactory {

    @Override
    public Brick createStandardBrick(Graphics2D graphics) {
        return new Brick(graphics, 1);
    }

    @Override
    public Brick createIndestructibleBrick(Graphics2D graphics) {
        return new Brick(graphics);
    }

    @Override
    public Brick createMultiHitBrick(Graphics2D graphics, int health) {
        return new Brick(graphics, health);
    }

}
