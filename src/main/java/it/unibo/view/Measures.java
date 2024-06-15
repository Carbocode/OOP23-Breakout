package it.unibo.view;

import java.awt.*;

/**
 * this class returns some measurements.
 * @author Sohail Mama
 */
public class Measures {
    public final int getScreenHeight() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.height;
    }

    public final int getScreenWidth() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.width;
    }

    public final int getGameAreaHeight() {
        return getScreenHeight() / 2;
    }

    public final int getGameAreaWidth() {
        return getScreenWidth() / 2;
    }

}
