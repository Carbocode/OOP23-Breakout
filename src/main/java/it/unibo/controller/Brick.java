package it.unibo.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Objects;

import javax.swing.text.Position;

import it.unibo.api.GameEntityImpl;

public class Brick extends GameEntityImpl {

    public Brick(Position position, Dimension dimension, Color color, int health) {
        super(position, dimension, color, health);
    }

    public Brick(Position position, Dimension dimension, Color color) {
        super(position, dimension, color);
    }

    @Override
    public void onCollision() {
        int health = super.getHealth();
        if (health > 0)
            super.setHealth(health--);
    }

    @Override
    public Graphics2D getGraphics() {
        return super.getGraphics();
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
        /*
         * if (this == o)
         * return true;
         * if (o == null || getClass() != o.getClass())
         * return false;
         * Brick mattone = (Brick) o;
         * return this.getGraphics().get == mattone.dimensione && Objects.equals(colore,
         * mattone.colore);
         */
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition());
    }

    @Override
    public Point getPosition() {
        super.getPosition();
    }

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

}
