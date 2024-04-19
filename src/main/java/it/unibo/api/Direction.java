package it.unibo.api;

public class Direction {
    private int x;
    private int y;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getHorizontalVelocity() {
        return x;
    }

    public int GetVerticalVelocity() {
        return y;
    }
}