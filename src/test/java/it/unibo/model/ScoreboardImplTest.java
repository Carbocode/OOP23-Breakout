package it.unibo.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * test for scoreboard class.
 */
class ScoreboardImplTest {

    private static final String TEST_SCOREBOARD_FILE = "scoreboard/ScoreboardTest.json";
    private ScoreboardImpl scoreboard;

    private static final int TEST_VALUE11 = 11;
    private static final int TEST_VALUE12 = 12;
    private static final int TEST_VALUE15 = 15;
    private static final String NAME = "name";
    private static final String POINTS = "points";

    /**
     * before each test set a temporary file path only used for testing purpouse
     * and create json file content.
     * 
     * @throws IOException
     * @throws URISyntaxException
     */
    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        // Set the test file path for the scoreboard
        ScoreboardImpl.setScoreboardFileForTest(TEST_SCOREBOARD_FILE);

        // Create a sample JSON array to be used as the initial scoreboard
        final JSONArray initialData = new JSONArray();
        for (int i = 0; i < 10; i++) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put(NAME, "Player" + i);
            jsonObject.put(POINTS, 10 - i);
            initialData.put(jsonObject);
        }

        // Write the sample data to the test file
        final Path path = Paths.get(getClass().getClassLoader().getResource(TEST_SCOREBOARD_FILE).toURI());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(initialData.toString(2));
        }
    }

    /**
     * test json scoreboard custom opening.
     * 
     * @throws IOException
     */
    @Test
    void testOpen() throws IOException {

        // Initialize the scoreboard implementation
        scoreboard = new ScoreboardImpl();

        final JSONArray jsonArray = new JSONArray(scoreboard.readScoreboardFile());
        assertNotNull(jsonArray);
        assertEquals(10, jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            assertEquals("Player" + i, jsonObject.getString(NAME));
            assertEquals(10 - i, jsonObject.getInt(POINTS));
        }
    }

    /**
     * test top 10 scoreboard method.
     */
    @Test
    void testTop10() {

        // Initialize the scoreboard implementation
        scoreboard = new ScoreboardImpl();

        final JList<String> top10List = scoreboard.top10();

        assertNotNull(top10List);
        assertEquals(10, top10List.getModel().getSize());
        for (int i = 0; i < top10List.getModel().getSize(); i++) {
            final String entry = top10List.getModel().getElementAt(i);
            assertTrue(entry.contains("Player" + i));
            assertTrue(entry.contains("Points: " + (10 - i)));
        }
    }

    /**
     * test add method.
     * 
     * @throws IOException
     */
    @Test
    void testAdd() throws IOException {

        // Initialize the scoreboard implementation
        scoreboard = new ScoreboardImpl();
        // Add a new entry with a high score
        scoreboard.add("NewPlayer", TEST_VALUE15);

        // Verify the new entry is added in the correct position
        JSONArray jsonArray = new JSONArray(scoreboard.readScoreboardFile());
        assertNotNull(jsonArray);
        assertEquals(TEST_VALUE11, jsonArray.length());
        final JSONObject newEntry = jsonArray.getJSONObject(0);
        assertEquals("NewPlayer", newEntry.getString(NAME));
        assertEquals(TEST_VALUE15, newEntry.getInt(POINTS));

        // Add a new entry with a low score
        scoreboard.add("LowPlayer", 0);

        // Verify the new entry is added in the correct position
        jsonArray = scoreboard.readScoreboardFile();
        assertNotNull(jsonArray);
        assertEquals(TEST_VALUE12, jsonArray.length());
        final JSONObject lowEntry = jsonArray.getJSONObject(TEST_VALUE12 - 1);
        assertEquals("LowPlayer", lowEntry.getString(NAME));
        assertEquals(0, lowEntry.getInt(POINTS));
    }
}
