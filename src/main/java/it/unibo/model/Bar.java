package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;

/**
 * Bar class that handle the movements and dynamics of the bar.
 */
public class Bar extends GameEntityImpl {

    // (-1) left - (1) right - (0) do not move
    private static final int LEFT_VALUE = -1;
    private static final int RIGHT_VALUE = 1;
    private static final int STOP_VALUE = 0;

    private static final float MOVE_VALUE = (float) 0.01 * (float) GameInfo.GAME_WIDTH;

    private int direction;

    /**
     * Standard constructor.
     * 
     * @param position starting position of the bar
     * @param size     dimension of the bar
     * @param health   ignored since bar will not be affected by health
     * @param color    colour displayed in the bar
     */
    public Bar(final Point position, final Dimension size, final int health, final Color color) {
        super(position, size, health, color);
    }

    /**
     * This function move the bar every game cycle depending on the button currently
     * being pressed.
     * if none is pressed the bar will stay in position
     */
    public final void move() {
        Point position = getPosition();
        switch (direction) {
            case LEFT_VALUE:
                if (position.x - MOVE_VALUE > 0) {
                    position.x -= MOVE_VALUE;
                }
                break;

            case RIGHT_VALUE:
                if (position.x + getSize().width + MOVE_VALUE < GameInfo.GAME_WIDTH) {
                    position.x += MOVE_VALUE;
                }
                break;
            default:
                break;
        }
        setPosition(position);
    }

    /**
     * Method used to change the width of the bar during game loop.
     * 
     * @param newwidth change width, used to handle power up
     */
    public final void setWidth(final int newwidth) {
        setSize(new Dimension(newwidth, getSize().height));
    }

    /**
     * This method is used to detect a button pressed by the user.
     * if the button match the arrow left or arrow right,
     * the last button pressed is stored and used for the next movement
     * 
     * @param e button pressed
     */
    public final void buttonPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                // left arrow button pressed -> move left
                direction = LEFT_VALUE;
                break;

            case KeyEvent.VK_RIGHT:
                // right arrow button pressed -> move right
                direction = RIGHT_VALUE;
                break;

            default:
                // do nothing
        }
    }

    /**
     * This method is used to detect a button released by the user.
     * if the button match the arrow left or arrow right the bar will not be moved
     * until next button pressed
     * 
     * @param e button pressed
     */
    public final void buttonReleased(final KeyEvent e) {
        // button released -> stop moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT && this.direction == LEFT_VALUE
                || e.getKeyCode() == KeyEvent.VK_RIGHT && this.direction == RIGHT_VALUE) {
            direction = STOP_VALUE;
        }
    }

    @Override
    public void onCollision() {
    }
}
