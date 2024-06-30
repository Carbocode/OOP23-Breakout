package it.unibo.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import it.unibo.api.GameInfo;
import it.unibo.controller.GameLoop;
import it.unibo.controller.Match;

/**
 * Test class for GameView.
 */
class GameViewTest {
    private final GameView gameView;
    private final Logger log;

    /**
     * GameViewTest constructor.
     */
    GameViewTest() {
        log = Logger.getLogger(GameView.class.getName());
        Match.init();
        gameView = new GameView();
        final GameLoop gameLoop = new GameLoop();
        gameLoop.addView(gameView);
    }

    /**
     * Test the GameView constructor.
     * Ensures that the GameView object is created correctly with the expected
     * properties.
     */
    @Test
    void testGameViewConstructor() {
        assertNotNull(gameView, "GameView instance should not be null");
        assertEquals(new Dimension(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT), gameView.getPreferredSize(),
                "Preferred size should match game dimensions");
        assertTrue(gameView.isFocusable(), "GameView should be focusable");
    }

    /**
     * Test the backgroundPanel method.
     * Verifies that the background image is loaded correctly.
     */
    @Test
    void testBackgroundPanel() {
        try {
            final URL url = getClass().getClassLoader().getResource("images/bg.jpg");
            assertNotNull(url, "Background image URL should not be null");
            assertNotNull(ImageIO.read(url), "Background image should be loaded successfully");
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    /**
     * Test keyPressed and keyReleased methods.
     * Simulates key press and release events to ensure they are handled correctly.
     */
    @Test
    void testKeyEvents() {
        KeyEvent keyEvent = new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
                KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        gameView.getKeyListeners()[0].keyPressed(keyEvent);

        keyEvent = new KeyEvent(gameView, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,
                KeyEvent.CHAR_UNDEFINED);
        gameView.getKeyListeners()[0].keyReleased(keyEvent);
    }
}
