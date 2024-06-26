package it.unibo.model;

import java.awt.Color;

/**
 * Enum representing different colors for bricks.
 */
public enum BrickColors {

    /**
     * Purple.
     */
    PURPLE(new Color(127, 0, 255)),
    /**
     * Blue.
     */
    BLUE(new Color(2, 55, 136)),
    /**
     * Cyan.
     */
    CYAN(new Color(45, 255, 230)),
    /**
     * Yellow.
     */
    YELLOW(new Color(249, 200, 14)),
    /**
     * Magenta.
     */
    MAGENTA(new Color(247, 6, 207)),
    /**
     * Red.
     */
    RED(new Color(253, 55, 119)),
    /**
     * Dark Purple.
     */
    DARK_PURPLE(new Color(84, 13, 110));

    /**
     * Color.
     */
    private final Color color;

    /**
     * Constructor for BrickColors.
     * 
     * @param color the color of the brick
     */
    BrickColors(final Color color) {
        this.color = color;
    }

    /**
     * Gets the color for a given row.
     * 
     * @param row the row index
     * @return the color corresponding to the row index
     */
    public static Color getColor(final int row) {
        final BrickColors[] values = BrickColors.values();
        return values[row % values.length].color;
    }

    /**
     * Gets the color of the brick.
     * 
     * @return the color of the brick
     */
    public Color getColor() {
        return this.color;
    }
}
