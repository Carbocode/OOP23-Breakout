package main.java.it.unibo.model;
import java.awt.*;

public class BarImpl extends GameEntityImpl implements Bar{

    public BarImpl(){
        /* to do 
         * set initial position
         * set initial size
        */
    }

    void move(int distance){
        int newPosition = this.getGraphics().Pos2D.getX() + distance;
        
        if(newPosition>0 && newPosition < 10/*left limit of game area to do */){
            /*TO DO set the new position */
        }
    }

    void setWidth(int width){
        /* TO DO set width */
        
    }

    
}
