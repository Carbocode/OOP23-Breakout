package it.unibo.model;

import it.unibo.api.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;

public class ScoreboardImpl implements Scoreboard{

    /**
     * this function open and read the JSON file and return JSON file converted into JSONArray
    */
    private JSONArray open() throws IOException{
        try {
            // read JSON file content
            String jsonContent = new String(Files.readAllBytes(Paths.get("appdata/Scoreboard.json")));

            // convert JSON string into JSON object array
            return new JSONArray(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;    
    }

    public List<String> top10(){
        try {

            JSONArray jsonArray = open();

            // create output list
            List<String> resultList = new ArrayList<>();

            // add first 10 elements in the json file to the output list
            for (int i = 0; i < Math.min(10, jsonArray.length()); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int points = jsonObject.getInt("points");
                String resultString = "Name: " + name + ", Points: " + points;
                resultList.add(resultString);
            }

            // print results (only for debug)
            //for (String result : resultList) {
            //    System.out.println(result);
            //}

            return resultList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void add(String name,int points){

        //if put is true the new score needs to be added, if it's false it means it was already added
        Boolean put = true;

        JSONArray inputArray = new JSONArray();
        
        //input JSONArray
        try{
            inputArray = open();
        }catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new JSONArray for the results
        JSONArray outputArray = new JSONArray();

        // Create a new JSONArray of output with all the elements in order
        for(int i = 0; i<inputArray.length();i++){
            JSONObject jsonObject = inputArray.getJSONObject(i);
            
            if(put && points>jsonObject.getInt("points")){
                JSONObject outputNewObject = new JSONObject();
                outputNewObject.put("name", name);
                outputNewObject.put("points", points);
                outputArray.put(outputNewObject);
                put = false;
            }

            JSONObject outputObject = new JSONObject();
            outputObject.put("name", jsonObject.getString("name"));
            outputObject.put("points", jsonObject.getInt("points"));
            outputArray.put(outputObject);

        };

        if(put){
            JSONObject outputNewObject = new JSONObject();
            outputNewObject.put("name", name);
            outputNewObject.put("points", points);
            outputArray.put(outputNewObject);
        }

        // Save the output JSONArray to a JSON file
        try (FileWriter fileWriter = new FileWriter("appdata/Scoreboard.json")) {
            fileWriter.write(outputArray.toString());
        } catch (IOException e) {
            System.err.println("Error while saving JSON file: " + e.getMessage());
        }
    }
}