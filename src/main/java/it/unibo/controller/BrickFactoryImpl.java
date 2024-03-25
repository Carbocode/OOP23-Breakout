package it.unibo.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import it.unibo.api.BrickFactory;

public class BrickFactoryImpl implements BrickFactory {

    @Override
    public Brick createStandardBrick(Point position, Dimension size, Color color) {
        return new Brick(position, size, 1, color);
    }

    @Override
    public Brick createIndestructibleBrick(Point position, Dimension size) {
        return new Brick(position, size);
    }

    @Override
    public Brick createMultiHitBrick(Point position, Dimension size, Color color, int health) {
        return new Brick(position, size, health, color);
    }

}
