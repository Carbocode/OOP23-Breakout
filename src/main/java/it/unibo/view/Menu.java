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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private static final float FONTSIZE = 55.0f;
    private static final int BUTTONWIDTH = 100;
    private static final int BUTTONHEIGHT = 50;

    /**
     * The Menu constructor.
     * 
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
        titleLabel.setFont(font.deriveFont(FONTSIZE));

        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);
        sound.playMenuSound();

        playButton = new JButton("PLAY");
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);
        playButton.setBackground(Color.ORANGE);
        playButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setOpaque(true);
        scoreboardButton.setBorderPainted(false);
        scoreboardButton.setBackground(Color.ORANGE);
        scoreboardButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        exitButton = new JButton("EXIT");
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setBackground(Color.ORANGE);
        exitButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                JFrame game = new JFrame();
                // show a popup tutorial
                JOptionPane.showMessageDialog(game,
                        "I mattoncini grigi sono indistruttibili ma tutto il resto invece si\nSFOGA LA TUA RABBIA",
                        "Tutorial",
                        JOptionPane.INFORMATION_MESSAGE);
                GameView gamePanel = new GameView();
                gamePanel = new GameView();
                game.add(gamePanel);
                Match.init(gamePanel);
                game.pack();
                game.setVisible(true);
                game.setResizable(false);
                sound.playButtonSound();
                sound.playGameSound();
                sound.playBackgroundSound();
                game.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent e) {
                        System.out.println("Chiusura della finestra");

                        if (confirmExit()) {
                            dispose(); // close the window
                            System.exit(0);
                        }
                    }
                });

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

    private boolean confirmExit() {
        // show a pop up that ask you if you are sure that you want to leave
        int option = javax.swing.JOptionPane.showConfirmDialog(this, "Sei sicuro di voler uscire?", "Conferma Uscita",
                javax.swing.JOptionPane.YES_NO_OPTION);
        return option == javax.swing.JOptionPane.YES_OPTION;
    }

}
