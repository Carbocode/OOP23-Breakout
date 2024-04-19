package main.java.it.unibo.model;
import java.awt.*;

import it.unibo.api.GameEntityImpl;

public class BarImpl extends GameEntityImpl implements Bar{


    // (-1) left - (1) right - (0) do not move
    final private static int LEFT_VALUE = -1;
    final private static int RIGHT_VALUE = 1;
    final private static int STOP_VALUE = 0;

    private int direction;

    public BarImpl(){
        /* to do 
         * set initial position
         * set initial size
        */
    }

    void move(){
        switch (direction) {
            case LEFT_VALUE:
                if(position.x)
                break;
        
            default:
                break;
        }
    }

    void setWidth(int newwidth){
        size.width = newwidth;
        return;
    }

    void buttonPressed(KeyEvent e) {

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

    void buttonReleased(KeyEvent e) {
        //button released -> stop moving
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) 
            direction = STOP_VALUE;
    }

    
}
