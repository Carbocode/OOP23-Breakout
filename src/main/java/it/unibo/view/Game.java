package it.unibo.view;

import javax.swing.*;

public class Game extends JFrame {
    private JPanel gamePanel;
    private JButton button;
    private Measures measure = new Measures();

    public Game() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);
    }
}
