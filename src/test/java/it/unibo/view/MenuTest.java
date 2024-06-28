package it.unibo.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

/**
 * JUnit test class for the Menu class.
 */
public class MenuTest {

    private Menu menu;
    private static final float FONTTESTSIZE = 55.0f;

    /**
     * MenuTest constructor.
     */
    public MenuTest() {
        menu = new Menu();
    }

    /**
     * Test the constructor of the Menu class.
     */
    @Test
    public void testConstructor() {
        assertNotNull(menu);
        assertEquals("Breakout", menu.getTitle());
        assertEquals(new Dimension(menu.getMeasure().getGameAreaWidth(), menu.getMeasure().getGameAreaHeight()),
                menu.getSize());
    }

    /**
     * Test the components of the Menu class.
     */
    @Test
    public void testComponents() {
        final JPanel mainPanel = (JPanel) menu.getContentPane().getComponent(0);
        assertNotNull(mainPanel);

        final JLabel titleLabel = (JLabel) mainPanel.getComponent(0);
        assertNotNull(titleLabel);
        assertEquals("BREAKOUT", titleLabel.getText());
        assertTrue(titleLabel.getFont().getSize() >= FONTTESTSIZE);

        final JPanel buttonPanel = (JPanel) mainPanel.getComponent(1);
        assertNotNull(buttonPanel);

        final JButton playButton = (JButton) buttonPanel.getComponent(0);
        assertNotNull(playButton);
        assertEquals("PLAY", playButton.getText());

        final JButton scoreboardButton = (JButton) buttonPanel.getComponent(1);
        assertNotNull(scoreboardButton);
        assertEquals("SCOREBOARD", scoreboardButton.getText());

        final JButton exitButton = (JButton) buttonPanel.getComponent(2);
        assertNotNull(exitButton);
        assertEquals("EXIT", exitButton.getText());
    }
}
