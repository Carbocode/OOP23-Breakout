package it.unibo.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

import it.unibo.api.BrickWall;
import it.unibo.model.Brick;
import it.unibo.model.BrickColors;

/**
 * Brick wall Implementation using random generation.
 */
public class BrickWallImpl implements BrickWall {
    /**
     * How many bricks?
     */
    public static final int SCALAR = 4;

    private Set<Brick> wall;
    private int width;
    private int height;
    private int sideOffset;

    /**
     * Constructor.
     * 
     * @param width  the width of the brick wall
     * @param height the height of the brick wall
     */
    public BrickWallImpl(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public final void generateLayout() {
        this.resetLayout();
        int gcd = getGcd(this.width, this.height);
        int brickWidth = getBrickWidth(gcd);
        int brickHeight = getBrickHeight(brickWidth);
        int numBricksRow = getNumBricksRow(brickWidth);
        int numBricksColumn = getNumBricksColumn(brickHeight);
        this.sideOffset = getSideOffset(brickWidth, numBricksRow);

        IntStream
                .range(0, numBricksColumn)
                .forEach(i -> addBricksToRow(i, brickWidth, brickHeight, numBricksRow));

        this.toString();
    }

    private int getBrickWidth(final int gcd) {
        return gcd * SCALAR;
    }

    private int getBrickHeight(final int brickWidth) {
        return (int) (brickWidth / Brick.ASPECT_RATIO);
    }

    private int getNumBricksRow(final int brickWidth) {
        return (int) Math.floor(this.width / brickWidth);
    }

    private int getNumBricksColumn(final int brickHeight) {
        return (int) Math.floor(this.height / brickHeight);
    }

    private int getSideOffset(final int brickWidth, final int numBricksRow) {
        return (int) Math.floor((this.width - (brickWidth * numBricksRow)) / 2);
    }

    private void addBricksToRow(
            final int rowIndex,
            final int brickWidth,
            final int brickHeight,
            final int numBricksRow) {

        addImmortalBrick(
                new Point(0, rowIndex * brickHeight),
                new Dimension(sideOffset, brickHeight));

        IntStream
                .range(0, numBricksRow)
                .forEach(j -> addRandomBrick(rowIndex, j, brickWidth, brickHeight));

        addImmortalBrick(
                new Point(this.width - this.sideOffset, rowIndex * brickHeight),
                new Dimension(sideOffset, brickHeight));
    }

    public void addImmortalBrick(final Point position, final Dimension size) {
        if (sideOffset > 0) {
            wall.add(BrickFactory.createImmortalBrick(position, size));
        }
    }

    public void addRandomBrick(final int rowIndex, final int colIndex, final int brickWidth, final int brickHeight) {
        wall.add(BrickFactory.createRandomBrick(
                new Point((colIndex * brickWidth) + this.sideOffset, rowIndex * brickHeight),
                new Dimension(brickWidth, brickHeight),
                BrickColors.getColor(rowIndex)));
    }

    private static int getGcd(final int x, final int y) {
        int a = x;
        int b = y;
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    @Override
    public final void resetLayout() {
        this.wall = new LinkedHashSet<>();
    }

    @Override
    public final void shiftLayout() {
        throw new UnsupportedOperationException("Unimplemented method 'shiftLayout'");
    }

    @Override
    public final void removeDeathBricks() {
        throw new UnsupportedOperationException("Unimplemented method 'removeDeathBricks'");
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final Set<Brick> getWall() {
        return wall;
    }

    @Override
    public final String toString() {
        return wall.toString();
    }
}
