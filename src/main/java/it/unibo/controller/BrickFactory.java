package it.unibo.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import it.unibo.model.Brick;
import it.unibo.model.BrickColors;
import it.unibo.model.BrickTypes;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public class BrickFactory {

    static private long seed = System.currentTimeMillis();
    static private final Random rand = new Random(BrickFactory.seed);

    /**
     * Creates a brick with defined health
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * 
     * @return Brick
     */
    static public Brick createRandomBrick(Point position, Dimension size) {
        int health = BrickTypes.getRandomHealth(BrickFactory.rand);

        if (health < 0)
            return createImmortalBrick(position, size);
        else {
            Color color = BrickColors.getColor(position.y);

            return new Brick(position, size, health, color);
        }

    }

    static public void setSeed(long seed) {
        BrickFactory.seed = seed;
        BrickFactory.rand.setSeed(seed);
    }

    static public long getSeed() {
        return BrickFactory.seed;
    }

    static public Brick createImmortalBrick(Point position, Dimension size) {
        {
            return new Brick(
                    position,
                    size,
                    BrickTypes.IMMORTAL.getHealth(),
                    new Color(128, 128, 128));
        }
    }
}
