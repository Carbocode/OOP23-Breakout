package it.unibo.controller;

import it.unibo.view.GameView;

import it.unibo.api.GameInfo;
import java.awt.Color;

/**
 * this class initialize a new game.
 */
public final class Match {

    private static GameLoop gameLoop;

    private Match() {
        try {
            throw new Exception();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * this method set the size and the background of the game.
     * 
     * @param gamePanel
     */
    public static void init(final GameView gamePanel) {
        gameLoop = new GameLoop(gamePanel);
        gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
        gamePanel.setBackground(Color.BLACK);
    }

    /**
     * this method return the gameloop.
     * 
     * @return gameloop
     */
    public static GameLoop getGameLoop() {
        return gameLoop;
    }

}
