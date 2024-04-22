package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.api.*;
import it.unibo.model.*;

public class Game extends JFrame {
    private JPanel gamePanel;
    private JButton button;
    private Measures measure = new Measures();
    private ArrayList<Brick> bricks;
    private Ball ball;
    long time;
    private Paddle paddle;
    private int points = 0;
    private int lives = 3;
    private int level = 1;
    private boolean inGame = false;
    private boolean gamePaused = false;
    private boolean playerIsDead = false;

    
    public Game() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
    }


}
