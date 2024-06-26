package it.unibo.view;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.GraphicsDevice;

/**
 * this class returns some measurements.
 * 
 */
public class Measures {
    /**
     * This method get the screen height.
     * 
     * @return ScreenHeight
     */
    public final int getScreenHeight() {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (final GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.height;
    }

    /**
     * This method get the screen width.
     * 
     * @return ScreenWidth
     */
    public final int getScreenWidth() {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (final GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.width;
    }

    /**
     * This method return the game area height.
     * 
     * @return GameAreaHeight
     */
    public final int getGameAreaHeight() {
        return getScreenHeight() / 2;
    }

    /**
     * This method return the game area width.
     * 
     * @return GameAreaWidth
     */
    public final int getGameAreaWidth() {
        return getScreenWidth() / 2;
    }

}
