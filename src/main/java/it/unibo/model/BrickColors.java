package it.unibo.model;

import java.awt.Color;

/**
 * Enum representing different colors for bricks.
 */
public enum BrickColors {
    /**
     * Blue.
     */
    BLUE(new Color(32, 46, 133)),
    /**
     * Yellow.
     */
    YELLOW(new Color(51, 51, 255)),
    /**
     * Magenta.
     */
    MAGENTA(new Color(255, 0, 255)),
    /**
     * Light Blue.
     */
    LIGHT_BLUE(new Color(51, 255, 255)),
    /**
     * Purple.
     */
    PURPLE(new Color(127, 0, 255));

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
        BrickColors[] values = BrickColors.values();
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
