package it.unibo.controller;

import it.unibo.view.GameView;

import it.unibo.api.GameInfo;
import java.awt.Color;

/**
 * this class initialize a new game.
 */
public final class Match {

    private static GameLoop gameLoop;

    /**
     * this method initialize the gamepanel.
     * 
     * @param gamePanel
     */
    public static void init(final GameView gamePanel) {
        gameLoop = new GameLoop(gamePanel);
        gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
        gamePanel.setBackground(Color.BLACK);
    }

    /**
     * this method returns the game loop.
     * 
     * @return gameloop
     */
    public static GameLoop getGameLoop() {
        return gameLoop;
    }

    private Match() {
    }
}
