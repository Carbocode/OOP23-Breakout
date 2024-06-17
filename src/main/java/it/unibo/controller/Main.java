package it.unibo.controller;

import javax.swing.SwingUtilities;
import it.unibo.view.Menu;

/**
 * this class start the game.
 */
public final class Main {
    private Main() {
        try {
            throw new Exception();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Main class.
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menuPanel = new Menu();
            }
        });
    }
}
