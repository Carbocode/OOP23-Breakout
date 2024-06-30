package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import it.unibo.model.ScoreboardImpl;
import it.unibo.api.GameInfo;

/**
 * This is the view of the scoreboard.
 */
public class ScoreboardView extends JFrame {
    public static final long serialVersionUID = 4328749;

    private static final float FONTSIZE = 40.0f;
    private static final int FONTSIZE2 = 28;
    private static final int BUTTONWIDTH = 400;
    private static final int BUTTONHEIGHT = 50;
    private final transient Logger log = Logger.getLogger(GameView.class.getName());

    /**
     * Scoreboard view constructor.
     * 
     * @param menu The menu to return to.
     */
    public ScoreboardView(final Menu menu) {
        setTitle("SCOREBOARD");
        setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT); // Set the size based on GameInfo constants
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add some spacing between components

        final ScoreboardImpl scoreboard = new ScoreboardImpl();

        // Create a title label
        final JLabel titleLabel = new JLabel("TOP 10", SwingConstants.CENTER);

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
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Add padding to top and bottom

        // Get the top 10 scores as a JList
        final JList<String> topScoresList = scoreboard.top10();
        // Set the font size and center alignment for the list
        topScoresList.setFont(new Font("Arial", Font.BOLD, FONTSIZE2)); // Adjust font size as needed
        final DefaultListCellRenderer renderer = (DefaultListCellRenderer) topScoresList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a panel to hold the title and the list
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(topScoresList), BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the edges

        // Add the panel to the center of the frame
        add(panel, BorderLayout.CENTER);

        // Create and style the back button
        final JButton backButton = new JButton("< RETURN");
        backButton.setBackground(Color.YELLOW);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT)); // Set a preferred size for the button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
                menu.setVisible(true);
            }
        });

        // Add the back button to a panel at the bottom
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}

