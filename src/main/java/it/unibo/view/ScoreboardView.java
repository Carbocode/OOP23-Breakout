package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import it.unibo.model.ScoreboardImpl;

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

        ScoreboardImpl s = new ScoreboardImpl();

        add(new JScrollPane(s.top10()), BorderLayout.CENTER);

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
