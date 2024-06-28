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
public class ScoreboardImplTest {

    private static final String TEST_SCOREBOARD_FILE = "scoreboard/ScoreboardTest.json";
    private ScoreboardImpl scoreboard;

    private static final int TEST_VALUE11 = 11;
    private static final int TEST_VALUE12 = 12;
    private static final int TEST_VALUE15 = 15;

    /**
     * before each test set a temporary file path only used for testing purpouse 
     * and create json file content.
     * @throws IOException
     * @throws URISyntaxException 
     */
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        // Set the test file path for the scoreboard
        ScoreboardImpl.setScoreboardFileForTest(TEST_SCOREBOARD_FILE);

        // Create a sample JSON array to be used as the initial scoreboard
        JSONArray initialData = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "Player" + i);
            jsonObject.put("points", 10 - i);
            initialData.put(jsonObject);
        }

        // Write the sample data to the test file
        Path path = Paths.get(getClass().getClassLoader().getResource(TEST_SCOREBOARD_FILE).toURI());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(initialData.toString(2));
        }

        // Initialize the scoreboard implementation
        scoreboard = new ScoreboardImpl();
    }

    /**
     * test json scoreboard custom opening.
     * @throws IOException
     */
    @Test
    public void testOpen() throws IOException {
        JSONArray jsonArray = scoreboard.open();
        assertNotNull(jsonArray);
        assertEquals(10, jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            assertEquals("Player" + i, jsonObject.getString("name"));
            assertEquals(10 - i, jsonObject.getInt("points"));
        }
    }

    /**
     * test top 10 scoreboard method.
     */
    @Test
    public void testTop10() {
        JList<String> top10List = scoreboard.top10();
        assertNotNull(top10List);
        assertEquals(10, top10List.getModel().getSize());
        for (int i = 0; i < top10List.getModel().getSize(); i++) {
            String entry = top10List.getModel().getElementAt(i);
            assertTrue(entry.contains("Player" + i));
            assertTrue(entry.contains("Points: " + (10 - i)));
        }
    }

    /**
     * test add method.
     * @throws IOException
     */
    @Test
    public void testAdd() throws IOException {
        // Add a new entry with a high score
        scoreboard.add("NewPlayer", TEST_VALUE15);

        // Verify the new entry is added in the correct position
        JSONArray jsonArray = scoreboard.open();
        assertNotNull(jsonArray);
        assertEquals(TEST_VALUE11, jsonArray.length());
        JSONObject newEntry = jsonArray.getJSONObject(0);
        assertEquals("NewPlayer", newEntry.getString("name"));
        assertEquals(TEST_VALUE15, newEntry.getInt("points"));

        // Add a new entry with a low score
        scoreboard.add("LowPlayer", 0);

        // Verify the new entry is added in the correct position
        jsonArray = scoreboard.open();
        assertNotNull(jsonArray);
        assertEquals(TEST_VALUE12, jsonArray.length());
        JSONObject lowEntry = jsonArray.getJSONObject(TEST_VALUE12 - 1);
        assertEquals("LowPlayer", lowEntry.getString("name"));
        assertEquals(0, lowEntry.getInt("points"));
    }
}
