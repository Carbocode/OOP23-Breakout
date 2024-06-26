package it.unibo.model;

import it.unibo.api.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;

public class ScoreboardImpl implements Scoreboard{

    /** 
     * This function opens and reads the JSON file and returns the JSON file converted into JSONArray.
     */
    public JSONArray open() throws IOException {
        try {
            // Get the URL of the JSON file
            URL indFile = getClass().getClassLoader().getResource("scoreboard/Scoreboard.json");

            // Read the content of the JSON file and convert it to a string
            String jsonContent = new String(Files.readAllBytes(Paths.get(indFile.toURI())));

            // Convert the JSON string into a JSONArray
            return new JSONArray(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IOException("Invalid URI", e);
        }
    }

    public JList<String> top10() {
        try {
            JSONArray jsonArray = open();

            // Create output list
            DefaultListModel<String> resultList = new DefaultListModel<>();

            // Add first 10 elements in the JSON file to the output list
            for (int i = 0; i < Math.min(10, jsonArray.length()); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                int points = jsonObject.getInt("points");

                String resultString = "Name: " + name + ", Points: " + points;
                resultList.addElement((i + 1) + "° - " + resultString);
            }
            JList<String> listd = new JList<>(resultList);

            return listd;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(String name, int points) {
        boolean put = true;
        JSONArray inputArray = new JSONArray();

        // Input JSONArray
        try {
            inputArray = open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new JSONArray for the results
        JSONArray outputArray = new JSONArray();

        // Create a new JSONArray of output with all the elements in order
        for (int i = 0; i < inputArray.length(); i++) {
            JSONObject jsonObject = inputArray.getJSONObject(i);

            if (put && points > jsonObject.getInt("points")) {
                JSONObject outputNewObject = new JSONObject();
                outputNewObject.put("name", name);
                outputNewObject.put("points", points);
                outputArray.put(outputNewObject);
                put = false;
            }

            outputArray.put(jsonObject);
        }

        // If the new score hasn't been added, add it to the end
        if (put) {
            JSONObject outputNewObject = new JSONObject();
            outputNewObject.put("name", name);
            outputNewObject.put("points", points);
            outputArray.put(outputNewObject);
        }

        // Save the output JSONArray to a JSON file
        try (FileWriter fileWriter = new FileWriter(getClass().getClassLoader().getResource("scoreboard/Scoreboard.json").getFile())) {
            fileWriter.write(outputArray.toString(2)); // Indent with 2 spaces for better readability
        } catch (IOException e) {
            System.err.println("Error while saving JSON file: " + e.getMessage());
        }
    }
}
