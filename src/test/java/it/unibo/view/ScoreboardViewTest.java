package it.unibo.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import org.junit.Before;
import org.junit.Test;

import it.unibo.api.GameInfo;

/**
 * JUnit test class for the ScoreboardView class.
 */
public class ScoreboardViewTest {

    private ScoreboardView scoreboardView;

    /**
     * Set up the test environment before each test case.
     */
    @Before
    public void setUp() {
        scoreboardView = new ScoreboardView(new Menu());
    }

    /**
     * Test the constructor of the ScoreboardView class.
     */
    @Test
    public void testConstructor() {
        assertNotNull(scoreboardView);
        assertEquals("SCOREBOARD", scoreboardView.getTitle());
        assertEquals(GameInfo.GAME_WIDTH, scoreboardView.getWidth());
        assertEquals(GameInfo.GAME_WIDTH, scoreboardView.getHeight());
        assertEquals(BorderLayout.class, scoreboardView.getLayout().getClass());
    }

    /**
     * Test the components of the ScoreboardView class.
     */
    @Test
    public void testComponents() {
        final Component centerComponent = scoreboardView.getContentPane().getComponent(0);
        assertNotNull(centerComponent);

        final JScrollPane scrollPane = (JScrollPane) centerComponent;
        final Component view = scrollPane.getViewport().getView();
        assertNotNull(view);

        final Component southComponent = scoreboardView.getContentPane().getComponent(1);
        assertNotNull(southComponent);

        final JButton backButton = (JButton) southComponent;
        assertEquals("< RETURN", backButton.getText());
    }
}
