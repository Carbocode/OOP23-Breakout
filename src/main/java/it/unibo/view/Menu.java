package it.unibo.view;

import javax.swing.*;
import java.util.*;
import java.io.*;

import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.controller.GameLoop;
import it.unibo.controller.Match;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private static JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private Font font;

    private Measures measure = new Measures();

    public Menu() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creation of the panel of the menu
        mainPanel = new JPanel(new GridLayout(3, 1));
        titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        InputStream myStream;
        try {
            myStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("font/8-bit-hud.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        titleLabel.setFont(font.deriveFont(50.0f));
        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(Color.CYAN);

        playButton = new JButton("PLAY");
        playButton.setFont(font);
        playButton.setPreferredSize(new Dimension(100, 50));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setPreferredSize(new Dimension(100, 50));

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(100, 50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame game = new JFrame();
                GameView gamePanel = new GameView();
                gamePanel = new GameView();
                game.add(gamePanel);
                Match.init(gamePanel);
                game.pack();
                game.setVisible(true);
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
