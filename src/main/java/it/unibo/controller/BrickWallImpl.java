package it.unibo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.api.BrickWall;

public class BrickWallImpl implements BrickWall {

    private Set<Brick> wall;
    private String seed;
    private int width;
    private int height;

    public BrickWallImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void generateLayout() {
        this.resetLayout();
    }

    @Override
    public void resetLayout() {
        this.wall = new HashSet<>();
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

}
