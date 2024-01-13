package it.unibo.model;

import it.unibo.api.ScoreManager;

public class ScoreManagerImpl implements ScoreManager{

    private int score;

    /**
     *  the constructor initialize the score of the game to zero
     */
    ScoreManagerImpl() {
        this.score = 0;
    }


    @Override
    public void increment(int points) {
        score+=points;
    }

    @Override
    public int getScore() {
        return score;
    }

}