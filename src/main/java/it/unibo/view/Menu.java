package it.unibo.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

import it.unibo.api.SoundManager;
import it.unibo.controller.Match;

import java.awt.Font;
import java.awt.Window;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the menu frame, where you can choose between the play, scoreboard,
 * exit button.
 * 
 * @author Sohail Mama
 */
public class Menu extends JFrame {
    private static JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private Font font;
    private SoundManager sound = new SoundManagerImpl();
    private Measures measure = new Measures();

    // these are some constant measures
    private final float fontSize = 55.0f;
    private final int buttonWidth = 100;
    private final int buttonHeight = 50;

    /**
     * The Menu constructor.
     * 
     * @author Sohail Mama
     */
    public Menu() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting of an external font
        InputStream fontStream;
        try {
            fontStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("font/8-bit-hud.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // creation of the panel of the menu
        mainPanel = new JPanel(new GridLayout(2, 1));
        titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        titleLabel.setFont(font.deriveFont(fontSize));

        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);
        sound.playMenuSound();

        playButton = new JButton("PLAY");
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);
        playButton.setBackground(Color.ORANGE);
        playButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setOpaque(true);
        scoreboardButton.setBorderPainted(false);
        scoreboardButton.setBackground(Color.ORANGE);
        scoreboardButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        exitButton = new JButton("EXIT");
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setBackground(Color.ORANGE);
        exitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                JFrame game = new JFrame();
                // show a popup tutorial
                JOptionPane.showMessageDialog(game,
                        "Se leggi sto tutorial sei forte!",
                        "Tutorial",
                        JOptionPane.INFORMATION_MESSAGE);
                GameView gamePanel = new GameView();
                gamePanel = new GameView();
                game.add(gamePanel);
                Match.init(gamePanel);
                game.pack();
                game.setVisible(true);
                sound.playButtonSound();
                sound.playGameSound();
                sound.playBackgroundSound();

                // it close the other window
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                // TO DO
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
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
        setFocusable(true);
        setVisible(true);
    }

}
