package it.unibo.view;

import static org.junit.Assert.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the SoundManagerImpl class.
 */
class SoundManagerImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private SoundManagerImpl soundManager;
    private static final String UAFE = "UnsupportedAudioFileException";
    private static final String IOE = "IOException";
    private static final String LU = "LineUnavailableException";

    /**
     * Set up the test environment before each test case.
     */
    @Before
    void setUp() {
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
        soundManager = new SoundManagerImpl();
    }

    /**
     * Restore the original output stream after each test case.
     */
    @After
    void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test the playBackgroundSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayBackgroundSound() {
        soundManager.playBackgroundSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playGameOverSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayGameOverSound() {
        soundManager.playGameOverSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playCollisionSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayCollisionSound() {
        soundManager.playCollisionSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playGameSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayGameSound() {
        soundManager.playGameSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playButtonSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayButtonSound() {
        soundManager.playButtonSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playMenuSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayMenuSound() {
        soundManager.playMenuSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playVictorySound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayVictorySound() {
        soundManager.playVictorySound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playBombSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayBombSound() {
        soundManager.playBombSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playBonusSound method of the SoundManagerImpl class.
     */
    @Test
    void testPlayBonusSound() {
        soundManager.playBonusSound();
        final String logOutput = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }
}
