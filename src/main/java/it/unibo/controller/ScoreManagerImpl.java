package it.unibo.controller;

import it.unibo.api.ScoreManager;

public class ScoreManagerImpl implements ScoreManager {

    private int score;

    /**
     * the constructor initialize the score of the game to zero.
     */
    public ScoreManagerImpl() {
        this.score = 0;
    }

    @Override
    public final void increment(final int points) {
        score += points;
    }

    @Override
    public final int getScore() {
        return score;
    }

}