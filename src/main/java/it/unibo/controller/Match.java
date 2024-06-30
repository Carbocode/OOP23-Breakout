package it.unibo.controller;

import it.unibo.view.GameView;

import it.unibo.api.GameInfo;
import it.unibo.api.GameLoopAccessor;

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
    public static void addGameView(final GameView gamePanel) {
        if (gameLoop != null) {
            gameLoop.addView(gamePanel);
            gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
            gamePanel.setBackground(Color.BLACK);
        } else {
            throw new IllegalAccessError("Please Init before adding GameView!");
        }
    }
    /**
     * 
     */
    public static void init() {
        if (gameLoop == null) {
            gameLoop = new GameLoop();
        }

    }
    /**
     * this method returns the game loop.
     * 
     * @return gameloop
     */
    public static GameLoopAccessor getGameLoop() {
        if (gameLoop != null) {
            return gameLoop.getAccessor();
        } else {
            throw new UnsupportedOperationException("Init before accessing");
        }
    }

    private Match() {
    }
}
