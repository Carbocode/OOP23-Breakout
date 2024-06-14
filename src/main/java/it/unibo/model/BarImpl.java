package it.unibo.model;
import it.unibo.view.*;
import it.unibo.api.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import it.unibo.api.GameEntityImpl;

public class BarImpl  extends GameEntityImpl {//implements Bar {

    // (-1) left - (1) right - (0) do not move
    final private static int LEFT_VALUE = -1;
    final private static int RIGHT_VALUE = 1;
    final private static int STOP_VALUE = 0;

    final private static int MOVE_VALUE = 5;

    private int direction;

    public BarImpl(Point position, Dimension size, int health, Color color) {
        super(position, size, health, color);
    }

    @Override
    public void onCollision() {
        // play sound
    }
    
    
    public void move(){
        switch (direction) {
            case LEFT_VALUE:
                if(position.x - MOVE_VALUE>0)
                    position.x -= MOVE_VALUE;
                break;
            
            case RIGHT_VALUE:
                if(position.x + size.width + MOVE_VALUE < GameInfo.GAME_WIDTH)
                    position.x += MOVE_VALUE;
                break;
            default:
                break;
        }
    }

    public void setWidth(int newwidth){
        size.width = newwidth;
        return;
    }

    public void buttonPressed(KeyEvent e) {

        switch(e.getKeyCode()){

            case KeyEvent.VK_LEFT:
                //left arrow button pressed -> move left
                direction = LEFT_VALUE;
                break;

            case KeyEvent.VK_RIGHT:
                //right arrow button pressed -> move right
                direction = RIGHT_VALUE;
                break;

            default:
                //do nothing     

        }
    }

    public void buttonReleased(KeyEvent e) {
        //button released -> stop moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) 
            direction = STOP_VALUE;
    }

    
}
