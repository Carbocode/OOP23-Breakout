package it.unibo.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import it.unibo.model.Brick;
import it.unibo.model.BrickTypes;

/**
 * Factory pattern that creates all kinds of brick the game has.
 */
public final class BrickFactory {

    private static long seed = System.currentTimeMillis();
    private static final Random RAND = new Random(BrickFactory.seed);

    private BrickFactory() {
    }

    /**
     * Creates a brick with defined health.
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @return a random brick
     */
    public static Brick createRandomBrick(final Point position, final Dimension size, final Color color) {
        int health = BrickTypes.getHealth(BrickFactory.RAND);

        if (health < 0) {
            return createImmortalBrick(position, size);
        } else {
            return new Brick(position, size, health, color);
        }
    }

    /**
     * Creates an immortal brick.
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @return an immortal brick
     */
    public static Brick createImmortalBrick(final Point position, final Dimension size) {
        return new Brick(
                position,
                size,
                BrickTypes.IMMORTAL.getHealth(),
                Color.GRAY);
    }

    /**
     * Sets the seed for random number generation.
     * 
     * @param seed the seed value
     */
    public static void setSeed(final long seed) {
        BrickFactory.seed = seed;
        BrickFactory.RAND.setSeed(seed);
    }

    /**
     * Gets the current seed value.
     * 
     * @return the current seed value
     */
    public static long getSeed() {
        return BrickFactory.seed;
    }

}
