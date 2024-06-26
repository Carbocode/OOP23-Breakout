package it.unibo.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import it.unibo.model.Brick;
import it.unibo.model.BrickColors;
import it.unibo.model.BrickTypes;

/**
 * Factory pattern that creates all kinds of brick the game has.
 */
public class BrickFactory {

    private static long seed = System.currentTimeMillis();
    private static final Random rand = new Random(BrickFactory.seed);

    /**
     * Creates a brick with defined health.
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @return a random brick
     */
    public static Brick createRandomBrick(Point position, Dimension size) {
        int health = BrickTypes.getRandomHealth(BrickFactory.rand);

        if (health < 0) {
            return createImmortalBrick(position, size);
        } else {
            Color color = BrickColors.getColor(position.y);
            return new Brick(position, size, health, color);
        }
    }

    /**
     * Sets the seed for random number generation.
     * 
     * @param seed the seed value
     */
    public static void setSeed(long seed) {
        BrickFactory.seed = seed;
        BrickFactory.rand.setSeed(seed);
    }

    /**
     * Gets the current seed value.
     * 
     * @return the current seed value
     */
    public static long getSeed() {
        return BrickFactory.seed;
    }

    /**
     * Creates an immortal brick.
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * @return an immortal brick
     */
    public static Brick createImmortalBrick(Point position, Dimension size) {
        return new Brick(
                position,
                size,
                BrickTypes.IMMORTAL.getHealth(),
                new Color(128, 128, 128));
    }
}
