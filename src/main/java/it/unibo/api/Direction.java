package it.unibo.api;

/**
 * The Direction class represents a direction with horizontal and vertical velocities.
 */
public class Direction {
    private int x;
    private int y;

    /**
     * Constructs a new Direction object with specified horizontal and vertical velocities.
     *
     * @param x the horizontal velocity
     * @param y the vertical velocity
     */
    public Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the horizontal velocity.
     *
     * @return the horizontal velocity
     */
    public int getHorizontalVelocity() {
        return x;
    }

    /**
     * Gets the vertical velocity.
     *
     * @return the vertical velocity
     */
    public int getVerticalVelocity() { // Corrected the method name to follow Java naming conventions
        return y;
    }
}
