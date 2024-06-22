package it.unibo.model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BigBar extends Bar {
    private boolean bigBar = false;
    private int time = 0;
    private final int fullTime = 300;
    private Bar bar;

    public BigBar() {
        this.bar = new Bar(getPosition(), getSize(), IMMORTAL_ENTITY_HEALTH, DEFAULT_COLOR);
    }

    public final void extendedBar() {
        this.bar.setWidth(300);
        return;
    }

}