package it.unibo.controller;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.api.GameInfo;
import it.unibo.view.GameView;
import it.unibo.view.Menu;

public class Main {
    private static GameView gamePanel;
    private static Menu menuPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                JFrame game = new JFrame();
                gamePanel = new GameView();
                game.add(gamePanel);
                GameLoop gls = new GameLoop(gamePanel);
                gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
                gamePanel.setBackground(Color.BLACK);
                game.pack();
                game.setVisible(true);

            }
        });
    }
}
