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

import it.unibo.api.GameInfo;
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

import java.util.logging.Logger;

/**
 * This is the menu frame, where you can choose between the play, scoreboard,
 * exit button.
 * 
 */
public class Menu extends JFrame {
    public static final long serialVersionUID = 4328743;

    private final transient SoundManager sound = new SoundManagerImpl();
    private final transient Measures measure = new Measures();
    private final transient Logger log = Logger.getLogger(GameView.class.getName());

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

        final JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        final JLabel titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);

        // setting of an external font
        InputStream fontStream;
        final Font font;
        try {
            fontStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("font/8-bit-hud.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            titleLabel.setFont(font.deriveFont(FONTSIZE));
        } catch (FontFormatException | IOException e) {
            log.warning(e.getMessage());
        }

        // creation of the panel of the menu

        // these are the buttons of the menu with their panel
        final JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);
        sound.playMenuSound();

        final JButton playButton = new JButton("PLAY");
        playButton.setOpaque(true);
        playButton.setBorderPainted(false);
        playButton.setBackground(Color.ORANGE);
        playButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        final JButton scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setOpaque(true);
        scoreboardButton.setBorderPainted(false);
        scoreboardButton.setBackground(Color.ORANGE);
        scoreboardButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        final JButton exitButton = new JButton("EXIT");
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.setBackground(Color.ORANGE);
        exitButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                final JFrame game = new JFrame();
                // show a popup tutorial
                JOptionPane.showMessageDialog(game,
                        "Breakout Rules: Destroy all bricks using the ball without letting it fall off the screen.\n"
                        + "At least one ball must remain in game, if you lose your last ball you lose the game\n"
                        + "Remember grey bricks are indestructable and some bricks requires 2 hits to get destroyed\n"
                        + "Each brick destructions give you some points, the more you break the more you score!\n\n"
                        + "Use left and right arrow to move the paddle around\n"
                        + "Depending on which side of the paddle you use to hit the ball the ball will get a differente bounce\n"
                        + "Power-Ups:\n"
                        + "1) Paddle Enlargement: Paddle becomes larger for a few seconds.\n" 
                        + "2) Ball Duplication: Split the ball into two balls.\n" 
                        + "3) Bomb: Creates an explosion destroying or damaging nearby bricks.\n"
                        + "HAVE FUN!",
                        "Tutorial",
                        JOptionPane.INFORMATION_MESSAGE);
                GameView gamePanel;
                Match.init();
                gamePanel = new GameView();
                Match.addGameView(gamePanel);
                game.add(gamePanel);
                game.pack();
                game.setVisible(true);
                game.setResizable(false);
                sound.playButtonSound();
                sound.playGameSound();
                sound.playBackgroundSound();
                game.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent e) {
                        if (confirmExit()) {
                            dispose(); // close the window
                            Runtime.getRuntime().exit(0);
                        }
                    }
                });

                // it close the other window
                final JComponent comp = (JComponent) e.getSource();
                final Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

            }
        });

        final Menu menuPrevious = this;
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                final JFrame scoreBoard = new ScoreboardView(menuPrevious);
                scoreBoard.setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT);
                scoreBoard.setVisible(true);
                scoreBoard.setResizable(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                sound.playButtonSound();
                Runtime.getRuntime().exit(0);
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
        final int option = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler uscire?", "Conferma Uscita",
                JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    /**
     * this method return the measures of the game.
     * 
     * @return measure
     */
    public Measures getMeasure() {
        return measure;
    }

}
