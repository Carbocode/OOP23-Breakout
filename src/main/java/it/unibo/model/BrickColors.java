package it.unibo.model;

import java.awt.Color;

public enum BrickColors {
    RED(new Color(255, 48, 48)),
    GREEN(new Color(13, 193, 0)),
    BLUE(new Color(32, 46, 133)),
    YELLOW(new Color(250, 243, 19));

    private final Color color;

    private BrickColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
