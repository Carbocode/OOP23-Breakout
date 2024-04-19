package it.unibo.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.api.BrickWall;
import it.unibo.model.Brick;

public class BrickWallImpl implements BrickWall {

    final public static Dimension DEFAULT_BRICK_DIM_PERC = new Dimension(10, 5);

    private Set<Brick> wall;
    private int width;
    private int height;

    public BrickWallImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void generateLayout() {
        this.resetLayout();

        int brickWidth = (width * DEFAULT_BRICK_DIM_PERC.width) / 100;
        int brickHeight = (width * DEFAULT_BRICK_DIM_PERC.height) / 100;

        int brickPerColumn = 100 / DEFAULT_BRICK_DIM_PERC.width;
        int brickPerRow = 100 / DEFAULT_BRICK_DIM_PERC.height;
        for (int i = 0; i < brickPerColumn; i++) {
            for (int j = 0; j < brickPerRow; j++) {
                wall.add(
                        BrickFactory.createRandomBrick(
                                new Point(j * brickWidth, i * brickHeight),
                                new Dimension(getBrickWidth(), getBrickHeight())));
            }
        }

    }

    private int getBrickWidth() {
        return width / 100 * DEFAULT_BRICK_DIM_PERC.width;
    }

    private int getBrickHeight() {
        return height / 100 * DEFAULT_BRICK_DIM_PERC.height;
    }

    @Override
    public void resetLayout() {
        this.wall = new LinkedHashSet<Brick>();
    }

    @Override
    public void shiftLayout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shiftLayout'");
    }

    @Override
    public void removeDeathBricks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeDeathBricks'");
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Set<Brick> getWall() {
        return wall;
    }

    @Override
    public String toString() {
        return wall.toString();
    }
}
