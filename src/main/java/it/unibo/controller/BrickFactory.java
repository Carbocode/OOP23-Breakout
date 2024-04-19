package it.unibo.controller;

import java.awt.*;
import java.util.Random;

import it.unibo.model.Brick;
import it.unibo.model.BrickColors;
import it.unibo.model.BrickTypes;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public class BrickFactory {

    static Random rand = new Random();

    /**
     * Choses a Color for the Brick
     * 
     * @return a Color for the Brick
     */
    static Color getRandomColor() {
        BrickColors[] colors = BrickColors.values();
        return colors[rand.nextInt(colors.length)].getColor();
    }

    /**
     * Choses a Health for the Brick
     * 
     * @return a Health for the Brick
     */
    static int getRandomHealth() {
        BrickTypes[] colors = BrickTypes.values();
        return colors[rand.nextInt(colors.length)].getHealth();
    }

    /**
     * Creates a brick with defined health
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * 
     * @return Brick
     */
    static Brick createRandomBrick(Point position, Dimension size) {
        return new Brick(position, size, getRandomHealth(), getRandomColor());
    }

}
