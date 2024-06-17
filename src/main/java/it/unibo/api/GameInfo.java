package it.unibo.api;

import java.awt.Dimension;

/**
 * The GameInfo class contains static constants that define various
 * configurations and settings for the game.
 */
public final class GameInfo {
    /**
     * The width of the game window.
     */
    public static final int GAME_WIDTH = 800;

    /**
     * The height of the game window.
     */
    public static final int GAME_HEIGHT = 600;

    /**
     * The refresh rate of the game in frames per second (FPS).
     */
    public static final int REFRESH_RATE = 60;

    /**
     * Flag to enable or disable debug mode.
     */
    public static final boolean DEBUG_MODE = true;

    /**
     * The speed of the ball in the game.
     */
    public static final int BALL_SPEED = 3;

    /**
     * The dimensions of the game bar (paddle).
     */
    public static final Dimension BAR_DIMENSION = new Dimension(200, 15);
    private GameInfo() {
        throw new UnsupportedOperationException();
    }

}
