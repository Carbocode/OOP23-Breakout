package it.unibo.controller;

import javax.swing.SwingUtilities;
import it.unibo.view.Menu;

/**
 * this class starts the game.
 */
public final class Main {

    /**
     * Main class.
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }

    private Main() {
    }
}
