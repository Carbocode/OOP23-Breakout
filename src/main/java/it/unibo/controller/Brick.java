package it.unibo.controller;

import java.awt.Graphics2D;

public class Brick extends GameEntityImpl {

    public Brick(Graphics2D graphics, int health) {
        super(graphics, health);
    }

    public Brick(Graphics2D graphics) {
        super(graphics);
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
        // return Objects.hash(super.getPosition);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hashCode'");
    }

}
