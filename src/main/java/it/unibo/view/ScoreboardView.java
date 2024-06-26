package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import it.unibo.api.GameInfo;

/**
 * This is the view of the scoreboard.
 */
public class ScoreboardView extends JFrame {
    /**
     * Scoreboard view constructor.
     * 
     * @param menu
     */
    public ScoreboardView(final Menu menu) {
        setTitle("SCOREBOARD");
        setSize(GameInfo.GAME_WIDTH, GameInfo.GAME_WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] scores = {
                "Stringa 1", "Stringa 2", "Stringa 3", "Stringa 4", "Stringa 5",
                "Stringa 6", "Stringa 7", "Stringa 8", "Stringa 9", "Stringa 10"
        };

        JList<String> stringList = new JList<>(scores);
        add(new JScrollPane(stringList), BorderLayout.CENTER);

        JButton backButton = new JButton("< RETURN");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
                menu.setVisible(true);
            }
        });

        add(backButton, BorderLayout.SOUTH);
    }
}
