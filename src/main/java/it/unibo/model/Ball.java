package it.unibo.model;

import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.view.SoundManagerImpl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

/**
 * The Ball class represents a ball entity in the game.
 * It extends the GameEntityImpl class and defines the behavior
 * and properties of the ball, including its movement and collision handling.
 */
public final class Ball extends GameEntityImpl {
    public static final long serialVersionUID = 438743;
    private Direction dir;
    private final Random rand = new Random();
    private static final Dimension BALL_DIMENSION = new Dimension(5, 5);
    private static final Point START_POINT = new Point(GameInfo.GAME_WIDTH / 2, GameInfo.GAME_HEIGHT - 50);
    private final SoundManager sound = new SoundManagerImpl();

    /**
     * Constructs a new Ball object at the default starting position
     * with a default direction.
     */
    public Ball() {
        super(START_POINT, BALL_DIMENSION, 1);
        this.dir = new Direction(-1, -1);
    }

    /**
     * Constructs a new Ball object by duplicating the original Ball object.
     * The direction of the new Ball is inverted to prevent overlap.
     *
     * @param orig the original Ball object to duplicate
     */
    public Ball(final Ball orig) {
        super(orig.getPosition(), orig.getSize(), 1);
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity(),
                orig.getDirection().getVerticalVelocity());
    }

    /**
     * Gets the current direction of the Ball.
     *
     * @return the current direction
     */
    public Direction getDirection() {
        sound.playCollisionSound();

        return new Direction(dir.getHorizontalVelocity(), dir.getVerticalVelocity());
    }

    /**
     * Updates the position and state of the Ball.
     * The Ball's direction is reversed if it goes out of bounds,
     * and its health is decreased if it falls out of the game area.
     */
    public void update() {
        boolean acceptable = true;
        final Point position = getPosition();
        final Point candidate = new Point(position.x + dir.getHorizontalVelocity() * GameInfo.BALL_SPEED,
                position.y + dir.getVerticalVelocity() * GameInfo.BALL_SPEED);
        // Reverse direction if out of horizontal bounds
        if (candidate.getX() <= 0 || candidate.getX() >= GameInfo.GAME_WIDTH) {
            sound.playCollisionSound();
            dir = new Direction(-dir.getHorizontalVelocity(), dir.getVerticalVelocity());
            acceptable = false;
        }
        // Reverse direction if touching the top
        if (candidate.getY() <= 0) {
            sound.playCollisionSound();
            dir = new Direction(dir.getHorizontalVelocity(), -dir.getVerticalVelocity());
            acceptable = false;
        }
        // Decrease health if the ball falls out of the game area
        if (candidate.getY() > GameInfo.GAME_HEIGHT) {
            super.setHealth(super.getHealth() - 1);
        }
        if (acceptable) {
            setPosition(candidate);
        }
    }

    /**
     * Handles the collision event by changing the Ball's direction.
     */
    @Override
    public void onCollision() {
        sound.playCollisionSound();
        dir = new Direction(rand.nextInt(3) - 1, -dir.getVerticalVelocity());
    }

    /**
     * We directly tell the ball where to go in some cases.
     * 
     * @param hitDirection the excepted bounce from the bar.
     */
    public void guidedCollision(final int hitDirection) {
        sound.playCollisionSound();
        dir = new Direction(hitDirection, -dir.getVerticalVelocity());
    }
}
