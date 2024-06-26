package it.unibo.api;

import java.util.List;

import javax.swing.JList;

/**
 * An interface modelling a factory of Scoreboard.
 * The general idea is that the software will store the scoreboard into
 * Json file and can be updated using a designed method and you can
 * retrieve the top 10 players list
 */

public interface Scoreboard {

    /**
     * 
     */

    /**
     * @return a List<String> containing the top 10 players and score
     * e.g.:  (format: "*position* *user* : *score*p" example: "1 ABC : 3814p")
     */
    public JList<String> top10();

    /**
     * @return void 
     * add a player (as soon as the game ends) with his score into the right spot in the leaderboard
     * @param name the name of the player
     * @param points the points made by the player during the game
     */
    public void add(String name,int points);
}