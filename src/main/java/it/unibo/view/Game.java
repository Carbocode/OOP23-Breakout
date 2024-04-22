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
    private Bar bar;
    private int points = 0;
    private int lives = 3;
    private int level = 1;
    private boolean inGame = false;
    private boolean gamePaused = false;
    private boolean playerIsDead = false;

    private Graphics2D g2;

    
    public Game() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);

    }

    private void gameInit(){
        ball=new Ball();
        bar=new BarImpl(getMousePosition(), getSize(), ABORT, getForeground());
        //bricks
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if(inGame){
            drawObjects(g2);
        }else{
            gameFinished(g2);
        }

    }

    private void drawObjects(Graphics2D g2) {
        g2.drawImage("../appdata/images/ball.png", ball.getSize());
    }

    private void gameFinished(Graphics2D g2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameFinished'");
    }


}
