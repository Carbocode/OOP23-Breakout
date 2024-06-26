package it.unibo.model;

import java.awt.Color;

public enum BrickColors {
    BLUE(new Color(32, 46, 133)),
    YELLOW(new Color(51, 51, 255)),
    MAGENTA(new Color(255, 0, 255)),
    LIGHT_BLUE(new Color(51, 255, 255)),
    PURPLE(new Color(127, 0, 255));

    private final Color color;

    private BrickColors(Color color) {
        this.color = color;
    }

    public static Color getColor(int row) {
        BrickColors[] values = BrickColors.values();
        return values[(row) % values.length].color;
    }
}
