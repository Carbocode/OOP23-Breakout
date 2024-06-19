package it.unibo.controller;
import it.unibo.model.ScoreboardImpl;

import javax.swing.SwingUtilities;
import it.unibo.view.Menu;

public class Main {

    /* Main class.
     * @param args args
     */
    public static void main(final String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println();
                Menu menuPanel = new Menu();
            }
        });
    }
}
