package it.unibo.view;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;




public class GUI extends JPanel implements ActionListener, KeyListener {

	private Image backgroundImage;
    //here are initialized the height and the width of the frame, where the game will be played
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;

    private boolean inGame;
    private int score;
    private long startTime;
    private long elapsedTime;


    private JButton playAgainButton;

    /**
     * this is the constructor of the GUI, where is initialized the dimensions, the backgrond image and the play again button 
     */

    public GUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        //initGame();
        try {
            backgroundImage = ImageIO.read(new File("mirko.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> playAgain());
        add(playAgainButton, BorderLayout.SOUTH);
        playAgainButton.setVisible(false);
    }

    
    private void initGame() {
        startTime = System.currentTimeMillis();

        inGame = true;
        
        paddle = new Paddle(WIDTH / 2 - 40, HEIGHT - 30,WIDTH,HEIGHT);
        ball = new Ball(WIDTH / 2, HEIGHT - 50,paddle,WIDTH,HEIGHT);
        bricks = new ArrayList<>();
        generateBricks();

        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    
    private void playAgain() {
        startTime = System.currentTimeMillis();

        inGame = true;
        score = 0;
        
        paddle = new Paddle(WIDTH / 2 - 40, HEIGHT - 30,WIDTH,HEIGHT);
        ball = new Ball(WIDTH / 2, HEIGHT - 50,paddle,WIDTH,HEIGHT);
        bricks.clear();
        generateBricks();
        timer.start();
        repaint();
    }
    
    
    private void generateBricks() { // Generates the initial bricks of the game
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                Color brickColor = COLORS[(int) (Math.random() * COLORS.length)];
                bricks.add(new Brick(10 + j * (90 + 2), 10 + i * (40 + 2), brickColor));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	 if (backgroundImage != null) {
    	        g.drawImage(backgroundImage, 0, 0, this);
    	    }
        
        draw(g);
    }

    
    private void draw(Graphics g) {
    	if (inGame) { // while the game is active (player didn't win or lose)
            playAgainButton.setVisible(false);
            ball.draw(g);
            ball.drawTrajectory(g); 
            paddle.draw(g);
            for (Brick brick : bricks) {
                brick.draw(g);
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + score, 10, HEIGHT - 10);

        	 if (bricks.isEmpty()) { // check if all bricks have been destroyed
        		 g.drawString("You Won!", WIDTH / 2 - 30, HEIGHT / 2);
        		 g.drawString("Time Taken: " + elapsedTime + " Seconds", WIDTH / 2 - 60, HEIGHT / 2 + 40);
        		 
        		/*   g.drawString("Top 5 High Scores:", 10, HEIGHT - 130);
        		  int i = 1;
        		  
        		  for (String n: highScorers) { 
        			  if(i==6)break;
        			  
        			  g.drawString(i + ". " + n + ": " + playerTopScores.get(n) + " seconds", 10, HEIGHT - 130 + i * 20);
        			  i++;
        			  
        		  }
        		 
        		  topScores.clear(); */
        	 } else {
                 g.drawString("Game Over", WIDTH / 2 - 50, HEIGHT / 2);
             }
            g.drawString("Score: " + score, WIDTH / 2 - 30, HEIGHT / 2 + 20);
            playAgainButton.setVisible(true);
        
    }


    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            ball.move();
            paddle.move();
            ball.updateTrajectory(paddle); 
            paddle.updateBallPosition(ball);
            checkCollisions();
        }
        repaint();
    }


    private long getElapsedTimeInSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    private void checkCollisions() {
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseYDirection();
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (ball.getBounds().intersects(bricks.get(i).getBounds())) {
                Color hitBrickColor = bricks.get(i).getColor();
                removeConnectedBricks(i, hitBrickColor);
                ball.reverseYDirection();
                break;
            }
        }
        
        if (bricks.isEmpty()) {
            inGame = false;
            elapsedTime = getElapsedTimeInSeconds();
        }

        if (ball.getY() >= HEIGHT) {
        	System.out.println(ball.getY()+" "+HEIGHT);
            inGame = false;
        }
    }

    
    private void removeConnectedBricks(int brickIndex, Color brickColor) {
        if (brickIndex < 0 || brickIndex >= bricks.size()) {
            return;
        }

        Brick brick = bricks.get(brickIndex);
        if (!brick.getColor().equals(brickColor)) {
            return;
        }

        Rectangle brickBounds = brick.getBounds();
        bricks.remove(brickIndex);
        score++;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int direction = 0; direction < 4; direction++) {
            int nextX = brickBounds.x + dx[direction] * (brickBounds.width + 2);
            int nextY = brickBounds.y + dy[direction] * (brickBounds.height + 2);

            for (int i = 0; i < bricks.size(); i++) {
                if (bricks.get(i).getBounds().contains(nextX, nextY)) {
                    removeConnectedBricks(i, brickColor);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setDx(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.setDx(5);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && ball.isAttachedToPaddle()) {
            ball.setAttachedToPaddle(false); // Detach the ball from the paddle
        }
    }



    public void keyReleased(KeyEvent e) {
        paddle.setDx(0);
    }
     
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Brick Breaker Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GUI game = new GUI();
            //frame.add(new BrickBreakerGame());
            String playerName = JOptionPane.showInputDialog(frame, "Enter your name:");
            
            game.initGame();
            frame.add(game);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
}