package it.unibo.view;

import java.awt.*;
import javax.swing.*;

public class Game extends JFrame{
    JPanel gamePanel;
    JButton button;

    public int getScreenHeight() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.height;
    }

    public int getScreenWidth() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }
        return bounds.width;
    }

    public int getGameAreaHeight() {
        return getScreenHeight() / 2;
    }

    public int getGameAreaWidth() {
        return getScreenWidth() / 2;
    }
    public Game(){
        setTitle("Breakout");
        setSize(getGameAreaWidth(), getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);
    }
}
