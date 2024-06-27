package it.unibo.view;

import static org.junit.Assert.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the SoundManagerImpl class.
 */
public class SoundManagerImplTest {

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
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        soundManager = new SoundManagerImpl();
    }

    /**
     * Restore the original output stream after each test case.
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test the playBackgroundSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayBackgroundSound() {
        soundManager.playBackgroundSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playGameOverSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayGameOverSound() {
        soundManager.playGameOverSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playCollisionSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayCollisionSound() {
        soundManager.playCollisionSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playGameSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayGameSound() {
        soundManager.playGameSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playButtonSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayButtonSound() {
        soundManager.playButtonSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playMenuSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayMenuSound() {
        soundManager.playMenuSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playVictorySound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayVictorySound() {
        soundManager.playVictorySound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playBombSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayBombSound() {
        soundManager.playBombSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }

    /**
     * Test the playBonusSound method of the SoundManagerImpl class.
     */
    @Test
    public void testPlayBonusSound() {
        soundManager.playBonusSound();
        final String logOutput = outContent.toString();
        assertFalse(logOutput.contains(UAFE) || logOutput.contains(IOE)
                || logOutput.contains(LU));
    }
}
