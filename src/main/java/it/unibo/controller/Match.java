package it.unibo.controller;

import it.unibo.view.GameView;

import it.unibo.api.GameInfo;
import java.awt.Color;
/**
 * 
 */
public class Match {

    private static GameLoop gameLoop;

    public static void init(final GameView gamePanel) {
        gameLoop = new GameLoop(gamePanel);
        gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
        gamePanel.setBackground(Color.BLACK);
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

}
