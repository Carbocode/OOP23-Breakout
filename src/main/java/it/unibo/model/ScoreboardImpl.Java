package main.java.it.unibo.model;
import main.java.it.unibo.api.Scoreboard;

import org.json.JSONArray;
import org.json.JSONObject;
import java.*;

public ScoreboardImpl implements Scoreboard{

    //this function open and read the JSON file and return JSON file converted into JSONArray
    private JSONArray open(){

        // read JSON file content
        String jsonContent = new String(Files.readAllBytes(Paths.get("/Scoreboard.json")));

        // convert JSON string into JSON object array
        return new JSONArray(jsonContent);

    }

    List<String> top10(){
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
    }


    void add(String name,int points){

    }

    

}