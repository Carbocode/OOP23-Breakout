package it.unibo.controller;


import javax.swing.SwingUtilities;
import it.unibo.view.Menu;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menuPanel = new Menu();
            }
        });
    }
}
