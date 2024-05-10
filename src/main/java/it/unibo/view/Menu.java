package it.unibo.view;

import javax.swing.*;

import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.controller.GameLoop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private static JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton exitButton;

    private Measures measure = new Measures();

    public Menu() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creation of the panel of the menu
        mainPanel = new JPanel(new GridLayout(3, 1));
        titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(Color.CYAN);

        playButton = new JButton("PLAY");
        playButton.setPreferredSize(new Dimension(100, 50));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setPreferredSize(new Dimension(100, 50));

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(100, 50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TO DO
                dispose();
                JPanel gamePanel=new JPanel();
                GameView game= new GameView();
                GameLoop gl = new GameLoop(game);
                gamePanel.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
                gamePanel.setBackground(Color.BLACK);
                gamePanel.setVisible(true);
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TO DO
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(scoreboardButton);
        buttonPanel.add(exitButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        mainPanel.setVisible(true);
        mainPanel.setBackground(Color.CYAN);
        setFocusable(true);
        setVisible(true);
    }

}
