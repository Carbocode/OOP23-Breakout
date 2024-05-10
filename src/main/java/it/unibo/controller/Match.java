package it.unibo.controller;

import it.unibo.view.*;

import it.unibo.api.GameInfo;
import java.util.*;
import java.awt.*;

public class Match {

    private static GameLoop gameLoop;

    public static void init(GameView gamePanel) {
        gameLoop = new GameLoop(gamePanel);
        gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
        gamePanel.setBackground(Color.BLACK);
    }

    public static GameLoop getGameLoop() {
        return gameLoop;
    }

}
