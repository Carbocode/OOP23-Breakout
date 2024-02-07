package main.java.it.unibo.model;

import it.unibo.api.GameEntity;
import main.java.it.unibo.api.Direction;
import main.java.it.unibo.api.Pos2D;
public class Ball implements GameEntity{
    private Pos2D pos;
    private Direction dir;
    private boolean alive;
    public Ball(){
        this.pos = new Pos2D(50, 50);
        this.dir = new Direction(1, 1);
        this.alive = true;
    }
    //duplicating a ball
    public Ball(Ball orig){
        this.pos = orig.getPosition();
        // we invert the direction so they dont overlap.
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity, orig.getDirection().getVerticalVelocity());
        this.alive = true;
    }
    public void update(){
        Pos2D candidate = Pos2D.transform(pos, dir);
        //we validate it. if its out of bounds, we reverse direction
        if (candidate.getX()<= 0 || candidate.getX() >= GAME_WIDTH){
            dir = new Direction(dir.getHorizontalVelocity(), dir.GetVerticalVelocity());
        }
        // if the ball fell
        if(candidate.getY() > GAME_HEIGHT){
            die();
        }
    }
    private void die(){
        this.alive = false;
    }
    public boolean isAlive(){
        return this.alive;
    }

}
