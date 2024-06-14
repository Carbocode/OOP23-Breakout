package it.unibo.model;

import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;;

public final class Ball extends GameEntityImpl {
    private Direction dir;
    Random rand = new Random();

    public Ball() {
        super(new Point(GameInfo.GAME_WIDTH / 2, GameInfo.GAME_HEIGHT - 50), new Dimension(5, 5), 1);
        this.dir = new Direction(-1, -1);
    }

    // duplicating a ball
    public Ball(Ball orig) {
        super(orig.getPosition(), orig.getSize(), 1);
        // we invert the direction so they dont overlap.
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity(),
                orig.getDirection().GetVerticalVelocity());
    }

    private Direction getDirection() {
        return dir;
    }

    public void update() {
        Point candidate = new Point(position.x + dir.getHorizontalVelocity()*GameInfo.BALL_SPEED, position.y + dir.GetVerticalVelocity()*GameInfo.BALL_SPEED);
        // we validate it. if its out of bounds, we reverse direction
        if (candidate.getX() <= 0 || candidate.getX() >= GameInfo.GAME_WIDTH) {
            dir = new Direction(-dir.getHorizontalVelocity(), dir.GetVerticalVelocity());
        }
        // if we touch the top reverse direction
        if (candidate.getY() <= 0) {
            dir = new Direction(-dir.getHorizontalVelocity(), -dir.GetVerticalVelocity());
        }
        // if we fall, we die
        if (candidate.getY() > GameInfo.GAME_HEIGHT) {
            super.setHealth(super.getHealth() - 1);
        }
        position = candidate;
    }

    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void onCollision() {
        
        dir = new Direction(dir.getHorizontalVelocity(), rand.nextInt(3)-1);
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }

}
