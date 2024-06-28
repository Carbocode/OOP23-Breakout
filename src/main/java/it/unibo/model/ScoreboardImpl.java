package it.unibo.model;

import it.unibo.api.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.Path;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * `ScoreboardImpl` is a Java class that implements the `Scoreboard` interface.
 */
public class ScoreboardImpl implements Scoreboard {
    private static final Logger LOGGER = Logger.getLogger(ScoreboardImpl.class.getName());
    private static String scoreboardFile = "scoreboard/Scoreboard.json";
    private static final String POINT_KEY = "points";

    /**
     * This function opens and reads the JSON file and returns the JSON file
     * converted into JSONArray.
     * 
     * @return jsonArray
     */
    public JSONArray open() throws IOException {
        try {
            // Get the URL of the JSON file
            final URL indFile = getClass().getClassLoader().getResource(scoreboardFile);
            if (indFile == null) {
                throw new IOException("File not found: " + scoreboardFile);
            }

            // Read the content of the JSON file and convert it to a string
            final String jsonContent = new String(Files.readAllBytes(Paths.get(indFile.toURI())));

            // Convert the JSON string into a JSONArray
            return new JSONArray(jsonContent);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI", e);
        }
        return null;
    }

    /**
     * @return JList<String> list of top 10 playes based on score.
     */
    @Override
    public final JList<String> top10() {
        try {
            final JSONArray jsonArray = open();

            // Create output list
            final DefaultListModel<String> resultList = new DefaultListModel<>();

            // Add first 10 elements in the JSON file to the output list
            for (int i = 0; i < Math.min(10, jsonArray.length()); i++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);

                final String name = jsonObject.getString("name");
                final int pValue = jsonObject.getInt(POINT_KEY);

                final String resultString = "Name: " + name + ", Points: " + pValue;
                resultList.addElement((i + 1) + "° - " + resultString);
            }

            return new JList<>(resultList);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
        }
        return null;
    }

    /**
     * The `add` method in the `ScoreboardImpl` class is responsible for adding a
     * new entry to the
     * scoreboard with the given name and points.
     * 
     * @param name
     * @param pval
     */
    @Override
    public void add(final String name, final int pval) {
        boolean put = true;
        JSONArray inputArray = new JSONArray();

        // Input JSONArray
        try {
            inputArray = open();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
        }

        // Create a new JSONArray for the results
        final JSONArray outputArray = new JSONArray();

        // Create a new JSONArray of output with all the elements in order
        for (int i = 0; i < inputArray.length(); i++) {
            final JSONObject jsonObject = inputArray.getJSONObject(i);

            if (put && pval > jsonObject.getInt(POINT_KEY)) {
                final JSONObject outputNewObject = new JSONObject();
                outputNewObject.put("name", name);
                outputNewObject.put(POINT_KEY, pval);
                outputArray.put(outputNewObject);
                put = false;
            }

            outputArray.put(jsonObject);
        }

        // If the new score hasn't been added, add it to the end
        if (put) {
            final JSONObject outputNewObject = new JSONObject();
            outputNewObject.put("name", name);
            outputNewObject.put(POINT_KEY, pval);
            outputArray.put(outputNewObject);
        }

        // Save the output JSONArray to a JSON file
        try {
            final URL indFile = getClass().getClassLoader().getResource(scoreboardFile);
            if (indFile == null) {
                throw new IOException("File not found: " + scoreboardFile);
            }
            final Path filePath = Paths.get(indFile.toURI());
            try (BufferedWriter fileWriter = Files.newBufferedWriter(filePath)) {
                fileWriter.write(outputArray.toString(2)); // Indent with 2 spaces for better readability
            }
        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Exception occurred", e);
        }
    }
    /**
     * 
     * @param filePath
     */
    public void setScoreboardFileForTest(final String filePath) {
        scoreboardFile = filePath;
    }
}
