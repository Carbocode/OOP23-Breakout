package it.unibo.model;

import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;

import java.awt.Dimension;
import java.awt.Point;

public class Ball extends GameEntityImpl{
    private Direction dir;

    private boolean alive;

    public Ball(){
        super(new Point(GameInfo.GAME_WIDTH/2, GameInfo.GAME_HEIGHT-50), new Dimension(5,5),1);
        this.dir = new Direction(-1, -1);
        this.alive = true;
    }
    //duplicating a ball
    public Ball(Ball orig){
        super(orig.getPosition(), orig.getSize(), 1);
        // we invert the direction so they dont overlap.
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity(), orig.getDirection().GetVerticalVelocity());
        this.alive = true;
    }
    
    private Direction getDirection() {
        return dir;
    }
    public void update(){
        Point candidate = new Point(position.x + dir.getHorizontalVelocity(), position.y + dir.GetVerticalVelocity());
        //we validate it. if its out of bounds, we reverse direction
        if (candidate.getX()<= 0 || candidate.getX() >= GameInfo.GAME_WIDTH){
            dir = new Direction(-dir.getHorizontalVelocity(), dir.GetVerticalVelocity());
        }
        // if we touch the top reverse direction
        if(candidate.getY() <=0 ){
            dir = new Direction(-dir.getHorizontalVelocity(), -dir.GetVerticalVelocity());
        }
        // if we fall, we die
        if(candidate.getY() > GameInfo.GAME_HEIGHT){
            die();
        }
        position = candidate;
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
        dir = new Direction(dir.getHorizontalVelocity(), -dir.GetVerticalVelocity());
    }
    @Override
    public Point getPosition() {
        return this.position;
    }
    @Override
    public Dimension getSize() {
        return size;
    }

}
