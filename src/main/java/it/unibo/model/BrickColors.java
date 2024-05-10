package it.unibo.model;

import java.awt.Color;

public enum BrickColors {
    RED(new Color(255, 51, 51)),
    GREEN(new Color(0, 153, 0)),
    BLUE(new Color(32, 46, 133)),
    YELLOW(new Color(51, 51, 255)),
    MAGENTA(new Color(255, 0, 255)),
    LIGHT_BLUE(new Color(51, 255, 255)),
    PURPLE(new Color(127, 0, 255)),
    ORANGE(new Color(255, 128, 0));

    private final Color color;

    private BrickColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
