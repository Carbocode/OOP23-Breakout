package it.unibo.model;

import it.unibo.api.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.Path;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * `ScoreboardImpl` is a Java class that implements the `Scoreboard` interface.
 */
public class ScoreboardImpl implements Scoreboard {
    private static final Logger LOGGER = Logger.getLogger(ScoreboardImpl.class.getName());
    private static String scoreboardFile = "scoreboard/Scoreboard.json";
    private static final String POINT_KEY = "points";

    /**
     * Reads the JSON file and returns its contents as a JSONArray.
     * 
     * @return JSONArray containing the scores
     * @throws IOException if an I/O error occurs
     */
    public JSONArray readScoreboardFile() throws IOException {
        try {
            URI uri = getClass().getClassLoader().getResource(scoreboardFile).toURI();

            if ("jar".equals(uri.getScheme())) {
                for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
                    if (provider.getScheme().equalsIgnoreCase("jar")) {
                        try {
                            provider.getFileSystem(uri);
                        } catch (FileSystemNotFoundException e) {
                            // in this case we need to initialize it first:
                            provider.newFileSystem(uri, Collections.emptyMap());
                        }
                    }
                }
            }
            Path source = Paths.get(uri);
            final byte[] jsonBytes = Files.readAllBytes(source);
            final String jsonContent = new String(jsonBytes, StandardCharsets.UTF_8);

            return new JSONArray(jsonContent);
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI for the file: " + scoreboardFile, e);
        }
    }

    /**
     * Writes the given JSONArray to the JSON file.
     * 
     * @param jsonArray the JSONArray to write to the file
     * @throws IOException if an I/O error occurs
     */
    private void writeScoreboardFile(final JSONArray jsonArray) throws IOException {
        try {
            final URL fileUrl = getClass().getClassLoader().getResource(scoreboardFile);
            if (fileUrl == null) {
                throw new IOException("File not found: " + scoreboardFile);
            }
            final Path filePath = Paths.get(fileUrl.toURI());
            try (BufferedWriter fileWriter = Files.newBufferedWriter(filePath)) {
                fileWriter.write(jsonArray.toString(2)); // Indent with 2 spaces for better readability
            }
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI for the file: " + scoreboardFile, e);
        }
    }

    /**
     * @return JList<String> list of top 10 players based on score.
     */
    @Override
    public JList<String> top10() {
        try {
            final JSONArray jsonArray = readScoreboardFile();

            final DefaultListModel<String> topPlayersList = new DefaultListModel<>();

            IntStream.range(0, Math.min(10, jsonArray.length()))
                    // Create a stream of integers from 0 (inclusive) 
                    //to the smaller value of 10 or the length of the jsonArray (exclusive)
                    .mapToObj(i -> {
                        final JSONObject player = jsonArray.getJSONObject(i);
                        final String name = player.getString("name");
                        final int points = player.getInt(POINT_KEY);

                        // Format the extracted data into a string in the format "i° - Name: name, Points: points".
                        return String.format("%d° - Name: %s, Points: %d", i + 1, name, points);
                    })
                    // Add each formatted string to the topPlayersList.
                    .forEach(topPlayersList::addElement);

            return new JList<>(topPlayersList);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading scoreboard file", e);
            return null;
        }
    }

    /**
     * Adds a new entry to the scoreboard.
     * 
     * @param name the name of the player
     * @param points the points scored by the player
     */
    @Override
    public void add(final String name, final int points) {
        try {
            final JSONArray scoreboard = readScoreboardFile();

            List<JSONObject> sortedScoreboard = IntStream.range(0, scoreboard.length())
                    .mapToObj(scoreboard::getJSONObject)
                    .collect(Collectors.toList());

            final JSONObject newScore = new JSONObject();
            newScore.put("name", name);
            newScore.put(POINT_KEY, points);

            sortedScoreboard.add(newScore);

            // Convert the List back into a stream of JSONObjects.
            sortedScoreboard = sortedScoreboard.stream()
                    .sorted((a, b) -> Integer.compare(b.getInt(POINT_KEY), a.getInt(POINT_KEY)))
                    .collect(Collectors.toList());

            final JSONArray updatedScoreboard = new JSONArray(sortedScoreboard);

            writeScoreboardFile(updatedScoreboard);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error updating scoreboard file", e);
        }
    }

    /**
     * Sets the scoreboard file path for testing purposes.
     * 
     * @param filePath the path of the scoreboard file
     */
    public static void setScoreboardFileForTest(final String filePath) {
        scoreboardFile = filePath;
    }
}
