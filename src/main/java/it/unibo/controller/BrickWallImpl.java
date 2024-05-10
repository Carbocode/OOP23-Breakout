package it.unibo.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

import it.unibo.api.BrickWall;
import it.unibo.model.Brick;

public class BrickWallImpl implements BrickWall {

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

        int numBrickWidth = this.getNumBricksWidth();
        int numBrickHeight = this.getNumBricksHeight();

        int brickHeight = this.getBrickHeight();
        int brickWidth = this.getBrickWidth();

        for (int i = 0; i < numBrickHeight; i++) {
            for (int j = 0; j < numBrickWidth; j++) {
                wall.add(
                        BrickFactory.createRandomBrick(
                                new Point(j * brickWidth, i * brickHeight),
                                new Dimension(brickWidth, brickHeight)));
            }
        }

    }

    private int getBrickHeight() {
        return (int) Math.floor(Math.sqrt(this.height * this.width * Brick.ASPECT_RATIO));
    }

    private int getBrickWidth() {
        return (int) (this.getBrickHeight() * Brick.ASPECT_RATIO);
    }

    private int getNumBricksWidth() {
        return this.width / getBrickWidth();
    }

    private int getNumBricksHeight() {
        return this.height / getBrickHeight();
    }

    @Override
    public void resetLayout() {
        this.wall = new LinkedHashSet<Brick>();
    }

    @Override
    public void shiftLayout() {
        throw new UnsupportedOperationException("Unimplemented method 'shiftLayout'");
    }

    @Override
    public void removeDeathBricks() {
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
