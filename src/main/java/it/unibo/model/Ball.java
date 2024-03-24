package it.unibo.model;

import it.unibo.api.GameEntity;
import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.Pos2D;

import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Ball extends GameEntityImpl{
    private Point pos;
    private Direction dir;
    private boolean alive;
    public Ball(){
        this.pos = new Point(50, 50);
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
        Point candidate = new Point(pos.x + dir.getHorizontalVelocity(), pos.y + dir.GetVerticalVelocity());
        //we validate it. if its out of bounds, we reverse direction
        if (candidate.getX()<= 0 || candidate.getX() >= GAME_WIDTH){
            dir = new Direction(dir.getHorizontalVelocity(), dir.GetVerticalVelocity());
        }
        // if the ball fell
        if(candidate.getY() > GAME_HEIGHT){
            die();
        }
        pos = candidate;
    }
    private void die(){
        this.alive = false;
    }
    public boolean isAlive(){
        return this.alive;
    }
    @Override
    public void onCollision() {
        // much code
    }

}
